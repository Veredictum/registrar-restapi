/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/

package io.veredictum.registrar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Bytes8;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.request.RawTransaction;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.RawTransactionManager;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * RESTful API end point
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
@RestController
public class RegistrarController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private static final int SLEEP_DURATION = 15000;
    private static final int ATTEMPTS = 40;

    private final int sleepDuration = SLEEP_DURATION;
    private final int attempts = ATTEMPTS;

    @Value("${ethereum.account.password}")
    private String ethereumAccountPassword;

    @Value("${ethereum.account.keyStoreFile}")
    private String ethereumKeyStoreFile;

    @Value("${gas.limit}")
    private BigInteger gasLimit;

    @Value("${gas.price}")
    private BigInteger gasPrice;

    @Value("${contract.address}")
    private String contractAddress;

    @Value("${etherscan.site}")
    private String etherScanSite;

    @Value("${registrar.function.name}")
    private String registrarFunctionName;


    @RequestMapping(path="/register/content" , method = RequestMethod.POST)
    ResponseEntity<?> registerContent(@RequestBody ContentRegistrarRequest request) throws Exception {
        logger.info("transaction request received");
        Web3j web3j = Web3j.build(new HttpService());
        request.setContentId(System.currentTimeMillis()); // set dummy unique contentId
        request.setOriginalFileHash(Hasher.hashString("" + request.getContentId()));
        request.setTranscodedFileHash(Hasher.hashString("" + (Long.MAX_VALUE - request.getContentId())));
        Credentials credentials = WalletUtils.loadCredentials(ethereumAccountPassword, ethereumKeyStoreFile);
        List<Type> inputParameters = new ArrayList<>();
        inputParameters.add(convert(request.getAddresses()));
        inputParameters.add(convert(request.getShares()));
        inputParameters.add(new Bytes8(ByteBuffer.allocate(8).putLong(request.getContentId()).array()));
        inputParameters.add(new Bytes32(request.getOriginalFileHash()));
        inputParameters.add(new Bytes32(request.getTranscodedFileHash()));
        Function function = new Function(registrarFunctionName, inputParameters, Collections.emptyList());
        String encodedFunction = FunctionEncoder.encode(function);
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                credentials.getAddress(), DefaultBlockParameterName.LATEST).sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        RawTransaction rawTransaction  = RawTransaction.createTransaction(
                nonce,
                gasPrice,
                gasLimit,
                contractAddress,
                encodedFunction
        );
        RawTransactionManager transactionManager = new RawTransactionManager(web3j, credentials);
        EthSendTransaction ethSendTransaction = transactionManager.signAndSend(rawTransaction);
        if(ethSendTransaction.hasError()) {
            Response.Error error = ethSendTransaction.getError();
            logger.error("error code: " + error.getCode());
            logger.error("error message: " + error.getMessage());
            logger.error("error data: " + error.getData());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        } else {
            String transactionHash = ethSendTransaction.getTransactionHash();
            logger.info("Transaction Hash: " + transactionHash);
            return ResponseEntity.ok(new Transaction(transactionHash, etherScanSite));
        }
    }

    @RequestMapping(path="/confirm/registration" , method = RequestMethod.GET)
    ResponseEntity<?> getBlockNumber(@RequestParam String transactionHash) throws Exception {
        logger.info("block number request received");
        Web3j web3j = Web3j.build(new HttpService());
        Optional<TransactionReceipt> receiptOptional =
                sendTransactionReceiptRequest(web3j, transactionHash);
        for (int i = 0; i < attempts; i++) {
            if (!receiptOptional.isPresent()) {
                Thread.sleep(sleepDuration);
                receiptOptional = sendTransactionReceiptRequest(web3j, transactionHash);
            } else {
                TransactionReceipt transactionReceipt = receiptOptional.get();
                logger.info("block number: " + transactionReceipt.getBlockNumber());
                return ResponseEntity.ok(new Block(transactionReceipt.getBlockNumber().toString(), etherScanSite));
            }
        }

        throw new TransactionTimeoutException("Transaction receipt was not generated after "
                + ((sleepDuration * attempts) / 1000
                + " seconds for transaction: " + transactionHash));
    }


    private Optional<TransactionReceipt> sendTransactionReceiptRequest(Web3j web3j, String transactionHash) throws IOException {
        EthGetTransactionReceipt transactionReceipt =
                web3j.ethGetTransactionReceipt(transactionHash).send();
        if (transactionReceipt.hasError()) {
            throw new RuntimeException("Error processing request: " + transactionReceipt.getError().getMessage());
        }
        return transactionReceipt.getTransactionReceipt();
    }


    private DynamicArray<Address> convert(String[] sa) {
        Address[] addresses = new Address[sa.length];
        for (int i = 0; i < addresses.length; i++) {
            addresses[i] = new Address(sa[i]);
        }
        return new DynamicArray<>(addresses);
    }


    private DynamicArray<Uint8> convert(int[] a) {
        Uint8[] shares = new Uint8[a.length];
        for (int i = 0; i < a.length; i++) {
            shares[i] = new Uint8(a[i]);
        }
        return new DynamicArray<>(shares);
    }


}

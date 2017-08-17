package io.veredictum.registrar.service;

import io.veredictum.registrar.model.ContentRegistrarRequest;
import io.veredictum.registrar.util.Hasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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

@Service
public class RegistrarService {

    private static final int SLEEP_DURATION = 15000;
    private static final int ATTEMPTS = 40;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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

    @Value("${registrar.function.name}")
    private String registrarFunctionName;


    public EthSendTransaction sendRequest(ContentRegistrarRequest request) throws Exception {
        Web3j web3j = Web3j.build(new HttpService());
        request.setContentId(System.currentTimeMillis()); // set dummy unique contentId
        request.setOriginalFileHash(Hasher.hashString("" + request.getContentId()));
        request.setTranscodedFileHash(Hasher.hashString("" + (Long.MAX_VALUE - request.getContentId())));
        logger.info("Content registrar request: " + request);
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
        return transactionManager.signAndSend(rawTransaction);
    }

    public TransactionReceipt getTransactionReceipt(String transactionHash) throws TransactionTimeoutException, InterruptedException, IOException {
        Web3j web3j = Web3j.build(new HttpService());
        Optional<TransactionReceipt> receiptOptional =
                sendTransactionReceiptRequest(web3j, transactionHash);
        for (int i = 0; i < ATTEMPTS; i++) {
            if (!receiptOptional.isPresent()) {
                Thread.sleep(SLEEP_DURATION);
                receiptOptional = sendTransactionReceiptRequest(web3j, transactionHash);
            } else {
                return receiptOptional.get();
            }
        }

        throw new TransactionTimeoutException("Transaction receipt was not generated after "
                + ((SLEEP_DURATION * ATTEMPTS) / 1000
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

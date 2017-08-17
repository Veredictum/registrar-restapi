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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.protocol.core.Response;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

/**
 * RESTful API end point
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
@RestController
public class RegistrarController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RegistrarService registrarService;

    @Value("${etherscan.site}")
    private String etherScanSite;

    @RequestMapping(path = "/register/content", method = RequestMethod.POST)
    ResponseEntity<?> registerContent(@RequestBody ContentRegistrarRequest request) throws Exception {
        logger.info("transaction request received");
        EthSendTransaction ethSendTransaction = registrarService.sendRequest(request);
        if (ethSendTransaction.hasError()) {
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

    @RequestMapping(path = "/confirm/registration", method = RequestMethod.GET)
    ResponseEntity<?> confirmRegistration(@RequestParam String transactionHash) throws Exception {
        logger.info("block number request received");
        TransactionReceipt transactionReceipt = registrarService.getTransactionReceipt(transactionHash);
        return ResponseEntity.ok(new Block(transactionReceipt.getBlockNumber().toString(), etherScanSite));
    }

}

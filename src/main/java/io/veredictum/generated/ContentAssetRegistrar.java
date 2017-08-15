package io.veredictum.generated;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Bytes8;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * Auto generated code.<br>
 * <strong>Do not modify!</strong><br>
 * Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>, or {@link org.web3j.codegen.SolidityFunctionWrapperGenerator} to update.
 *
 * <p>Generated with web3j version 2.3.0.
 */
public final class ContentAssetRegistrar extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b5b60008054600160a060020a03191633600160a060020a03161790555b5b610b868061003c6000396000f300606060405236156100a15763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416632267b2e381146100a6578063226de790146100d5578063297e28e9146101095780632e859ccd1461013b57806363c7cc4b1461017a57806383197ef0146101ec57806398539df314610201578063d7e0b6e314610235578063d94f05e414610259578063e6f0e32a1461028a575b600080fd5b34156100b157600080fd5b6100b96102af565b604051600160a060020a03909116815260200160405180910390f35b34156100e057600080fd5b6100f5600160c060020a0319600435166102be565b604051901515815260200160405180910390f35b341561011457600080fd5b610129600160c060020a0319600435166102d3565b60405190815260200160405180910390f35b341561014657600080fd5b6101786024600480358281019290820135918135918201910135600160c060020a0319604435166064356084356102e5565b005b341561018557600080fd5b6101a6600160a060020a0360043516600160c060020a0319602435166105fc565b604051600160a060020a039095168552600160c060020a03199093166020850152604080850192909252606084015260ff909116608083015260a0909101905180910390f35b34156101f757600080fd5b61017861066c565b005b341561020c57600080fd5b610178600160a060020a0360043516600160c060020a03196024351660ff6044351661069a565b005b341561024057600080fd5b610178600160a060020a036004351660243561090d565b005b341561026457600080fd5b610129600160a060020a0360043516610992565b60405190815260200160405180910390f35b341561029557600080fd5b610178600160c060020a0319600435166024356109a4565b005b600054600160a060020a031681565b60026020526000908152604090205460ff1681565b60036020526000908152604090205481565b60008060006102f2610b2c565b600054600160a060020a03908116903316811461030e57600080fd5b6103448c8c8080602002602001604051908101604052809392919081815260200183836020028082843750610a86945050505050565b151561034f57600080fd5b8a891461035b57600080fd5b600160c060020a0319881660009081526002602052604090205460ff161561038257600080fd5b6103b88a8a8080602002602001604051908101604052809392919081815260200183836020028082843750610aea945050505050565b15156103c357600080fd5b600094505b8a851015610527578989868181106103dc57fe5b9050602002013560ff1693508b8b8681811015156103f657fe5b90506020020135600160a060020a0316925060a06040519081016040908152600160a060020a038516808352600160c060020a03198b1660208085018290528385018c9052606085018b905260ff89166080860152600092835260018152838320918352522090925082908151815473ffffffffffffffffffffffffffffffffffffffff1916600160a060020a039190911617815560208201518154780100000000000000000000000000000000000000000000000090910474010000000000000000000000000000000000000000027bffffffffffffffff00000000000000000000000000000000000000001990911617815560408201516001820155606082015160028201556080820151600391909101805460ff191660ff909216919091179055505b6001909401936103c8565b600160c060020a0319881660009081526002602052604090819020805460ff191660011790557f532f9de220a5945c830751e137da7aa8d7d2d1228cbab9b822ba898ce837f434908d908d908d908d908d908d908d9051600160c060020a031984166040820152606081018390526080810182905260a080825281018790528060208082019060c08301908b908b028082843790910184810383528881526020908101915089908902808284378201915050995050505050505050505060405180910390a15b5b505050505050505050505050565b600160208181526000938452604080852090915291835291208054918101546002820154600390920154600160a060020a03841693740100000000000000000000000000000000000000009004780100000000000000000000000000000000000000000000000002929060ff1685565b600054600160a060020a03908116903316811461068857600080fd5b600054600160a060020a0316ff5b5b50565b600160a060020a033381166000908152600160209081526040808320600160c060020a0319871684529091529020541615156106d557600080fd5b600160a060020a0333166000908152600160209081526040808320600160c060020a03198616845290915290206003015460ff8083169116101561071857600080fd5b600160a060020a0333166000908152600160209081526040808320600160c060020a03198616845290915281206003015460ff161161075657600080fd5b33600160a060020a039081166000908152600160208181526040808420600160c060020a031988168086529083528185206003908101805460ff1980821660ff9283168c90038316179092558b8916885295855283872092875291909352932090810180549384169383168601909216929092179055541615156108a257600160a060020a038381166000818152600160208181526040808420600160c060020a0319891680865281845282862080547bffffffffffffffff000000000000000000000000000000000000000019167401000000000000000000000000000000000000000078010000000000000000000000000000000000000000000000008d04021773ffffffffffffffffffffffffffffffffffffffff1916909717875533909716855283835281852096855295825290922080820154918401919091556002908101549390915201555b7fb011bd23c101c4e77b3217ec920cd55a00f5f6e07af3c8967024c42b1ea67fff33848484604051600160a060020a039485168152929093166020830152600160c060020a03191660408083019190915260ff9092166060820152608001905180910390a15b505050565b600054600160a060020a03908116903316811461092957600080fd5b600160a060020a038316600090815260046020526040908190208390557f4c5d15f5779ae1ecd7d55bd3283d898e29c35743d172bd7b83a16e17bc0afe2a908490849051600160a060020a03909216825260208201526040908101905180910390a15b5b505050565b60046020526000908152604090205481565b600160c060020a0319821660009081526002602052604090205460ff1615156109cc57600080fd5b600160a060020a033381166000908152600160209081526040808320600160c060020a031987168452909152902054161515610a0757600080fd5b600160c060020a03198216600090815260036020526040908190208290557f5ecacca9b76241dcda5c84d3f5b4a958761c2f266fb47056d3a3f5106f2fb31c9033908490849051600160a060020a039093168352600160c060020a031990911660208301526040808301919091526060909101905180910390a15b5050565b6000805b8251811015610adf5760046000848381518110610aa357fe5b90602001906020020151600160a060020a031681526020810191909152604001600020541515610ad65760009150610ae4565b5b600101610a8a565b600191505b50919050565b600080805b8351811015610b1e57838181518110610b0457fe5b9060200190602002015160ff16820191505b600101610aef565b8160641492505b5050919050565b60a06040519081016040908152600080835260208301819052908201819052606082018190526080820152905600a165627a7a723058205d80b40f374c5f94f9546eb06d263936708c9ba2b06f09be1dddf780734675950029";

    private ContentAssetRegistrar(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    private ContentAssetRegistrar(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<ContentRegisteredEventResponse> getContentRegisteredEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("ContentRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint8>>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ContentRegisteredEventResponse> responses = new ArrayList<ContentRegisteredEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ContentRegisteredEventResponse typedResponse = new ContentRegisteredEventResponse();
            typedResponse._owners = (DynamicArray<Address>) eventValues.getNonIndexedValues().get(0);
            typedResponse._shares = (DynamicArray<Uint8>) eventValues.getNonIndexedValues().get(1);
            typedResponse._contentId = (Bytes8) eventValues.getNonIndexedValues().get(2);
            typedResponse._originalFileHash = (Bytes32) eventValues.getNonIndexedValues().get(3);
            typedResponse._transcodedFileHash = (Bytes32) eventValues.getNonIndexedValues().get(4);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ContentRegisteredEventResponse> contentRegisteredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("ContentRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}, new TypeReference<DynamicArray<Uint8>>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ContentRegisteredEventResponse>() {
            @Override
            public ContentRegisteredEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ContentRegisteredEventResponse typedResponse = new ContentRegisteredEventResponse();
                typedResponse._owners = (DynamicArray<Address>) eventValues.getNonIndexedValues().get(0);
                typedResponse._shares = (DynamicArray<Uint8>) eventValues.getNonIndexedValues().get(1);
                typedResponse._contentId = (Bytes8) eventValues.getNonIndexedValues().get(2);
                typedResponse._originalFileHash = (Bytes32) eventValues.getNonIndexedValues().get(3);
                typedResponse._transcodedFileHash = (Bytes32) eventValues.getNonIndexedValues().get(4);
                return typedResponse;
            }
        });
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("OwnershipTransferred", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Uint8>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.from = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.to = (Address) eventValues.getNonIndexedValues().get(1);
            typedResponse.contentId = (Bytes8) eventValues.getNonIndexedValues().get(2);
            typedResponse.share = (Uint8) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<OwnershipTransferredEventResponse> ownershipTransferredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("OwnershipTransferred", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Uint8>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.from = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.to = (Address) eventValues.getNonIndexedValues().get(1);
                typedResponse.contentId = (Bytes8) eventValues.getNonIndexedValues().get(2);
                typedResponse.share = (Uint8) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public List<UserRegisteredEventResponse> getUserRegisteredEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("UserRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<UserRegisteredEventResponse> responses = new ArrayList<UserRegisteredEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            UserRegisteredEventResponse typedResponse = new UserRegisteredEventResponse();
            typedResponse.userAddress = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.userInfoHash = (Bytes32) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<UserRegisteredEventResponse> userRegisteredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("UserRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, UserRegisteredEventResponse>() {
            @Override
            public UserRegisteredEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                UserRegisteredEventResponse typedResponse = new UserRegisteredEventResponse();
                typedResponse.userAddress = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.userInfoHash = (Bytes32) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public List<AuthorisedDomainsRegisteredEventResponse> getAuthorisedDomainsRegisteredEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("AuthorisedDomainsRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Bytes32>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<AuthorisedDomainsRegisteredEventResponse> responses = new ArrayList<AuthorisedDomainsRegisteredEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            AuthorisedDomainsRegisteredEventResponse typedResponse = new AuthorisedDomainsRegisteredEventResponse();
            typedResponse.owner = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.contentId = (Bytes8) eventValues.getNonIndexedValues().get(1);
            typedResponse.authorisedDomainsHash = (Bytes32) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<AuthorisedDomainsRegisteredEventResponse> authorisedDomainsRegisteredEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("AuthorisedDomainsRegistered", 
                Arrays.<TypeReference<?>>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Bytes32>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, AuthorisedDomainsRegisteredEventResponse>() {
            @Override
            public AuthorisedDomainsRegisteredEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                AuthorisedDomainsRegisteredEventResponse typedResponse = new AuthorisedDomainsRegisteredEventResponse();
                typedResponse.owner = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.contentId = (Bytes8) eventValues.getNonIndexedValues().get(1);
                typedResponse.authorisedDomainsHash = (Bytes32) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Future<Address> veredictum() {
        Function function = new Function("veredictum", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bool> contentIdRegistered(Bytes8 param0) {
        Function function = new Function("contentIdRegistered", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<Bytes32> authorisedDomainsRegistrar(Bytes8 param0) {
        Function function = new Function("authorisedDomainsRegistrar", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> registerContent(DynamicArray<Address> _owners, DynamicArray<Uint8> _shares, Bytes8 _contentId, Bytes32 _originalFileHash, Bytes32 _transcodedFileHash) {
        Function function = new Function("registerContent", Arrays.<Type>asList(_owners, _shares, _contentId, _originalFileHash, _transcodedFileHash), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<List<Type>> contentIdRegistrar(Address param0, Bytes8 param1) {
        Function function = new Function("contentIdRegistrar", 
                Arrays.<Type>asList(param0, param1), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Bytes8>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint8>() {}));
        return executeCallMultipleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> destroy() {
        Function function = new Function("destroy", Arrays.<Type>asList(), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> transfer(Address _other, Bytes8 _contentId, Uint8 _share) {
        Function function = new Function("transfer", Arrays.<Type>asList(_other, _contentId, _share), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<TransactionReceipt> registerUser(Address _userAddress, Bytes32 _userInfoHash) {
        Function function = new Function("registerUser", Arrays.<Type>asList(_userAddress, _userInfoHash), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public Future<Bytes32> userInfoHashRegistrar(Address param0) {
        Function function = new Function("userInfoHashRegistrar", 
                Arrays.<Type>asList(param0), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallSingleValueReturnAsync(function);
    }

    public Future<TransactionReceipt> registerAuthorisedDomains(Bytes8 _contentId, Bytes32 _authorisedDomainsHash) {
        Function function = new Function("registerAuthorisedDomains", Arrays.<Type>asList(_contentId, _authorisedDomainsHash), Collections.<TypeReference<?>>emptyList());
        return executeTransactionAsync(function);
    }

    public static Future<ContentAssetRegistrar> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(ContentAssetRegistrar.class, web3j, credentials, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static Future<ContentAssetRegistrar> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialWeiValue) {
        return deployAsync(ContentAssetRegistrar.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "", initialWeiValue);
    }

    public static ContentAssetRegistrar load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContentAssetRegistrar(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static ContentAssetRegistrar load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ContentAssetRegistrar(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class ContentRegisteredEventResponse {
        public DynamicArray<Address> _owners;

        public DynamicArray<Uint8> _shares;

        public Bytes8 _contentId;

        public Bytes32 _originalFileHash;

        public Bytes32 _transcodedFileHash;
    }

    public static class OwnershipTransferredEventResponse {
        public Address from;

        public Address to;

        public Bytes8 contentId;

        public Uint8 share;
    }

    public static class UserRegisteredEventResponse {
        public Address userAddress;

        public Bytes32 userInfoHash;
    }

    public static class AuthorisedDomainsRegisteredEventResponse {
        public Address owner;

        public Bytes8 contentId;

        public Bytes32 authorisedDomainsHash;
    }
}

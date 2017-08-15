package io.veredictum.registrar;

public class Transaction {

    private String transactionHash;
    private String etherScanSite;
    private String url;

    public Transaction(String transactionHash, String etherScanSite) {
        this.transactionHash = transactionHash;
        this.etherScanSite = etherScanSite;
        this.url = etherScanSite + "/tx/" + transactionHash;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public String getEtherScanSite() {
        return etherScanSite;
    }

    public String getUrl() {
        return url;
    }

}

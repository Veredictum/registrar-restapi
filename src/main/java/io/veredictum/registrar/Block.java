package io.veredictum.registrar;

public class Block {

    private String blockNumber;
    private String etherScanSite;
    private String url;

    public Block(String blockNumber, String etherScanSite) {
        this.blockNumber = blockNumber;
        this.etherScanSite = etherScanSite;
        this.url = etherScanSite + "/block/" + blockNumber;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getEtherScanSite() {
        return etherScanSite;
    }

    public void setEtherScanSite(String etherScanSite) {
        this.etherScanSite = etherScanSite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

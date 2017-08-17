/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/

package io.veredictum.registrar;


/**
 * Simple bean to be converted to JSON and sent to client
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */

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

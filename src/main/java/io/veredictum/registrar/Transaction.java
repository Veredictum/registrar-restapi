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

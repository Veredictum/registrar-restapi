/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/

package io.veredictum.registrar.model;

import java.util.Arrays;

/**
 * It represents a request to register content ownership from client
 *
 * @author Fei Yang <fei.yang@veredictum.io>
 */
public class ContentRegistrarRequest {

    private String[] addresses;

    private int[] shares;

    private long contentId;

    private byte[] originalFileHash;

    private byte[] transcodedFileHash;

    public String[] getAddresses() {
        return addresses;
    }

    public void setAddresses(String[] addresses) {
        this.addresses = addresses;
    }

    public int[] getShares() {
        return shares;
    }

    public void setShares(int[] shares) {
        this.shares = shares;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public byte[] getOriginalFileHash() {
        return originalFileHash;
    }

    public void setOriginalFileHash(byte[] originalFileHash) {
        this.originalFileHash = originalFileHash;
    }

    public byte[] getTranscodedFileHash() {
        return transcodedFileHash;
    }

    public void setTranscodedFileHash(byte[] transcodedFileHash) {
        this.transcodedFileHash = transcodedFileHash;
    }

    @Override
    public String toString() {
        return "ContentRegistrarRequest{" +
                "addresses=" + Arrays.toString(addresses) +
                ", shares=" + Arrays.toString(shares) +
                ", contentId=" + contentId +
                ", originalFileHash=" + Arrays.toString(originalFileHash) +
                ", transcodedFileHash=" + Arrays.toString(transcodedFileHash) +
                '}';
    }
}

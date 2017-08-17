/*

This software is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
See MIT Licence for further details.
<https://opensource.org/licenses/MIT>.

*/

package io.veredictum.registrar.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class to hash file or text with SHA256
 *
 * @author Samuel G Brooks <sam.brooks@veredictum.io>
 * @author Fei Yang <fei.yang@veredictum.io>
 */
public class Hasher {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hasher.class);

    public static byte[] hashFile(String filePath) {
        Path path = Paths.get(filePath);
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            return digester.digest(Files.readAllBytes(path));
        } catch (NoSuchAlgorithmException | IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e); // should be considered a bug if we are here
        }
    }

    public static byte[] hashString(String input) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            return digester.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e); // should be considered a bug if we are here
        }
    }

    public static byte[] hashBytes(byte[] bytes) {
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA-256");
            return digester.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e); // should be considered a bug if we are here
        }
    }
}
package attendanceautomation.BLL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class used to hash passwords.
 * @author Dominik
 */
public class Hasher {

    private static MessageDigest md;

    /**
     * Hash a password for secure storing.
     * @param password The given password that will be hashed.
     * @return The hash of the given string.
     */
    public String hash(String password) {
        String hashed = "";
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] digested = md.digest(password.getBytes());
            for (byte b : digested) {
                hashed += Integer.toHexString(0xFF & b); //0xFF is for maskig the last 8 bit
            }
            System.out.println(hashed);
            return hashed;
        }
        catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Hasher.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashed;
    }

}

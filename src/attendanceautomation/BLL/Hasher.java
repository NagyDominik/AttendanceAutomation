package attendanceautomation.BLL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dominik
 */
public class Hasher {

    private static MessageDigest md;

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

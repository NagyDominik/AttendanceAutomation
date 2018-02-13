/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attendanceautomation.BLL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Dominik
 */
public class Encrypter {

    private static MessageDigest md;

    public String encrypt(String password) throws NoSuchAlgorithmException {
        String encrypted = "";
        md = MessageDigest.getInstance("MD5");
        byte[] digested = md.digest(password.getBytes());
        for (byte b : digested) {
            encrypted += Integer.toHexString(0xFF & b); //0xFF is for maskig the last 8 bit
        }
        System.out.println(encrypted);
        return encrypted;
    }

}

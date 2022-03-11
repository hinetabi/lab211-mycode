/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Legion
 */
public final class IOHelper {

    // save an object to binary file
    public static void saveObject(String filename, Object object) throws FileNotFoundException, IOException {
        try (FileOutputStream file = new FileOutputStream(filename); ObjectOutputStream os = new ObjectOutputStream(file)) {
            os.writeObject(object);
            os.close();
        }
        
    }

    // load an object to binary file
    public static Object loadObject(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (FileInputStream file = new FileInputStream(filename); ObjectInputStream is = new ObjectInputStream(file)) {
            Object obj = is.readObject();
            is.close();
            return obj;
        }
    }
    
    // encode sha256 str
    /**
     * 
     * @param str
     * @return 
     */
    public static String SHA256(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] encoded = md.digest(str.getBytes());

            String result = "";
            for (byte b : encoded) {
                String hex = Integer.toHexString(b);
                result += (hex.length() == 1 ? "0" : "") + hex;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}

package edu.bsu.cs222;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {
    public static final String SALT = "saltyText";

    //allow user to input information on GUI
    public void signup(String usernameInput, String passwordInput, String typeInput) {
        String hashedPassword = createHashedPassword(passwordInput);
        SQLUserProcessor.addUser(usernameInput,hashedPassword,"Patron");
    }

    //allow user to input information on GUI
    public void loginUser(String usernameInput, String passwordInput) {
        String hashedPassword = createHashedPassword(passwordInput);
        SQLUserProcessor.login(usernameInput,hashedPassword);
    }

    public static String createHashedPassword(String password) {
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        
        return hashedPassword;
    }
    
    public static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }

}
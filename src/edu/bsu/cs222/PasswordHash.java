package edu.bsu.cs222;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class PasswordHash {

    //Hash function works by adding the salt key to a user's password
    //Then running the combined string through a hashing algorithm
    //This generates a 40 digit value that is stored in our database

    private static final String SALT = "saltyText"; //This string is only known to the devs as an extra security measure

    //allow user to input information on GUI
    static boolean userSignup(String usernameInput, String passwordInput, String typeInput) {

        //adds the salt key to user's password and hashes it
        String hashedPassword = createHashedPassword(passwordInput);
        return SQLUserProcessor.addUser(usernameInput,hashedPassword,typeInput);
    }

    //allow user to input information on GUI
    static boolean userLogin (String usernameInput, String passwordInput) {

        String hashedPassword = createHashedPassword(passwordInput);
        SQLUserProcessor.login(usernameInput,hashedPassword);

        return SQLUserProcessor.login(usernameInput,hashedPassword);
    }

    private static String createHashedPassword(String password) {
        String saltedPassword = SALT + password;
        return generateHash(saltedPassword);
    }


    //Hash function, SHA-1, was provided by Veera Sundar at https://dzone.com/articles/storing-passwords-java-web
    private static String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (byte b : hashedBytes) {
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }

}
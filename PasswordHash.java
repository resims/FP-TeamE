package edu.bsu.cs222;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class PasswordHash {
    //public class PasswordHash extends SQLProcessor{

    Map<String, String> exampleDB = new HashMap<String, String>();
    public static final String SALT = "saltyText";

    /**
      System.out.println("Please enter username");
    Scanner nameInput = new Scanner(System.in);
    String userName = nameInput.nextLine();

        System.out.println("Please enter password");
    Scanner passwordInput = new Scanner(System.in);
    String password = passwordInput.nextLine();
    */


    //the following is a tester
    public static void main(String args[]) {
        PasswordHash tester = new PasswordHash();
        tester.signup("john", "right-password");

        // login should succeed.
        if (tester.login("john", "right-password"))
            System.out.println("user login successful.");

        // login should fail because of wrong password.
        if (tester.login("john", "wrongpassword"))
            System.out.println("User login successful.");
        else
            System.out.println("user login failed.");
    }

    public void signup(String username, String password) {
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        exampleDB.put(username, hashedPassword);

        //make sure password is hashed
        System.out.println(hashedPassword);
    }

    public Boolean login(String username, String password) {
        Boolean isAuthenticated = false;

        // remember to use the same SALT value used while storing password
        // for the first time.
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);

        String storedPasswordHash = exampleDB.get(username);
        if(hashedPassword.equals(storedPasswordHash)){
            isAuthenticated = true;
        }else{
            isAuthenticated = false;
        }
        return isAuthenticated;
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

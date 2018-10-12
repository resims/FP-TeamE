package edu.bsu.cs222;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CLI {

    public static void main(String[] args) {
        try {

            ResultSet rs = SQLProcessor.veiwAllBooks();
            String CallNumber="A1334";
            SQLProcessor.parse(rs);
            SQLProcessor.checkout("A1334");
            rs = SQLProcessor.veiwAllBooks();
            SQLProcessor.parse(rs);


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}

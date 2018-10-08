package edu.bsu.cs222;

import java.util.Scanner;
import java.sql.*;
//import java.sql.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class CLI {
    static Connection conn = null;
    public static void main(String args[]) {
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/library?" +
                            "user=root&useSSL=false");


            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM books");
            parse(rs);


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    public static void parse(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {System.out.print(metaData.getColumnName(i)+", ");}System.out.println();
        while (resultSet.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue);
            }
            System.out.println();
        }
    }
}







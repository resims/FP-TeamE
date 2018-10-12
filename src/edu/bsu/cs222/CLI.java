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
            conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
                            "user=root&useSSL=false");

            //checkout("A1334");
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM books");
            String CallNumber="A1334";
            parse(rs);
            checkout(CallNumber);
            rs = conn.createStatement().executeQuery("SELECT * FROM books");
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
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            System.out.print(metaData.getColumnName(i) + ", ");
        }
        System.out.println();
        while (resultSet.next()) {
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue);
            }
            System.out.println();
        }
    }
    public static int getAvailable(ResultSet resultSet) throws SQLException {
        int number=0;

        if (resultSet.first()) {
            number=resultSet.getInt(1);
        }
        return number;

    }
    public static void checkout(String CallNumber) {
        int NumberAvailable = 0;
        try {
            ResultSet rs =conn.createStatement().executeQuery("select `Number Available` from books where `Call Number`=\"" + CallNumber + "\";");
            NumberAvailable=getAvailable(rs);
            if (NumberAvailable != 0) {
                conn.createStatement().execute("update books set `Number Available`=`Number Available`-1 where `Call Number`=\"" + CallNumber + "\";");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}







package edu.bsu.cs222;


import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLProcessor {
    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/library?" +
                        "user=root&useSSL=false");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ResultSet veiwAllBooks(){
        ResultSet rs=null;
        try {
             rs = conn.createStatement().executeQuery("SELECT * FROM books");
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
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







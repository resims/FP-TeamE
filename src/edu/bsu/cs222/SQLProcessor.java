package edu.bsu.cs222;

import java.sql.*;

public class SQLProcessor {
    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&useSSL=false");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet generateQueryResultSet(String statement) {
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(statement);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static boolean executeSQL(String statement) {
        try {
            return conn.createStatement().execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void parse(ResultSet resultSet) {
        ResultSetMetaData metaData = null;
        try {
            metaData = resultSet.getMetaData();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

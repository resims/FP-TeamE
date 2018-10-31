package edu.bsu.cs222;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLProcessor {
    private static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&useSSL=false&allowMultiQueries=true");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static ResultSet generateQueryResultSet(String statement) {
        ResultSet rs=null;
        try {
            rs = conn.createStatement().executeQuery(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    static boolean executeSQL(String statement) {
        try {
            return conn.createStatement().execute(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    static void parse(ResultSet resultSet) {
        ResultSetMetaData metaData;
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
    public static ArrayList parseasList(ResultSet resultSet) {
        ResultSetMetaData metaData;
        ArrayList<List<String>> list=new ArrayList<>();
        try {
            metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                System.out.print(metaData.getColumnName(i) + ", ");
            }
            System.out.println();
            ArrayList<String> innerlist=new ArrayList<>();
            while (resultSet.next()) {
                innerlist.clear();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    System.out.print(columnValue);
                    innerlist.add(columnValue);
                }
                list.add(innerlist);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

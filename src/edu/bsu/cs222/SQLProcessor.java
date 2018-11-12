package edu.bsu.cs222;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class SQLProcessor {
    private static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/library?user=root&useSSL=false&allowMultiQueries=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=EST");
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
            conn.createStatement().execute(statement);
            return true;
        } catch(SQLIntegrityConstraintViolationException e){
            System.out.println("cannot add user");
        }
        catch (SQLException e) {
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
    static String parseasString(ResultSet resultSet){
        ResultSetMetaData metaData;
        StringBuilder result= new StringBuilder();
        try {
            metaData = resultSet.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                result.append(metaData.getColumnName(i)).append(", ");
            }
            result.append(";");
            while (resultSet.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    if (i > 1) result.append(",  ");
                    String columnValue = resultSet.getString(i);
                    result.append(columnValue);
                }
                result.append(";");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    static ArrayList parseasList(ResultSet resultSet) {
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

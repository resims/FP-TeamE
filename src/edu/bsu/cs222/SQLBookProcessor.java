package edu.bsu.cs222;


import java.sql.*;
import java.sql.SQLException;


public class SQLBookProcessor extends SQLProcessor {


    static ResultSet viewAllBooks() {
        return generateQueryResultSet(SQLGenerator.viewAllBooks());
    }
    static ResultSet viewAllTransactions() {
        return generateQueryResultSet("Select* from circulation;");
    }

    public static ResultSet search(String type, String term) {
        return generateQueryResultSet(SQLGenerator.search(type,term,0));
    }
    public static ResultSet advancedSearch(String type, String term,int qualifier) {
        //qualifier is -1 for %term (ends with), 0 for %term% (contains), 1 for term% (starts with)
        return generateQueryResultSet(SQLGenerator.search(type,term,qualifier));
    }

    private static int getAvailable(int barcode_number) {
        int number = 0;
        ResultSet rs=generateQueryResultSet(SQLGenerator.getAvailable(barcode_number));
        try {
            if (rs.first()) {
                number = rs.getInt(1);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return number;
    }
    static void checkout(int barcode_number, int UserID){
        int NumberAvailable = getAvailable(barcode_number);
        if (NumberAvailable != 0) {
            System.out.println("Checking out");
        executeSQL(SQLGenerator.checkout(barcode_number,UserID));
        }else{System.out.println("No Available copies to check out");}
    }


    static void checkin(int barcode_number){
        int NumberAvailable = getAvailable(barcode_number);
        if (NumberAvailable !=1 ) {
            System.out.println("Checking in");
            executeSQL(SQLGenerator.checkin(barcode_number));
        }else{System.out.println("No Available copies to check out");}
    }
}







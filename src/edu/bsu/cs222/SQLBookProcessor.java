package edu.bsu.cs222;


import java.sql.*;
import java.sql.SQLException;


class SQLBookProcessor{


    static ResultSet viewAllBooks() {
        return SQLProcessor.generateQueryResultSet(SQLGenerator.viewAllBooks());
    }
    static ResultSet viewAllTransactions() {
        return SQLProcessor.generateQueryResultSet("Select* from circulation;");
    }

    static ResultSet search(String type, String term) {
        return advancedSearch(type,term,0);
    }
    static ResultSet advancedSearch(String type, String term, int qualifier) {
        //qualifier is -1 for %term (ends with), 0 for %term% (contains), 1 for term% (starts with)
        return SQLProcessor.generateQueryResultSet(SQLGenerator.search(type,term,qualifier));
    }

    private static int getAvailable(int barcode_number) {
        int number = 0;
        ResultSet rs=SQLProcessor.generateQueryResultSet(SQLGenerator.getAvailable(barcode_number));
        try {
            if (rs.first()) {
                number = rs.getInt(1);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return number;
    }
    static boolean checkout(int barcode_number, int UserID){
        int NumberAvailable = getAvailable(barcode_number);
        if (NumberAvailable != 0) {
            return true;
        //SQLProcessor.executeSQL(SQLGenerator.checkout(barcode_number,UserID));
        }else{return false;}
    }


    static boolean checkin(int barcode_number){
        int NumberAvailable = getAvailable(barcode_number);
        if (NumberAvailable !=1 ) {
            return true;
            //SQLProcessor.executeSQL(SQLGenerator.checkin(barcode_number));
        }else{return false;}
    }
}







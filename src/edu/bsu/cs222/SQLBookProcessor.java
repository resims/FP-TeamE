package edu.bsu.cs222;


import java.sql.*;
import java.sql.SQLException;

@SuppressWarnings("unused")
class SQLBookProcessor {


    static ResultSet viewAllBooks() {
        return SQLProcessor.generateQueryResultSet(SQLGenerator.viewAllBooks());
    }

    static ResultSet viewAllTransactions() {
        return SQLProcessor.generateQueryResultSet("Select* from circulation;");
    }

    static ResultSet advancedSearch(String type, String term, int qualifier) {
        //qualifier is -1 for %term (ends with), 0 for %term% (contains), 1 for term% (starts with)
        qualifier -= 1;
        return SQLProcessor.generateQueryResultSet(SQLGenerator.search(type, term, qualifier));
    }

    private static int getAvailable(int barcode_number) {
        int number = 0;
        ResultSet rs = SQLProcessor.generateQueryResultSet(SQLGenerator.getAvailable(barcode_number));
        try {
            if (rs.first()) {
                number = rs.getInt(1);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return number;
    }

    private static String getReservation(int barcode_number) {
        String reserved = "";
        ResultSet rs = SQLProcessor.generateQueryResultSet(SQLGenerator.getReservation(barcode_number));
        try {
            if (rs.first()) {
                reserved = rs.getString(1);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return reserved;
    }
    @SuppressWarnings("ConstantConditions")
    static boolean checkout(int barcode_number, int UserID) {
        int NumberAvailable = getAvailable(barcode_number);

        String reserved = getReservation(barcode_number);
        String user = SQLUserProcessor.getUsername(UserID);
        if (SQLUserProcessor.userExists(UserID)) {
            if (NumberAvailable != 0 || (NumberAvailable == 0 && reserved.equals(user))) {
                SQLProcessor.executeSQL(SQLGenerator.checkout(barcode_number, UserID));
                return true;
            }}
            return false;
    }


    static boolean checkin(int barcode_number) {
        int NumberAvailable = getAvailable(barcode_number);
        if (NumberAvailable != 1) {

            SQLProcessor.executeSQL(SQLGenerator.checkin(barcode_number));
            return true;
        } else {
            return false;
        }
    }

    static boolean update_due_date(int user_id, int book_id, String newDueDate) {
        return SQLProcessor.executeSQL(SQLGenerator.update_due_date(user_id, book_id, newDueDate));
    }

    static ResultSet show_overdue_items() {
        return SQLProcessor.generateQueryResultSet(SQLGenerator.overdue());
    }

    static boolean reserve_book(int barcode_number) {
        if (SQLBookProcessor.getReservation(barcode_number).equals("")) {
            return SQLProcessor.executeSQL(SQLGenerator.reserveBook("" + barcode_number));
        }
        return false;
    }

    static String getDamage(int barcode_number){
        String damageNotes="";
        ResultSet rs = SQLProcessor.generateQueryResultSet(SQLGenerator.getDamage(barcode_number));
        try {
            if (rs.first()) {
                damageNotes = rs.getString(1);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return damageNotes;
    }
    static boolean editDamage(int barcode_number, String damageNotes) {
        return SQLProcessor.executeSQL(SQLGenerator.editDamage(barcode_number, damageNotes));
    }
}

package edu.bsu.cs222;


import java.sql.*;
import java.sql.SQLException;


public class SQLBookProcessor extends SQLProcessor {


    static ResultSet viewAllBooks() {
        return generateQueryResultSet("SELECT rs1.Call_number, rs1.Title, rs1.Author, rs1.Available, rs2.Total FROM (SELECT Call_Number,Title,Author , COUNT(*) Available FROM books Where Available=1 GROUP BY Call_Number) as rs1 inner join (select Call_Number, COUNT(*) Total FROM books GROUP by Call_Number) as rs2 on rs1.Call_Number=rs2.Call_Number GROUP BY rs1.Call_number;");
    }
    static ResultSet viewAllTransactions() {
        return generateQueryResultSet("Select* from circulation;");
    }

    public static ResultSet search(String type, String term) {
        return generateQueryResultSet("Select* from books where " + type + " like '%" + term + "%';");
    }

    private static int getAvailable(int barcode_number) {
        int number = 0;
        //ResultSet rs =generateQueryResultSet("select Number_Available from books where barcode_number='" + barcode_number + "';");
        //SELECT Call_Number,Title,Author , COUNT(*) Available FROM books Where Call_Number="A1334" and Available=1,COUNT(*) Total FROM books Where Call_Number="A1334" GROUP BY Title ORDER BY COUNT(*) ASC
        //The second one adds a column with the total number of books
        ResultSet rs=generateQueryResultSet("SELECT Available FROM books Where barcode_number="+barcode_number+";");
        try {
            if (rs.first()) {
                number = rs.getInt(1);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return number;
    }

    static void checkout(int barcode_number, int UserID) {
        int NumberAvailable = getAvailable(barcode_number);
        if (NumberAvailable != 0) {
            System.out.println("Checking out");
            executeSQL("update books set Available=0 where barcode_number='" + barcode_number + "';");
            System.out.println(executeSQL("INSERT INTO `circulation`(`user_id`, `book_id`, `due_date`) VALUES ("+UserID+",'"+barcode_number+"',DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 14 DAY));"));
        }else{System.out.println("No Available copies to check out");}
    }

    static void checkin(int barcode_number, int UserID){
        int NumberAvailable = getAvailable(barcode_number);
        if (NumberAvailable !=1 ) {
            System.out.println("Checking in");
            executeSQL("update books set Available=1 where barcode_number='" + barcode_number + "';");
                    executeSQL("update circulation set checkin_date=CURRENT_TIMESTAMP WHERE book_id='"+barcode_number+"'and user_id="+UserID+" and checkin_date is null ORDER BY trans_id ASC limit 1;");
        }else{System.out.println("No Available copies to check out");}
    }
}







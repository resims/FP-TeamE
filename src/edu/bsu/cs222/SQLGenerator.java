package edu.bsu.cs222;

public class SQLGenerator {
    static String checkout(int barcode_number, int UserID) {
        return ("update books set Available=0 where barcode_number='" + barcode_number + "'; INSERT INTO `circulation`(`user_id`, `book_id`, `due_date`) VALUES ("+UserID+",'"+barcode_number+"',DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 14 DAY));");
    }
    static String checkin(int barcode_number){
        return "update books set Available=1 where barcode_number='" + barcode_number + "'; "+"update circulation set checkin_date=CURRENT_TIMESTAMP WHERE book_id='"+barcode_number+"' and checkin_date is null ORDER BY trans_id ASC limit 1;";
    }
    static String getAvailable(int barcode_number){
        return "SELECT Available FROM books Where barcode_number="+barcode_number+";";
    }
    static String viewAllBooks(){
        return "SELECT rs1.Call_number, rs1.Title, rs1.Author, rs1.Available, rs2.Total FROM (SELECT Call_Number,Title,Author , COUNT(*) Available FROM books Where Available=1 GROUP BY Call_Number) as rs1 inner join (select Call_Number, COUNT(*) Total FROM books GROUP by Call_Number) as rs2 on rs1.Call_Number=rs2.Call_Number GROUP BY rs1.Call_number;";
    }
    static String search(String type, String term,int qualifier){
        if (qualifier==-1) {term="%"+term;}
        else if (qualifier==0) {term="%"+term+"%";}
        else {term=term+"%";}
        return "Select* from books where " + type + " like '" + term + "';";
    }
}

package edu.bsu.cs222;


import java.sql.ResultSet;
import java.util.Scanner;

public class CLI {
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {


        SQLBookProcessor.parse(SQLBookProcessor.viewAllBooks());
        SQLBookProcessor.checkout(1, 2);
        waitforit();
        SQLBookProcessor.checkin(1);

        SQLBookProcessor.checkout(2, 3);
        waitforit();
        SQLBookProcessor.checkin(2);
        SQLBookProcessor.parse(SQLBookProcessor.viewAllTransactions());

    }
    public static void waitforit(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void login() {
        String username = s.next();
    }
}

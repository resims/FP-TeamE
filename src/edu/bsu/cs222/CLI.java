package edu.bsu.cs222;

import java.util.Scanner;

public class CLI {
    private static Scanner s = new Scanner(System.in);
    private static String userType="Guest";
    public static void main(String[] args) {
        System.out.println("Library Management System 1.0 Command-line option");
        switch (userType) {
            case "Guest":
                guestMode();
                break;
            case "Patron":
                patronMode();

                break;
            case "Dean":
                deanMode();

                break;
        }

        //this is where I am putting function calls to get them to stop complaining about not being used
        SQLProcessor.parse(SQLBookProcessor.viewAllBooks());
        SQLBookProcessor.checkout(1, 2);
        waitForIt();
        SQLBookProcessor.checkin(1);

        SQLBookProcessor.checkout(2, 3);
        waitForIt();
        SQLBookProcessor.checkin(2);
        SQLProcessor.parse(SQLBookProcessor.viewAllTransactions());
        SQLBookProcessor.search("H","Title");
        System.out.println(SQLBookProcessor.search("T","Author"));
        System.out.println(SQLProcessor.parseasList(SQLBookProcessor.advancedSearch("B","Author",-1)));
}

    private static void deanMode() {
        System.out.println("Options: \n [1] Log in \n [2]Add User");
        System.out.print("Enter choice: ");
        int choice=s.nextInt();
        if (choice==1){
            logout();
        }
        else if (choice==2){
            addUser();
        }
    }

    private static void logout() {
        userType="Guest";
    }

    private static void patronMode() {

    }

    private static void guestMode(){
        System.out.println("Options: \n [1] Log in \n []");
        System.out.print("Enter choice: ");
        int choice=s.nextInt();
        if (choice==1){
            login();
        }
    }
    private static void waitForIt(){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void login() {
        System.out.print("Username: ");
        String username = s.next();

        System.out.print("\nPassword: ");
        String password = s.next();
        if (SQLUserProcessor.login(username,password)){
            System.out.println("Success!");
            userType=SQLUserProcessor.getUserType(username);

        }
    }
    private static void addUser(){
        System.out.print("Username: ");
        String username = s.next();

        System.out.print("\nPassword: ");
        String password = s.next();

        System.out.print("\nRole: ");
        String role = s.next();

        if (role.equals("")){
            role="Patron";
        }

        if (SQLUserProcessor.addUser(username,password,role)){
            System.out.println("Success!");
        }
    }
}

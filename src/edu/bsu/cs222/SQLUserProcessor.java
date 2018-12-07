package edu.bsu.cs222;

import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
class SQLUserProcessor {
    static String Username="";
    static String getUserType(String username){
        ResultSet rs = SQLProcessor.generateQueryResultSet(SQLGenerator.getUserType(username));
        try {
            if (rs.first()) {
                return rs.getString(1);
            }

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return "Guest";
    }

    private static boolean isValid(String username){
        return !username.contains("\"") && !username.contains("%") && !username.contains(";") && !username.contains("'");
    }
    //I got rid of the addUser(username, password), and instead, it will be taken care of at the interface level, if role is blank, change it to patron.
    //-Ryan

    static boolean addUser(String username, String password, String type){
    //hash the password so that each character is encoded into index*ASCII +next+next etc
        if (isValid(username)){
            return SQLProcessor.executeSQL(SQLGenerator.addUser(username, password, type));
            //returns true if added successfully, false if it didn't add
            //the username column is set to unique in the SQL database, so that takes care of vetting duplicate usernames
        }
        return false; //Failed to add username due to invalid characters
    }
    static boolean login(String username, String password){
        boolean result= SQLProcessor.parseasString(SQLProcessor.generateQueryResultSet(SQLGenerator.getPassword(username))).contains(password);
        if (result){
            Username=username;
        }
        return result;
        //if the username does not exist, the results should be empty.
        //if the username does exist, the sql query responds with the password, and .contains() checks if the password matches.
    }
    static ResultSet check_due_dates(){
        return  SQLProcessor.generateQueryResultSet(SQLGenerator.check_due_dates(Username));
    }
    static boolean removeUser(int userId){
        return SQLProcessor.executeSQL(SQLGenerator.removeUser(userId));
    }
    static boolean changeUserType(int userID, String type){
        return SQLProcessor.executeSQL(SQLGenerator.changeUserType(userID,type));
    }

    static String getUsername(int userID) {
        String username="";
        ResultSet rs=SQLProcessor.generateQueryResultSet(SQLGenerator.getUsername(userID));
        try {
            if (rs.first()) {
                username = rs.getString(1);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return username;

    }
    static boolean userExists(int userID){
        int usercount=0;
        ResultSet rs=SQLProcessor.generateQueryResultSet(SQLGenerator.userExists(userID));
        try {
            if (rs.first()) {
                usercount = rs.getInt(1);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return usercount==1;

    }
    }

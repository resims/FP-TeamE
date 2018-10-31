package edu.bsu.cs222;

public class SQLUserProcessor {
    public void checkUsername(String username){

    }
    public void addUser(String username,String password){
        addUser(username,password,"Patron");
    }
    public void addUser(String username, String password,String type){
    //hash the password so that each character is encoded into index*ASCII +next+next etc
    }
}

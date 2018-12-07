package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class SQLGeneratorTest {

    @Test
    public void checkout() {
        Assert.assertEquals(SQLGenerator.checkout(4,6),"update books set Reservations='', Available=0 where barcode_number='4'; INSERT INTO `circulation`(`user_id`, `book_id`, `due_date`) VALUES (6,'4',DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 14 DAY));");
    }

    @Test
    public void checkin() {
        Assert.assertEquals(SQLGenerator.checkin(5),"update books set Available=1 where barcode_number='5'; "+"update circulation set checkin_date=CURRENT_TIMESTAMP WHERE book_id='5' and checkin_date is null ORDER BY trans_id ASC limit 1;");
    }

    @Test
    public void getAvailable() {
        Assert.assertEquals(SQLGenerator.getAvailable(8),"SELECT Available FROM books Where barcode_number=8;");
    }

    @Test
    public void viewAllBooks() {
        Assert.assertEquals(SQLGenerator.viewAllBooks(),"SELECT rs1.Call_number, rs1.Title, rs1.Author, rs1.Available, rs2.Total FROM (SELECT Call_Number,Title,Author , COUNT(*) Available FROM books Where Available=1 GROUP BY Call_Number) as rs1 inner join (select Call_Number, COUNT(*) Total FROM books GROUP by Call_Number) as rs2 on rs1.Call_Number=rs2.Call_Number GROUP BY rs1.Call_number;");
    }

    @Test
    public void searchStartsWith() {
        Assert.assertEquals(SQLGenerator.search("Author","A",-1),"Select* from books where Author like '%A';");
    }
    @Test
    public void searchContains() {
        Assert.assertEquals(SQLGenerator.search("Author","A",0),"Select* from books where Author like '%A%';");
    }
    @Test
    public void searchEndsWith() {
        Assert.assertEquals(SQLGenerator.search("Author","A",1),"Select* from books where Author like 'A%';");
    }

    @Test
    public void addUser() {
        Assert.assertEquals(SQLGenerator.addUser("username","password","type"),"Insert into Users (Username,Password, Type) values ('username','password','type');");
    }

    @Test
    public void getPassword() {
        Assert.assertEquals(SQLGenerator.getPassword("resims"),"select Password from Users where Username='resims';");
    }
    @Test

    public void getUserType() {
         Assert.assertEquals(SQLGenerator.getUserType("username"),"select Type from users where username='username';");
    }
    @Test

    public void check_due_dates(){
        Assert.assertEquals(SQLGenerator.check_due_dates("Username"),"select books.Title, books.barcode_number, rs1.due_date from books join (select * from circulation join users on circulation.user_id=users.ID where users.Username='Username' and circulation.checkin_date is null) as rs1 on rs1.book_id=books.barcode_number");
    }
    @Test

    public void update_due_date() {
        Assert.assertEquals(SQLGenerator.update_due_date(1,2,"12-1-2019"),"UPDATE `circulation` SET `due_date` = '12-1-2019' WHERE user_id="+1+" and book_id="+2+" and checkin_date is null limit 1;");
    }
    @Test

    public void removeUser() {
         Assert.assertEquals(SQLGenerator.removeUser(3),"DELETE FROM `users` WHERE `users`.`ID` = "+3);
    }
    @Test

    public void changeUserType() {
         Assert.assertEquals(SQLGenerator.changeUserType(5,"Dean"),"Update users set Type= "+"Dean"+" where ID="+5);
    }
    @Test

    public void overdue() {
         Assert.assertEquals(SQLGenerator.overdue(),"SELECT * FROM `circulation` where (CURRENT_TIMESTAMP>due_date and checkin_date is null) or checkin_date>due_date");
    }
}

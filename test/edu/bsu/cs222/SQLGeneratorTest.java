package edu.bsu.cs222;

import org.junit.Assert;
import org.junit.Test;

public class SQLGeneratorTest {

    @Test
    public void checkout() {
        Assert.assertEquals(SQLGenerator.checkout(4,6),"update books set Available=0 where barcode_number='4'; INSERT INTO `circulation`(`user_id`, `book_id`, `due_date`) VALUES (6,'4',DATE_ADD(CURRENT_TIMESTAMP, INTERVAL 14 DAY));");
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
        Assert.assertEquals(SQLGenerator.addUser("username","password","type"),"Insert into Users (Username,Password, Type) values (username,password,type);");
    }

    @Test
    public void getPassword() {
        Assert.assertEquals(SQLGenerator.getPassword("resims"),"select Password from Users where Username='resims';");
    }
}
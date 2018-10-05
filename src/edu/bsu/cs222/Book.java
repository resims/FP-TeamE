package edu.bsu.cs222;

public class Book {
    public final String title;
    public final String author;
    public final String ISBN;
    public final String callnumber;
    public Book(String title, String author, String ISBN, String callnumber){
        this.title=title;
        this.author=author;
        this.ISBN=ISBN;
        this.callnumber=callnumber;
    }
}

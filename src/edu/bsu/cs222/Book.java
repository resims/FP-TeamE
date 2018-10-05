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
    public String getBook(String title, String author, String ISBN, String callnumber ){
        if (this.title.equals(title) || this.author.equals(author) || this.ISBN.equals(ISBN) || this.callnumber.equals(callnumber))
            return title+" "+author+" "+ISBN+" "+callnumber;
        return "Book not found";
    }
}

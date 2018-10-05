import edu.bsu.cs222.Book;
import org.junit.Assert;
import org.junit.Test;

public class tests {
    @Test
    public void TestBookCreation(){
        Book book1=new Book("Book1 Title", "Book1 Author", "Book1 ISBN", "Book1 Call#");
        Assert.assertEquals("Book1 Title",book1.title);
        Assert.assertEquals("Book1 Author",book1.author);
        Assert.assertEquals("Book1 ISBN",book1.ISBN);
        Assert.assertEquals("Book1 Call#",book1.callnumber);
    }
    public void GetBook(){
        Book book1=new Book("Book1 Title", "Book1 Author", "Book1 ISBN", "Book1 Call#");
        Assert.assertEquals("Book1 Title",book1.title);
        Assert.assertEquals("Book1 Author",book1.author);
        Assert.assertEquals("Book1 ISBN",book1.ISBN);
        Assert.assertEquals("Book1 Call#",book1.callnumber);
    }
}

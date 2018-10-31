package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI_Library extends Application {
    public Stage primaryStage;
    private String username;
    private String password;

    public void GUI(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {//throws Exception{
        primaryStage.setTitle("Library Title Screen");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setMinSize(800, 500);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color:  #800080;");

        Button add_remove_book = new Button("Add/Remove book");
        Button check_reservation_dues = new Button("check Reservations / due dates");
        Button view_overdue = new Button("view Overdue Books");

        Button check_in_check_out = new Button("Check In / Check Out");
        Button search_book = new Button("Search Book");

        Button close = new Button("Exit");
        grid.add(close,10,5);
        close.setOnAction(e ->{
            primaryStage.close();
        });

        /// ADD REMOVE BOOK
        grid.add(add_remove_book, 0, 4);  //position of button 0,0 being topmost left
        add_remove_book.setOnAction(e -> {
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Add Book");
            GridPane secondGrid = new GridPane();
            secondGrid.setPadding(new Insets(25, 25, 10, 25));
            secondGrid.setMinSize(300, 500);
            secondGrid.setVgap(5);
            secondGrid.setHgap(5);

            TextField add = new TextField("Book");
            secondGrid.add(add,1,2);

            Button add_book = new Button("Add Book");
            Button remove_book = new Button("Remove Book");
            Button back = new Button("previous page");

            Text complete = new Text("No Action performed");
            secondGrid.add(complete,3,3);

            secondGrid.add(add_book,1,1);
            add_book.setOnAction(e2 ->{

                //insert book add function here

                complete.setText("Book Added");
            });
            secondGrid.add(remove_book,2,1);
            remove_book.setOnAction(e2 ->{

                //insert book remover function here

                complete.setText("Book Removed");
            });
            secondGrid.add(back,3,7);
            back.setOnAction(e2->{
                secondaryStage.close();
            });
            Scene secondScene = new Scene(secondGrid, 600, 600);
            secondaryStage.setScene(secondScene);
            secondaryStage.show();
        });

        grid.add(check_reservation_dues, 2, 4);  //position of button 0,0 being topmost left
        check_reservation_dues.setOnAction(e -> {
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("");
            GridPane secondGrid = new GridPane();
            secondGrid.setPadding(new Insets(25, 25, 10, 25));
            secondGrid.setMinSize(300, 500);
            secondGrid.setVgap(5);
            secondGrid.setHgap(5);

            TextField user = new TextField("Username");
            secondGrid.add(user,1,2);

            Text complete = new Text("No Action performed");

            secondGrid.add(complete,3,3);

            Button back = new Button("previous page");

            Button check_reservations = new Button("Check Current Reservations");
            secondGrid.add(check_reservations,1,1);
            check_reservations.setOnAction(e2 ->{
                //insert reservations for user function here
                String books_reserved = "books reserved: -----";
                complete.setText(books_reserved);
            });
            Button due_dates = new Button("Check Current due dates");
            secondGrid.add(due_dates,2,1);
            due_dates.setOnAction(e2 ->{

                //insert due date functionality

                String books_due = "books:----- due at: ------";
                complete.setText(books_due);
            });

            secondGrid.add(back,3,7);
            back.setOnAction(e2->{
                secondaryStage.close();
            });

            Scene secondScene = new Scene(secondGrid, 600, 600);
            secondaryStage.setScene(secondScene);
            secondaryStage.show();
        });

        grid.add(view_overdue, 3, 4);  //position of button 0,0 being topmost left
        view_overdue.setOnAction(e -> {
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("");
            GridPane secondGrid = new GridPane();
            secondGrid.setPadding(new Insets(25, 25, 10, 25));
            secondGrid.setMinSize(300, 500);
            secondGrid.setVgap(5);
            secondGrid.setHgap(5);

            TextField add = new TextField("Username");
            secondGrid.add(add,1,2);

            Text complete = new Text("No Action performed");
            secondGrid.add(complete,3,3);

            Button back = new Button("previous page");

            Button check_reservations = new Button("Check Current overdues");
            secondGrid.add(check_reservations,1,1);
            check_reservations.setOnAction(e2 ->{

                //insert overdue check for user function here

                String books_reserved = "overdue status: -----";
                complete.setText(books_reserved);
            });

            secondGrid.add(back,3,7);
            back.setOnAction(e2->{
                secondaryStage.close();
            });

            Scene secondScene = new Scene(secondGrid, 600, 600);
            secondaryStage.setScene(secondScene);
            secondaryStage.show();
        });

        grid.add(check_in_check_out, 0, 5);  //position of button 0,0 being topmost left
        check_in_check_out.setOnAction(e -> {
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("");
            GridPane secondGrid = new GridPane();
            secondGrid.setPadding(new Insets(25, 25, 10, 25));
            secondGrid.setMinSize(300, 500);
            secondGrid.setVgap(5);
            secondGrid.setHgap(5);

            TextField add = new TextField("Book");
            secondGrid.add(add,1,2);

            Button check_in = new Button("Check In");
            Button check_out = new Button("Check Out");
            Button back = new Button("previous page");

            Text complete = new Text("No Action performed");
            secondGrid.add(complete,3,3);

            secondGrid.add(check_in,1,1);
            check_in.setOnAction(e2 ->{

                //insert book check in function here

                complete.setText("Book checked in");
            });
            secondGrid.add(check_out,2,1);
            check_out.setOnAction(e2 ->{

                //insert book check out function here

                complete.setText("Book checked out");
            });
            secondGrid.add(back,3,7);
            back.setOnAction(e2->{
                secondaryStage.close();
            });
            Scene secondScene = new Scene(secondGrid, 600, 600);
            secondaryStage.setScene(secondScene);
            secondaryStage.show();
        });

        grid.add(search_book, 2, 5);  //position of button 0,0 being topmost left
        search_book.setOnAction(e -> {
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("");
            GridPane secondGrid = new GridPane();
            secondGrid.setPadding(new Insets(25, 25, 10, 25));
            secondGrid.setMinSize(300, 500);
            secondGrid.setVgap(5);
            secondGrid.setHgap(5);

            TextField add = new TextField("Keywords");
            secondGrid.add(add,1,2);

            Text complete = new Text("No Action performed");
            secondGrid.add(complete,3,3);

            Button back = new Button("previous page");
            Button search = new Button("Search for book");
            secondGrid.add(search,1,1);
            search.setOnAction(e2 ->{

                //insert search function here

                String books_reserved = "books found: ----";
                complete.setText(books_reserved);
            });

            secondGrid.add(back,3,7);
            back.setOnAction(e2->{
                secondaryStage.close();
            });

            Scene secondScene = new Scene(secondGrid, 600, 600);
            secondaryStage.setScene(secondScene);
            secondaryStage.show();
        });

        Text time = new Text("Time");               //should display time
        grid.add(time, 0, 0);

        Text date = new Text("Date");               //should display date
        grid.add(date, 2, 0);

        Scene scene = new Scene(grid, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
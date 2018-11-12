package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class GUI_Library extends Application {

    Scene login_scene;

    @Override
    public void start(Stage PrimaryStage) {//throws Exception{
        PrimaryStage.setTitle("Library Database");

        //scene Patron

        GridPane Patron_grid = new GridPane();
        Patron_grid.setPadding(new Insets(10, 10, 10, 10));
        Patron_grid.setMinSize(800, 500);
        Patron_grid.setVgap(5);
        Patron_grid.setHgap(5);
        Patron_grid.setStyle("-fx-background-color:  #800080;");

        Button search = new Button("Search Books");
        Button reserve = new Button("Reserve books");
        Button due_dates = new Button("Check current due dates");

        search.setPrefSize(200,25);
        reserve.setPrefSize(200,25);
        due_dates.setPrefSize(200,25);

        Patron_grid.add(search,1,1);
        Patron_grid.add(reserve,1,2);
        Patron_grid.add(due_dates,1,3);

        Scene patron_scene = new Scene(Patron_grid,300,250);

        //scene Librarian

        Button Librarian_search = new Button("Search Books");
        Button Librarian_reserve = new Button("Reserve books");
        Button Linrarian_due_dates = new Button("Check current due dates");
        Button check_in = new Button("Check in");
        Button check_out = new Button("Check out");

        VBox Librarian_layout = new VBox(20);
        Librarian_layout.getChildren().addAll(Librarian_search,Librarian_reserve,Linrarian_due_dates,check_in,check_out);
        Scene Librarian_scene = new Scene(Librarian_layout,300,250);

        //scene Admin

        Button Admin_search = new Button("Search Books");
        Button Admin_reserve = new Button("Reserve books");
        Button Admin_due_dates = new Button("Check current due dates");

        Button Admin_check_in = new Button("Check in");
        Button Admin_check_out = new Button("Check out");

        Button Admin_Functions = new Button("Admin Functions");

        VBox Admin_layout = new VBox(20);
        Admin_layout.getChildren().addAll(Admin_search,Admin_reserve,Admin_due_dates,Admin_check_in,Admin_check_out,Admin_Functions);
        Scene Admin_scene = new Scene(Admin_layout,300,250);

        //scene Login

        GridPane Login_grid = new GridPane();
        Login_grid.setPadding(new Insets(10, 10, 10, 10));
        Login_grid.setMinSize(800, 500);
        Login_grid.setVgap(5);
        Login_grid.setHgap(5);
        Login_grid.setStyle("-fx-background-color:  #800080;");

        Text username = new Text("Username:");
        username.setFont(Font.font(30));
        Text password = new Text("Password:");
        password.setFont(Font.font(30));

        TextField user = new TextField("Username");
        user.setPrefSize(200,25);
        TextField pass = new TextField("Password");
        pass.setPrefSize(200,25);


        Button Login = new Button("Login");
        Login.setPrefSize(200,25);
        Login.setOnAction(e ->{PrimaryStage.setScene(patron_scene);});

        Button SignUp = new Button("Sign up");
        SignUp.setPrefSize(200,25);
        SignUp.setOnAction(e ->{PrimaryStage.setScene(Librarian_scene);});

        Button close = new Button("close");
        close.setPrefSize(200,25);
        close.setOnAction(e ->{PrimaryStage.setScene(Admin_scene);});

        Login_grid.add(username,1,1);
        Login_grid.add(password,1,2);
        Login_grid.add(Login,2,1);
        Login_grid.add(SignUp,2,2);
        Login_grid.add(close,10,10);


        Scene login_scene = new Scene(Login_grid,800,500);

        //---------------------------------------------------------------------------//

        PrimaryStage.setScene(login_scene);
        PrimaryStage.show();
    }

    public static void main(String[] args){launch(args);}

        /*
        LoginStage.setTitle("Library Database Login");
        GridPane Login_Grid = new GridPane();
        Login_Grid.setPadding(new Insets(10, 10, 10, 10));
        Login_Grid.setMinSize(800, 500);
        Login_Grid.setVgap(5);
        Login_Grid.setHgap(5);
        Login_Grid.setStyle("-fx-background-color:  #800080;");

        PatronStage.setTitle("Library Patron Home Screen");
        GridPane Patron_Grid = new GridPane();
        Patron_Grid.setPadding(new Insets(10, 10, 10, 10));
        Patron_Grid.setMinSize(800, 500);
        Patron_Grid.setVgap(5);
        Patron_Grid.setHgap(5);
        Patron_Grid.setStyle("-fx-background-color:  #800080;");

        LibrarianStage.setTitle("Librarian Home Screen");
        GridPane Librarian_Grid = new GridPane();
        Librarian_Grid.setPadding(new Insets(10, 10, 10, 10));
        Librarian_Grid.setMinSize(800, 500);
        Librarian_Grid.setVgap(5);
        Librarian_Grid.setHgap(5);
        Librarian_Grid.setStyle("-fx-background-color:  #800080;");

        AdminStage.setTitle("Admin Home Screen");
        GridPane Admin_Grid = new GridPane();
        Admin_Grid.setPadding(new Insets(10, 10, 10, 10));
        Admin_Grid.setMinSize(800, 500);
        Admin_Grid.setVgap(5);
        Admin_Grid.setHgap(5);
        Admin_Grid.setStyle("-fx-background-color:  #800080;");
    */

        //-------------------------------------------------------//

        //Login grid creation

        //PasswordHash UserInfo = new PasswordHash();  //needed to instantiate PasswordHash to reference
/*
        Text username = new Text("Username:");
        Login_Grid.add(username,1,2);
        Text password = new Text("Password:");
        Login_Grid.add(password,1,3);


        TextField user = new TextField("Username");
        Login_Grid.add(user, 2, 2);
        TextField Pass = new TextField("Password");
        Login_Grid.add(Pass, 2, 3);

        Button Login = new Button("Login");

        Login.setOnAction(e ->{
            //Boolean check = UserInfo.userLogin(user.getText(),Pass.getText());
            Boolean check = true;
            String type = "";   //testing purposes for lines 85 and 86
            if (check){
                //String type = SQLUserProcessor.getUserType(user.getText());
                if (type.toUpperCase() == "PATRON"){
                    PatronStage.show();
                    LoginStage.close();
                }
                if (type.toUpperCase() == "LIBRARIAN"){
                    LibrarianStage.show();
                    LoginStage.close();
                }
                if (type.toUpperCase() == "ADMIN"){
                    AdminStage.show();
                    LoginStage.close();
                }
            }
            else{
                Popup flag = new Popup();
                flag.setX(300);
                flag.setY(200);
                flag.getContent().addAll(new Circle(25, 25, 50, Color.AQUAMARINE));
                Stage PopupStage = new Stage();
                TextField Popup = new TextField("Invalid Credentials, please try again.");
                Button close = new Button();
                close.setOnAction(e2 ->{
                    PopupStage.close();
                });
                GridPane Popup_Grid = new GridPane();
                Popup_Grid.add(Popup,1,1);
                Popup_Grid.add(close,1,2);
                Scene Popup_Scene = new Scene(Popup_Grid,400,300);
                PopupStage.setScene(Popup_Scene);
                PopupStage.show();

            }


        });

        Button SignUp = new Button("Sign up");

        SignUp.setOnAction(e ->{
            //Boolean Authenticate = UserInfo.userSignup(user.getText(),Pass.getText(),"Patron");

        });

        Button close = new Button("close");

        Login_Grid.add(Login,3,2);
        Login_Grid.add(SignUp,3,3);
        Login_Grid.add(close,7,7);
        close.setOnAction(e ->{
            LoginStage.close();
        });

*/
        //End of Login Grid

        //--------------------------------------------------------------//

        //Scene scene = new Scene(grid, 600, 500);
//        PrimaryStage.setScene(login_scene);
//        PrimaryStage.show();
//    }
/*
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
    }*/
}

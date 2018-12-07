package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GUI_Library extends Application {

    private PasswordHash UserInfo = new PasswordHash();
    private Stage SecondaryStage = new Stage();
    private Scene login_scene;

    @Override
    public void start(Stage PrimaryStage) {//throws Exception{
        PrimaryStage.setTitle("Library Database");

        // Search book Scene --------------------------------------------------------------------------------

        GridPane search_grid = new GridPane();
        search_grid.setPadding(new Insets(10, 10, 10, 10));
        search_grid.setMinSize(800, 500);
        search_grid.setVgap(5);
        search_grid.setHgap(5);
        search_grid.setStyle("-fx-background-color:  #800080;");

        String title = "Title";
        String barcode = "barcode_number";
        String call_number = "Call_number";
        String isbn = "ISBN";
        String source_type = "Type";
        String author = "Author";
        String contains = "Contains";
        String starts_with = "Starts with";
        String ends_with = "Ends with";

        TextField term = new TextField();
        term.setPrefSize(100,25);
        search_grid.add(term,3,2);

        Button cancel = new Button("Cancel");
        cancel.setOnAction(e ->{PrimaryStage.setScene(this.SecondaryStage.getScene());});
        search_grid.add(cancel,6,2);

        ChoiceBox<String> search_type = new ChoiceBox<String>();
        search_type.setTooltip(new Tooltip("Search terms type"));
        search_type.getItems().addAll(title,barcode,source_type,author,call_number,isbn);

        VBox root = new VBox(search_type);
        search_grid.add(root,1,2);

        ChoiceBox<String> search_method = new ChoiceBox<String>();
        search_method.setTooltip(new Tooltip("Set method of search"));
        search_method.getItems().addAll(contains,starts_with,ends_with);

        VBox root_2 = new VBox(search_method);
        search_grid.add(root_2,2,2);

        Button search_books = new Button("Search");
        search_books.setOnAction(e ->{
            String text_string = SQLProcessor.parseasString(SQLBookProcessor.search(search_type.getSelectionModel().getSelectedItem(),term.getText()));
            TextArea results = new TextArea();
            results.setWrapText(true);
            results.setText(text_string);
            search_grid.add(results,1,4,100,100);
        });
        search_grid.add(search_books,5,2);

        Scene search_scene = new Scene(search_grid, 960, 600);

        // Librarian search scene ----------------------------------------------------------------------------------

        GridPane Librarian_search_grid = new GridPane();
        Librarian_search_grid.setPadding(new Insets(10, 10, 10, 10));
        Librarian_search_grid.setMinSize(800, 500);
        Librarian_search_grid.setVgap(5);
        Librarian_search_grid.setHgap(5);
        Librarian_search_grid.setStyle("-fx-background-color:  #800080;");

        String title2 = "Title";
        String barcode2 = "barcode_number";
        String call_number2 = "Call_number";
        String isbn2 = "ISBN";
        String source_type2 = "Type";
        String author2 = "Author";
        String contains2 = "Contains";
        String starts_with2 = "Starts with";
        String ends_with2 = "Ends with";

        TextField term2 = new TextField();
        term2.setPrefSize(100,25);
        Librarian_search_grid.add(term2,3,2);

        Button cancel_2 = new Button("Cancel");
        cancel_2.setOnAction(e ->{PrimaryStage.setScene(this.SecondaryStage.getScene());});
        Librarian_search_grid.add(cancel_2,6,2);

        ChoiceBox<String> search_type2 = new ChoiceBox<>();
        search_type2.setTooltip(new Tooltip("Search terms type"));
        search_type2.getItems().addAll(title2,barcode2,source_type2,author2,call_number2,isbn2);

        VBox root_3 = new VBox(search_type2);
        Librarian_search_grid.add(root_3,1,2);

        ChoiceBox<String> search_method2 = new ChoiceBox<String>();
        search_method2.setTooltip(new Tooltip("Set method of search"));
        search_method2.getItems().addAll(contains2,starts_with2,ends_with2);

        VBox root_4 = new VBox(search_method2);
        Librarian_search_grid.add(root_4,2,2);

        Button Librarian_search_books = new Button("Search");
        Librarian_search_books.setOnAction(e ->{
            String text_string = SQLProcessor.parseasString(SQLBookProcessor.search(search_type2.getSelectionModel().getSelectedItem(),term2.getText()));
            System.out.print("text string="+text_string);
            TextArea results = new TextArea();
            results.setWrapText(true);
            results.setText(text_string);
            Librarian_search_grid.add(results,1,4,100,100);
        });
        Librarian_search_grid.add(Librarian_search_books,5,2);

        Scene Librarian_search_scene = new Scene(Librarian_search_grid, 960, 600);

        // Librarian check in/ check out Scene -------------------------------------------------------------------------------

        GridPane Librarian_check_in_grid = new GridPane();
        Librarian_check_in_grid.setPadding(new Insets(10, 10, 10, 10));
        Librarian_check_in_grid.setMinSize(800, 500);
        Librarian_check_in_grid.setVgap(5);
        Librarian_check_in_grid.setHgap(5);
        Librarian_check_in_grid.setStyle("-fx-background-color:  #800080;");

        GridPane Librarian_check_out_grid = new GridPane();
        Librarian_check_out_grid.setPadding(new Insets(10, 10, 10, 10));
        Librarian_check_out_grid.setMinSize(800, 500);
        Librarian_check_out_grid.setVgap(5);
        Librarian_check_out_grid.setHgap(5);
        Librarian_check_out_grid.setStyle("-fx-background-color:  #800080;");

        TextField book_to_be_checked_in = new TextField("Barcode of book");
        Librarian_check_in_grid.add(book_to_be_checked_in,1,1);


        Button cancel_3 = new Button("Cancel");
        cancel_3.setOnAction(e ->{PrimaryStage.setScene(this.SecondaryStage.getScene());});
        Librarian_check_in_grid.add(cancel_3,6,4);

        Button cancel_4 = new Button("Cancel");
        cancel_4.setOnAction(e ->{PrimaryStage.setScene(this.SecondaryStage.getScene());});
        Librarian_check_out_grid.add(cancel_4,6,2);

        TextField book_to_be_checked_out = new TextField("Barcode of book");
        Librarian_check_out_grid.add(book_to_be_checked_out,1,1);

        TextField user_id_checking_out = new TextField("User ID");
        Librarian_check_out_grid.add(user_id_checking_out,2,1);

        Button Librarian_check_in = new Button("Check In");
        Librarian_check_in.setOnAction(e ->{
            boolean check =SQLBookProcessor.checkin(Integer.parseInt(book_to_be_checked_in.getText()));
            if (check){
                Stage popup_Stage = new Stage();
                popup_Stage.setTitle("Pop-up");

                GridPane popup_grid = new GridPane();

                Text success = new Text("check in Successful!");
                success.setFont(Font.font(18));
                popup_grid.add(success, 1, 1);
                GridPane.setHalignment(success, HPos.CENTER);

                Button close_warn = new Button("Close");
                close_warn.setOnAction(e2 -> {
                    popup_Stage.close();
                });
                popup_grid.add(close_warn, 1, 3);
                GridPane.setHalignment(close_warn, HPos.CENTER);

                Scene popup_Scene = new Scene(popup_grid, 300, 100);
                popup_Stage.setScene(popup_Scene);
                popup_Stage.show();
            }else{
                Stage popup_Stage = new Stage();
                popup_Stage.setTitle("Warning");

                GridPane popup_grid = new GridPane();

                Text warning = new Text("Unable to check in.");
                warning.setFont(Font.font(18));
                popup_grid.add(warning, 1, 1);
                GridPane.setHalignment(warning, HPos.CENTER);


                Text warning_2 = new Text("Please check barcode");
                warning_2.setFont(Font.font(14));
                popup_grid.add(warning_2, 1, 2);
                GridPane.setHalignment(warning_2, HPos.CENTER);

                Button close_popup = new Button("Close");
                close_popup.setOnAction(e2 -> {
                    popup_Stage.close();
                });
                popup_grid.add(close_popup, 1, 3);
                GridPane.setHalignment(close_popup, HPos.CENTER);

                Scene Popup_Scene = new Scene(popup_grid, 300, 100);
                popup_Stage.setScene(Popup_Scene);
                popup_Stage.show();
            }
        });

        Librarian_check_in_grid.add(Librarian_check_in,2,1);
        Scene Librarian_check_in_scene = new Scene(Librarian_check_in_grid, 960, 600);

        Button Librarian_check_out = new Button("Check out");
        Librarian_check_out.setOnAction(e ->{
            boolean check =SQLBookProcessor.checkout(Integer.parseInt(book_to_be_checked_out.getText()),1);//no way to check user ID yet, will see too it at a later time
            if (check){
                Stage popup_Stage = new Stage();
                popup_Stage.setTitle("Pop-up");

                GridPane popup_grid = new GridPane();

                Text success = new Text("check out Successful!");
                success.setFont(Font.font(18));
                popup_grid.add(success, 1, 1);
                GridPane.setHalignment(success, HPos.CENTER);

                Button close_warn = new Button("Close");
                close_warn.setOnAction(e2 -> {
                    popup_Stage.close();
                });
                popup_grid.add(close_warn, 1, 3);
                GridPane.setHalignment(close_warn, HPos.CENTER);

                Scene popup_Scene = new Scene(popup_grid, 300, 100);
                popup_Stage.setScene(popup_Scene);
                popup_Stage.show();
            }else{
                Stage popup_Stage = new Stage();
                popup_Stage.setTitle("Warning");

                GridPane popup_grid = new GridPane();

                Text warning = new Text("Unable to check out.");
                warning.setFont(Font.font(18));
                popup_grid.add(warning, 1, 1);
                GridPane.setHalignment(warning, HPos.CENTER);


                Text warning_2 = new Text("Please check barcode");
                warning_2.setFont(Font.font(14));
                popup_grid.add(warning_2, 1, 2);
                GridPane.setHalignment(warning_2, HPos.CENTER);

                Button close_popup = new Button("Close");
                close_popup.setOnAction(e2 -> {
                    popup_Stage.close();
                });
                popup_grid.add(close_popup, 1, 3);
                GridPane.setHalignment(close_popup, HPos.CENTER);

                Scene Popup_Scene = new Scene(popup_grid, 300, 100);
                popup_Stage.setScene(Popup_Scene);
                popup_Stage.show();
            }
        });
        Librarian_check_out_grid.add(Librarian_check_out,3,1);
        Scene Librarian_check_out_scene = new Scene(Librarian_check_out_grid, 960, 600);

        //Patron scene ---------------------------------------------------------------------------------------------

        GridPane Patron_grid = new GridPane();
        Patron_grid.setPadding(new Insets(10, 10, 10, 10));
        Patron_grid.setMinSize(800, 500);
        Patron_grid.setVgap(50);
        Patron_grid.setHgap(5);
        Patron_grid.setStyle("-fx-background-color:  #800080;");

        Button search = new Button("Search Books");
        search.setOnAction(e ->{
            this.SecondaryStage.setScene(PrimaryStage.getScene());
            PrimaryStage.setScene(search_scene);
        });
        search.setPrefSize(400, 40);
        Patron_grid.add(search, 1, 1,2,1);
        Patron_grid.setHalignment(search,HPos.CENTER);

        Button reserve = new Button("Reserve books");
        reserve.setPrefSize(400, 40);
        Patron_grid.add(reserve, 1, 2,2,1);
        Patron_grid.setHalignment(reserve,HPos.CENTER);


        Button due_dates = new Button("Check current due dates");
        due_dates.setPrefSize(400, 40);
        Patron_grid.add(due_dates, 1, 3,2,1);
        Patron_grid.setHalignment(due_dates,HPos.CENTER);

        Patron_grid.setAlignment(Pos.CENTER);

        Button sign_out = new Button("Sign Out");
        sign_out.setOnAction(e ->{
           PrimaryStage.setScene(this.login_scene);
        });
        sign_out.setPrefSize(100,20);
        Patron_grid.add(sign_out,3,5);
        Patron_grid.setHalignment(sign_out,HPos.LEFT);


        Button close = new Button("Exit");
        close.setOnAction(e ->{PrimaryStage.close();});
        close.setPrefSize(100,20);
        Patron_grid.add(close,0,5);
        Patron_grid.setHalignment(close,HPos.RIGHT);

        Text time = new Text();
        time.setText("Eventaully ill add this time in");
        Patron_grid.add(time,0,0);

        Text date = new Text();
        date.setText("Eventaully ill add this time in");
        Patron_grid.add(date,1,0);

        Scene patron_scene = new Scene(Patron_grid, 800, 500);

        //scene Librarian -----------------------------------------------------------------------------------

        GridPane Librarian_grid = new GridPane();
        Librarian_grid.setPadding(new Insets(10, 10, 10, 10));
        Librarian_grid.setMinSize(800, 500);
        Librarian_grid.setVgap(50);
        Librarian_grid.setHgap(5);
        Librarian_grid.setStyle("-fx-background-color:  #800080;");

        Button Librarian_search = new Button("Search Books");
        Librarian_search.setOnAction(e ->{
            this.SecondaryStage.setScene(PrimaryStage.getScene());
            PrimaryStage.setScene(Librarian_search_scene);
        });
        Librarian_search.setPrefSize(250, 30);
        Librarian_grid.add(Librarian_search, 1, 1);

        Button Librarian_reserve = new Button("Reserve books");
        Librarian_reserve.setPrefSize(250, 30);
        Librarian_grid.add(Librarian_reserve, 1, 2);

        Button Librarian_due_dates = new Button("Check current due dates");
        Librarian_due_dates.setPrefSize(250, 30);
        Librarian_grid.add(Librarian_due_dates, 1, 3);

        Button check_in = new Button("Check in");
        check_in.setOnAction(e ->{
            this.SecondaryStage.setScene(PrimaryStage.getScene());
            PrimaryStage.setScene(Librarian_check_in_scene);
        });
        check_in.setPrefSize(250,30);
        Librarian_grid.add(check_in,2,1);

        Button check_out = new Button("Check out");
        check_out.setOnAction(e ->{
            this.SecondaryStage.setScene(PrimaryStage.getScene());
            PrimaryStage.setScene(Librarian_check_out_scene);
        });
        check_out.setPrefSize(250,30);
        Librarian_grid.add(check_out,2,2);

        Scene Librarian_scene = new Scene(Librarian_grid, 800, 500);

        //scene Admin -------------------------------------------------------------------------------

        Button Admin_search = new Button("Search Books");
        Button Admin_reserve = new Button("Reserve books");
        Button Admin_due_dates = new Button("Check current due dates");

        Button Admin_check_in = new Button("Check in");
        Button Admin_check_out = new Button("Check out");

        Button Admin_Functions = new Button("Admin Functions");

        VBox Admin_layout = new VBox(20);
        Admin_layout.getChildren().addAll(Admin_search, Admin_reserve, Admin_due_dates, Admin_check_in, Admin_check_out, Admin_Functions);
        Scene Admin_scene = new Scene(Admin_layout, 800, 500);

        //scene Login ----------------------------------------------------------------------------------------

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
        user.setPrefSize(200, 25);
        TextField pass = new TextField("Password");
        pass.setPrefSize(200, 25);


        Button Login = new Button("Login");
        Login.setPrefSize(200, 25);
        Login.setOnAction(e -> {
            Boolean check = UserInfo.userLogin(user.getText(), pass.getText());
            if (check) {
                String type = SQLUserProcessor.getUserType(user.getText());
                if (type.equals("Patron")) {
                    PrimaryStage.setScene(patron_scene);
                }
                if (type.equals("Librarian")){
                    PrimaryStage.setScene(Librarian_scene);
                }
                if (type.equals("Dean")){
                    PrimaryStage.setScene(Admin_scene);
                }
            } else {

                Stage popup_Stage = new Stage();
                popup_Stage.setTitle("Warning");

                GridPane popup_grid = new GridPane();

                Text warning = new Text("Invalid Credentials, please try again.");
                warning.setFont(Font.font(18));
                popup_grid.add(warning, 1, 1);

                Button close_warn = new Button("Close");
                close_warn.setOnAction(e2 -> {
                    popup_Stage.close();
                });
                popup_grid.add(close_warn, 1, 3);
                GridPane.setHalignment(close_warn, HPos.CENTER);

                Scene Popup_Scene = new Scene(popup_grid, 300, 100);
                popup_Stage.setScene(Popup_Scene);
                popup_Stage.show();
            }
        });

        Button SignUp = new Button("Sign up");
        SignUp.setPrefSize(200, 25);
        SignUp.setOnAction(e -> {

            boolean check = UserInfo.userSignup(user.getText(), pass.getText(), "Patron");

            if (check) {
                Stage popup_Stage = new Stage();
                popup_Stage.setTitle("Pop-up");

                GridPane popup_grid = new GridPane();

                Text success = new Text("Sign up Successful!");
                success.setFont(Font.font(18));
                popup_grid.add(success, 1, 1);
                GridPane.setHalignment(success, HPos.CENTER);

                Text success_2 = new Text("Please close this window and log in to continue");
                success_2.setFont(Font.font(14));
                popup_grid.add(success_2, 1, 2);
                GridPane.setHalignment(success_2, HPos.CENTER);

                Button close_warn = new Button("Close");
                close_warn.setOnAction(e2 -> {
                    popup_Stage.close();
                });
                popup_grid.add(close_warn, 1, 3);
                GridPane.setHalignment(close_warn, HPos.CENTER);

                Scene popup_Scene = new Scene(popup_grid, 300, 100);
                popup_Stage.setScene(popup_Scene);
                popup_Stage.show();
            } else {
                Stage popup_Stage = new Stage();
                popup_Stage.setTitle("Warning");

                GridPane popup_grid = new GridPane();

                Text warning = new Text("Unable to Sign Up.");
                warning.setFont(Font.font(18));
                popup_grid.add(warning, 1, 1);
                GridPane.setHalignment(warning, HPos.CENTER);


                Text warning_2 = new Text("Please check spelling and character types used");
                warning_2.setFont(Font.font(14));
                popup_grid.add(warning_2, 1, 2);
                GridPane.setHalignment(warning_2, HPos.CENTER);

                Button close_popup = new Button("Close");
                close_popup.setOnAction(e2 -> {
                    popup_Stage.close();
                });
                popup_grid.add(close_popup, 1, 3);
                GridPane.setHalignment(close_popup, HPos.CENTER);

                Scene Popup_Scene = new Scene(popup_grid, 300, 100);
                popup_Stage.setScene(Popup_Scene);
                popup_Stage.show();
            }


        });

        Button close_login = new Button("close");
        close_login.setPrefSize(200, 25);
        close_login.setOnAction(e -> {
            PrimaryStage.close();
        });

        Login_grid.add(username, 1, 1);
        Login_grid.add(password, 1, 2);
        Login_grid.add(user, 2, 1);
        Login_grid.add(pass, 2, 2);
        Login_grid.add(Login, 3, 1);
        Login_grid.add(SignUp, 3, 2);
        Login_grid.add(close_login, 10, 15);
        GridPane.setValignment(close_login, VPos.BOTTOM);

        Scene login_scene = new Scene(Login_grid, 800, 500);
        //this.login_scene=login_scene;
        //---------------------------------------------------------------------------//
        this.login_scene = login_scene;
        PrimaryStage.setScene(login_scene);
        PrimaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package edu.bsu.cs222;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static edu.bsu.cs222.PasswordHash.*;

public class real_GUI extends Application {

    private Stage SecondaryStage = new Stage();
    //username storage updates on signin sign out

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("Library Database");

        GridPane grid = new GridPane();     //grid creation

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setMinSize(800, 500);
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setStyle("-fx-background-color:  #800080;");

        //Login stuffs ------------------

        Text username = new Text("Username:");      //visible texts
        Text password = new Text("Password:");

        TextField input_username = new TextField("Username");     //Typeable text
        PasswordField input_password = new PasswordField();


        Button login = new Button("Login");   //Login buttons
        Button SignUp = new Button("Sign up");

        Button close = new Button("close"); //close primaryStage
        close.setOnAction(e -> primaryStage.close());

        Button search = new Button("Search Books");
        search.setVisible(false);
        Button reserve = new Button("Reserve books");
        reserve.setVisible(false);
        Button due_dates = new Button("Check current due dates");
        due_dates.setVisible(false);
        Button sign_out = new Button("Sign Out");

        sign_out.setVisible(false);
        Text time = new Text("Time");
        time.setVisible(false);
        Text date = new Text("Date");
        date.setVisible(false);


        Button check_in = new Button("Check in");
        check_in.setVisible(false);
        Button check_out = new Button("Check out");
        check_out.setVisible(false);

        Button admin_Functions = new Button("Admin Functions");
        admin_Functions.setVisible(false);

        login.setPrefSize(200, 25);
        login.setOnAction(e -> {
            boolean check = userLogin(input_username.getText(), input_password.getText());

            if (check) {
                String type = SQLUserProcessor.getUserType(input_username.getText());
                username.setVisible(false);
                password.setVisible(false);
                input_username.setVisible(false);
                input_password.setVisible(false);
                login.setVisible(false);
                SignUp.setVisible(false);

                search.setVisible(true);
                reserve.setVisible(true);
                due_dates.setVisible(true);
                sign_out.setVisible(true);
                time.setVisible(true);
                date.setVisible(true);

                if (type.equals("Patron")) {
                    search.setPrefSize(400, 40);
                    GridPane.setHalignment(search,HPos.CENTER);
                    reserve.setPrefSize(400, 40);
                    GridPane.setHalignment(reserve,HPos.CENTER);
                    due_dates.setPrefSize(400, 40);
                    GridPane.setHalignment(due_dates,HPos.CENTER);

                }
                if (type.equals("Librarian")) {
                    search.setPrefSize(400, 40);
                    GridPane.setHalignment(search,HPos.LEFT);
                    reserve.setPrefSize(400, 40);
                    GridPane.setHalignment(reserve,HPos.LEFT);
                    due_dates.setPrefSize(400, 40);
                    GridPane.setHalignment(due_dates,HPos.LEFT);
                    check_in.setVisible(true);
                    check_in.setPrefSize(400, 40);
                    GridPane.setHalignment(check_in,HPos.RIGHT);
                    check_out.setVisible(true);
                    check_out.setPrefSize(400, 40);
                    GridPane.setHalignment(check_out,HPos.RIGHT);
                    GridPane.setHalignment(sign_out,HPos.RIGHT);
                }
                if (type.equals("Dean")) {
                    GridPane.setHalignment(search,HPos.LEFT);
                    GridPane.setHalignment(reserve,HPos.LEFT);
                    GridPane.setHalignment(due_dates,HPos.LEFT);
                    GridPane.setHalignment(check_in,HPos.RIGHT);
                    GridPane.setHalignment(check_out,HPos.RIGHT);
                    admin_Functions.setVisible(true);
                    admin_Functions.setPrefSize(400, 40);
                    GridPane.setHalignment(admin_Functions,HPos.CENTER);
                    GridPane.setHalignment(sign_out,HPos.RIGHT);
                }
            }else{

                Stage popup_Stage = new Stage();
                popup_Stage.setTitle("Warning");

                GridPane popup_grid = new GridPane();

                Text warning = new Text("Invalid Credentials, please try again.");
                warning.setFont(Font.font(18));
                popup_grid.add(warning, 1, 1);

                Button close_warn = new Button("Close");
                close_warn.setOnAction(e2 -> popup_Stage.close());
                popup_grid.add(close_warn, 1, 3);
                GridPane.setHalignment(close_warn, HPos.CENTER);

                Scene Popup_Scene = new Scene(popup_grid, 300, 100);
                popup_Stage.setScene(Popup_Scene);
                popup_Stage.show();
            }
        });

        SignUp.setPrefSize(200, 25);
        SignUp.setOnAction(e -> {

            boolean check = userSignup(input_username.getText(), input_password.getText(), "Patron");

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
                close_warn.setOnAction(e2 -> popup_Stage.close());
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
                close_popup.setOnAction(e2 -> popup_Stage.close());
                popup_grid.add(close_popup, 1, 3);
                GridPane.setHalignment(close_popup, HPos.CENTER);

                Scene Popup_Scene = new Scene(popup_grid, 300, 100);
                popup_Stage.setScene(Popup_Scene);
                popup_Stage.show();
            }


        });

        search.setOnAction(e ->{
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

            TextField term = new TextField("Term");
            term.setPrefSize(100,25);
            search_grid.add(term,3,2);

            Button cancel = new Button("Cancel");
            cancel.setOnAction(e2 -> SecondaryStage.close());
            search_grid.add(cancel,6,2);

            ChoiceBox<String> search_type = new ChoiceBox<>();
            search_type.isShowing();
            search_type.setValue("Title");
            search_type.setTooltip(new Tooltip("Search terms type"));
            search_type.getItems().addAll(title,barcode,source_type,author,call_number,isbn);

            VBox root = new VBox(search_type);
            search_grid.add(root,1,2);

            ChoiceBox<String> search_method = new ChoiceBox<>();
            search_method.isShowing();
            search_method.setValue("Contains");
            search_method.setTooltip(new Tooltip("Set method of search"));
            search_method.getItems().addAll(ends_with,contains,starts_with);

            VBox root_2 = new VBox(search_method);
            search_grid.add(root_2,2,2);

            Button search_books = new Button("Search");
            search_books.setOnAction(e2 ->{
                String text_string = SQLProcessor.parseasString(SQLBookProcessor.advancedSearch(search_type.getSelectionModel().getSelectedItem(),term.getText(),search_method.getSelectionModel().getSelectedIndex()));
                TextArea results = new TextArea();
                results.setEditable(false);
                results.setWrapText(true);
                results.setText(text_string);
                search_grid.add(results,1,4,100,100);
            });
            search_grid.add(search_books,5,2);

            Scene search_scene = new Scene(search_grid, 960, 600);
            SecondaryStage.setScene(search_scene);
            SecondaryStage.show();
        });

        check_in.setOnAction(e ->{

            GridPane check_in_grid = new GridPane();
            check_in_grid.setPadding(new Insets(10, 10, 10, 10));
            check_in_grid.setMinSize(800, 500);
            check_in_grid.setVgap(5);
            check_in_grid.setHgap(5);
            check_in_grid.setStyle("-fx-background-color:  #800080;");

            TextField book_to_be_checked_in = new TextField("Barcode of book");
            check_in_grid.add(book_to_be_checked_in,1,1);

            Button cancel = new Button("Cancel");
            cancel.setOnAction(e2 -> SecondaryStage.close());
            check_in_grid.add(cancel,6,4);

            Button inner_check_in = new Button("Check In");
            inner_check_in.setOnAction(e2 ->{
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
                    close_warn.setOnAction(e3 -> popup_Stage.close());
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
                    close_popup.setOnAction(e3 -> popup_Stage.close());
                    popup_grid.add(close_popup, 1, 3);
                    GridPane.setHalignment(close_popup, HPos.CENTER);

                    Scene Popup_Scene = new Scene(popup_grid, 300, 100);
                    popup_Stage.setScene(Popup_Scene);
                    popup_Stage.show();
                }
            });

            check_in_grid.add(inner_check_in,2,1);
            Scene check_in_scene = new Scene(check_in_grid, 960, 600);
            SecondaryStage.setScene(check_in_scene);
            SecondaryStage.show();
        });

        check_out.setOnAction(e ->{
            GridPane check_out_grid = new GridPane();
            check_out_grid.setPadding(new Insets(10, 10, 10, 10));
            check_out_grid.setMinSize(800, 500);
            check_out_grid.setVgap(5);
            check_out_grid.setHgap(5);
            check_out_grid.setStyle("-fx-background-color:  #800080;");

            TextField book_to_be_checked_out = new TextField("Barcode of book");
            check_out_grid.add(book_to_be_checked_out,1,1);

            Button cancel = new Button("Cancel");
            cancel.setOnAction(e2 -> SecondaryStage.close());
            check_out_grid.add(cancel,6,2);

            TextField user_id_checking_out = new TextField("User ID");
            check_out_grid.add(user_id_checking_out,2,1);

            Button inner_check_out = new Button("Check out");
            inner_check_out.setOnAction(e2 ->{
                boolean check =SQLBookProcessor.checkout(Integer.parseInt(book_to_be_checked_out.getText()),Integer.parseInt(user_id_checking_out.getText()));//no way to check user ID yet, will see too it at a later time
                if (check){
                    Stage popup_Stage = new Stage();
                    popup_Stage.setTitle("Pop-up");

                    GridPane popup_grid = new GridPane();

                    Text success = new Text("check out Successful!");
                    success.setFont(Font.font(18));
                    popup_grid.add(success, 1, 1);
                    GridPane.setHalignment(success, HPos.CENTER);

                    Button close_warn = new Button("Close");
                    close_warn.setOnAction(e3 -> popup_Stage.close());
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
                    close_popup.setOnAction(e3 -> popup_Stage.close());
                    popup_grid.add(close_popup, 1, 3);
                    GridPane.setHalignment(close_popup, HPos.CENTER);

                    Scene Popup_Scene = new Scene(popup_grid, 300, 100);
                    popup_Stage.setScene(Popup_Scene);
                    popup_Stage.show();
                }
            });

            check_out_grid.add(inner_check_out,3,1);
            Scene check_out_scene = new Scene(check_out_grid, 960, 600);
            SecondaryStage.setScene(check_out_scene);
            SecondaryStage.show();

        });

        sign_out.setOnAction(e ->{
            username.setVisible(true);
            password.setVisible(true);
            input_username.setVisible(true);
            input_username.clear();
            input_password.setVisible(true);
            input_password.clear();
            login.setVisible(true);
            SignUp.setVisible(true);

            search.setVisible(false);
            reserve.setVisible(false);
            due_dates.setVisible(false);
            sign_out.setVisible(false);
            time.setVisible(false);
            date.setVisible(false);

            check_in.setVisible(false);
            check_out.setVisible(false);

            admin_Functions.setVisible(false);
        });

        due_dates.setOnAction(e ->{
            GridPane due_date_grid = new GridPane();
            due_date_grid.setPadding(new Insets(10, 10, 10, 10));
            due_date_grid.setMinSize(800, 500);
            due_date_grid.setVgap(5);
            due_date_grid.setHgap(5);
            due_date_grid.setStyle("-fx-background-color:  #800080;");

            Button cancel = new Button("Cancel");
            cancel.setOnAction(e2 -> SecondaryStage.close());

            TextArea checked_out_books = new TextArea();
            checked_out_books.setWrapText(true);
            checked_out_books.setEditable(false);
            checked_out_books.setText(SQLProcessor.parseasString(SQLUserProcessor.check_due_dates()));

            GridPane.setHalignment(checked_out_books,HPos.CENTER);
            due_date_grid.add(checked_out_books,1,2,175,80);

            due_date_grid.setHalignment(cancel,HPos.CENTER);
            due_date_grid.add(cancel,75,85);

            Scene due_date_scene = new Scene(due_date_grid, 960, 600);
            SecondaryStage.setScene(due_date_scene);
            SecondaryStage.show();
        });

        reserve.setOnAction(e ->{
            GridPane reserve_grid = new GridPane();
            reserve_grid.setPadding(new Insets(10, 10, 10, 10));
            reserve_grid.setMinSize(800, 500);
            reserve_grid.setVgap(5);
            reserve_grid.setHgap(5);
            reserve_grid.setStyle("-fx-background-color:  #800080;");

            Button cancel = new Button("Cancel");
            cancel.setOnAction(e2 -> SecondaryStage.close());

            Text to_reserve = new Text("Book to reserve");

            TextField book_to_reserve = new TextField("Barcode Number");

            Button reserve_book = new Button("Reserve");
            reserve_book.setOnAction(e2 ->{
                boolean check =false;  // change this to an SQL processor statement to add functionality to this button
                if (check){
                    Stage popup_Stage = new Stage();
                    popup_Stage.setTitle("Pop-up");

                    GridPane popup_grid = new GridPane();

                    Text success = new Text("Reservation Successful!");
                    success.setFont(Font.font(18));
                    popup_grid.add(success, 1, 1);
                    GridPane.setHalignment(success, HPos.CENTER);

                    Button close_warn = new Button("Close");
                    close_warn.setOnAction(e3 -> {
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

                    Text warning = new Text("Unable to reserve.");
                    warning.setFont(Font.font(18));
                    popup_grid.add(warning, 1, 1);
                    GridPane.setHalignment(warning, HPos.CENTER);


                    Text warning_2 = new Text("Please check barcode and/or Availability");
                    warning_2.setFont(Font.font(14));
                    popup_grid.add(warning_2, 1, 2);
                    GridPane.setHalignment(warning_2, HPos.CENTER);

                    Button close_popup = new Button("Close");
                    close_popup.setOnAction(e3 -> {
                        popup_Stage.close();
                    });
                    popup_grid.add(close_popup, 1, 3);
                    GridPane.setHalignment(close_popup, HPos.CENTER);

                    Scene Popup_Scene = new Scene(popup_grid, 300, 100);
                    popup_Stage.setScene(Popup_Scene);
                    popup_Stage.show();
                }
            });

            reserve_grid.add(to_reserve,1,2);
            reserve_grid.add(book_to_reserve,2,2);
            reserve_grid.add(reserve_book,3,2);



            GridPane.setHalignment(cancel,HPos.CENTER);
            reserve_grid.add(cancel,75,85);

            Scene reserve_scene = new Scene(reserve_grid, 960, 600);
            SecondaryStage.setScene(reserve_scene);
            SecondaryStage.show();
        });

        grid.add(username, 1, 1);
        grid.add(password, 1, 2);
        grid.add(input_username, 2, 1);
        grid.add(input_password, 2, 2);
        grid.add(login, 3, 1);
        grid.add(SignUp, 3, 2);

        grid.add(search, 1, 1,2,1);
        grid.add(reserve, 1, 2,2,1);
        grid.add(due_dates, 1, 3,2,1);
        grid.add(sign_out,3,5);
        grid.add(close,0,5);
        grid.add(time,0,0);
        grid.add(date,1,0);

        grid.add(check_in,3,1);
        grid.add(check_out,3,2);

        Scene scene = new Scene(grid, 800, 500);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}


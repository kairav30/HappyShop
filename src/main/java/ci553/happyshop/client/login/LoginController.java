package ci553.happyshop.client.login;
import ci553.happyshop.client.customer.*;
import ci553.happyshop.client.emergency.EmergencyExit;
import ci553.happyshop.client.orderTracker.OrderTracker;
import ci553.happyshop.client.picker.PickerController;
import ci553.happyshop.client.picker.PickerModel;
import ci553.happyshop.client.picker.PickerView;
import ci553.happyshop.client.warehouse.*;
import ci553.happyshop.orderManagement.OrderHub;
import ci553.happyshop.storageAccess.DatabaseRW;
import ci553.happyshop.storageAccess.DatabaseRWFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class LoginController {

    public  LoginView loginView;
    public void Guests(Stage stage){
        CustomerView view = new CustomerView();
        CustomerController controller = new CustomerController();
        CustomerModel model = new CustomerModel();
        DatabaseRW databaseRW = DatabaseRWFactory.createDatabaseRW();

        view.cusController = controller;
        controller.cusModel = model;
        model.cusView = view;
        model.databaseRW = databaseRW;

        view.start(stage);
        PickerModel pickerModel = new PickerModel();
        PickerView pickerView = new PickerView();
        PickerController pickerController = new PickerController();
        pickerView.pickerController = pickerController;
        pickerController.pickerModel = pickerModel;
        pickerModel.pickerView = pickerView;
        pickerModel.registerWithOrderHub();
        pickerView.start(new Stage());
        OrderTracker orderTracker = new OrderTracker();
        orderTracker.registerWithOrderHub();
        OrderHub.getOrderHub().initializeOrderMap();
        EmergencyExit.getEmergencyExit();

    }
    String btn_sty = // blueprint for styling buttons
            "-fx-background-color: #937A62;" +
                    "-fx-text-fill: white;" +
                    "-fx-background-radius: 11;" +
                    "-fx-padding: 20 50;" +
                    "-fx-font-size: 25px;";



    public void warehouse(Stage stage){ // method for warehouse button
        WarehouseView view = new WarehouseView();
        WarehouseController controller = new WarehouseController();
        WarehouseModel model = new WarehouseModel();
        DatabaseRW databaseRW = DatabaseRWFactory.createDatabaseRW();

        view.controller = controller;
        controller.model = model;
        model.view = view;
        model.databaseRW = databaseRW;

        view.start(stage);
    }
    public void account_creation(Stage stage){ //method for account creation
        PasswordField encryption = new PasswordField();
        Label Messages = new Label();


        Button Save_Button = new Button("Save");
        Save_Button.setStyle(btn_sty); // using the styling created earlier
        Save_Button.setPrefWidth(190); // width of button
        Save_Button.setPrefHeight(30); // height of button

        Button Back_Button = new Button("Back");
        Back_Button.setStyle(btn_sty); // using styling created earlier on button
        Back_Button.setPrefWidth(190); // width of button
        Back_Button.setPrefHeight(30); // height of button



        Save_Button.setOnAction(e ->{
            try{
                Files.write(
                        Paths.get("accounts.txt"), (encryption.getText() + System.lineSeparator()).getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
                Messages.setText("Account has been Created"); // account sucessfully been created
            } catch (IOException ex){
                Messages.setText("Error saving PIN");
            }
        });

        Back_Button.setOnAction(e -> loginView.start(stage)); // back button that takes users back to stage

        VBox root = new VBox(10, new Label("Create a pin number!"), encryption, Save_Button, Back_Button, Messages);
        root.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(root, 400, 300));
        root.setStyle("-fx-background-color: #C3B091;"); // styles background

    }


    public void login(Stage stage){ // method for login button
        PasswordField encryption_two = new PasswordField();
        Label Err_Msg = new Label();

        Button Loginbutton = new Button("Login");
        Loginbutton.setStyle(btn_sty); // uses the styling created earlier on login button
        Loginbutton.setPrefWidth(190); // width of the login button
        Loginbutton.setPrefHeight(30); // height of the login button

        Button Escapebutton = new Button("Escape");
        Escapebutton.setStyle(btn_sty); // uses the styling created ealier on escape button
        Escapebutton.setPrefWidth(190); // width of the escape button
        Escapebutton.setPrefHeight(30);// height of the escape button


        Loginbutton.setOnAction(e-> {
            try{
                List<String> password = Files.readAllLines(Paths.get("accounts.txt"));
                if(password.contains(encryption_two.getText())){
                    Guests(stage);
                } else{
                    Err_Msg.setText("Invalid pin"); // message shows if pin is not valid or account has not been created
                }
            } catch (IOException ex){
                Err_Msg.setText("Error");
            }
        });
        Escapebutton.setOnAction(e->loginView.start(stage)); // Escape button used to take users back to the stage
        VBox root = new VBox(10, new Label("enter pin number"), encryption_two, Loginbutton, Escapebutton, Err_Msg);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #C3B091;"); // styling background


        stage.setScene(new Scene(root, 400, 300));
    }



}

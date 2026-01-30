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


    }


    public void warehouse(Stage stage){
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
    public void account_creation(Stage stage){
        PasswordField encryption = new PasswordField();
        Label Messages = new Label();


        Button Save_Button = new Button("Save");
        Button Back_Button = new Button("Back");



        Save_Button.setOnAction(e ->{
            try{
                Files.write(
                        Paths.get("accounts.txt"), (encryption.getText() + System.lineSeparator()).getBytes(),StandardOpenOption.CREATE,StandardOpenOption.APPEND);
                Messages.setText("Account has been Created");
            } catch (IOException ex){
                Messages.setText("Error saving PIN");
            }
        });

        Back_Button.setOnAction(e -> loginView.start(stage));

        VBox root = new VBox(10, new Label("Create a pin number!"), encryption, Save_Button, Back_Button, Messages);
        root.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(root, 400, 300));

    }


    public void login(Stage stage){
        PasswordField encryption_two = new PasswordField();
        Label Err_Msg = new Label();

        Button Loginbutton = new Button("Login");
        Button Escapebutton = new Button("Escape");


        Loginbutton.setOnAction(e-> {
            try{
                List<String> password = Files.readAllLines(Paths.get("accounts.txt"));
                if(password.contains(encryption_two.getText())){
                    Guests(stage);
                } else{
                    Err_Msg.setText("Invalid pin");
                }
            } catch (IOException ex){
                Err_Msg.setText("Error");
            }
        });
        Escapebutton.setOnAction(e->loginView.start(stage));
        VBox root = new VBox(10, new Label("enter pin number"), encryption_two, Loginbutton, Escapebutton, Err_Msg);
        root.setAlignment(Pos.CENTER);


        stage.setScene(new Scene(root, 400, 300));
    }



}

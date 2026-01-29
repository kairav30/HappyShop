package ci553.happyshop.client.login;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;
public class LoginView {
    public LoginController loginController;

    public void start(Stage stage){
        Label Title = new Label("Welcome to Kairavs happy shop!");

        Button Btn_Guest = new Button("Continue as a Guest");
        Button Btn_Login = new Button("Customer Login");
        Button Btn_Creation = new Button("Create New Account");
        Button Btn_Warehouse = new Button("Staff login");

        Btn_Guest.setOnAction(e -> loginController.Guests(stage));
        Btn_Login.setOnAction(e -> loginController.login(stage));
        Btn_Creation.setOnAction(e -> loginController.account_creation(stage));
        Btn_Warehouse.setOnAction(e -> loginController.warehouse(stage));




    }
}

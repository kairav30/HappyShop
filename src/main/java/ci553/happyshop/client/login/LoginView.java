package ci553.happyshop.client.login;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class LoginView {
    public LoginController loginController;

    public void start(Stage stage){
        Label Title = new Label("Welcome to Kairavs happy shop!");

        Button Btn_Guest = new Button("Continue as a Guest"); //creates guest button
        Button Btn_Login = new Button("Customer Login"); //creates login button
        Button Btn_Creation = new Button("Create New Account"); //create account creation button
        Button Btn_Warehouse = new Button("Staff login"); //creates staff button

        Btn_Guest.setOnAction(e -> loginController.Guests(stage)); //links the button to method in login controller
        Btn_Login.setOnAction(e -> loginController.login(stage));
        Btn_Creation.setOnAction(e -> loginController.account_creation(stage));
        Btn_Warehouse.setOnAction(e -> loginController.warehouse(stage));

        VBox vb = new VBox(15, Title, Btn_Guest, Btn_Login, Btn_Creation, Btn_Warehouse); //displayed on the window to users
        vb.setAlignment(Pos.CENTER);

        stage.setScene(new Scene(vb, 400, 300));
        stage.setTitle("Happy shop login");
        stage.show();




    }
}

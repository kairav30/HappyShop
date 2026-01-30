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
        Title.setStyle(
                "-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;" //styling Title
        );
        String btn_sty = // Styling button
                "-fx-background-color: #937A62;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-radius: 11;" +
                        "-fx-padding: 8 20;" +
                        "-fx-font-size: 10px;";





        Button Btn_Guest = new Button("Continue as a Guest"); //creates guest button
        Btn_Guest.setStyle(btn_sty); // style to button
        Btn_Guest.setPrefWidth(220); // width of button
        Btn_Guest.setPrefHeight(50); //height of button

        Button Btn_Login = new Button("Customer Login"); //creates login button
        Btn_Login.setStyle(btn_sty); // style to button
        Btn_Login.setPrefWidth(220); // width of button
        Btn_Login.setPrefHeight(50); // height of button

        Button Btn_Creation = new Button("Create New Account"); //create account creation button
        Btn_Creation.setStyle(btn_sty); //styles button
        Btn_Creation.setPrefWidth(220); // width of button
        Btn_Creation.setPrefHeight(50); // height of button

        Button Btn_Warehouse = new Button("Staff login"); //creates staff button
        Btn_Warehouse.setStyle(btn_sty); // styles button
        Btn_Warehouse.setPrefWidth(220); // width of button
        Btn_Warehouse.setPrefHeight(50); // height of button


        Btn_Guest.setOnAction(e -> loginController.Guests(stage)); //links the button to method in login controller
        Btn_Login.setOnAction(e -> loginController.login(stage));
        Btn_Creation.setOnAction(e -> loginController.account_creation(stage));
        Btn_Warehouse.setOnAction(e -> loginController.warehouse(stage));

        VBox vb = new VBox(15, Title, Btn_Guest, Btn_Login, Btn_Creation, Btn_Warehouse); //displayed on the window to users
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color: #C3B091;"); // sets background colour

        stage.setScene(new Scene(vb, 400, 300));
        stage.setTitle("Happy shop login");
        stage.show();




    }
}

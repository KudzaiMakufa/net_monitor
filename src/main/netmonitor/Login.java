package main.netmonitor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.netmonitor.controller.UserSession;
import main.netmonitor.encryption.Aes;
import main.netmonitor.model.Tables.Users;
import main.netmonitor.validation.Alerts;
import main.netmonitor.validation.EmailValidation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.prefs.Preferences;

public class Login extends Application {
    EmailValidation validator = new EmailValidation();
    @FXML
    Button btnlogin ;
    @FXML
    TextField txtemail ;
    @FXML
    PasswordField txtpassword ;

    @Override
    public void start(Stage primaryStage) throws Exception{
        URL url1 = new File("src/main/resources/fxml/login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url1);
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    public void Register(Event event){
        Aes aes = new Aes();
        Users users = new Users();

        if( validator.isValidEmailAddress(txtemail.getText())) {
            try {

                String encrypted = aes.encrypt(txtpassword.getText().toString());

                //method to compare with stored value here
                System.out.println(users.InsertUser(txtemail.getText(), txtemail.getText(), "user",encrypted));


            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else{
            System.out.println("Invalid email address");
        }
    }
    public void Login(Event event) {

    if( validator.isValidEmailAddress(txtemail.getText())) {
        Users user = new Users();
        System.out.println(user.Login(txtemail.getText().toString(), txtpassword.getText().toString()));
        if (user.Login(txtemail.getText().toString(), txtpassword.getText().toString()).equals("admin") ) {
            UserSession.setMyVariable("admin");

            Parent root;
            try {
                URL dashboard = new File("src/main/resources/fxml/main.fxml").toURI().toURL();
                root = FXMLLoader.load(dashboard);
                Stage stage = new Stage();
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root, 934, 451));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else if (user.Login(txtemail.getText().toString(), txtpassword.getText().toString()).equals("user")) {
            UserSession.setMyVariable("user");
            Parent root;
            try {
                URL dashboard = new File("src/main/resources/fxml/main.fxml").toURI().toURL();
                root = FXMLLoader.load(dashboard);
                Stage stage = new Stage();
                stage.setTitle("Dashboard");
                stage.setScene(new Scene(root, 934, 451));
                stage.show();
                // Hide this current window (if this is what you want)
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else{
            Alerts alert = new Alerts();
            alert.Warning("Login Failed","Error","Invalid email or password");

        }
    }
    else{
        Alerts alert = new Alerts();
        alert.Warning("Warning","Error","Invalid email address");

    }

    }




    public static void main(String[] args) {
        launch(args);
    }
}

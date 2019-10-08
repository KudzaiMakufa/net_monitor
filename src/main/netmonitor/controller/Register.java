package main.netmonitor.controller;

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
import main.netmonitor.encryption.Aes;
import main.netmonitor.model.Tables.Users;
import main.netmonitor.validation.Alerts;
import main.netmonitor.validation.EmailValidation;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Register {
    EmailValidation validator = new EmailValidation();
    @FXML
    Button btnlogin;
    @FXML
    TextField txtemail;
    @FXML
    PasswordField txtpassword;
    @FXML
    TextField phone_number;
    @FXML
    PasswordField txtconfirm;


    public void Register(Event event) {
        System.out.println(txtpassword.getText());
        System.out.println(txtconfirm.getText());

        if (validator.isValidEmailAddress(txtemail.getText()) && txtpassword.getText().toString().equals(txtconfirm.getText().toString())) {
            Users user = new Users();
            if (user.InsertUser(txtemail.getText().toString(), phone_number.getText().toString(), txtpassword.getText().toString())) {

                ((Node) (event.getSource())).getScene().getWindow().hide();
//                Parent root;
//                try {
//                    URL dashboard = new File("src/main/resources/fxml/register.fxml").toURI().toURL();
//                    root = FXMLLoader.load(dashboard);
//                    Stage stage = new Stage();
//                    stage.setTitle("Dashboard");
//                    stage.setScene(new Scene(root, 900, 600));
//                    stage.show();
//                    // Hide this current window (if this is what you want)
//                    ((Node) (event.getSource())).getScene().getWindow().hide();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        } else {

                Alerts alert = new Alerts();
                alert.Warning("Warning","Failed to register","Invalid email address or passwords did not match");



        }

    }
}





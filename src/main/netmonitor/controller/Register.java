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
                Alerts alert = new Alerts();
                alert.Information("Success","Accounted created "," account with email "+txtemail.getText()+" added");
                ((Node) (event.getSource())).getScene().getWindow().hide();
//

            }
        } else {

                Alerts alert = new Alerts();
                alert.Warning("Warning","Failed to register","Invalid email address or passwords did not match");



        }

    }
}





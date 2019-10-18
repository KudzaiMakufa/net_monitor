package main.netmonitor.controller;

import javafx.event.Event;
import javafx.scene.Node;
import main.netmonitor.encryption.Aes;
import main.netmonitor.model.Tables.Users;
import main.netmonitor.validation.Alerts;
import main.netmonitor.validation.EmailValidation;

public class Test {
    public static void main(String[] args) throws Exception{


        EmailValidation validator = new EmailValidation();
        String email = "admin@admin.com";
        String password = "pass123";
        String phoneno = "0782257826";

        if (validator.isValidEmailAddress(email)) {
            Users user = new Users();
            Aes aes = new Aes();
            if (user.InsertUser(email, phoneno,"admin" ,aes.encrypt(password))) {
                System.out.println("Success");

            }
        } else {

            System.out.println("error") ;

        }

    }

}

package main.netmonitor.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import main.netmonitor.model.Tables.Plan;

import java.awt.*;

public class InternetPlan {
    @FXML javafx.scene.control.TextField txtspeed;
    @FXML  javafx.scene.control.TextField txtprice;


    public void Update(ActionEvent event){
        Plan plan = new Plan();
        System.out.println(plan.InsertPlan(Double.parseDouble(txtspeed.getText()) , txtprice.getText().toString())) ;
        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

}

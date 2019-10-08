package main.netmonitor.validation;

import javafx.scene.control.Alert;

public class Alerts {
    public void Warning(String title,String headertext,String contexttext){
        Alert DevNotSelected = new Alert(Alert.AlertType.WARNING);
        DevNotSelected.setTitle(title);
        DevNotSelected.setHeaderText(headertext);
        DevNotSelected.setContentText(contexttext);
        DevNotSelected.show();
    }
    public void Information(String title,String headertext,String contexttext){
        Alert DevNotSelected = new Alert(Alert.AlertType.INFORMATION);
        DevNotSelected.setTitle(title);
        DevNotSelected.setHeaderText(headertext);
        DevNotSelected.setContentText(contexttext);
        DevNotSelected.show();
    }
}

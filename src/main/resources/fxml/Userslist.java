package main.resources.fxml;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import main.netmonitor.validation.Alerts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Userslist {

    Connection c = null;
    Statement stmt = null;
    Boolean inserted = false;
    @FXML
    Button button;


    @FXML
    ListView lsuserslist;

    @FXML
    protected void initialize() {
        try {

            String admin = "admin@admin.com";
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE email != '" + admin + "' ;");
            while (rs.next()) {


                lsuserslist.getItems().add(rs.getString("ID") + "          " + rs.getString("email")
                        + "          " + rs.getString("phone_no"));

                button.setOnAction(event -> {
                    ObservableList selectedIndices = lsuserslist.getSelectionModel().getSelectedIndices();

                    for (Object o : selectedIndices) {
                        //System.out.println("o = " + o + " (" + o.getClass() + ")");
                        Alerts alert = new Alerts();
                        System.out.println(Integer.valueOf(o.toString()) + 2);
                        String id = String.valueOf(Integer.valueOf(o.toString()) + 2) ;
                        if(DeleteUser(id)){

                            alert.Information("Success","Deleted ","Account successfully deleted");
                        }
                        else{
                            alert.Warning("Error","Failed ","Failed to delete account");
                        }

                    }
                });


//                String paassword = rs.getString("password");
//                String emaill = rs.getString("email");
//
//                System.out.println( "NAME = " + paassword + "email = "+emaill);


            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public boolean DeleteUser(String  id) {
        {

            boolean deleted = false;

            Connection c = null;
            Statement stmt = null;
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
                c.setAutoCommit(false);
                System.out.println("Opened database successfully");
                stmt = c.createStatement();
                String sql = "DELETE from Users where ID= '"+id+"' ";
                stmt.executeUpdate(sql);
                c.commit();

                stmt.close();
                c.close();
                deleted = true ;
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            return deleted;
        }
    }
}



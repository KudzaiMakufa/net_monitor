package main.netmonitor.model;
import java.sql.*;

public class DbConnection {

    public void Connect(){
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
            System.out.println("Opened database successfully");

            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

}

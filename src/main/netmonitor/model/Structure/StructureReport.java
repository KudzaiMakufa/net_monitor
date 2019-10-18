package main.netmonitor.model.Structure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class StructureReport {

    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "CREATE TABLE Reports " +
                    "(ID INTEGER PRIMARY KEY NOT NULL," +
                    " loss BLOB NOT NULL, " +
                    " time TEXT)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Reports table created successfully");
    }
}
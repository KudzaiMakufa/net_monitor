package main.netmonitor.model.Structure;

import java.sql.*;
public class StructureUsers
{
    public static void main( String args[] )
    {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "CREATE TABLE Users " +
                    "(ID INTEGER PRIMARY KEY NOT NULL," +
                    " email TEXT NOT NULL, " +
                    " phone_no INT NOT NULL, " +
                    " password TEXT)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Users table created successfully");
    }
}
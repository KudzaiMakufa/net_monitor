package main.netmonitor.model.Tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Reports {
    Connection c = null;
    Statement stmt = null;
    Boolean inserted = false ;
    public  boolean Insert(String loss,String time)
    {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();

            String sql = "INSERT INTO Reports (ID,loss,time) " +
                    "VALUES (null, '"+loss+"', '"+time+"' );";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
            inserted = true ;
            return inserted ;
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return inserted ;
    }
}

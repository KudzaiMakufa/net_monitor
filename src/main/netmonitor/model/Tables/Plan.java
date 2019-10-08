package main.netmonitor.model.Tables;

import main.netmonitor.encryption.Aes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Plan
{
    Connection c = null;
    Statement stmt = null;
    Boolean inserted = false ;
    public boolean InsertPlan(double speed,String price)
    {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();

            String sql = "INSERT INTO Plan (ID,speed,price) " +
                    "VALUES (null,'"+speed+"', '"+price+"' );";
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
    public String GetSpeed()
    {

        String speed = null;
        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Plan ;" );
            while ( rs.next() ) {

                speed = rs.getString("speed");
//

            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
     return speed ;
    }
    public String GetPrice()
    {

        String price = null;
        try {

            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Plan ;" );
            while ( rs.next() ) {

                price = rs.getString("price");
//

            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return price ;
    }
    public boolean Update(double speed ,String price){
        Connection c = null;
        Statement stmt = null;
       boolean updated = false ;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            String sql = "UPDATE Plan set speed = '"+speed+"' AND price = '"+price+"' where ID=1;";
            stmt.executeUpdate(sql);
            c.commit();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Plan;" );
            while ( rs.next() ) {
                updated = true ;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return updated ;
    }
}



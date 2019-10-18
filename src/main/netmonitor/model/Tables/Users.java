package main.netmonitor.model.Tables;

import main.netmonitor.encryption.Aes;

import java.sql.*;
public class Users
{
    Connection c = null;
    Statement stmt = null;
    Boolean inserted = false ;
    public  boolean InsertUser(String email,String phone_no,String type ,String password)
    {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();

            String sql = "INSERT INTO Users (ID,email,phone_no,type,password) " +
                    "VALUES (null,'"+email+"','"+phone_no+"', '"+type+"', '"+password+"' );";
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
    public  String Login(String email ,String Password)
    {
        String auth = "" ;
        Aes aes = new Aes();

        try {
            String password = aes.encrypt(Password);
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE email = '"+email+"' AND password='"+password+"';" );
            while ( rs.next() ) {

//                String paassword = rs.getString("password");
//                String emaill = rs.getString("email");
//
//                System.out.println( "NAME = " + paassword + "email = "+emaill);
                auth = rs.getString("type") ;


            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return auth ;
    }

}
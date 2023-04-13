/*
 * Property of Okami�
 * Not destined for commercial use
 */
package utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author 
 */
public class ConnectionDB {
    
    public String url="jdbc:mysql://localhost:3306/healthherald";
    public String login="root";
    public String pwd="";
    Connection c;
    public static ConnectionDB instance;
    
    private ConnectionDB() {
        try {
            c = DriverManager.getConnection(url, login, pwd);
            // System.out.println("Successfully connected to the database.");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public Connection getConnection() {
        return c;
    }
    
    public static ConnectionDB getInstance() {
        if (instance == null) {
            instance = new ConnectionDB();
        }
        return instance;
    }
    
}

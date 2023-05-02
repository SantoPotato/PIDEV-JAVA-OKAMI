/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionUtils {
    
    private Connection cnx;
    private static ConnectionUtils instance;
    
    private final String USER = "root";
    private final String PWD = "";
    private final String URL = "jdbc:mysql://localhost:3306/gestion_user";

    private ConnectionUtils() {
        try {
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static ConnectionUtils getInstance() {
        if(instance == null)
            instance = new ConnectionUtils();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
   
  
    
}

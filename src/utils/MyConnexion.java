/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
/**
 *
 * @author SONDESH
 */
public class MyConnexion {
    public String url="jdbc:mysql://localhost:3306/healthherald";
    public String login="root";
    public String pwd="";
    Connection cnx;
    private static MyConnexion instance;
    public MyConnexion(){
        try {
          cnx =  DriverManager.getConnection(url, login, pwd);
          System.out.println("connexion etabli !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public Connection getcnx(){
    return cnx;
    }
    public static MyConnexion getInstance(){
    
        if (instance == null){
        
            instance = new MyConnexion();
        }
        return instance;
    }
}

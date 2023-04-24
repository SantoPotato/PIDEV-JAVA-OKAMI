/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salle.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lenovo
 */
public class MyDB {
    

String url = "jdbc:mysql://localhost:3306/healthherald";
    String username = "root";
    String password = "";
    Connection cnx;
    
        private static MyDB instance;

        private MyDB() {
        try {
            cnx = DriverManager.getConnection(url, username, password);
            System.out.println("Connected");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static com.oracle.nio.BufferSecrets.instance;
import entities.user;
import java.sql.Connection;
import utils.MyConnexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import static jdk.nashorn.internal.objects.Global.instance;



/**
 *
 * @author SONDESH
 */
public class userCRUD {
    private static userCRUD instance;
     Connection cnx = MyConnexion.getInstance().getcnx();
    
   public void ajouteruser(String nom, String prenom, String password, String email,String roles){
    try {
        String requet = "INSERT INTO user (nom, prenom, password, email,roles) VALUES (?, ?, ?, ?,?)";
        PreparedStatement preparedStatement = new MyConnexion().getcnx().prepareStatement(requet);//i est utilisé pour exécuter une requête SQL paramétrée dans une base de données.
        preparedStatement.setString(1, nom);
        preparedStatement.setString(2, prenom);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, "user");
        preparedStatement.executeUpdate();
        System.out.println("user ajoutée avec succès");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}
   
      azzzzz  
    
     public static userCRUD getInstance(){
        if(instance == null)
            instance = new userCRUD();
        return instance;
    }
    public void ajouteruser2(user u){}
    public List<user> afficherusers(){
        List<user> myList =new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM user";
            Statement st = new MyConnexion().getcnx().createStatement();// utilisé pour exécuter des instructions SQL statiques qui ne nécessitent pas de paramètres. 
         ResultSet rs = st.executeQuery(requete3);
           while(rs.next()) {
               user u =new user();
               u.setId(rs.getInt(1));
               u.setNom(rs.getString("nom"));
               u.setPrenom(rs.getString("prenom"));
               u.setPassword(rs.getString("password"));
               u.setEmail(rs.getString("email"));
               u.setRoles(rs.getString("roles"));
               myList.add(u);
           }
        } catch (SQLException ex) {
               System.err.println(ex.getMessage());
        }
        return myList;
    }
    
    public void deleteuser(int id) {
        try {
            String requete="DELETE FROM user WHERE id=?";
            PreparedStatement st=MyConnexion.getInstance().getcnx().prepareStatement(requete);
            
            st.setInt(1, id);
            st.executeUpdate();
            System.out.println("User deleted!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
}
    
    public void updateuser(int id,String nom,String prenom,String password,String email) {
       
        try {
            String requete="UPDATE user SET nom=?, prenom=?, password=?, email=? where id=?";
            PreparedStatement st = MyConnexion.getInstance().getcnx().prepareStatement(requete);
            
            
            st.setString(1, nom);
            st.setString(2, prenom);
            st.setString(3, password);
            st.setString(4, email);
           
           
            st.setInt(5, id);
            
            st.executeUpdate();
            System.out.println("User Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<user> getAll() {
    List<user> myList =new ArrayList<>();
    try {
        String requete3 = "SELECT * FROM user";
        Statement st = new MyConnexion().getcnx().createStatement();
        ResultSet rs = st.executeQuery(requete3);
        while(rs.next()) {
            user u =new user();
            u.setId(rs.getInt(1));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
            u.setRoles(rs.getString("roles"));
            myList.add(u);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return myList;
}
      public boolean login(String password, String email){
    
         try {
            String requete = "SELECT count(*) FROM user where password=? AND email=? ";
            
            PreparedStatement preparedStatement = MyConnexion.getInstance().getcnx().prepareStatement(requete);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
       
    } 
   
    

    
    public List<user> searchUserByName(String nom) {
    List<user> myList = new ArrayList<>();
    try {
        String requete = "SELECT * FROM user WHERE nom=?";
        PreparedStatement st = MyConnexion.getInstance().getcnx().prepareStatement(requete);
        st.setString(1, nom);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            user u = new user();
            u.setId(rs.getInt(1));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setPassword(rs.getString("password"));
            u.setEmail(rs.getString("email"));
             u.setEmail(rs.getString("roles"));
            myList.add(u);
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return myList;
}
    
  

    public user getUser(String email, String password) {
        try {
        Connection conn = MyConnexion.getInstance().getcnx();
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, email);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            user u = new user(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("password"), rs.getString("role"));
            return u;
        }
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
    return null;
           }

    public void BlockUser(String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicules.services;

import vehicules.entities.Vehicules;
import vehicules.utils.MyConnection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vehicules.intefaces.InterfaceVehicules;

/**
 *
 * @author khaled
 */
public class VehiculesCRUD implements InterfaceVehicules {

    @Override
    public void ajouterEvenements(Vehicules r) {
      try {

            String requete1 = "INSERT INTO Vehicules(catv_id,nomvh,dispovh,etatvh,descvh,imagesvh,date) VALUES(?,?,?,?,?,?,STR_TO_Date(?,'%Y-%m-%d'))";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete1);
            pst.setString(1, r.getCatv_id());
            pst.setString(2, r.getNomvh());
            pst.setInt(3, r.getDispovh());
            pst.setString(4, r.getEtatvh());
            pst.setString(5, r.getImagesvh());
             pst.setString(6, r.getDescvh());
            pst.setString(7, r.getDate());
           
            pst.executeUpdate();
            System.out.println("vehicule ajoutee avec succes !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
        
        
        
        
        
        
        
         
    }

    @Override
    public void modifierEvenements(Vehicules r) {
        try {
            String requete4;
            requete4 = " UPDATE Vehicules SET catv_id=?,nomvh=?,dispovh=?,etatvh=?,descvh=?,imagesvh=?,date=? WHERE id=?" ;
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete4);
            pst.setString(1, r.getCatv_id());
            pst.setString(2, r.getNomvh());
            pst.setInt(3, r.getDispovh());
            pst.setString(4, r.getEtatvh());
            pst.setString(5, r.getImagesvh());
            pst.setString(6, r.getDescvh());
            pst.setString(7, r.getDate());
           
            pst.setInt(8, r.getId());
               pst.executeUpdate();
            System.out.println("Evenements modifie");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }   
        
        
        
        
        
    }

    @Override
    public void supprimerEvenements(int id) {
        try {
            String req = "DELETE FROM Vehicules WHERE id = " + id;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Vehicule supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    

        
        
        
    }

@Override

    public List<Vehicules> afficherEvenements()
    {
ArrayList<Vehicules> myList = new ArrayList();
        try {
            String requete = "SELECT * FROM Vehicules";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
     
              while (rs.next()) {
           Vehicules r = new Vehicules(rs.getInt("id"), rs.getString("catv_id"), rs.getString("nomvh"), rs.getInt("dispovh"),rs.getString("etatvh"), rs.getString("imagesvh"), rs.getString("descvh"), rs.getString("date"));
              myList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            }
        return myList;        
        
        
        
    }
  
}
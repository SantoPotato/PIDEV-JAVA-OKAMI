/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.services;

import Interfaces.InterfaceCRUD;
import stock.entities.Vehicules;
import stock.entities.Categorievehicules;
import stock.utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import stock.services.StockcategoriesCRUD;
import stock.entities.Stockcategories;

/**
 *
 * 
 */
public class VehiculesCRUD  {
   Statement ste;
    Connection cnx ;
    
   public VehiculesCRUD() {
         cnx = MyDB.getInstance().getCnx();
    }
   // @Override





    
    
public void Ajouter(Vehicules t) {
    try {
        // Exécutez la requête SELECT pour récupérer toutes les valeurs de la colonne `id`
        String selectQuery = "SELECT c.id FROM categoriesvehicules c JOIN vehicules s ON c.id = s.catv_id";

        cnx = MyDB.getInstance().getCnx();
        Statement selectStm = cnx.createStatement();
        ResultSet rs = selectStm.executeQuery(selectQuery);

      

        // Si la valeur existe dans la liste, vous pouvez exécuter la requête INSERT
        String insertQuery = "INSERT INTO `vehicules` (`catv_id`, `nomvh`, `dispovh`, `etatvh`, `descvh`) " +
                 "VALUES ('" + t.getCatv_id().getId() + "','" + t.getNomvh() + "','" +
                 t.isDispovh() + "','" + t.getEtatvh() + "','" + t.getDescvh() + "')";

        cnx = MyDB.getInstance().getCnx();
        Statement insertStm = cnx.createStatement();
        insertStm.executeUpdate(insertQuery);

        System.out.println("Vehicules a été ajouté avec succès !");

    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout du Vehicule : " + ex.getMessage());
    }
}




   
    public List<Vehicules> Afficher() {
        List<Vehicules> Vehicules = new ArrayList<>();
      try {
            String qry ="SELECT * FROM `vehicules` ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
           while(rs.next()){
    Vehicules p = new Vehicules();
    p.setId(rs.getInt(1));
    int catv_id = rs.getInt(2); // Modifier pour récupérer le stockcat_id à partir du ResultSet

    // Créer un objet Stockcategories et définir ses attributs à partir des données de la table
    Categorievehicules ca = new Categorievehicules();
    
    ca.setTypecatv(GetDoctorById(catv_id).getTypecatv()); // Utiliser la méthode GetDoctorById pour récupérer le typecat
    p.setCatv_id(ca);
    
    p.setNomvh(rs.getString(3));
    
    p.setDispovh(rs.getBoolean(4));
    p.setEtatvh(rs.getString(5));
    p.setDescvh(rs.getString(6));

    Vehicules.add(p);
    
}

            return Vehicules;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Vehicules;
        
        
        
        
    }
    public Categorievehicules GetDoctorById(int Id) {
    Categorievehicules h = new Categorievehicules();
    String req = "SELECT typecatv FROM categoriesvehicules WHERE id = " + Id;
    
    try {
        cnx = MyDB.getInstance().getCnx(); // Vérifier que la connexion est initialisée
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req); 
        
        if (rs.next()){
            
            h.setTypecatv(rs.getString(1));
        } else {
            System.out.println("Aucune catégorie de vehicule trouvée pour l'ID " + Id);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de la catégorie de vehicule pour l'ID " + Id + ": " + ex.getMessage());
    }
    
    return h;
}

    
    
   
      //@Override
    public void Supprimer(int id) {
try {
            String qry ="DELETE from vehicules where id = "+id+";";
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
               String recipient = "mohamedamine.sidhom@esprit.tn";
                 try {
                 stock.utils.Mail.envoyer(recipient);
                 System.out.println("Le message a été envoyé avec succès.");
                 } catch (Exception e) {
                 System.err.println("Erreur lors de l'envoi du message : " + e.getMessage());
                 e.printStackTrace();}
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    }

    
  
//@Override
 public void modifier(Vehicules e,int id)  {
    try {
    String req = "UPDATE vehicules SET catv_id=?, nomvh=?, dispovh=?, etatvh=?, descvh=? WHERE id=?";
    PreparedStatement pre=cnx.prepareStatement(req);
    
    
    
    if (e.getCatv_id() != null) {
    pre.setInt(1, e.getCatv_id().getId());
     } else {
    // Handle the case when the stockcat_id is null
       }
   
    pre.setString(2, e.getNomvh());
   
         pre.setBoolean(3, e.isDispovh());
           pre.setString(4, e.getEtatvh());
             pre.setString(5, e.getDescvh());
         
   
    pre.setInt(6, id);
    

    pre.executeUpdate();
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    System.out.println("vehicule vous modifié !");
}
 
  
    public List<Vehicules> AfficherParId(int id) {
        List<Vehicules> Vehicules = new ArrayList<>();
      try {
            String qry ="Select * from Stock  where id =" +id;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
             Vehicules p = new Vehicules();
    p.setId(rs.getInt(1));
    int catv_id = rs.getInt(2); // Modifier pour récupérer le stockcat_id à partir du ResultSet

    // Créer un objet Stockcategories et définir ses attributs à partir des données de la table
    Categorievehicules ca = new Categorievehicules();
    
    ca.setTypecatv(GetDoctorById(catv_id).getTypecatv()); // Utiliser la méthode GetDoctorById pour récupérer le typecat
    p.setCatv_id(ca);
    
    p.setNomvh(rs.getString(3));
    
    p.setDispovh(rs.getBoolean(4));
    p.setEtatvh(rs.getString(5));
    p.setDescvh(rs.getString(6));

    Vehicules.add(p);
            }
            return Vehicules;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Vehicules;
        
        
        
        
    }
    
    
    
    public List<Vehicules> AfficherParNom() {
        List<Vehicules> Vehicules = new ArrayList<>();
      try {
            String qry ="Select * from stock  where nomst ='aspirine'" ;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                     Vehicules p = new Vehicules();
    p.setId(rs.getInt(1));
    int catv_id = rs.getInt(2); // Modifier pour récupérer le stockcat_id à partir du ResultSet

    // Créer un objet Stockcategories et définir ses attributs à partir des données de la table
    Categorievehicules ca = new Categorievehicules();
    
    ca.setTypecatv(GetDoctorById(catv_id).getTypecatv()); // Utiliser la méthode GetDoctorById pour récupérer le typecat
    p.setCatv_id(ca);
    
    p.setNomvh(rs.getString(3));
    
    p.setDispovh(rs.getBoolean(4));
    p.setEtatvh(rs.getString(5));
    p.setDescvh(rs.getString(6));

    Vehicules.add(p);
            }
            return Vehicules;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Vehicules;
        
        
        }
        
 }

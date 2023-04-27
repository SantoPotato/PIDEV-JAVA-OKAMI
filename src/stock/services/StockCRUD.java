/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.services;

import Interfaces.InterfaceCRUD;
import stock.entities.Stock;
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
public class StockCRUD  {
   Statement ste;
    Connection cnx ;
    
   public StockCRUD() {
         cnx = MyDB.getInstance().getCnx();
    }
   // @Override





    
    
public void Ajouter(Stock t) {
    try {
        // Exécutez la requête SELECT pour récupérer toutes les valeurs de la colonne `id`
        String selectQuery = "SELECT c.id FROM Stockcategories c JOIN Stock s ON c.id = s.stockcat_id";

        cnx = MyDB.getInstance().getCnx();
        Statement selectStm = cnx.createStatement();
        ResultSet rs = selectStm.executeQuery(selectQuery);

      

        // Si la valeur existe dans la liste, vous pouvez exécuter la requête INSERT
        String insertQuery = "INSERT INTO `Stock`(`nomst`, `description`, `dateexpirationst`, `stockcat_id`, `quantites`) " +
                 "VALUES ('" + t.getNomst() + "','" + t.getDescription() + "','" +
                 t.getDateexpirationst() + "','" + t.getStockcat_id().getId() + "','" + t.getQuantites() + "')";

        cnx = MyDB.getInstance().getCnx();
        Statement insertStm = cnx.createStatement();
        insertStm.executeUpdate(insertQuery);

        System.out.println("Le stock a été ajouté avec succès !");

    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout du stock : " + ex.getMessage());
    }
}




   
    public List<Stock> Afficher() {
        List<Stock> Stock = new ArrayList<>();
      try {
            String qry ="SELECT * FROM `Stock` ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
           while(rs.next()){
    Stock p = new Stock();
    p.setId(rs.getInt(1));
    p.setNomst(rs.getString(2));
    p.setDescription(rs.getString(3));
    p.setDateexpirationst(rs.getDate(4).toLocalDate());
    int stockcat_id = rs.getInt(5); // Modifier pour récupérer le stockcat_id à partir du ResultSet

    // Créer un objet Stockcategories et définir ses attributs à partir des données de la table
    Stockcategories ca = new Stockcategories();
    
    ca.setTypecat(GetDoctorById(stockcat_id).getTypecat()); // Utiliser la méthode GetDoctorById pour récupérer le typecat
    p.setStockcat_id(ca);
    
    p.setQuantites(rs.getInt(6));
    

    Stock.add(p);
    
}

            return Stock;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Stock;
        
        
        
        
    }
    public Stockcategories GetDoctorById(int Id) {
    Stockcategories h = new Stockcategories();
    String req = "SELECT Typecat FROM Stockcategories WHERE id = " + Id;
    
    try {
        cnx = MyDB.getInstance().getCnx(); // Vérifier que la connexion est initialisée
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(req); 
        
        if (rs.next()){
            
            h.setTypecat(rs.getString(1));
        } else {
            System.out.println("Aucune catégorie de stock trouvée pour l'ID " + Id);
        }
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la récupération de la catégorie de stock pour l'ID " + Id + ": " + ex.getMessage());
    }
    
    return h;
}

    
    
   
      //@Override
    public void Supprimer(int id) {
try {
            String qry ="DELETE from Stock where id = "+id+";";
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    }

    
  
//@Override
 public void modifier(Stock e,int id)  {
    try {
    String req = "UPDATE Stock SET nomst=?, description=?, dateexpirationst=?, stockcat_id=?, quantites=? WHERE id=?";
    PreparedStatement pre=cnx.prepareStatement(req);

    pre.setString(1, e.getNomst());
    pre.setString(2, e.getDescription());
    if (e.getDateexpirationst() != null) {
            pre.setDate(3, Date.valueOf(e.getDateexpirationst()));
        } else {
            pre.setDate(3, null);
        }
        
    if (e.getStockcat_id() != null) {
    pre.setInt(4, e.getStockcat_id().getId());
} else {
    // Handle the case when the stockcat_id is null
}

    pre.setInt(5, e.getQuantites());
    pre.setInt(6, id);
    

    pre.executeUpdate();
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    System.out.println("stock vous modifié !");
}
 
  
    public List<Stock> AfficherParId(int id) {
        List<Stock> Stock = new ArrayList<>();
      try {
            String qry ="Select * from Stock  where id =" +id;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Stock p =new Stock();
                p.setId(rs.getInt(1));
               p.setNomst(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setDateexpirationst(rs.getDate(4).toLocalDate());

                int stockcat_id = rs.getInt(5); // Modifier pour récupérer le stockcat_id à partir du ResultSet
                // Créer un objet Stockcategories et définir ses attributs à partir des données de la table
                Stockcategories ca = new Stockcategories();
                ca.setId(stockcat_id);
                ca.setTypecat(GetDoctorById(stockcat_id).getTypecat()); // Utiliser la méthode GetDoctorById pour récupérer le typecat
                p.setStockcat_id(ca);

                p.setQuantites(rs.getInt(6));


                Stock.add(p);

            }
            return Stock;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Stock;
        
        
        
        
    }
    
    
    
    public List<Stock> AfficherParNom() {
        List<Stock> Stock = new ArrayList<>();
      try {
            String qry ="Select * from stock  where nomst ='aspirine'" ;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Stock p =new Stock();
                p.setId(rs.getInt(1));
                p.setNomst(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setDateexpirationst(rs.getDate(4).toLocalDate());

                int stockcat_id = rs.getInt(5); // Modifier pour récupérer le stockcat_id à partir du ResultSet
             // Créer un objet Stockcategories et définir ses attributs à partir des données de la table
                Stockcategories ca = new Stockcategories();
                ca.setId(stockcat_id);
                ca.setTypecat(GetDoctorById(stockcat_id).getTypecat()); // Utiliser la méthode GetDoctorById pour récupérer le typecat
                p.setStockcat_id(ca);

                p.setQuantites(rs.getInt(6));
              

                Stock.add(p);

            }
            return Stock;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Stock;
    }
    
        
 }

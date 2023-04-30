/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categoriesequipement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.ConnectionDB;

/**
 *
 * @author SNAKE 2-16
 */
public class CategoriesEquipementCRUD implements InterfaceCategorie {
    Statement ste;
    Connection conn ;
    
   public CategoriesEquipementCRUD() {
         conn = ConnectionDB.getInstance().getConnection();
    }
   
   @Override
    public void ajouterCategorie(Categoriesequipement Ct) {
        try {
            String req = "INSERT INTO `categoriesequipement`( `nomecate`) VALUES ('"+Ct.getNomcate()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Categoriesequipement ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Categoriesequipement non ajouté");
                      }
 }
 
      @Override
    public void ajouterCategorie2(Categoriesequipement Ct) {
 try {
            String req = "INSERT INTO `categoriesequipement` (`id`, `nomcate`) VALUES (DEFAULT,?)";
            PreparedStatement ps=conn.prepareStatement(req);
          
            ps.setString(1, Ct.getNomcate());
             ps.executeUpdate();
            System.out.println("Categorie ajouté!!!");
        } catch (SQLException ex) {
        }   
    }
    
   @Override
    public void modifierCategorie(Categoriesequipement Ct,int id) {
        try {
            String req = "UPDATE categoriesequipement SET nomcate =? where id=?";
            PreparedStatement pst = conn.prepareStatement(req);
            pst.setString(1, Ct.getNomcate());
            pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("categoriesequipement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  
   @Override
    public void supprimerCategorie(int id) {
        try {
            String req = "DELETE FROM `categoriesequipement` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Equipement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

    
   @Override
    public List<Categoriesequipement> afficherCategorie() {
       List<Categoriesequipement> list = new ArrayList<>();
        try {
            String req = "Select * from categoriesequipement";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Categoriesequipement Ct = new Categoriesequipement();
             Ct.setNomcate(RS.getString("nomcate"));
             Ct.setId(RS.getInt(1));
             
             list.add(Ct);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    public Categoriesequipement getCategoriesequipementById(int categorie_id) {
    Categoriesequipement categorie = null;
    try {
        String query = "SELECT * FROM categoriesequipement WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, categorie_id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            categorie = new Categoriesequipement();
            categorie.setId(rs.getInt("id"));
            categorie.setNomcate(rs.getString("nomcate"));
            
        }
    } catch (SQLException ex) {
        System.out.println("Error while getting categoriesequipement by id: " + ex.getMessage());
    }
    return categorie;
}
    
}

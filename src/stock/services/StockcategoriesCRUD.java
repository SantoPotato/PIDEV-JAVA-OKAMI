/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.services;

import stock.entities.Stockcategories;
import stock.entities.Stock;
import stock.entities.Stockcategories;
import stock.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Statement;
import stock.services.StockCRUD;

/**
 *

 */
public class StockcategoriesCRUD {

    Connection cnx;
   // @Override
    
   public void Ajouterc(Stockcategories t) {
        try {
        String qry ="INSERT INTO `stockcategories`(`id`,`typecat`) VALUES ('"+t.getId()+"','"+t.getTypecat ()+"')";
     cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
                
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    
    }

   
    public List<Stockcategories> Afficherc() {
        List<Stockcategories> stockcategories = new ArrayList<>();
      try {
            String qry ="SELECT * FROM `stockcategories` ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Stockcategories p =new Stockcategories();
                
                
                p.setTypecat(rs.getString("typecat"));
                p.setId(rs.getInt(1));
                
              

                stockcategories.add(p);

            }
            return stockcategories;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stockcategories;
        
        
        
        
    }
    
    
   
      //@Override
    public void Supprimerc(int id) {
try {
            String qry ="DELETE from stockcategories where id = "+id+";";
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    }

    
  
   // @Override
    public void modifier(Stockcategories t) {
 try {
     String qry ="UPDATE stockcategories SET `typecat`='"+t.getTypecat()+"' WHERE id="+t.getId()+";";   
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        }
 
  
    public List<Stockcategories> AffichercParId(int id) {
        List<Stockcategories> stockcategories = new ArrayList<>();
      try {
            String qry ="Select * from stockcategories  where id =" +id;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Stockcategories p =new Stockcategories();
                p.setId(rs.getInt(1));
                p.setTypecat(rs.getString(2));
                
               

                stockcategories.add(p);

            }
            return stockcategories;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stockcategories;
        
        
        
        
    }
    
    
    
    public List<Stockcategories> AffichercParNom() {
        List<Stockcategories> stockcategories = new ArrayList<>();
      try {
            String qry ="Select * from stockcategories  where typecat ='ghg'" ;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Stockcategories p =new Stockcategories();
                p.setId(rs.getInt(1));
                p.setTypecat(rs.getString(2));
                
              

                stockcategories.add(p);

            }
            return stockcategories;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stockcategories;
        
        
        
        
    }
    }


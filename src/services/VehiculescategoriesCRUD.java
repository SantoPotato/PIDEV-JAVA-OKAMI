/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorievehicules;
import utils.MyDB;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

/**
 *

 */
public class VehiculescategoriesCRUD {

    Connection cnx;
   // @Override
    
   public void Ajouterc(Categorievehicules t) {
        try {
        String qry ="INSERT INTO `categoriesvehicules`(`id`,`typecatv`) VALUES ('"+t.getId()+"','"+t.getTypecatv ()+"')";
     cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
                
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    
    }

   
    public List<Categorievehicules> Afficherc() {
        List<Categorievehicules> Categorievehicules = new ArrayList<>();
      try {
            String qry ="SELECT * FROM `categoriesvehicules` ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Categorievehicules p =new Categorievehicules();
                
                
                p.setTypecatv(rs.getString("typecatv"));
                p.setId(rs.getInt(1));
                
              

                Categorievehicules.add(p);

            }
            return Categorievehicules;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Categorievehicules;
        
        
        
        
    }
    
    
   
      //@Override
    public void Supprimerc(int id) {
try {
            String qry ="DELETE from categoriesvehicules where id = "+id+";";
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    }

    
  
   // @Override
    public void modifier(Categorievehicules t,int id) {
 try {
     String qry ="UPDATE categoriesvehicules SET `typecatv`='"+t.getTypecatv()+"' WHERE id="+t.getId()+";";   
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        }
 
  
    public List<Categorievehicules> AffichercParId(int id) {
        List<Categorievehicules> Categorievehicules = new ArrayList<>();
      try {
            String qry ="Select * from categoriesvehicules  where id =" +id;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Categorievehicules p =new Categorievehicules();
                p.setId(rs.getInt(1));
                p.setTypecatv(rs.getString(2));
                
               

                Categorievehicules.add(p);

            }
            return Categorievehicules;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Categorievehicules;
        
        
        
        
    }
    
    
    
    public List<Categorievehicules> AffichercParNom() {
        List<Categorievehicules> Categorievehicules = new ArrayList<>();
      try {
            String qry ="Select * from categoriesvehicules  where typecat =?" ;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Categorievehicules p =new Categorievehicules();
                p.setId(rs.getInt(1));
                p.setTypecatv(rs.getString(2));
                
              

                Categorievehicules.add(p);

            }
            return Categorievehicules;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Categorievehicules;
        
        
        
        
    }
    }


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salle.services;

import salle.entities.Salle;
import salle.utils.MyDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * 
 */
public class SalleCRUD  {
Connection cnx;
   // @Override
    
   public void AjouterSalle(Salle s) {
        try {
        String qry ="INSERT INTO `salle`(`numsa`,`etagesa`,`typesa`) VALUES ('"+s.getNumsa()+"','"+s.getEtagesa()+"','"+s.getTypesa()+"')";
     cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
                
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    
    }

   
    public List<Salle> AfficherSalle() {
        List<Salle> salles = new ArrayList<>();
      try {
            String qry ="SELECT * FROM `salle` ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Salle s =new Salle();
                
                s.setId(rs.getInt(1));
                s.setNumsa(rs.getInt(2));
                s.setEtagesa(rs.getInt(3));
                s.setTypesa(rs.getString(4));
              

                salles.add(s);

            }
            return salles;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return salles;
        
        
        
        
    }
    
   
      //@Override
    public void SupprimerSalle(int id) {
try {
            String qry ="DELETE from salle where id = "+id+";";
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    
    }

    
  
   // @Override
    public void modifierSalle(Salle s) {
 try {
     String qry ="UPDATE salle SET `numsa`='"+s.getNumsa()+"',`etagesa`='"+s.getEtagesa()+"',`typesa`='"+s.getTypesa()+"' WHERE id="+s.getId()+";";    
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        }
 
  
    public List<Salle> AfficherSalleParId(int id) {
        List<Salle> Salles = new ArrayList<>();
      try {
            String qry ="Select * from salle  where id =" +id;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Salle s =new Salle();
                s.setId(rs.getInt(1));
                s.setNumsa(rs.getInt(2));
                s.setEtagesa(rs.getInt(3));
                s.setTypesa(rs.getString(4));
               

                Salles.add(s);

            }
            return Salles;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Salles;
        
        
        
        
    }
    
    
    
    public List<Salle> AfficherSalleParNum() {
        List<Salle> Salles = new ArrayList<>();
      try {
            String qry ="Select * from salle  where numsa =5" ;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Salle s =new Salle();
                s.setId(rs.getInt(1));
                s.setNumsa(rs.getInt(2));
                s.setEtagesa(rs.getInt(3));
                s.setTypesa(rs.getString(4));
              

                Salles.add(s);

            }
            return Salles;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Salles;
    }
    
    public List<Integer> getAllSalles() {
    List<Integer> ids = new ArrayList<>();
    try {
        String qry = "SELECT id FROM salle";
        cnx = MyDB.getInstance().getCnx();
        Statement stm = cnx.createStatement();
        ResultSet rs = stm.executeQuery(qry);
        while (rs.next()) {
            ids.add(rs.getInt(1));
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return ids;
}

        /*public List<Salle> afficherDemandeparid(int id){
       List<Salle> list = new ArrayList<>();
       try {
           String req ="Select * from Evenements  where id =" +id;
           Statement st = cnx.createStatement();
           ResultSet RS = st.executeQuery(req);
           while (RS.next()) {
            Salle D = new Salle();
            D.setId(RS.getInt(1));
            D.setTitre(RS.getString(2));
            D.setDescription(RS.getString(3));
            D.setDate(RS.getDate("cv"));
            D.setPhoto(RS.getString("description"));
                      
              
                
            list.add(D);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }
   public List<Salle> afficherDemandeparcategorie (){
       List<Salle> list = new ArrayList<>();
       try {
           String req ="Select * from evenements  where traitement ='en cours de traitement'" ;
           
           Statement st = cnx.createStatement();
           ResultSet RS = st.executeQuery(req);
           
           while (RS.next()) {
            Salle D = new Salle();
            D.setId(RS.getInt(1));
            D.setTitre(RS.getString(2));
            D.setDescription(RS.getString(3));
            D.setDate(RS.getDate("cv"));
            D.setPhoto(RS.getString("description"));
                      
              
                
               list.add(D);
               
           }
       }catch (SQLException ex) {
           System.out.println(ex.getMessage());
   } 
      return list; 
       
  }*/
        
        
    }

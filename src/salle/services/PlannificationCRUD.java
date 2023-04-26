/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salle.services;

import salle.entities.Plannification;
import salle.entities.Salle;
import salle.entities.Plannification;
import salle.utils.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Statement;

/**
 *

 */
public class PlannificationCRUD {

    Connection cnx;
   // @Override
    
   public void AjouterPlannification(Plannification p) {
        try {
        String qry ="INSERT INTO `plannification`(`salle`,`datepl`,`heuredebutpl`,`heurefinpl`) VALUES ('"+p.getId_salle()+"','"+p.getDatepl()+"','"+p.getHeuredebutpl()+"','"+p.getHeurefinpl()+"')";
     cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
                
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
    
    }

   
    public List<Plannification> AfficherPlannification() {
        List<Plannification> plannifications = new ArrayList<>();
      try {
            String qry ="SELECT * FROM `plannification` ";
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Plannification p =new Plannification();
                
//                p.setId(rs.getInt(1));
//                p.setId_salle(rs.getInt(2));
//                p.setDatepl (rs.getDate(3));
//                p.setHeuredebutpl(rs.getTime(4));
//                p.setHeurefinpl(rs.getTime(5));
//              

                plannifications.add(p);

            }
            return plannifications;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return plannifications;
        
        
        
        
    }
    
   
      //@Override
    public void SupprimerPlannification(int id) {
try {
            String qry ="DELETE from plannification where id = "+id+";";
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }    }

    
  
   // @Override
    public void ModifierPlannification(Plannification p) {
 try {
     String qry ="UPDATE plannification SET `salle`='"+p.getId_salle()+"',`datepl`='"+p.getDatepl()+"',`heuredebutpl`='"+p.getHeuredebutpl()+"',`heurefinpl`='"+p.getHeurefinpl()+"' WHERE id="+p.getId()+";";    
            cnx = MyDB.getInstance().getCnx();
      
            Statement stm =cnx.createStatement();
            
            stm.executeUpdate(qry);
            
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
        }
 
  
   /* public List<Plannification> AfficherEvenementParId(int id) {
        List<Plannification> Inscriptions = new ArrayList<>();
      try {
            String qry ="Select * from Inscriptions  where id =" +id;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Plannification p =new Plannification();
                p.setId(rs.getInt(1));
                p.setUtilisateur_id(rs.getInt(2));
                p.setEvenements_id (rs.getInt(3));
                p.setDateinscrievent(rs.getDate(4));
                p.setDescins(rs.getString(5));
               

                Inscriptions.add(p);

            }
            return Inscriptions;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Inscriptions;
        
        
        
        
    }
    
    
    
    public List<Plannification> AfficherEvenementParNom() {
        List<Plannification> Inscriptions = new ArrayList<>();
      try {
            String qry ="Select * from Inscriptions  where descins ='zaeezazae'" ;
            cnx = MyDB.getInstance().getCnx();
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Plannification p =new Plannification();
                p.setId(rs.getInt(1));
                p.setUtilisateur_id(rs.getInt(2));
                p.setEvenements_id (rs.getInt(3));
                p.setDateinscrievent(rs.getDate(4));
                p.setDescins(rs.getString(5));
              

                Inscriptions.add(p);

            }
            return Inscriptions;
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Inscriptions;
        
        
        
        
    }*/
    }


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Categoriesequipement;
import Entities.Equipement;
import Interfaces.InterfaceCRUD;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EquipementCRUD implements InterfaceCRUD {
   Statement ste;
    Connection conn ;
    
   public EquipementCRUD() {
         conn = MyConnection.getInstance().getConn();
    }
   
   
 
      @Override
    public void ajouterEquipement2(Equipement e) {
 try {
            String req = "INSERT INTO `equipement` (`id`, `nomeq`, `etateq`, `dispoeq`, `Categoriesequipement`) VALUES (DEFAULT,?,?,?,?)";
            PreparedStatement ps=conn.prepareStatement(req);
          
            ps.setString(1, e.getNomeq());
            System.out.println(e.getNomeq());
            ps.setBoolean(2, e.getEtateq());
            ps.setBoolean(3, e.getDispoeq());
            ps.setInt(4, e.getCategoriesequipement().getId());
             ps.executeUpdate();
            System.out.println("equipement ajouté!!!");
        } catch (SQLException ex) {
            ex.printStackTrace();                    
        }   
    }
    
   @Override
    public void modifierequipement(Equipement e,int id) {
        try {
            String req = "UPDATE equipement SET nomeq =?,etateq=?,dispoeq=?,Categoriesequipement=? where id=?";
            PreparedStatement pst = conn.prepareStatement(req);
            pst.setString(1, e.getNomeq());
            pst.setBoolean(2, e.getEtateq());
            pst.setBoolean(3, e.getDispoeq());
            pst.setInt(4, e.getCategoriesequipement().getId());
            pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("Equipement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  
   @Override
    public void supprimerequipement(int id) {
        try {
            String req = "DELETE FROM `equipement` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Equipement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    

    
   @Override
    public List<Equipement> afficherEquipement() {
       List<Equipement> list = new ArrayList<>();
        try {
            String req = "Select e.*,c.* from equipement e INNER JOIN categoriesequipement c ON c.id=e.Categoriesequipement";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Categoriesequipement c = new Categoriesequipement(RS.getInt("c.id"),RS.getString("c.nomcate"));
             Equipement e = new Equipement();
             e.setNomeq(RS.getString("e.nomeq"));
             e.setId(RS.getInt("e.id"));
             e.setEtateq(RS.getBoolean("e.etateq"));
             e.setDispoeq(RS.getBoolean("e.dispoeq"));
             e.setCategoriesequipement(c);
             list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
  

    
    
     
    
} 


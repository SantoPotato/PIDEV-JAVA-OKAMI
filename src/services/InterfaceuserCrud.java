/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.user;
import java.util.List;

/**
 *
 * @author SONDESH
 */
public interface InterfaceuserCrud {
     public void ajouteruser(String nom, String prenom, String password, String email);
      public void deleteuser(int id);
      public void updateuser(int id,String nom,String prenom,String password,String email);
     
    
}

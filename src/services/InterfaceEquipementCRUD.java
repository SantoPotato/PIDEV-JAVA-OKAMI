/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Equipement;
import java.util.List;
/**
 *
 * @author SNAKE 2-16
 */
public interface InterfaceEquipementCRUD {
    
     public void ajouterEquipement2(Equipement e);
    public void modifierequipement(Equipement e,int id);
    public void supprimerequipement(int id) ;
    public List<Equipement> afficherEquipement();
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Entities.Categoriesequipement;
import Entities.Equipement;
import Services.CategoriesEquipementCRUD;
import Services.EquipementCRUD;

/**
 *
 * @author SNAKE 2-16
 */
public class PIDEV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String minouche="minouchette";
        // TODO code application logic here
        Categoriesequipement c = new Categoriesequipement (6,minouche);
        CategoriesEquipementCRUD ct = new CategoriesEquipementCRUD();
        ct.ajouterCategorie2(c);
        Equipement e = new Equipement(minouche,true,true,c);
        EquipementCRUD ep = new EquipementCRUD();
        
        ep.ajouterEquipement2(e);
    }
    
}

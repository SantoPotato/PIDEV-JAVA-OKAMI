/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import stock.entities.Vehicules;
import stock.entities.Stockcategories;
import java.util.List;

/**
 *
 * @author SNAKE 2-16
 */
public interface InterfaceCategorievehicules {
     public void ajouterCategorie(Stockcategories Ct);
     public void ajouterCategorie2(Stockcategories Ct);
    public void modifierCategorie(Stockcategories Ct);
    public void supprimerCategorie(int id) ;
    public List<Stockcategories> afficherCategorie();
}

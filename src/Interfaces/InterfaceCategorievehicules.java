/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import app.entities.Vehicules;
import app.entities.Categorievehicules;
import java.util.List;

/**
 *
 * @author SNAKE 2-16
 */
public interface InterfaceCategorievehicules {
     public void ajouterCategorie(Categorievehicules Ct);
     public void ajouterCategorie2(Categorievehicules Ct);
    public void modifierCategorie(Categorievehicules Ct);
    public void supprimerCategorie(int id) ;
    public List<Categorievehicules> afficherCategorie();
}

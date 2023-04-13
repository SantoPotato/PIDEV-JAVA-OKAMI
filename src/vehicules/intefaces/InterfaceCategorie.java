/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicules.intefaces;

import vehicules.entities.Categorie;
import java.util.List;


public interface InterfaceCategorie {

    public void ajouterCategorie(Categorie r);

    public void modifierCategorie(Categorie r);

    public void supprimerCategorie(int id);


    public List<Categorie> afficherCategorie();
}

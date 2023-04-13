/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicules.intefaces;

import vehicules.entities.Vehicules;
import java.util.List;


public interface InterfaceVehicules {

    public void ajouterEvenements(Vehicules r);

    public void modifierEvenements(Vehicules r);

    public void supprimerEvenements(int id);


    public List<Vehicules> afficherEvenements();
}

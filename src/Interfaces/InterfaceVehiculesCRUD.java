/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import app.entities.Vehicules;

import java.util.List;
/**
 *
 * @author SNAKE 2-16
 */
public interface InterfaceVehiculesCRUD {
    
     public void Ajoutert(Vehicules e);
    public void modifier(Vehicules e,int id);
    public void supprimer(int id) ;
    public List<Vehicules> Afficher();
   
}

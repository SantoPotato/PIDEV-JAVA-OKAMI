/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorievehicules;
import entities.Vehicules;

import java.util.List;

/**
 *
 * @author SNAKE 2-16
 */
public interface InterfaceVehiculesCRUD {

    public void Ajouter(Vehicules t);

    public void modifier(Vehicules e, int id);
    
    public List<Vehicules> Afficher();

    public Categorievehicules GetDoctorById(int Id);

    public void Supprimer(int id);

    public List<Vehicules> AfficherParId(int id);

    public List<Vehicules> AfficherParNom();

}

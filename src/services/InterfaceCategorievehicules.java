/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categorievehicules;
import java.util.List;

/**
 *
 * @author SNAKE 2-16
 */
public interface InterfaceCategorievehicules {

    public void Ajouterc(Categorievehicules t);

    public List<Categorievehicules> Afficherc();

    public void Supprimerc(int id);

    public void modifier(Categorievehicules t, int id);

    public List<Categorievehicules> AffichercParId(int id);

    public List<Categorievehicules> AffichercParNom();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Categoriesequipement;
import java.util.List;

/**
 *
 * @author SNAKE 2-16
 */
public interface InterfaceCategorieEquipement {
     public void ajouterCategorie(Categoriesequipement Ct);
     public void ajouterCategorie2(Categoriesequipement Ct);
    public void modifierCategorie(Categoriesequipement Ct,int id);
    public void supprimerCategorie(int id) ;
    public List<Categoriesequipement> afficherCategorie();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import stock.entities.Stock;
import stock.entities.Stockcategories;
import java.util.List;
/**
 *
 * @author SNAKE 2-16
 */
public interface InterfaceCRUD {
    
     public void Ajoutert(Stock e);
    public void modifier(Stock e,int id);
    public void supprimer(int id) ;
    public List<Stock> Afficher();
   
}

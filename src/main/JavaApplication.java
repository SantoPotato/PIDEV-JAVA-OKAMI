/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import stock.entities.Stock;
import stock.entities.Stockcategories;
import stock.services.StockCRUD;
import stock.services.StockcategoriesCRUD;
import java.sql.Date;

/**
 *
 * @author Dell
 */
public class JavaApplication {

    /**
     * @param args the command line arguments
     * DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate date = LocalDate.parse("25/08/2024", dateFormatter);

Stock ajouter = new Stock("amine", "hhhh", date, "3", 5);

     */
    public static void main(String[] args) {
        Date dateexpirationst = new Date (System.currentTimeMillis());
       
        //Stock ajouter = new Stock ("amine","hhhh",dateexpirationst,"3",5);
        //Stock modifier = new Stock (119,"amine","hhhh",dateexpirationst,3,50);
        StockCRUD E = new StockCRUD();
        
       
              Date dateinscrievent = new Date (System.currentTimeMillis());

         Stockcategories ajouterI = new Stockcategories ("balti");
               
        Stockcategories modifierI = new Stockcategories (13,"ghg");
        StockcategoriesCRUD I = new StockcategoriesCRUD();
        //I.Supprimerc(26);
        // I.Ajouterc(ajouterI);
        //I.modifier(modifierI);
        //System.out.println(I.Afficher());
        
       // E.Ajouter(ajouter);
        //System.out.println("Stock ajouté : " + ajouter.toString());
        
        System.out.println(E.Afficher());
        
        //E.Supprimer(111);
        
        //E.modifier(modifier);
        
        //System.out.println(E.AfficherParId(91));
        
        //System.out.println(E.AfficherParNom());
        //System.out.println(I.AffichercParNom());
        //System.out.println(E.AffichercParId(91));
    }
    
}

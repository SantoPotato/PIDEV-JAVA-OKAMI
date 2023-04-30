/*
 * Property of Okami�
 * Not destined for commercial use
 */
package tests;


import entities.Rendezvous;
import java.time.LocalDateTime;
import services.RendezvousCRUD;

/**
 *
 * @author 
 */
public class Main {
    
    
    public static void main(String[] args) { 
        RendezvousCRUD R = new RendezvousCRUD();
        Rendezvous rdv = new Rendezvous();
        rdv.setDaterv(LocalDateTime.now());
 
        
        System.out.println(rdv.toString());
        System.out.println(       R.searchRendezvous("aziz")           );
        
    }
    
}

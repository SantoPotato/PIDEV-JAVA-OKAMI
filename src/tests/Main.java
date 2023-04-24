/*
 * Property of Okami�
 * Not destined for commercial use
 */
package tests;


import entities.Rendezvous;
import java.util.Date;
import services.RendezvousCRUD;

/**
 *
 * @author 
 */
public class Main {
    
    
    public static void main(String[] args) { 
        RendezvousCRUD R = new RendezvousCRUD();
        
        Rendezvous r = new Rendezvous();
        Date d = new Date();
        r.setDaterv(d);

        Date d1 = new Date(d.getTime() + 3720 *1000);
        
        r.setEndAt(d1);
        
        Date duree = new Date(d1.getTime() - d.getTime());
        
        System.out.println(r.getDaterv());
        System.out.println(r.getEndAt());
        
        System.out.println(duree);
        System.out.println(r.showDuree());
        
    }
    
}

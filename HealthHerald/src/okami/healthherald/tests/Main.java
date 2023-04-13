/*
 * Property of Okami�
 * Not destined for commercial use
 */
package okami.healthherald.tests;


import okami.healthherald.services.RendezvousCRUD;

/**
 *
 * @author 
 */
public class Main {
    
    
    public static void main(String[] args) { 
        RendezvousCRUD R = new RendezvousCRUD();
        System.out.println(R.showAll());
        
        R.showAll().stream().map((r) -> {
            System.out.println(r);
            return r;
        }).forEachOrdered((r) -> {
            r.getUserCollection().forEach((u) -> {
                System.out.println("    " + u);
            });
        });
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.entities;

import java.sql.Date;

/**
 *
 
 */
public class Stockcategories {
   private int id;
    private String typecat;
    

    public Stockcategories( String typecat) {
        this.typecat = typecat;
        
    }

    public Stockcategories(int id, String typecat) {
        this.id = id;
        this.typecat = typecat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypecat() {
        return typecat;
    }

    public void setTypecat(String typecat) {
        this.typecat = typecat;
    }

    
   public Stockcategories() {
    }
    @Override
    public String toString() {
        return typecat;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}







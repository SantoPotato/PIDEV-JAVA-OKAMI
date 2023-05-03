/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


public class Categorievehicules {
private int id;
    private String typecatv;

    public Categorievehicules() {
    }

    public Categorievehicules(String typecatv) {
        this.typecatv = typecatv;
    }

    public Categorievehicules(int id, String typecatv) {
        this.id = id;
        this.typecatv = typecatv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypecatv() {
        return typecatv;
    }

    public void setTypecatv(String typecatv) {
        this.typecatv = typecatv;
    }

    @Override
    public String toString() {
        return  typecatv;
    }
   
}

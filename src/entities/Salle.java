/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salle.entities;
//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.util.Callback;


public class Salle {
  private int id,numsa,etagesa;
    private String typesa;

    public Salle(int numsa, int etagesa, String typesa) {
        this.numsa = numsa;
        this.etagesa = etagesa;
        this.typesa = typesa;
    }

    
    
    public Salle(int id, int numsa, int etagesa, String typesa) {
        this.id = id;
        this.numsa = numsa;
        this.etagesa = etagesa;
        this.typesa = typesa;
    }

    public Salle() {
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumsa() {
        return numsa;
    }

    public void setNumsa(int numsa) {
        this.numsa = numsa;
    }

    public int getEtagesa() {
        return etagesa;
    }

    public void setEtagesa(int etagesa) {
        this.etagesa = etagesa;
    }

    public String getTypesa() {
        return typesa;
    }

    public void setTypesa(String typesa) {
        this.typesa = typesa;
    }
    
    @Override
    public String toString() {
        return "Salle{" + "ID =" + id + ", Numéro =" + numsa + ", Etage=" + etagesa + ", Type =" + typesa + '}';
    }
    
    
}

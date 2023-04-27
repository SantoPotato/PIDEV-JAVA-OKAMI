/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.entities;
//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.util.Callback;


public class Vehicules {

     private int id;
    private Categorievehicules catv_id;
    private String nomvh;
    private boolean dispovh;
    private String etatvh;
    private String descvh;

    public Vehicules(int id, Categorievehicules catv_id, String nomvh, boolean dispovh, String etatvh, String descvh) {
        this.id = id;
        this.catv_id = catv_id;
        this.nomvh = nomvh;
        this.dispovh = dispovh;
        this.etatvh = etatvh;
        this.descvh = descvh;
    }

    public Vehicules(Categorievehicules catv_id, String nomvh, boolean dispovh, String etatvh, String descvh) {
        this.catv_id = catv_id;
        this.nomvh = nomvh;
        this.dispovh = dispovh;
        this.etatvh = etatvh;
        this.descvh = descvh;
    }

    public Vehicules() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorievehicules getCatv_id() {
        return catv_id;
    }

    public void setCatv_id(Categorievehicules catv_id) {
        this.catv_id = catv_id;
    }

    public String getNomvh() {
        return nomvh;
    }

    public void setNomvh(String nomvh) {
        this.nomvh = nomvh;
    }

    public boolean isDispovh() {
        return dispovh;
    }

    public void setDispovh(boolean dispovh) {
        this.dispovh = dispovh;
    }

    public String getEtatvh() {
        return etatvh;
    }

    public void setEtatvh(String etatvh) {
        this.etatvh = etatvh;
    }

    public String getDescvh() {
        return descvh;
    }

    public void setDescvh(String descvh) {
        this.descvh = descvh;
    }

    @Override
    public String toString() {
        return "Vehicules{" + "id=" + id + ", catv_id=" + catv_id.getTypecatv() + ", nomvh=" + nomvh + ", dispovh=" + dispovh + ", etatvh=" + etatvh + ", descvh=" + descvh + '}';
    }
    
    
    
    
    
     

    
    
    
    
    
    
    
}

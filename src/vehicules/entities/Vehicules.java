/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicules.entities;
//import java.util.Date;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SingleSelectionModel;
import javafx.util.Callback;


public class Vehicules {
    private int id;
    private String catv_id;
    private String nomvh;
    private int dispovh;
    private String etatvh;
    private String descvh;
    private String imagesvh;
    private String date;

    public Vehicules(int id, String catv_id, String nomvh, int dispovh, String etatvh, String descvh, String imagesvh, String date) {
        this.id = id;
        this.catv_id = catv_id;
        this.nomvh = nomvh;
        this.dispovh = dispovh;
        this.etatvh = etatvh;
        this.descvh = descvh;
        this.imagesvh = imagesvh;
        this.date = date;
    }

    public Vehicules(String catv_id, String nomvh, int dispovh, String etatvh, String descvh, String imagesvh, String date) {
        this.catv_id = catv_id;
        this.nomvh = nomvh;
        this.dispovh = dispovh;
        this.etatvh = etatvh;
        this.descvh = descvh;
        this.imagesvh = imagesvh;
        this.date = date;
    }

    public Vehicules() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCatv_id() {
        return catv_id;
    }

    public void setCatv_id(String catv_id) {
        this.catv_id = catv_id;
    }

    public String getNomvh() {
        return nomvh;
    }

    public void setNomvh(String nomvh) {
        this.nomvh = nomvh;
    }

    public int getDispovh() {
        return dispovh;
    }

    public void setDispovh(int dispovh) {
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

    public String getImagesvh() {
        return imagesvh;
    }

    public void setImagesvh(String imagesvh) {
        this.imagesvh = imagesvh;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vehicules{" + "id=" + id + ", catv_id=" + catv_id + ", nomvh=" + nomvh + ", dispovh=" + dispovh + ", etatvh=" + etatvh + ", descvh=" + descvh + ", imagesvh=" + imagesvh + ", date=" + date + '}';
    }

 

   
    
    
    
    
    
    
}

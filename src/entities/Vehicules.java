/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


public class Vehicules {

    private int id;
    private String nomvh;
    private int dispovh;
    private String etatvh;
    private String descvh;
    private String imagesvh;
    private String date;
    private Categorievehicules Categoriesvehicules;

    public Vehicules(int id, String nomvh, int dispovh, String etatvh, String descvh, String imagesvh, String date, Categorievehicules Categoriesvehicules) {
        this.id = id;
        this.nomvh = nomvh;
        this.dispovh = dispovh;
        this.etatvh = etatvh;
        this.descvh = descvh;
        this.imagesvh = imagesvh;
        this.date = date;
        this.Categoriesvehicules = Categoriesvehicules;
    }

    public Vehicules(String nomvh, int dispovh, String etatvh, String descvh, String imagesvh, String date, Categorievehicules Categoriesvehicules) {
        this.nomvh = nomvh;
        this.dispovh = dispovh;
        this.etatvh = etatvh;
        this.descvh = descvh;
        this.imagesvh = imagesvh;
        this.date = date;
        this.Categoriesvehicules = Categoriesvehicules;
    }

    public Vehicules() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomvh() {
        return nomvh;
    }

    public void setNomvh(String nomvh) {
        this.nomvh = nomvh;
    }

    public int isDispovh() {
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

    public Categorievehicules getCatv_id() {
        return Categoriesvehicules;
    }

    public void setCatv_id(Categorievehicules Categoriesvehicules) {
        this.Categoriesvehicules = Categoriesvehicules;
    }

    @Override
    public String toString() {
        return "Vehicules{" + "id=" + id + ", nomvh=" + nomvh + ", dispovh=" + dispovh + ", etatvh=" + etatvh + ", descvh=" + descvh + ", imagesvh=" + imagesvh + ", date=" + date + ", Categoriesvehicules=" + Categoriesvehicules.getTypecatv() + '}';
    }
    
    
    
    
    
     

    
    
    
    
    
    
    
}

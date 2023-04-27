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


public class Categorie {
private int id;
    private String typecatv;

    public Categorie(int id, String typecatv) {
        this.id = id;
        this.typecatv = typecatv;
    }

    public Categorie(String typecatv) {
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
        return "Categorie{" + "id=" + id + ", typecatv=" + typecatv + '}';
    }
    

   
    
    
}
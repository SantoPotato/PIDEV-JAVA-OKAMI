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


public class Stock {
private int id;
    private String nomst;
    private String description;
    private LocalDate dateexpirationst;
    private Stockcategories stockcat_id;
    private int quantites;

    public Stock(String nomst, String description, LocalDate dateexpirationst,Stockcategories stockcat_id, int quantites) {
        this.nomst = nomst;
        this.description = description;
        this.dateexpirationst = dateexpirationst;
        this.stockcat_id=stockcat_id;
        this.quantites = quantites;
    }
    
    

    public Stock(int id, String nomst, String description, LocalDate dateexpirationst, Stockcategories stockcat_id,int quantites) {
        this.id = id;
        this.nomst = nomst;
        this.description = description;
        this.dateexpirationst = dateexpirationst;
        this.stockcat_id= stockcat_id;
        this.quantites = quantites;
    }

    

    public Stock(int id, String description, LocalDate dateexpirationst, Stockcategories type, String quantites) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Stock(String nomst, String description, Date dateexpirationst, Stockcategories stockcat_id, int quantites) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

    

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomst() {
        return nomst;
    }

    public void setNomst(String nomst) {
        this.nomst = nomst;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateexpirationst() {
        return dateexpirationst;
    }

    public void setDateexpirationst(LocalDate dateexpirationst) {
        this.dateexpirationst = dateexpirationst;
    }
    
     public Stockcategories getStockcat_id() {
        return stockcat_id;
    }

    public void setStockcat_id(Stockcategories stockcat_id) {
        this.stockcat_id = stockcat_id;
    }

    public int getQuantites() {
        return quantites;
    }

    public void setQuantites(int quantites) {
        this.quantites = quantites;
    }
   
    
    

    public Stock() {
    }

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ","
                + " nomst=" + nomst + ","
                + " description=" + description + ","
                + " date=" + dateexpirationst + ","
                + " stockcat_id=" + stockcat_id.getTypecat()+ ","
                + " quantites=" + quantites + '}';
    }

    
    
    
    
    
    
    
    
    
    
    
    
}

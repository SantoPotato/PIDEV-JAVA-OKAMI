/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salle.entities;

import java.sql.Date;
import java.sql.Time;

/**
 *
 
 */
public class Plannification {
   private int id;
   private int id_salle;
   private Date datepl;
   private Time heuredebutpl;
   private Time heurefinpl;
   

    public Plannification(int id, int id_salle, Date datepl, Time heuredebutpl, Time heurefinpl, String descins) {
        this.id = id;
        this.id_salle = id_salle;
        this.datepl = datepl;
        this.heuredebutpl = heuredebutpl;
        this.heurefinpl = heurefinpl;
    }

    public Plannification(int id_salle, Date datepl, Time heuredebutpl, Time heurefinpl) {
        this.id_salle = id_salle;
        this.datepl = datepl;
        this.heuredebutpl = heuredebutpl;
        this.heurefinpl = heurefinpl;
    }
    

    public Plannification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public Date getDatepl() {
        return datepl;
    }

    public void setDatepl(Date datepl) {
        this.datepl = datepl;
    }

    public Time getHeuredebutpl() {
        return heuredebutpl;
    }

    public void setHeuredebutpl(Time heuredebutpl) {
        this.heuredebutpl = heuredebutpl;
    }

    public Time getHeurefinpl() {
        return heurefinpl;
    }

    public void setHeurefinpl(Time heurefinpl) {
        this.heurefinpl = heurefinpl;
    }

    @Override
    public String toString() {
        return "Plannification{" +
                "id=" + id +
                ", id_salle=" + id_salle +
                ", datepl=" + datepl +
                ", heuredebutpl=" + heuredebutpl +
                ", heurefinpl=" + heurefinpl +
                '}';
    }
}

    
    
    
    
    
    
    
    
    
    
    
    
    
}







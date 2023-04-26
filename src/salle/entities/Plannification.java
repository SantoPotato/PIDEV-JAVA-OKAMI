/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salle.entities;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

/**
 *
 
 */
public class Plannification {
   private int id;
   private int id_salle;
   private LocalDate datepl;
   private LocalDate heuredebutpl;
   private LocalDate heurefinpl;
   

    public Plannification(int id, int id_salle, LocalDate datepl, LocalDate heuredebutpl, LocalDate heurefinpl) {
        this.id = id;
        this.id_salle = id_salle;
        this.datepl = datepl;
        this.heuredebutpl = heuredebutpl;
        this.heurefinpl = heurefinpl;
    }

    public Plannification(int id_salle, LocalDate datepl, LocalDate heuredebutpl, LocalDate heurefinpl) {
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

    public LocalDate getDatepl() {
        return datepl;
    }

    public void setDatepl(LocalDate datepl) {
        this.datepl = datepl;
    }

    public LocalDate getHeuredebutpl() {
        return heuredebutpl;
    }

    public void setHeuredebutpl(LocalDate heuredebutpl) {
        this.heuredebutpl = heuredebutpl;
    }

    public LocalDate getHeurefinpl() {
        return heurefinpl;
    }

    public void setHeurefinpl(LocalDate heurefinpl) {
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

    
    
    
    
    
    
    
    
    
    
    
    
    








/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Collection;

/**
 *
 * @author SNAKE 2-16
 */

public class Categoriesequipement {
    
    private Integer id;
    private String nomcate;
    private Collection<Equipement> equipementCollection;

    public Categoriesequipement() {
    }

    public Categoriesequipement(Integer id) {
        this.id = id;
    }

    public Categoriesequipement(Integer id, String nomcate) {
        this.id = id;
        this.nomcate = nomcate;
    }

    public Categoriesequipement(String nom) {
        this.nomcate = nom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomcate() {
        return nomcate;
    }

    public void setNomcate(String nomcate) {
        this.nomcate = nomcate;
    }

    public Collection<Equipement> getEquipementCollection() {
        return equipementCollection;
    }

    public void setEquipementCollection(Collection<Equipement> equipementCollection) {
        this.equipementCollection = equipementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoriesequipement)) {
            return false;
        }
        Categoriesequipement other = (Categoriesequipement) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return   nomcate ;
    }
    
}

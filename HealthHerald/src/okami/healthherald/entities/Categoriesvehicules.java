/*
 * Property of Okami�
 * Not destined for commercial use
 */
package okami.healthherald.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author
 */
@Entity
@Table(name = "categoriesvehicules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoriesvehicules.findAll", query = "SELECT c FROM Categoriesvehicules c")
    , @NamedQuery(name = "Categoriesvehicules.findById", query = "SELECT c FROM Categoriesvehicules c WHERE c.id = :id")
    , @NamedQuery(name = "Categoriesvehicules.findByTypecatv", query = "SELECT c FROM Categoriesvehicules c WHERE c.typecatv = :typecatv")})
public class Categoriesvehicules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "typecatv")
    private String typecatv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriesvehicules")
    private Collection<Vehicules> vehiculesCollection;

    public Categoriesvehicules() {
    }

    public Categoriesvehicules(Integer id) {
        this.id = id;
    }

    public Categoriesvehicules(Integer id, String typecatv) {
        this.id = id;
        this.typecatv = typecatv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypecatv() {
        return typecatv;
    }

    public void setTypecatv(String typecatv) {
        this.typecatv = typecatv;
    }

    @XmlTransient
    public Collection<Vehicules> getVehiculesCollection() {
        return vehiculesCollection;
    }

    public void setVehiculesCollection(Collection<Vehicules> vehiculesCollection) {
        this.vehiculesCollection = vehiculesCollection;
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
        if (!(object instanceof Categoriesvehicules)) {
            return false;
        }
        Categoriesvehicules other = (Categoriesvehicules) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "okami.pidev.entities.Categoriesvehicules[ id=" + id + " ]";
    }
    
}

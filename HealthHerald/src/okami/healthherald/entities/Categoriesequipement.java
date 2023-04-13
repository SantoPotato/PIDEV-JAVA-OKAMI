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
@Table(name = "categoriesequipement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoriesequipement.findAll", query = "SELECT c FROM Categoriesequipement c")
    , @NamedQuery(name = "Categoriesequipement.findById", query = "SELECT c FROM Categoriesequipement c WHERE c.id = :id")
    , @NamedQuery(name = "Categoriesequipement.findByNomcate", query = "SELECT c FROM Categoriesequipement c WHERE c.nomcate = :nomcate")})
public class Categoriesequipement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nomcate")
    private String nomcate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "categoriesequipement")
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

    @XmlTransient
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
        return "okami.pidev.entities.Categoriesequipement[ id=" + id + " ]";
    }
    
}

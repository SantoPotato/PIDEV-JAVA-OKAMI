/*
 * Property of Okami�
 * Not destined for commercial use
 */
package okami.healthherald.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author
 */
@Entity
@Table(name = "equipement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipement.findAll", query = "SELECT e FROM Equipement e")
    , @NamedQuery(name = "Equipement.findById", query = "SELECT e FROM Equipement e WHERE e.id = :id")
    , @NamedQuery(name = "Equipement.findByNomeq", query = "SELECT e FROM Equipement e WHERE e.nomeq = :nomeq")
    , @NamedQuery(name = "Equipement.findByEtateq", query = "SELECT e FROM Equipement e WHERE e.etateq = :etateq")
    , @NamedQuery(name = "Equipement.findByDispoeq", query = "SELECT e FROM Equipement e WHERE e.dispoeq = :dispoeq")})
public class Equipement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nomeq")
    private String nomeq;
    @Basic(optional = false)
    @Column(name = "etateq")
    private boolean etateq;
    @Basic(optional = false)
    @Column(name = "dispoeq")
    private boolean dispoeq;
    @JoinColumn(name = "Categoriesequipement", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categoriesequipement categoriesequipement;

    public Equipement() {
    }

    public Equipement(Integer id) {
        this.id = id;
    }

    public Equipement(Integer id, String nomeq, boolean etateq, boolean dispoeq) {
        this.id = id;
        this.nomeq = nomeq;
        this.etateq = etateq;
        this.dispoeq = dispoeq;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeq() {
        return nomeq;
    }

    public void setNomeq(String nomeq) {
        this.nomeq = nomeq;
    }

    public boolean getEtateq() {
        return etateq;
    }

    public void setEtateq(boolean etateq) {
        this.etateq = etateq;
    }

    public boolean getDispoeq() {
        return dispoeq;
    }

    public void setDispoeq(boolean dispoeq) {
        this.dispoeq = dispoeq;
    }

    public Categoriesequipement getCategoriesequipement() {
        return categoriesequipement;
    }

    public void setCategoriesequipement(Categoriesequipement categoriesequipement) {
        this.categoriesequipement = categoriesequipement;
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
        if (!(object instanceof Equipement)) {
            return false;
        }
        Equipement other = (Equipement) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "okami.pidev.entities.Equipement[ id=" + id + " ]";
    }
    
}
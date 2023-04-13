/*
 * Property of Okami�
 * Not destined for commercial use
 */
package okami.healthherald.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author
 */
@Entity
@Table(name = "vehicules")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehicules.findAll", query = "SELECT v FROM Vehicules v")
    , @NamedQuery(name = "Vehicules.findById", query = "SELECT v FROM Vehicules v WHERE v.id = :id")
    , @NamedQuery(name = "Vehicules.findByNomvh", query = "SELECT v FROM Vehicules v WHERE v.nomvh = :nomvh")
    , @NamedQuery(name = "Vehicules.findByDispovh", query = "SELECT v FROM Vehicules v WHERE v.dispovh = :dispovh")
    , @NamedQuery(name = "Vehicules.findByEtatvh", query = "SELECT v FROM Vehicules v WHERE v.etatvh = :etatvh")
    , @NamedQuery(name = "Vehicules.findByDescvh", query = "SELECT v FROM Vehicules v WHERE v.descvh = :descvh")
    , @NamedQuery(name = "Vehicules.findByImagesvh", query = "SELECT v FROM Vehicules v WHERE v.imagesvh = :imagesvh")
    , @NamedQuery(name = "Vehicules.findByDate", query = "SELECT v FROM Vehicules v WHERE v.date = :date")})
public class Vehicules implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nomvh")
    private String nomvh;
    @Basic(optional = false)
    @Column(name = "dispovh")
    private boolean dispovh;
    @Basic(optional = false)
    @Column(name = "etatvh")
    private String etatvh;
    @Basic(optional = false)
    @Column(name = "descvh")
    private String descvh;
    @Column(name = "imagesvh")
    private String imagesvh;
    @Column(name = "[date]")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "Categoriesvehicules", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categoriesvehicules categoriesvehicules;

    public Vehicules() {
    }

    public Vehicules(Integer id) {
        this.id = id;
    }

    public Vehicules(Integer id, String nomvh, boolean dispovh, String etatvh, String descvh) {
        this.id = id;
        this.nomvh = nomvh;
        this.dispovh = dispovh;
        this.etatvh = etatvh;
        this.descvh = descvh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomvh() {
        return nomvh;
    }

    public void setNomvh(String nomvh) {
        this.nomvh = nomvh;
    }

    public boolean getDispovh() {
        return dispovh;
    }

    public void setDispovh(boolean dispovh) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Categoriesvehicules getCategoriesvehicules() {
        return categoriesvehicules;
    }

    public void setCategoriesvehicules(Categoriesvehicules categoriesvehicules) {
        this.categoriesvehicules = categoriesvehicules;
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
        if (!(object instanceof Vehicules)) {
            return false;
        }
        Vehicules other = (Vehicules) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "okami.pidev.entities.Vehicules[ id=" + id + " ]";
    }
    
}

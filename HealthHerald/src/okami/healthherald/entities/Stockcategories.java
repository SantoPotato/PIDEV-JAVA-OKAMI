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
@Table(name = "stockcategories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stockcategories.findAll", query = "SELECT s FROM Stockcategories s")
    , @NamedQuery(name = "Stockcategories.findById", query = "SELECT s FROM Stockcategories s WHERE s.id = :id")
    , @NamedQuery(name = "Stockcategories.findByTypecat", query = "SELECT s FROM Stockcategories s WHERE s.typecat = :typecat")})
public class Stockcategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "typecat")
    private String typecat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stockcategories")
    private Collection<Stock> stockCollection;

    public Stockcategories() {
    }

    public Stockcategories(Integer id) {
        this.id = id;
    }

    public Stockcategories(Integer id, String typecat) {
        this.id = id;
        this.typecat = typecat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypecat() {
        return typecat;
    }

    public void setTypecat(String typecat) {
        this.typecat = typecat;
    }

    @XmlTransient
    public Collection<Stock> getStockCollection() {
        return stockCollection;
    }

    public void setStockCollection(Collection<Stock> stockCollection) {
        this.stockCollection = stockCollection;
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
        if (!(object instanceof Stockcategories)) {
            return false;
        }
        Stockcategories other = (Stockcategories) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "okami.pidev.entities.Stockcategories[ id=" + id + " ]";
    }
    
}

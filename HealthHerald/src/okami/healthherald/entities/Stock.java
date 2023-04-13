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
@Table(name = "stock")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stock.findAll", query = "SELECT s FROM Stock s")
    , @NamedQuery(name = "Stock.findById", query = "SELECT s FROM Stock s WHERE s.id = :id")
    , @NamedQuery(name = "Stock.findByNomst", query = "SELECT s FROM Stock s WHERE s.nomst = :nomst")
    , @NamedQuery(name = "Stock.findByDescription", query = "SELECT s FROM Stock s WHERE s.description = :description")
    , @NamedQuery(name = "Stock.findByDateexpirationst", query = "SELECT s FROM Stock s WHERE s.dateexpirationst = :dateexpirationst")
    , @NamedQuery(name = "Stock.findByQuantites", query = "SELECT s FROM Stock s WHERE s.quantites = :quantites")})
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nomst")
    private String nomst;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "dateexpirationst")
    @Temporal(TemporalType.DATE)
    private Date dateexpirationst;
    @Basic(optional = false)
    @Column(name = "quantites")
    private int quantites;
    @JoinColumn(name = "Stockcategories", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Stockcategories stockcategories;

    public Stock() {
    }

    public Stock(Integer id) {
        this.id = id;
    }

    public Stock(Integer id, String nomst, String description, Date dateexpirationst, int quantites) {
        this.id = id;
        this.nomst = nomst;
        this.description = description;
        this.dateexpirationst = dateexpirationst;
        this.quantites = quantites;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Date getDateexpirationst() {
        return dateexpirationst;
    }

    public void setDateexpirationst(Date dateexpirationst) {
        this.dateexpirationst = dateexpirationst;
    }

    public int getQuantites() {
        return quantites;
    }

    public void setQuantites(int quantites) {
        this.quantites = quantites;
    }

    public Stockcategories getStockcategories() {
        return stockcategories;
    }

    public void setStockcategories(Stockcategories stockcategories) {
        this.stockcategories = stockcategories;
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
        if (!(object instanceof Stock)) {
            return false;
        }
        Stock other = (Stock) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "okami.pidev.entities.Stock[ id=" + id + " ]";
    }
    
}

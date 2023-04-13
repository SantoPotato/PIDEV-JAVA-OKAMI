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
@Table(name = "plannification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plannification.findAll", query = "SELECT p FROM Plannification p")
    , @NamedQuery(name = "Plannification.findById", query = "SELECT p FROM Plannification p WHERE p.id = :id")
    , @NamedQuery(name = "Plannification.findByDatepl", query = "SELECT p FROM Plannification p WHERE p.datepl = :datepl")
    , @NamedQuery(name = "Plannification.findByHeuredebutpl", query = "SELECT p FROM Plannification p WHERE p.heuredebutpl = :heuredebutpl")
    , @NamedQuery(name = "Plannification.findByHeurefinpl", query = "SELECT p FROM Plannification p WHERE p.heurefinpl = :heurefinpl")})
public class Plannification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "datepl")
    @Temporal(TemporalType.DATE)
    private Date datepl;
    @Basic(optional = false)
    @Column(name = "heuredebutpl")
    @Temporal(TemporalType.TIME)
    private Date heuredebutpl;
    @Basic(optional = false)
    @Column(name = "heurefinpl")
    @Temporal(TemporalType.TIME)
    private Date heurefinpl;
    @JoinColumn(name = "salle", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Salle salle;

    public Plannification() {
    }

    public Plannification(Integer id) {
        this.id = id;
    }

    public Plannification(Integer id, Date datepl, Date heuredebutpl, Date heurefinpl) {
        this.id = id;
        this.datepl = datepl;
        this.heuredebutpl = heuredebutpl;
        this.heurefinpl = heurefinpl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatepl() {
        return datepl;
    }

    public void setDatepl(Date datepl) {
        this.datepl = datepl;
    }

    public Date getHeuredebutpl() {
        return heuredebutpl;
    }

    public void setHeuredebutpl(Date heuredebutpl) {
        this.heuredebutpl = heuredebutpl;
    }

    public Date getHeurefinpl() {
        return heurefinpl;
    }

    public void setHeurefinpl(Date heurefinpl) {
        this.heurefinpl = heurefinpl;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
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
        if (!(object instanceof Plannification)) {
            return false;
        }
        Plannification other = (Plannification) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "okami.pidev.entities.Plannification[ id=" + id + " ]";
    }
    
}

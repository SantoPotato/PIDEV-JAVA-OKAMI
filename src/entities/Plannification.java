/*
 * Property of Okami�
 * Not destined for commercial use
 */
package entities;

import java.util.Date;

/**
 *
 * @author
 */
public class Plannification {

    private Integer id;
    private Date datepl;
    private Date heuredebutpl;
    private Date heurefinpl;
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
        return "entities.Plannification[ id=" + id + " ]";
    }

}

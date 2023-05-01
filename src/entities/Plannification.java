/*
 * Property of Okami�
 * Not destined for commercial use
 */
package entities;

import java.time.LocalDateTime;

/**
 *
 * @author
 */
public class Plannification {

    private Integer id;
    private LocalDateTime datepl;
    private LocalDateTime heuredebutpl;
    private LocalDateTime heurefinpl;
    private Salle salle;

    public Plannification() {
    }

    public Plannification(Integer id) {
        this.id = id;
    }

    public Plannification(Integer id, LocalDateTime datepl, LocalDateTime heuredebutpl, LocalDateTime heurefinpl) {
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

    public LocalDateTime getDatepl() {
        return datepl;
    }

    public void setDatepl(LocalDateTime datepl) {
        this.datepl = datepl;
    }

    public LocalDateTime getHeuredebutpl() {
        return heuredebutpl;
    }

    public void setHeuredebutpl(LocalDateTime heuredebutpl) {
        this.heuredebutpl = heuredebutpl;
    }

    public LocalDateTime getHeurefinpl() {
        return heurefinpl;
    }

    public void setHeurefinpl(LocalDateTime heurefinpl) {
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

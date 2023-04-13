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
@Table(name = "salle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salle.findAll", query = "SELECT s FROM Salle s")
    , @NamedQuery(name = "Salle.findById", query = "SELECT s FROM Salle s WHERE s.id = :id")
    , @NamedQuery(name = "Salle.findByNumsa", query = "SELECT s FROM Salle s WHERE s.numsa = :numsa")
    , @NamedQuery(name = "Salle.findByEtagesa", query = "SELECT s FROM Salle s WHERE s.etagesa = :etagesa")
    , @NamedQuery(name = "Salle.findByTypesa", query = "SELECT s FROM Salle s WHERE s.typesa = :typesa")})
public class Salle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "numsa")
    private int numsa;
    @Basic(optional = false)
    @Column(name = "etagesa")
    private int etagesa;
    @Basic(optional = false)
    @Column(name = "typesa")
    private String typesa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salle")
    private Collection<Rendezvous> rendezvousCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salle")
    private Collection<Plannification> plannificationCollection;

    public Salle() {
    }

    public Salle(Integer id) {
        this.id = id;
    }

    public Salle(Integer id, int numsa, int etagesa, String typesa) {
        this.id = id;
        this.numsa = numsa;
        this.etagesa = etagesa;
        this.typesa = typesa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumsa() {
        return numsa;
    }

    public void setNumsa(int numsa) {
        this.numsa = numsa;
    }

    public int getEtagesa() {
        return etagesa;
    }

    public void setEtagesa(int etagesa) {
        this.etagesa = etagesa;
    }

    public String getTypesa() {
        return typesa;
    }

    public void setTypesa(String typesa) {
        this.typesa = typesa;
    }

    @XmlTransient
    public Collection<Rendezvous> getRendezvousCollection() {
        return rendezvousCollection;
    }

    public void setRendezvousCollection(Collection<Rendezvous> rendezvousCollection) {
        this.rendezvousCollection = rendezvousCollection;
    }

    @XmlTransient
    public Collection<Plannification> getPlannificationCollection() {
        return plannificationCollection;
    }

    public void setPlannificationCollection(Collection<Plannification> plannificationCollection) {
        this.plannificationCollection = plannificationCollection;
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
        if (!(object instanceof Salle)) {
            return false;
        }
        Salle other = (Salle) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Salle " + etagesa + (numsa < 10 ? "0" + numsa : numsa);
    }
    
}

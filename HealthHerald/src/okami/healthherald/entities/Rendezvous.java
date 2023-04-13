/*
 * Property of Okami�
 * Not destined for commercial use
 */
package okami.healthherald.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author
 */
@Entity
@Table(name = "rendezvous")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rendezvous.findAll", query = "SELECT r FROM Rendezvous r")
    , @NamedQuery(name = "Rendezvous.findById", query = "SELECT r FROM Rendezvous r WHERE r.id = :id")
    , @NamedQuery(name = "Rendezvous.findByDaterv", query = "SELECT r FROM Rendezvous r WHERE r.daterv = :daterv")
    , @NamedQuery(name = "Rendezvous.findByRappel", query = "SELECT r FROM Rendezvous r WHERE r.rappel = :rappel")
    , @NamedQuery(name = "Rendezvous.findByEndAt", query = "SELECT r FROM Rendezvous r WHERE r.endAt = :endAt")})
public class Rendezvous implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "daterv")
    @Temporal(TemporalType.TIMESTAMP)
    private Date daterv;
    @Basic(optional = false)
    @Column(name = "rappel")
    private boolean rappel;
    @Basic(optional = false)
    @Column(name = "end_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endAt;
    @JoinTable(name = "rendezvous_user", joinColumns = {
        @JoinColumn(name = "rendezvous_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<User> userCollection;
    @JoinColumn(name = "Type", referencedColumnName = "id")
    @ManyToOne
    private RendezvousType type;
    @JoinColumn(name = "Salle", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Salle salle;

    public Rendezvous() {
    }

    public Rendezvous(Integer id) {
        this.id = id;
    }

    public Rendezvous(Integer id, Date daterv, Boolean rappel, Date endAt) {
        this.id = id;
        this.daterv = daterv;
        this.rappel = rappel;
        this.endAt = endAt;
        this.userCollection = new ArrayList<>();
    }
    
    public Rendezvous(Date daterv, Date endAt, Boolean rappel, Salle salle, RendezvousType type, Collection<User> userCollection) {
        this.daterv = daterv;
        this.rappel = rappel;
        this.endAt = endAt;
        this.salle = salle;
        this.type = type;
        this.userCollection = userCollection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDaterv() {
        return daterv;
    }

    public void setDaterv(Date daterv) {
        this.daterv = daterv;
    }

    public boolean getRappel() {
        return rappel;
    }

    public void setRappel(boolean rappel) {
        this.rappel = rappel;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }
    
    public void addUser(User user) {
        userCollection.add(user);
        //user.getRendezvousCollection().add(this);
    }

    public RendezvousType getType() {
        return type;
    }

    public void setType(RendezvousType type) {
        this.type = type;
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
        if (!(object instanceof Rendezvous)) {
            return false;
        }
        Rendezvous other = (Rendezvous) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Rendez-vous " + type + " le : " + String.format("%td/%tm/%tY", daterv, daterv, daterv) + " à " + String.format("%tH:%tM", daterv, daterv) + " en " + salle;
    }
    
}

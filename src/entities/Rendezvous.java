/*
 * Property of Okami�
 * Not destined for commercial use
 */
package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author
 */
public class Rendezvous {

    private Integer id;
    private Date daterv;
    private boolean rappel;
    private Date endAt;
    private Collection<User> userCollection;
    private RendezvousType type;
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
    
        public String showDuree()
    {
        Date duree = new Date(endAt.getTime() - daterv.getTime());
        int hours = Integer.parseInt(String.format("%tH", duree)) - 1;
        int minutes = Integer.parseInt(String.format("%tM", duree));
        String duree_string = "";
        if (hours > 0 && minutes > 0) {
            return duree_string + hours + " heures et " + minutes + " minutes";
        }
        else {
            if (hours > 0) {
                duree_string = duree_string + hours + " heures";
            }
            if (minutes > 0) {
                duree_string = duree_string + minutes + " minutes";
            }
        }
        return duree_string;
    }

}

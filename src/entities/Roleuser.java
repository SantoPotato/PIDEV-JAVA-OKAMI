/*
 * Property of Okami�
 * Not destined for commercial use
 */
package entities;

import java.util.Collection;

/**
 *
 * @author
 */
public class Roleuser {

    private Integer id;
    private String role;
    private Collection<User> userCollection;

    public Roleuser() {
    }

    public Roleuser(Integer id) {
        this.id = id;
    }

    public Roleuser(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
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
        if (!(object instanceof Roleuser)) {
            return false;
        }
        Roleuser other = (Roleuser) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "okami.pidev.entities.Roleuser[ id=" + id + " ]";
    }

}

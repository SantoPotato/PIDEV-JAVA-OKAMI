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
public class Historique {

    private Integer id;
    private String description;
    private Date date;
    private User userId;

    public Historique() {
    }

    public Historique(Integer id) {
        this.id = id;
    }

    public Historique(Integer id, String description, Date date) {
        this.id = id;
        this.description = description;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Historique)) {
            return false;
        }
        Historique other = (Historique) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "entities.Historique[ id=" + id + " ]";
    }

}

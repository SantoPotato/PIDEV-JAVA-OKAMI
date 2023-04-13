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
public class RendezvousType {

    private Integer id;
    private String type;
    private Collection<Rendezvous> rendezvousCollection;

    public RendezvousType() {
    }

    public RendezvousType(Integer id) {
        this.id = id;
    }

    public RendezvousType(String type) {
        this.type = type;
    }

    public RendezvousType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Rendezvous> getRendezvousCollection() {
        return rendezvousCollection;
    }

    public void setRendezvousCollection(Collection<Rendezvous> rendezvousCollection) {
        this.rendezvousCollection = rendezvousCollection;
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
        if (!(object instanceof RendezvousType)) {
            return false;
        }
        RendezvousType other = (RendezvousType) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return type;
    }

}

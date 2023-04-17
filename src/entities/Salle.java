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
public class Salle {

    private Integer id;
    private int numsa;
    private int etagesa;
    private String typesa;
    private Collection<Rendezvous> rendezvousCollection;
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

    public Collection<Rendezvous> getRendezvousCollection() {
        return rendezvousCollection;
    }

    public void setRendezvousCollection(Collection<Rendezvous> rendezvousCollection) {
        this.rendezvousCollection = rendezvousCollection;
    }

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

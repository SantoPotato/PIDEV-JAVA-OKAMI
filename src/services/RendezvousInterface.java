/*
 * Property of Okami�
 * Not destined for commercial use
 */
package services;

import entities.Rendezvous;
import java.util.List;

/**
 *
 * @author
 */
public interface RendezvousInterface {

    public void add(Rendezvous r);

    public void update(Rendezvous r, Integer id);

    public void remove(Integer id);

    public List<Rendezvous> showAll();
}

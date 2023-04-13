/*
 * Property of Okami�
 * Not destined for commercial use
 */
package services;

import entities.RendezvousType;
import java.util.List;

/**
 *
 * @author
 */
public interface RendezvousTypeInterface {

    public void add(RendezvousType t);

    public void update(RendezvousType t, Integer id);

    public void remove(Integer id);

    public List<RendezvousType> showAll();
}

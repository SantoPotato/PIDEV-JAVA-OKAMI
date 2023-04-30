/*
 * Property of Okami�
 * Not destined for commercial use
 */
package services;

import entities.Rendezvous;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 *
 * @author
 */
public interface RendezvousInterface {

    public void add(Rendezvous r);

    public void update(Rendezvous r, Integer id);

    public void remove(Integer id);

    public List<Rendezvous> showAll();
    
    public List<Rendezvous> getRendezvousByUser(LocalDateTime date, Integer userId);
    
    public List<Rendezvous> searchRendezvous(String value);
    
    public Map<Integer, Integer> statsRendezvous(LocalDateTime start, LocalDateTime end);
    
    public Map<String, Integer> statsRendezvousUser();
}

/*
 * Property of Okami�
 * Not destined for commercial use
 */
package services;

import entities.Historique;
import java.util.List;

/**
 *
 * @author ilyes
 */
public interface HistoriqueInterface {
    
    public void add(Integer user_id, String description);

    public void remove(Integer id);
    
    public List<Historique> showAll();
}

/*
 * Property of Okami�
 * Not destined for commercial use
 */
package utils;

import entities.Historique;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import services.HistoriqueCRUD;

/**
 *
 * @author
 */
public class HistoriqueMenuItem extends MenuItem {

    public HistoriqueMenuItem(Historique h) {
        
        Label label = new Label(h.toString());
        Button deleteButton = new Button("x");
        
        HBox hbox = new HBox(label, deleteButton);
        hbox.setAlignment(Pos.CENTER_LEFT);

        HBox.setHgrow(label, Priority.ALWAYS);
        HBox.setHgrow(deleteButton, Priority.NEVER);
        
        deleteButton.setOnAction(event -> {
            ((MenuButton) getParentPopup().getOwnerNode()).getItems().remove(this);
            HistoriqueCRUD hc = new HistoriqueCRUD();
            hc.remove(h.getId());
        });
        deleteButton.setStyle("-fx-background-color: none; -fx-font-weight: bold; -fx-text-fill:  #f41111");
        
        setGraphic(hbox);
        setStyle("-fx-background-color: transparent;  -fx-text-fill: white");
        
    }
}

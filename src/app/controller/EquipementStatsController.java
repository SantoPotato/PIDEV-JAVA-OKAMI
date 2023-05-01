/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import services.EquipementCRUD;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 * FXML Controller class
 *
 * @author SNAKE 2-16
 */
public class EquipementStatsController implements Initializable {

    @FXML
    private PieChart statetat;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

// Create an observable list of PieChart data
        EquipementCRUD ec = new EquipementCRUD();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        Map<String, Integer> stats = ec.getEquipementStatisticsByEtat();

// Iterate over your statistics map and add the data to the PieChart
        stats.entrySet().forEach((entry) -> {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        });
        statetat.setData(pieChartData);
        // TODO
    }

}

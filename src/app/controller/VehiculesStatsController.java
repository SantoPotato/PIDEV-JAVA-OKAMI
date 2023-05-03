/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import entities.Vehicules;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import services.VehiculesCRUD;
import java.sql.SQLException;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class VehiculesStatsController implements Initializable {

    VehiculesCRUD service = new VehiculesCRUD();
    @FXML
    private BarChart<String, Number> barChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            displayAppointmentCountsByDate(barChart);
        } catch (SQLException ex) {
        }
    }

    public Map<String, Integer> getAppointmentCountsByDate() throws SQLException {
        List<Vehicules> appointments = service.Afficher();
        Map<String, Integer> appointmentCountsByDate = new HashMap<>();
        appointments.stream().map((appointment) -> appointment.getCatv_id().toString()).forEachOrdered((date) -> {
            if (appointmentCountsByDate.containsKey(date)) {
                appointmentCountsByDate.put(date, appointmentCountsByDate.get(date) + 1);
            } else {
                appointmentCountsByDate.put(date, 1);
            }
        });
        return appointmentCountsByDate;
    }

    public void displayAppointmentCountsByDate(BarChart<String, Number> barChart) throws SQLException {
        Map<String, Integer> appointmentCountsByDate = getAppointmentCountsByDate();
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(" categories vehicules");
        appointmentCountsByDate.keySet().forEach((date) -> {
            series.getData().add(new XYChart.Data<>(date, appointmentCountsByDate.get(date)));
        });
        data.add(series);
        barChart.setData(data);
    }

    public BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("vehicule par categorieee");
        xAxis.setLabel("categorie");
        yAxis.setLabel("vehicule par categorieeeee");
        return barChart;
    }

    public VehiculesStatsController() {
        barChart = createBarChart();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.controller;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import services.ServiceUser;
import java.sql.SQLException;
import jfxtras.scene.control.agenda.Agenda.Appointment;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class StatController implements Initializable {

    ServiceUser service = new ServiceUser();
    @FXML
    private BarChart<String, Number> barChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            displayAppointmentCountsByDate(barChart);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Map<String, Integer> getAppointmentCountsByDate() throws SQLException {
        List<User> appointments = service.getAll();
        Map<String, Integer> appointmentCountsByDate = new HashMap<>();
        for (User appointment : appointments) {
            String date = appointment.getGender().toString();
            if (appointmentCountsByDate.containsKey(date)) {
                appointmentCountsByDate.put(date, appointmentCountsByDate.get(date) + 1);
            } else {
                appointmentCountsByDate.put(date, 1);
            }
        }
        return appointmentCountsByDate;
    }

    public void displayAppointmentCountsByDate(BarChart<String, Number> barChart) throws SQLException {
        Map<String, Integer> appointmentCountsByDate = getAppointmentCountsByDate();
        ObservableList<XYChart.Series<String, Number>> data = FXCollections.observableArrayList();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Nombre de rendez-vous");
        for (String date : appointmentCountsByDate.keySet()) {
            series.getData().add(new XYChart.Data<>(date, appointmentCountsByDate.get(date)));
        }
        data.add(series);
        barChart.setData(data);
    }

    public BarChart<String, Number> createBarChart() {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Nombre de rendez-vous par date");
        xAxis.setLabel("Date");
        yAxis.setLabel("Nombre de rendez-vous");
        return barChart;
    }

    public StatController() {
        barChart = createBarChart();
    }
}
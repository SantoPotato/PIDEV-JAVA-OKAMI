/*
 * Property of Okami�
 * Not destined for commercial use
 */
package app.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import services.RendezvousCRUD;

/**
 * FXML Controller class
 *
 * @author
 */
public class RendezvousStatsController implements Initializable {

    List<String> Months = new ArrayList<>();
    RendezvousCRUD rc = new RendezvousCRUD();

    @FXML
    private Button buttonIndex;
    @FXML
    private MenuItem buttonRendezvous;
    @FXML
    private MenuItem buttonRendezvousType;
    @FXML
    private MenuItem buttonRendezvousStatistique;
    @FXML
    private PieChart statsRendezvousUser;
    @FXML
    private BarChart<String, Integer> statsRendezvous;
    @FXML
    private Spinner<Integer> yearSelected;
    @FXML
    private MenuItem buttonRendezvousCalendrier;
    @FXML
    private MenuButton menuLanguage;
    @FXML
    private MenuItem menuEnglish;
    @FXML
    private MenuItem menuFrench;
    @FXML
    private Label labelStats;
    @FXML
    private MenuItem menuJapanese;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        Map<String, Integer> statsUser = rc.statsRendezvousUser();
        statsUser.entrySet().forEach((entry) -> {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        });
        statsRendezvousUser.setData(pieChartData);

        LocalDate now = LocalDate.now();

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1999, 2099);
        valueFactory.setValue(LocalDate.now().getYear());
        yearSelected.setValueFactory(valueFactory);
        yearSelected.valueProperty().addListener((ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) -> {
            setDataBarChart(rc, yearSelected.getValue());
        });

        changeLanguage(Locale.getDefault().toString());
    }

    @FXML
    private void redirectIndex(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/Index.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectRendezvous(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousIndex.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void redirectRendezvousType(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousTypeIndex.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void setDataBarChart(RendezvousCRUD rc, Integer selectedYear) {
        LocalDateTime start = LocalDateTime.of(selectedYear, 1, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(selectedYear, 12, 31, 23, 59, 59);
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        Map<Integer, Integer> stats = rc.statsRendezvous(start, end);
        for (Integer i = 1; i <= 12; i++) {
            if (stats.containsKey(i)) {
                series.getData().add(new XYChart.Data<>(Months.get(i - 1), stats.get(i)));
            } else {
                series.getData().add(new XYChart.Data<>(Months.get(i - 1), 0));
            }
        }
        statsRendezvous.getData().clear();
        statsRendezvous.getData().add(series);
    }

    @FXML
    private void redirectRendezvousCalendrier(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousCalendar.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void changeLanguageEnglish(ActionEvent event) {
        changeLanguage("en");
    }

    @FXML
    private void changeLanguageFrench(ActionEvent event) {
        changeLanguage("fr");
    }

    @FXML
    private void changeLanguageJapanese(ActionEvent event) {
        changeLanguage("jp");
    }

    private void changeLanguage(String lang) {
        Locale.setDefault(new Locale(lang));
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("src/app/localisation/ui_" + lang + ".properties"));
            Months = Arrays.asList(props.getProperty("barchartMonths").split(","));
            setDataBarChart(rc, yearSelected.getValue());
            statsRendezvousUser.setTitle(props.getProperty("piechartTitle"));
            statsRendezvous.setTitle(props.getProperty("barchartTitle"));
            labelStats.setText(props.getProperty("labelRendezvousStats"));
            buttonRendezvous.setText(props.getProperty("menuRendezvous"));
            buttonRendezvousType.setText(props.getProperty("menuRendezvousType"));
            buttonRendezvousStatistique.setText(props.getProperty("menuStats"));
            buttonRendezvousCalendrier.setText(props.getProperty("menuCalendar"));
            menuLanguage.setText(props.getProperty("Language"));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}

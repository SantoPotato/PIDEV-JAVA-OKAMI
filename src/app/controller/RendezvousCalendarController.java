/*
 * Property of Okami�
 * Not destined for commercial use
 */
package app.controller;

import com.calendarfx.view.CalendarView;
import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import services.RendezvousCRUD;

/**
 * FXML Controller class
 *
 * @author
 */
public class RendezvousCalendarController implements Initializable {

    @FXML
    private Button buttonIndex;
    @FXML
    private MenuItem buttonRendezvous;
    @FXML
    private MenuItem buttonRendezvousType;
    @FXML
    private MenuItem buttonRendezvousStatistique;
    @FXML
    private CalendarView calendarView;
    @FXML
    private MenuItem buttonRendezvousCalendrier;
    @FXML
    private MenuButton menuLanguage;
    @FXML
    private MenuItem menuEnglish;
    @FXML
    private MenuItem menuFrench;
    @FXML
    private MenuItem menuJapanese;
    @FXML
    private Label labelCalendar;


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // create a new calendar
        Calendar calendar = new Calendar("Calendar");
        
        // add some entries to the calendar
        RendezvousCRUD rc = new RendezvousCRUD();
        rc.showAll().forEach(item -> {
            Entry entry = new Entry(item.toString());
                entry.changeStartDate(item.getDaterv().toLocalDate());
                entry.changeStartTime(item.getDaterv().toLocalTime());
                entry.changeEndDate(item.getEndAt().toLocalDate());
                entry.changeEndTime(item.getEndAt().toLocalTime());
                calendar.addEntry(entry);

        });

        CalendarSource calendarSource = new CalendarSource("Rendez-Vous");
        calendarSource.getCalendars().add(calendar);
        calendarView.getCalendarSources().add(calendarSource);
        
        changeLanguage(Locale.getDefault().toString());

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

    @FXML
    private void redirectRendezvousStatistique(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/RendezvousStats.fxml"));
            buttonIndex.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
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
            labelCalendar.setText(props.getProperty("labelRendezvousCalendar"));
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

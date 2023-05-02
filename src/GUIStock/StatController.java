package GUIStock;

import com.sun.javafx.charts.Legend;
import java.io.IOException;
import stock.utils.MyDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;




public class StatController implements Initializable {

    @FXML
    private PieChart voy_stat;
    private Statement st;
    private ResultSet rs;
    private Connection cnx;
    ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    int n;
    @FXML
    private Button listback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cnx = MyDB.getInstance().getCnx();
        stat();
    }    

    private void stat() {
        try {
            String query = "SELECT COUNT(*), sc.typecat FROM Stock s INNER JOIN Stockcategories sc ON s.stockcat_id = sc.id GROUP BY s.stockcat_id;";
            PreparedStatement PreparedStatement = cnx.prepareStatement(query);
            rs = PreparedStatement.executeQuery();
            while (rs.next()) {               
                String typecat = rs.getString("typecat");
                int count = rs.getInt("COUNT(*)");
                data.add(new PieChart.Data(typecat + " (" + count + ")", count));
            }  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        
   voy_stat.setLegendVisible(true);
voy_stat.setLegendSide(Side.LEFT);
voy_stat.setData(data);
// Obtenir la légende
Legend legend = (Legend) voy_stat.lookup(".chart-legend");
if (legend != null) {
    // Définir la couleur de fond de la légende en noir
    legend.setStyle("-fx-background-color: #808080;");
}

}

    @FXML
    private void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Details.fxml"));
            listback.getScene().setRoot(loader.load());

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

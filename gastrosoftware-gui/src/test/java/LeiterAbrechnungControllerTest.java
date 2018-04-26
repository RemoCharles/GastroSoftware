import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.Bestellung;
import slgp.gastrosoftware.persister.BestellungDAO;
import slgp.gastrosoftware.persister.impl.BestellungDAOImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LeiterAbrechnungControllerTest implements Initializable {

    private static Logger logger = LogManager.getLogger(LeiterMitarbeiterControllerTest.class);

   @FXML
   private ComboBox<String> cmbMitarbeiter;

    @FXML
    private TableView<Bestellung> tblAbrechnung;

    @FXML
    private TableColumn<Bestellung, String> colTisch;

    @FXML
    private TableColumn<Bestellung, Integer> colBestellung;

    @FXML
    private TableColumn<Bestellung, String> colDatum;

    @FXML
    private TableColumn<Bestellung, Double> colSumme;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        BestellungDAO bestellungDaoTemp = new BestellungDAOImpl();
        try {
            List<Bestellung> bestellungList = bestellungDaoTemp.findAllBezahlt(true);

            ObservableList<Bestellung> bestellungObservableList = FXCollections.observableList(bestellungList);

            for (Bestellung Bes : bestellungObservableList) {
                logger.info(Bes);
            }



            colTisch.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("tisch"));
            colBestellung.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("anzahlKonsumartikel"));
            colDatum.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("datum"));
            colSumme.setCellValueFactory(new PropertyValueFactory<Bestellung, Double>("SummePreisKonsumartikel"));

            tblAbrechnung.setItems(bestellungObservableList);



        } catch (Exception e) {
            logger.error("Fehler beim Abruf der BestellungsListe ", e);
        }


    }

    private void mitarbeiterAuswahlLaden() throws Exception {
        
    }


    @FXML
    public void zurueck(ActionEvent event) throws Exception {
        Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
        Scene ma_interface_scene = new Scene(ma_interface_parent);
        Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ma_stage.setScene(ma_interface_scene);
        ma_stage.show();
    }

    public void mitarbeiterAuswahl(ActionEvent actionEvent) {
    }
}



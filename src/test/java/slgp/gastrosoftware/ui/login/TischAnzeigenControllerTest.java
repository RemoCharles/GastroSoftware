package slgp.gastrosoftware.ui.login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.zentrale.persister.api.BestellPositionDAO;
import slgp.gastrosoftware.zentrale.persister.domain.BestellPosition;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.impl.BestellPositionDAOImpl;
import slgp.gastrosoftware.zentrale.persister.impl.KonsumartikelDAOImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class TischAnzeigenControllerTest implements Initializable {
    private static final Logger logger = LogManager.getLogger(TischAnzeigenControllerTest.class);
    private static BestellPositionDAO bestellPositionDAO = new BestellPositionDAOImpl();
    private static KonsumartikelDAOImpl konsumartikelDAO = new KonsumartikelDAOImpl();

    @FXML
    private ComboBox<String> cmbKat;

    @FXML
    private TableView<BestellPosition> tblBestellPosition;

    @FXML
    private TableColumn<BestellPosition, String> konsKat;

    @FXML
    private TableColumn<BestellPosition, Integer> konsAnzahl;

    @FXML
    private Button butAkt;

    @FXML
    private Spinner spnAnzahl;

    @FXML
    private TableColumn<BestellPosition, String> konsBez;

    @FXML
    private TableColumn<BestellPosition, Double> konsPr;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {

            List<Konsumartikel> alleKonsumartikelListe = konsumartikelDAO.findAll();
            List<BestellPosition> alleBestellPositionListe = new ArrayList<>();

            for (Konsumartikel konsumartikel : alleKonsumartikelListe) {
                BestellPosition bestellPosition = new BestellPosition(konsumartikel, 0);
                alleBestellPositionListe.add(bestellPosition);
            }

            for (BestellPosition bestellPosition : alleBestellPositionListe) {
                logger.info(bestellPosition);
            }
            TreeSet<String> konsumartikelKategorie = new TreeSet<>();

            for (Konsumartikel kBez : alleKonsumartikelListe) {
                konsumartikelKategorie.add(kBez.getKategorie());
            }

            ObservableList<String> konsumArtikelBezeichnungsListe = FXCollections.observableArrayList();
            konsumArtikelBezeichnungsListe.addAll(konsumartikelKategorie);
            cmbKat.setItems(konsumArtikelBezeichnungsListe);

            if (konsumArtikelBezeichnungsListe.size() > 0) {
                cmbKat.getSelectionModel().select(0);
            }

            logger.info("Konsumartikelbezeichnung liste länge: " + konsumArtikelBezeichnungsListe.size());
            logger.info("KonsumartikelKategorieListe" + konsumartikelKategorie.size());
            logger.info("Alle KonsumartikelListe" +alleKonsumartikelListe.size());
            logger.info("Alle BestellPositionen: " + alleBestellPositionListe);

            /* TableView konfigurieren */
            konsKat.setCellValueFactory(new PropertyValueFactory<BestellPosition, String>("kategorie"));
            konsBez.setCellValueFactory(new PropertyValueFactory<BestellPosition, String>("bezeichnung"));
            konsPr.setCellValueFactory(new PropertyValueFactory<BestellPosition, Double>("preis"));
            konsAnzahl.setCellValueFactory(new PropertyValueFactory<BestellPosition, Integer>("anzahl"));

            ObservableList<BestellPosition> bestellPositionListe = FXCollections.observableArrayList();
            bestellPositionListe.addAll(alleBestellPositionListe);
            logger.info("bestellPositionListe länge:" + bestellPositionListe.size());

            tblBestellPosition.setItems(bestellPositionListe);

            updateTable();

        } catch (Exception e) {
            logger.error("Tabelle konnte nicht befüllt werden...", e);
        }

    }

    @FXML
    public void zurueck(ActionEvent event) throws Exception {
        Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/MaInterface.fxml"));
        Scene ma_interface_scene = new Scene(ma_interface_parent);
        Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ma_stage.setScene(ma_interface_scene);
        ma_stage.show();
    }


    @FXML
    private void updateTable() {
        try {
            /* cmbProduktTyp initialisieren */
            KonsumartikelDAOImpl KonsDAOImpl = new KonsumartikelDAOImpl();
            List<BestellPosition> alleBestellPosition = bestellPositionDAO.findAll();

            if (cmbKat.getSelectionModel().getSelectedItem() != null) {

                List<BestellPosition> tempListe = new ArrayList<>();

                /* Suche alle Konsumartikel-Objekte für den gewählten Namen */
                for (BestellPosition bestellPosition : alleBestellPosition) {

//                    if ( bestellPosition.getKonsumartikel().getKategorie().equals(cmbKat.getSelectionModel().getSelectedItem())) {
//                        tempListe.add(bestellPosition);
//                    }
                    tempListe.add(bestellPosition);
                }

                ObservableList<BestellPosition> bestellPositionListe = FXCollections.observableArrayList();
                bestellPositionListe.addAll(tempListe);
                tblBestellPosition.setItems(bestellPositionListe);
            }
        } catch (Exception e) {
            logger.error("Fehler beim Updaten der Tabelle: ", e);
            throw new RuntimeException();
        }

    }

}


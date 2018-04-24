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
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.BestellPosition;
import slgp.gastrosoftware.model.Esswaren;
import slgp.gastrosoftware.model.Getraenke;
import slgp.gastrosoftware.persister.BestellPositionDAO;
import slgp.gastrosoftware.persister.KonsumartikelDAO;
import slgp.gastrosoftware.persister.impl.BestellPositionDAOImpl;
import slgp.gastrosoftware.persister.impl.KonsumartikelDAOImpl;

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
    private ComboBox<String> cmbKategorie;

    @FXML
    private TableView<BestellPosition> tblBestellPosition;

    @FXML
    private TableColumn<BestellPosition, String> bPKat;

    @FXML
    private TableColumn<BestellPosition, Integer> bPAnzahl;

    @FXML
    private Spinner spnAnzahl;

    @FXML
    private TableColumn<BestellPosition, String> bPBez;

    @FXML
    private TableColumn<BestellPosition, Double> bPPreis;

    private List<BestellPosition> bestellPositionList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            kategorienAuswahlLaden();
            List<BestellPosition> bestellPositionList = bestellPositionDAO.findAll();

            ObservableList<BestellPosition> bestellPositionObservableList = FXCollections.observableList(bestellPositionList);

            for (BestellPosition bestellPosition : bestellPositionObservableList) {
                logger.info(bestellPosition);
            }

            bPBez.setCellValueFactory(new PropertyValueFactory<BestellPosition, String>("bezeichnung"));
            bPKat.setCellValueFactory(new PropertyValueFactory<BestellPosition, String>("kategorie"));
            bPAnzahl.setCellValueFactory(new PropertyValueFactory<BestellPosition, Integer>("anzahl"));
            bPPreis.setCellValueFactory(new PropertyValueFactory<BestellPosition, Double>("preis"));

            tblBestellPosition.setItems(bestellPositionObservableList);

            updateTable();
        } catch (Exception e) {
            logger.error("Tabelle konnte nicht befüllt werden...", e);
        }

    }

    @FXML
    private void updateTable() {
        try {
            List<BestellPosition> alleBestellPositionLoste = bestellPositionDAO.findAll();
            if (cmbKategorie.getSelectionModel().getSelectedItem() != null) {
                List<BestellPosition> tempListe = new ArrayList<>();

                for (BestellPosition bestellPosition : alleBestellPositionLoste) {
                    if (bestellPosition.getKategorie().equals(cmbKategorie.getSelectionModel().getSelectedItem())) {
                        tempListe.add(bestellPosition);
                    }
                }
                ObservableList<BestellPosition> bestellPositionObservableList = FXCollections.observableArrayList(tempListe);
                tblBestellPosition.setItems(bestellPositionObservableList);
            }
        } catch (Exception e) {
            logger.error("Fehler beim Updaten der Tabelle: ", e);
            throw new RuntimeException();
        }
    }

    @FXML
    private void updateKategorieAuswahl() throws Exception{
        TreeSet<String> bestellPositionKategorie = new TreeSet<>();
        for (BestellPosition bestellPosition : bestellPositionDAO.findAll()) {
            bestellPositionKategorie.add(bestellPosition.getKonsumartikel().getKategorie());
        }
        cmbKat.getSelectionModel().getSelectedItem().startsWith("E");
    }

    @FXML
    private void kategorienAuswahlLaden() throws Exception {
        BestellPositionDAO bestellPositionDAO = new BestellPositionDAOImpl();

        //Klassen Kategorie ComboBox füllen
        TreeSet<String> bestellPositionKlasse = new TreeSet<>();
        for (BestellPosition bestellPosition : bestellPositionDAO.findAll()) {
            bestellPositionKlasse.add(bestellPosition.getKonsumartikel().getClass().getSimpleName());
        }
        ObservableList<String> konsumArtikelBezeichnungList = FXCollections.observableArrayList(bestellPositionKlasse);
        cmbKat.setItems(konsumArtikelBezeichnungList);
        if (konsumArtikelBezeichnungList.size() > 0) {
            cmbKat.getSelectionModel().select(0);
        }

        //Kategorie ComboBox füllen
        TreeSet<String> bestellPositionKategorie = new TreeSet<>();
        List<BestellPosition> bestellPositionListGetraenke = new ArrayList<>();
        List<BestellPosition> bestellPositionListEsswaren = new ArrayList<>();
        for (BestellPosition bestellPosition : bestellPositionDAO.findAll()) {
            if (bestellPosition.getKonsumartikel().getClass().equals(Esswaren.class)) {
                bestellPositionListEsswaren.add(bestellPosition);
                logger.info("Alle Esswaren gefilter");
            } else if (bestellPosition.getKonsumartikel().getClass().equals(Getraenke.class)) {
                bestellPositionListGetraenke.add(bestellPosition);
                logger.info("Alle Getraenke gefilter");
            }
        }

        if (cmbKat.getSelectionModel().getSelectedItem().startsWith("E")) {
            for (BestellPosition bestellPosition : bestellPositionListEsswaren) {
                bestellPositionKategorie.add(bestellPosition.getKategorie());
                logger.info("Bestellposition Esswaren: " + bestellPosition);
            }
        } else {
            for (BestellPosition bestellPosition : bestellPositionListGetraenke) {
                bestellPositionKategorie.add(bestellPosition.getKategorie());
                logger.info("Bestellposition Getränke: " + bestellPosition);
            }
        }

        logger.info("Bestellposition nach Filterung: " + bestellPositionKategorie);

        ObservableList<String> konsumArtikelKategorieListe = FXCollections.observableArrayList(bestellPositionKategorie);
        cmbKategorie.setItems(konsumArtikelKategorieListe);
        if (konsumArtikelKategorieListe.size() > 0) {
            cmbKategorie.getSelectionModel().select(0);
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

    public void setTischNummer(int tischNummer) {

        System.out.println(tischNummer);
    }
}



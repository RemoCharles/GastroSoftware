package slgp.gastrosoftware.ui.login;

import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.impl.KonsumartikelDAOImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class TischAnzeigenControllerTest implements Initializable {
    private static final Logger logger = LogManager.getLogger(TischAnzeigenControllerTest.class);

    @FXML
    private ComboBox<String> cmbKat;

    @FXML
    private TableView<Konsumartikel> tblKonsumartikel;

    @FXML
    private TableColumn<Konsumartikel, String> konsKat;

    @FXML
    private TableColumn konsAnzahl;

    @FXML
    private Button butAkt;

    @FXML
    private Spinner spnAnzahl;

    @FXML
    private TableColumn<Konsumartikel, String> konsBez;

    @FXML
    private TableColumn<Konsumartikel, Double> konsPr;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            KonsumartikelDAOImpl KonsDAOImpl = new KonsumartikelDAOImpl();
            List<Konsumartikel> alleKonsumartikelListe = KonsDAOImpl.findAll();

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

            /* TableView konfigurieren */
            konsKat.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("kategorie"));
            konsBez.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("bezeichnung"));
            konsPr.setCellValueFactory(new PropertyValueFactory<Konsumartikel, Double>("preis"));
            //konsAnzahl.setCellValueFactory();

            ObservableList<Konsumartikel> konsumartikelListe = FXCollections.observableArrayList();
            konsumartikelListe.addAll(alleKonsumartikelListe);
            tblKonsumartikel.setItems(konsumartikelListe);

            updateTable();

        } catch (Exception e) {
            logger.error("Tabelle konnte nicht befüllt werden...");
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

    public void tblBefuellen() throws Exception {

        try {
            /* Konsumartikel initialisieren */
            KonsumartikelDAOImpl KonsDAOImpl = new KonsumartikelDAOImpl();
            List<Konsumartikel> alleKonsumartikelListe = KonsDAOImpl.findAll();

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

            /* TableView konfigurieren */
            konsKat.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("kategorie"));
            konsBez.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("bezeichnung"));
            konsPr.setCellValueFactory(new PropertyValueFactory<Konsumartikel, Double>("preis"));

            ObservableList<Konsumartikel> konsumartikelListe = FXCollections.observableArrayList();
            konsumartikelListe.addAll(alleKonsumartikelListe);
            tblKonsumartikel.setItems(konsumartikelListe);

            updateTable();

        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @FXML
    private void updateTable() {

        try {

            /* cmbProduktTyp initialisieren */
            KonsumartikelDAOImpl KonsDAOImpl = new KonsumartikelDAOImpl();
            List<Konsumartikel> alleKonsumartikelListe = KonsDAOImpl.findAll();

            if (cmbKat.getSelectionModel().getSelectedItem() != null) {

                List<Konsumartikel> tempListe = new ArrayList<>();

                /* Suche alle Konsumartikel-Objekte für den gewählten Namen */
                for (Konsumartikel konsArt : alleKonsumartikelListe) {

                    if (konsArt.getKategorie().equals(cmbKat.getSelectionModel().getSelectedItem())) {
                        tempListe.add(konsArt);
                    }
                }

                ObservableList<Konsumartikel> konsumartikelListe = FXCollections.observableArrayList();
                konsumartikelListe.addAll(tempListe);
                tblKonsumartikel.setItems(konsumartikelListe);
            }
        } catch (Exception e) {
            logger.error("Fehler beim Updaten der Tabelle: ", e);
            throw new RuntimeException();
        }

    }

}


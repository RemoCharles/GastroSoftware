import javafx.beans.value.ChangeListener;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.BestellPosition;
import slgp.gastrosoftware.model.Esswaren;
import slgp.gastrosoftware.model.Getraenke;
import slgp.gastrosoftware.persister.BestellPositionDAO;
import slgp.gastrosoftware.persister.impl.BestellPositionDAOImpl;
import slgp.gastrosoftware.persister.impl.KonsumartikelDAOImpl;
import slgp.gastrosoftware.persister.impl.TischDAOImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class TischAnzeigenControllerTest /*implements Initializable*/ {
    private static final Logger logger = LogManager.getLogger(TischAnzeigenControllerTest.class);
    private static BestellPositionDAO bestellPositionDAO = new BestellPositionDAOImpl();
    private static KonsumartikelDAOImpl konsumartikelDAO = new KonsumartikelDAOImpl();
    private static TischDAOImpl tischDAO = new TischDAOImpl();
    private int tischNummer;

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
    private Spinner<Integer> spnAnzahl;

    @FXML
    private TableColumn<BestellPosition, String> bPBez;

    @FXML
    private TableColumn<BestellPosition, Double> bPPreis;

    @FXML
    private TableView<Konsumartikel> tblAlleKonsumartikel;

    @FXML
    private TableColumn<Konsumartikel, String> konsBez;

    @FXML
    private TableColumn<Konsumartikel, String> konsKat;

    @FXML
    TableColumn<Konsumartikel, Integer> konsPreis;

    private List<BestellPosition> bestellPositionListGetraenke = new ArrayList<>();
    private List<BestellPosition> bestellPositionListEsswaren = new ArrayList<>();
    private TreeSet<String> bestellPositionKlasse = new TreeSet<>();

    private ObservableList<BestellPosition> bestellPositionObservableList;
    private BestellPosition selectedBestellPosition = null;
    private int tischNummer;

    public void initialize() {
        try {
            listenLaden();
            kategorienAuswahlLaden();
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99, 0);
            spnAnzahl.setValueFactory(valueFactory);

            List<Konsumartikel> konsumartikelList = konsumartikelDAO.findAll();
            List<BestellPosition> bestellPositionList = new ArrayList<>();

            bestellPositionObservableList = FXCollections.observableList(bestellPositionList);
            ObservableList<Konsumartikel> konsumartikelObservableList = FXCollections.observableList(konsumartikelList);


            bPBez.setCellValueFactory(new PropertyValueFactory<BestellPosition, String>("bezeichnung"));
            bPKat.setCellValueFactory(new PropertyValueFactory<BestellPosition, String>("kategorie"));
            bPAnzahl.setCellValueFactory(new PropertyValueFactory<BestellPosition, Integer>("anzahl"));
            bPPreis.setCellValueFactory(new PropertyValueFactory<BestellPosition, Double>("preis"));
            tblBestellPosition.setItems(bestellPositionObservableList);

            konsBez.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("bezeichnung"));
            konsKat.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("kategorie"));
            konsPreis.setCellValueFactory(new PropertyValueFactory<Konsumartikel, Integer>("preis"));
            tblAlleKonsumartikel.setItems(konsumartikelObservableList);

            spnAnzahl.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue<? extends Integer> observable,
                                    Integer oldValue, Integer newValue) {
                    if (selectedBestellPosition != null) {
                        selectedBestellPosition.setAnzahl(newValue);
                    }
                    tblBestellPosition.refresh();
                }
            });

            updateTable();
        } catch (Exception e) {
            logger.error("Tabelle konnte nicht befüllt werden...", e);
        }

    }


    @FXML
    private void updateSpinner() throws Exception {
        selectedBestellPosition = tblBestellPosition.getSelectionModel().getSelectedItem();
        spnAnzahl.getValueFactory().setValue(selectedBestellPosition.getAnzahl());
    }

    @FXML
    private void updateTable() throws Exception {
        try {
            List<Konsumartikel> konsumartikelList = konsumartikelDAO.findAll();
            List<Konsumartikel> tempListe = new ArrayList<>();
            if (cmbKategorie.getSelectionModel().getSelectedItem() != null) {

                for (Konsumartikel konsumartikel : konsumartikelList) {
                    if (konsumartikel.getKategorie().equals(cmbKategorie.getSelectionModel().getSelectedItem())) {
                        tempListe.add(konsumartikel);
                    }
                }
                ObservableList<Konsumartikel> konsumartikelObservableList = FXCollections.observableArrayList();
                konsumartikelObservableList.addAll(tempListe);
                tblAlleKonsumartikel.setItems(konsumartikelObservableList);
            }
        } catch (Exception e) {
            logger.error("Fehler beim Updaten der Tabelle: ", e);
            throw new RuntimeException();
        }
    }

    @FXML
    private void addToBestellPositionListe() {
        Konsumartikel konsumartikel = tblAlleKonsumartikel.getSelectionModel().getSelectedItem();
        BestellPosition bestellPosition = new BestellPosition(konsumartikel, 1);
        List<Konsumartikel> konsumartikelListAusBestellPosition = new ArrayList<>();
        for (BestellPosition bP : bestellPositionObservableList) {
            konsumartikelListAusBestellPosition.add(bP.getKonsumartikel());
        }
        if (!konsumartikelListAusBestellPosition.contains(konsumartikel)) {
            bestellPositionObservableList.add(bestellPosition);
        }
    }

    @FXML
    private void createNewBestellung() throws Exception {
        if (bestellPositionObservableList.size() > 0) {
            List<BestellPosition> bestellPositionList = new ArrayList<>();
            for (BestellPosition bestellPosition : bestellPositionObservableList) {
                bestellPositionList.add(bestellPosition);
            }
            Tisch tisch = tischDAO.findByTischNummer(tischNummer);

            Mitarbeiter mitarbeiter = Context.getInstance().getMitarbeiter();
            Bestellung bestellung = new Bestellung(mitarbeiter, tisch, bestellPositionList, false, false, LocalDate.now());
        }

        //Mitarbeiter mitarbeiter, Tisch tisch, List<BestellPosition> bestellPositionList, boolean zubereitet, boolean bezahlt, LocalDate datum


    }


    @FXML

    private void kategorienAuswahlLaden() throws Exception {
        TreeSet<String> bestellPositionKategorie = new TreeSet<>();
        if (cmbKat.getSelectionModel().getSelectedItem().startsWith("E")) {
            for (BestellPosition bestellPosition : bestellPositionListEsswaren) {
                bestellPositionKategorie.add(bestellPosition.getKategorie());
                logger.info("Bestellposition Essware: " + bestellPosition);
            }
        } else {
            for (BestellPosition bestellPosition : bestellPositionListGetraenke) {
                bestellPositionKategorie.add(bestellPosition.getKategorie());
                logger.info("Bestellposition Getränke: " + bestellPosition);
            }
        }
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
        this.tischNummer = tischNummer;
        System.out.println(tischNummer);
    }
    public int getTischNummer(){
        return tischNummer;
    }


    public void listenLaden() throws Exception {
        for (BestellPosition bestellPosition : bestellPositionDAO.findAll()) {
            if (bestellPosition.getKonsumartikel().getClass().equals(Esswaren.class)) {
                bestellPositionListEsswaren.add(bestellPosition);
                logger.info("Alle Esswaren gefiltert");
            } else if (bestellPosition.getKonsumartikel().getClass().equals(Getraenke.class)) {
                bestellPositionListGetraenke.add(bestellPosition);
                logger.info("Alle Getraenke gefiltert");
            }
        }
        for (BestellPosition bestellPosition : bestellPositionDAO.findAll()) {
            bestellPositionKlasse.add(bestellPosition.getKonsumartikel().getClass().getSimpleName());
        }
        ObservableList<String> konsumArtikelBezeichnungList = FXCollections.observableArrayList(bestellPositionKlasse);
        cmbKat.setItems(konsumArtikelBezeichnungList);
        if (konsumArtikelBezeichnungList.size() > 0) {
            cmbKat.getSelectionModel().select(0);
        }
    }

    public void TischRechnungAnzeigen(ActionEvent event) throws Exception {
        Parent tisch_rechnung_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/TischRechnungInterface.fxml"));
        Scene tisch_rechnung_interface_scene = new Scene(tisch_rechnung_interface_parent);
        Stage tisch_rechnung_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        tisch_rechnung_stage.setScene(tisch_rechnung_interface_scene);
        tisch_rechnung_stage.show();
    }
}



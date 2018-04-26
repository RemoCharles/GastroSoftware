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
import slgp.gastrosoftware.model.*;
import slgp.gastrosoftware.persister.BestellPositionDAO;
import slgp.gastrosoftware.persister.KonsumartikelDAO;
import slgp.gastrosoftware.persister.impl.BestellPositionDAOImpl;
import slgp.gastrosoftware.persister.impl.KonsumartikelDAOImpl;
import slgp.gastrosoftware.persister.impl.PersonDAOImpl;

import javax.xml.bind.annotation.XmlList;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class LeiterKonsumartikelControllerTest implements Initializable {
    private static final Logger logger = LogManager.getLogger(TischAnzeigenControllerTest.class);
    private static KonsumartikelDAOImpl konsumartikelDAO = new KonsumartikelDAOImpl();


    @FXML
    private ComboBox<String> cmbKat;

    @FXML
    private TableView<Konsumartikel> tblKonsumartikel;

    @FXML
    private TableColumn<Konsumartikel, String> bPKat;

    @FXML
    private Spinner spnAnzahl;

    @FXML
    private TableColumn<Konsumartikel, String> bPBez;

    @FXML
    private TableColumn<Konsumartikel, Double> bPPreis;

    @FXML
    private ComboBox cmbKat2;

    @FXML
    private ComboBox<String> cmbKategorieKuecheBar;

    @FXML
    private TextField lblBez;

    @FXML
    private TextField lblPreis;

    @FXML
    private CheckBox cbVerfuegbarkeit; //googlen mit Listener

    @FXML
    private Button btReActivate;

    @FXML
    private Label lblError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    try{
        kategorienAuswahlLaden();
        lblError.setText("");
        cbVerfuegbarkeit.setSelected(true);
        tabelleBefuellen();

        bPBez.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("bezeichnung"));
        bPKat.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("kategorie"));
        bPPreis.setCellValueFactory(new PropertyValueFactory<Konsumartikel, Double>("preis"));

        tblKonsumartikel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Konsumartikel>() {

            @Override
            public void changed(ObservableValue<? extends Konsumartikel> observable, Konsumartikel oldValue,
                                Konsumartikel newValue) {
                if (newValue != null) {
                    updateView();
                }
            }
        });

        //cbVerfuegbarkeit.selectedProperty().addListener((v, oldValue, newValue) -> verfuegbarkeitFiltern());
    }

     catch (Exception e) {
            logger.error("Tabelle konnte nicht befüllt werden...", e);
        }
    }

    @FXML
    public void updateTable() throws Exception {
        try {
            List<Konsumartikel> alleKonsumartikelList = new ArrayList<>();
            List<Konsumartikel> tempList = konsumartikelDAO.findAll();
            for(Konsumartikel kA : tempList){
                if(kA.getVerfuegbar()==true){
                    alleKonsumartikelList.add(kA);
                }
            }

            if (cmbKat.getSelectionModel().getSelectedItem() != null) {
                List<Konsumartikel> tempListe = new ArrayList<>();

                for (Konsumartikel kA : alleKonsumartikelList) {
                    if (kA.getKategorie().equals(cmbKat.getSelectionModel().getSelectedItem())) {
                        tempListe.add(kA);
                    } else if (cmbKat.getSelectionModel().getSelectedItem()=="Alle"){
                        tempListe.add(kA);
                    }
                }
                ObservableList<Konsumartikel> konsumartikelObservableList = FXCollections.observableArrayList(tempListe);
                tblKonsumartikel.setItems(konsumartikelObservableList);

                updateView();
            }
        } catch (Exception e) {
            logger.error("Fehler beim Updaten der Tabelle: ", e);
            throw new RuntimeException();
        }
    }
    @FXML
    private void updateView() {
        if (tblKonsumartikel.getSelectionModel().getSelectedItem() == null) {
            // cmbRolle.getSelectionModel().clearSelection();

            lblBez.setText("");
            lblPreis.setText("");
        } else {
            Konsumartikel kA = tblKonsumartikel.getSelectionModel().getSelectedItem();
            if(kA instanceof Esswaren){
                cmbKategorieKuecheBar.getSelectionModel().select(0);
            } else{
                cmbKategorieKuecheBar.getSelectionModel().select(1);
                }

            for(String s : getKategorienListe()){
                if(kA.getKategorie().equals(s)){
                    cmbKat2.getSelectionModel().select(kA.getKategorie());
                }
            }
            lblBez.setText(kA.getBezeichnung());
            String str = String.valueOf(kA.getPreis());
            lblPreis.setText(str);
        }

    }

    @FXML
    public void zurueck(ActionEvent event) throws Exception {
        Parent leiter_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
        Scene leiter_interface_scene = new Scene(leiter_interface_parent);
        Stage leiter_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        leiter_stage.setScene(leiter_interface_scene);
        leiter_stage.show();
    }

    private void kategorienAuswahlLaden() throws Exception {

    //Klassen Kategorie ComboBox füllen
    TreeSet<String> konsumartikelKategorie = new TreeSet<>();
        for (Konsumartikel kA : konsumartikelDAO.findAll()) {
            if(kA.getVerfuegbar()==true) {
                konsumartikelKategorie.add(kA.getKategorie());
            }
            konsumartikelKategorie.add("Alle");
    }

    ObservableList<String> konsumArtikelBezeichnungList = FXCollections.observableArrayList(konsumartikelKategorie);
        cmbKat.setItems(konsumArtikelBezeichnungList);
        if (konsumArtikelBezeichnungList.size() > 0) {
        cmbKat.getSelectionModel().select(0);
    }

        cmbKat2.setItems(getKategorienListe());
        cmbKat2.getSelectionModel().select(0);



        ObservableList<String> katKuecheBarList = FXCollections.observableArrayList();
        katKuecheBarList.add("Esswaren");
        katKuecheBarList.add("Getraenke");
        cmbKategorieKuecheBar.setItems(katKuecheBarList);
        cmbKategorieKuecheBar.getSelectionModel().select(0);

    }

    @FXML
    public void loeschen(ActionEvent event) throws Exception{
        if(tblKonsumartikel.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        Konsumartikel kA = tblKonsumartikel.getSelectionModel().getSelectedItem();


        if (kA != null) {
            try {
                kA.setVerfuegbar(false);
                konsumartikelDAO.update(kA);
                kategorienAuswahlLaden();
                tabelleBefuellen();
                System.out.println(kA.toString());
            } catch (Exception e) {
                logger.error("Fehler beim Löschen des Konsumartikels: ", e);
            }
        }
    }
    @FXML
    public void neuHinzufuegen(ActionEvent event) throws Exception{
        lblBez.setText("");
        lblPreis.setText("");

    }

    @FXML
    public void speichern(ActionEvent event) throws Exception{
        // Kontrolle ob dies funktioniert!

        if (eingabeValid()) {


            if (tblKonsumartikel.getSelectionModel().getSelectedItem() == null){

                // Neuer Konsumartikel anlegen
                String bez = lblBez.getText();
                double preis = Double.parseDouble(lblPreis.getText());
                String kategorie = cmbKat2.getSelectionModel().getSelectedItem().toString();
                String kuecheBarKategorie = cmbKategorieKuecheBar.getSelectionModel().getSelectedItem();
                System.out.println(bez + " " + preis + " "+ kategorie + " " + kuecheBarKategorie);
              try{

                if(kuecheBarKategorie == "Esswaren"){
                    Konsumartikel konsumartikelSpeichern = new Esswaren(bez, kategorie, preis);
                    System.out.println(konsumartikelSpeichern.toString());
                    konsumartikelDAO.save(konsumartikelSpeichern);
                } else {
                    Konsumartikel konsumartikelSpeichern = new Getraenke(bez, kategorie, preis);
                    System.out.println(konsumartikelSpeichern.toString());
                    konsumartikelDAO.save(konsumartikelSpeichern);
                }
                kategorienAuswahlLaden();
                tabelleBefuellen();

              } catch (Exception e) {
                    logger.error("Fehler beim Speichern des Konsumartikels: ", e);
                }
            } else {
                Konsumartikel  kA = tblKonsumartikel.getSelectionModel().getSelectedItem();
                String bez = lblBez.getText();
                String kategorie = cmbKat2.getSelectionModel().getSelectedItem().toString();
                String kuecheBarKategorie = cmbKategorieKuecheBar.getSelectionModel().getSelectedItem();

                double preis = Double.parseDouble(lblPreis.getText());

                //Ausgabe vom geänderter Artikel
                System.out.println(bez + " " + preis + " "+ kategorie + " " + kuecheBarKategorie);

                kA.setBezeichnung(bez);
                kA.setKategorie(kategorie);
                kA.setPreis(preis);

                try {
                    KonsumartikelDAO konsumartikelDAOImpl = new KonsumartikelDAOImpl();
                    konsumartikelDAOImpl.update(kA);
                    kategorienAuswahlLaden();
                    tabelleBefuellen();

                } catch (Exception e){
                    logger.error("Fehler beim Updaten des Konsumartikels: ", e);
                }
            }

        }
    }
    //Todo: funktioniert nicht
    @FXML
    public void verfuegbarkeitFiltern() {
        try {
            if (cbVerfuegbarkeit.isDisable()) {
                List<Konsumartikel> kAListDisabled = new ArrayList<>();
                List<Konsumartikel> tempList = konsumartikelDAO.findAll();
                for (Konsumartikel kA : tempList) {
                    if (kA.getVerfuegbar() == false) {
                        kAListDisabled.add(kA);
                    }
                }
                ObservableList<Konsumartikel> konsumartikelDisabledObservableList = FXCollections.observableList(kAListDisabled);
                tblKonsumartikel.setItems(konsumartikelDisabledObservableList);
            } else {
                tabelleBefuellen();
            }
        }catch (Exception e){
            logger.info("Es konnte nicht gefiltert werden...");
     }
    }

    @FXML
    public void reActivate() throws Exception{
        Konsumartikel kA = tblKonsumartikel.getSelectionModel().getSelectedItem();
        kA.setVerfuegbar(true);
        konsumartikelDAO.update(kA);
    }


    private static ObservableList<String> getKategorienListe(){
        ObservableList<String> kategorienListe = FXCollections.observableArrayList();
        kategorienListe.add("Vorspeise");
        kategorienListe.add("Hauptspeise");
        kategorienListe.add("Nachspeise");
        kategorienListe.add("Softgetränke");
        kategorienListe.add("Bier");
        kategorienListe.add("Wasser");
        kategorienListe.add("Alkohol ab 18");
        return kategorienListe;
    }

    // Ueberpruefung ob die Textfelder ausgefüllt sind
    private boolean eingabeValid() {
        lblError.setText("");
        if (isValid(lblBez.getText())) {

            // Preis Kontrolle
            try {
                Double.parseDouble(lblPreis.getText());
                return true;
            } catch (NumberFormatException e) {
                lblError.setText("Preis nicht korrekt eingegeben");
                return false;
            }
        } else {
            lblError.setText("Eingabe nicht korrekt");
            return false;
        }
    }

    // Funktion zur Pruefung
    private boolean isValid(String str) {
        return str != null && str.trim().length() > 0;
    }

    public void tabelleBefuellen() throws Exception {
        List<Konsumartikel> konsumartikelList = new ArrayList<>();
        List<Konsumartikel> tempList = konsumartikelDAO.findAll();
        for (Konsumartikel kA : tempList) {
            if (kA.getVerfuegbar() == (true)) {
                konsumartikelList.add(kA);
            }
        }


        ObservableList<Konsumartikel> konsumartikelObservableList = FXCollections.observableList(konsumartikelList);

        for (Konsumartikel kA : konsumartikelObservableList) {
            logger.info(kA);
        }
        tblKonsumartikel.setItems(konsumartikelObservableList);
    }
}
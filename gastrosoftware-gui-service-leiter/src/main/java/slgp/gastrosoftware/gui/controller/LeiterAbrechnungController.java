package slgp.gastrosoftware.gui.controller;

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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.*;
import slgp.gastrosoftware.persister.*;
import slgp.gastrosoftware.persister.impl.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class LeiterAbrechnungController implements Initializable {

    private static Logger logger = LogManager.getLogger(LeiterMitarbeiterController.class);

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

    @FXML
    private TextField txtUmsatz;

    @FXML
    private Button btnSpeichern;

    @FXML
    private Label lblError;

    @FXML
    private Button btnAbrechnungAnz;

    @FXML
    private Button btnZurueck;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        BestellungDAO bestellungDaoTemp = new BestellungDAOImpl();
        //initBestellungen();

        try {
            mitarbeiterAuswahlLaden();

            List<Bestellung> bestellungList = bestellungDaoTemp.findAllBezahlt(true);


            ObservableList<Bestellung> bestellungObservableList = FXCollections.observableList(bestellungList);



            colTisch.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("tisch"));
            colBestellung.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("anzahlKonsumartikel"));
            colDatum.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("datum"));
            colSumme.setCellValueFactory(new PropertyValueFactory<Bestellung, Double>("SummebestellPositionList"));

            tblAbrechnung.setItems(bestellungObservableList);


        } catch (Exception e) {
            logger.error("Fehler beim Abruf der BestellungsListe ", e);
        }


    }


    @FXML
    private void updateTabelle() {
        try {
            BestellungDAO bestellungDaoTemp = new BestellungDAOImpl();
            List<Bestellung> tempList = new ArrayList<>();

            List<Bestellung> alleBestellungList = bestellungDaoTemp.findAllBezahlt(true);

            Double summeUmsatz = null;

            for (Bestellung b : alleBestellungList) {
                if (b.getMitarbeiter().getName().equals(cmbMitarbeiter.getSelectionModel().getSelectedItem())) {
                    tempList.add(b);
                } else if (cmbMitarbeiter.getSelectionModel().getSelectedItem() == "Alle") {
                    tempList.add(b);
                }
            }

            if (tempList.size() != 0) {
                Double summeBestellung = 0.00;
                for (Bestellung b : tempList) {
                    summeBestellung = summeBestellung + b.getSummebestellPositionList();
                }
                String txtUmsatzSumme = String.valueOf(summeBestellung);
                txtUmsatz.setText(txtUmsatzSumme);
            } else {
                txtUmsatz.setText("0");
            }

            ObservableList<Bestellung> bestellungObservableList = FXCollections.observableArrayList(tempList);
            tblAbrechnung.setItems(bestellungObservableList);


        } catch (Exception e) {
            System.out.println("Ein Fehler beim Updaten ist aufgetreten" + e);
        }


    }



    @FXML
    public void speichern(ActionEvent event) throws Exception {
        TischRechnungDAO tischReschnungDaoTemp = new TischRechnungDAOImpl();
        List <TischRechnung> tischRechnungList = tischReschnungDaoTemp.findAll();
        List <TischRechnung> tischRechnungAufMitarbeiter = new ArrayList<>();

        MAAbrechnungDAO maAbrechnungDaoTemp = new MAAbrechnungDAOImpl();


        MAAbrechnung maAbrTemp = new MAAbrechnung();

        try {

            if (cmbMitarbeiter.getSelectionModel().getSelectedItem() == "Alle") {

                lblError.setTextFill(Color.RED);
                lblError.setText("Bitte Person auswählen");

            } else {

                Mitarbeiter mitarbeiterA = new Mitarbeiter();
                double summeUmsatz = 0;



                for (TischRechnung t : tischRechnungList){
                    if (t.getDatum().equals(LocalDate.now())){
                        List <Bestellung> bestellungList = t.getBestellungList();
                        for (Bestellung b : bestellungList){
                            if (b.getMitarbeiter().getName().equals(cmbMitarbeiter.getSelectionModel().getSelectedItem())){
                                mitarbeiterA = b.getMitarbeiter();
                                tischRechnungAufMitarbeiter.add(t);
                            }
                        }
                    }
                }

                for (TischRechnung tischRechnungAufMitarbeiterSumme : tischRechnungAufMitarbeiter){
                    summeUmsatz = summeUmsatz + tischRechnungAufMitarbeiterSumme.getSummeBestellungen();
                }


                maAbrTemp = new MAAbrechnung(LocalDate.now(), summeUmsatz, mitarbeiterA);


                List<MAAbrechnung> tempAb = maAbrechnungDaoTemp.findAll();
                int count = 0;

                for (MAAbrechnung m : tempAb) {
                    if (m.getDatum().equals(maAbrTemp.getDatum()) && m.getMitarbeiter().equals(maAbrTemp.getMitarbeiter())) {
                        lblError.setTextFill(Color.RED);
                        lblError.setText("Mitarbeiterabrechnung bereits vorhanden bitte Update verwenden");
                        count = 1;

                    }
                }

                if (count == 0) {
                    maAbrechnungDaoTemp.save(maAbrTemp);
                    lblError.setTextFill(Color.GREEN);
                    lblError.setText("Mitarbeiter Abrechnung wurde gespeichert");
                }

            }

        } catch (Exception e) {
            logger.error("Fehler beim Erstellen der Mitarbeiter Abrechnung", e);
        }

    }


    private void mitarbeiterAuswahlLaden() throws Exception {

        TischRechnungDAO tischReschnungDaoTemp = new TischRechnungDAOImpl();
        List <TischRechnung> tischRechnungListe = tischReschnungDaoTemp.findAll();

        System.out.println("--------------------------------" +tischRechnungListe.size());

        TreeSet <String> personAuswahl = new TreeSet<>();

        for(TischRechnung t : tischRechnungListe){
            if (t.getDatum().equals(LocalDate.now())){
                List <Bestellung> bestellungList = t.getBestellungList();
                for (Bestellung b : bestellungList){
                    personAuswahl.add(b.getMitarbeiter().getName());
                }

            }
            personAuswahl.add("Alle");
        }


        ObservableList<String> mitarbeiterListe = FXCollections.observableArrayList(personAuswahl);
        cmbMitarbeiter.setItems(mitarbeiterListe);
        cmbMitarbeiter.getSelectionModel().select(0);

    }

    @FXML
    private void abrechnungAnz(ActionEvent event) throws Exception {

        if (cmbMitarbeiter.getSelectionModel().getSelectedItem() == "Alle") {

            lblError.setTextFill(Color.RED);
            lblError.setText("Bitte Person auswählen");

        } else {

            String nameSuche = cmbMitarbeiter.getSelectionModel().getSelectedItem();

            MitarbeiterDAO mitarbeiterDAOTemp = new MitarbeiterDAOImpl();

            List<Mitarbeiter> mitarbeiterList = mitarbeiterDAOTemp.findAll();

            Mitarbeiter maController = new Mitarbeiter();

            for (Mitarbeiter m : mitarbeiterList) {
                if (m.getName().equals(nameSuche)) {
                    maController = m;
                    logger.info(m);
                }
            }

            ContextMitarbeiter.getInstance().setMitarbeiter(maController);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LeiterAbrechnungAnzeigen.fxml"));
            Parent ma_interface_parent = loader.load();
            Scene ma_interface_scene = new Scene(ma_interface_parent);
            Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            ma_stage.setScene(ma_interface_scene);
            ma_stage.show();

        }
    }


    @FXML
    public void zurueck(ActionEvent event) throws Exception {
        Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
        Scene ma_interface_scene = new Scene(ma_interface_parent);
        Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ma_stage.setScene(ma_interface_scene);
        ma_stage.show();
    }


}



package ch.hslu.slgp.gastrosoftware.gui.controller;

import ch.hslu.slgp.gastrosoftware.model.BestellPosition;
import ch.hslu.slgp.gastrosoftware.model.Bestellung;
import ch.hslu.slgp.gastrosoftware.model.TischRechnung;
import ch.hslu.slgp.gastrosoftware.pdfprinter.PDFPrinter;
import ch.hslu.slgp.gastrosoftware.pdfprinter.api.PrinterService;
import ch.hslu.slgp.gastrosoftware.rmi.api.RMIBestellService;
import ch.hslu.slgp.gastrosoftware.rmi.api.RMIRechnungService;
import ch.hslu.slgp.gastrosoftware.rmi.context.Context;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TischRechnungController implements Initializable {
    private static final Logger logger = LogManager.getLogger(TischRechnungController.class);

    private static RMIBestellService bestellService = Context.getInstance().getBestellService();
    private static RMIRechnungService rechnungService = Context.getInstance().getRechnungService();

    @FXML
    private ComboBox<String> cmbKat;

    @FXML
    private ComboBox<String> cmbKategorie;

    @FXML
    private TableView<BestellPosition> tblBestellPosition;

    @FXML
    private TableColumn<BestellPosition, Integer> bPAnzahl;

    @FXML
    private TableColumn<BestellPosition, String> bPBez;

    @FXML
    private TableColumn<BestellPosition, Double> bPEinzelPreis;

    @FXML
    private TableColumn<BestellPosition, Double> bPPreis;

    @FXML
    private Label lblFxmlSumme;

    private int tischNummer;

    List<Bestellung> bestellungListTemp = new ArrayList<>();

    private TischRechnung tischRechnung;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tabelleFuellen();
        updateTable();
    }


    public void setTischNummer(int tischNummer) {
        this.tischNummer = tischNummer;
    }

    public int getTischNummer() {
        return tischNummer;
    }


    @FXML
    private void updateTable() {
        try {
            tblBestellPosition.getItems().clear();
            tabelleFuellen();
        } catch (Exception e) {
            logger.error("Fehler beim Updaten der Tabelle: ", e);
            throw new RuntimeException();
        }
    }


    @FXML
    public void tabelleFuellen() {

        try {
            List<Bestellung> bestellungList = bestellService.findBestellungByTischNummer(getTischNummer());

            for (Bestellung bestellung : bestellungList) {
                if (!bestellung.getBezahlt()) {
                    bestellungListTemp.add(bestellung);
                    String summeText = String.valueOf(bestellung.berechneSummeBestellPositionList());
                    lblFxmlSumme.setText(summeText);
                }
            }

            List<BestellPosition> bestellPositionList = new ArrayList<>();

            for (Bestellung bestellung : bestellungListTemp) {
                bestellPositionList.addAll(bestellung.getKonsumartikel());
            }

            if (bestellPositionList.size() != 0) {
                Double summeRechnung = 0.00;
                for (BestellPosition bestellPosition : bestellPositionList) {
                    summeRechnung = summeRechnung + bestellPosition.getBerechneterPreis();
                }
                String txtsummeRechnung = String.valueOf(summeRechnung);
                lblFxmlSumme.setText(txtsummeRechnung);

            } else {
                lblFxmlSumme.setText("0");
            }

            bPBez.setCellValueFactory(new PropertyValueFactory<BestellPosition, String>("bezeichnung"));
            bPAnzahl.setCellValueFactory(new PropertyValueFactory<BestellPosition, Integer>("anzahl"));
            bPEinzelPreis.setCellValueFactory(new PropertyValueFactory<BestellPosition, Double>("preis"));
            bPPreis.setCellValueFactory(new PropertyValueFactory<BestellPosition, Double>("berechneterPreis"));

            ObservableList<BestellPosition> bestellPositionObservableList = FXCollections.observableArrayList(bestellPositionList);

            tblBestellPosition.setItems(bestellPositionObservableList);

        } catch (Exception e) {
            logger.info("Tabelle konnte nicht befuellt werden");
        }
    }

    @FXML
    public void rechnungBezahlt() {
        try {
            for (Bestellung bestellung : bestellungListTemp) {
                bestellung.setBezahlt(true);
                bestellService.bestellungAktualisieren(bestellung);
            }
            tischRechnung = new TischRechnung(LocalDate.now(), bestellungListTemp, 2);
            rechnungService.tischRechnungHinzufuegen(tischRechnung);
            rechnungDrucken();
            updateTable();
        } catch (Exception e) {
            logger.info("Konnte Bestellungen nicht auf bezahlt = true setzen: " + e);
        }

    }

    @FXML
    public void rechnungDrucken() {
        try {
            PrinterService printerService = new PDFPrinter();
            printerService.printTischRechnungAlsPdf(tischRechnung);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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


}





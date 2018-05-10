package slgp.gastrosoftware.gui.controller;

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
import slgp.gastrosoftware.persister.*;
import slgp.gastrosoftware.persister.impl.*;
import slgp.gastrosoftware.model.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class TischRechnungController implements Initializable {
    private static final Logger logger = LogManager.getLogger(TischRechnungController.class);
    private static BestellPositionDAO bestellPositionDAO = new BestellPositionDAOImpl();
    private static KonsumartikelDAOImpl konsumartikelDAO = new KonsumartikelDAOImpl();
    private static BestellungDAOImpl bestellungDAO = new BestellungDAOImpl();
    private static TischRechnungDAO tischRechnungDAO = new TischRechnungDAOImpl();
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
    private Label fxmlSumme;


    private int tischNummer;
    List<Bestellung> bestellungListTemp = new ArrayList<>();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


        tabelleFuellen();
     updateTable();
    }



    public void setTischNummer(int tischNummer) {
        this.tischNummer = tischNummer;
    }
    public int getTischNummer(){
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

       //Bestellungen find by Tischnummer (v query finden)
       try {

           List<Bestellung> bestellungList = bestellungDAO.findByTischNummer(getTischNummer());
           System.out.println(getTischNummer());

           for (Bestellung b2 : bestellungList) {
               if (b2.getBezahlt() == false) {
                   bestellungListTemp.add(b2);
               }
           }

           List<BestellPosition> bestellPositionList = new ArrayList<>();

           for (Bestellung b1 : bestellungListTemp) {
               bestellPositionList.addAll(b1.getKonsumartikel());
           }

           if (bestellPositionList.size() != 0) {
               Double summeRechnung = 0.00;
               for (BestellPosition b3 : bestellPositionList) {
                    summeRechnung = summeRechnung + b3.getBerechneterPreis();
               }
               String txtsummeRechnung = String.valueOf(summeRechnung);
               fxmlSumme.setText(txtsummeRechnung);

           } else {
               fxmlSumme.setText("0");
           }

           //List<BestellPosition> bestellPositionList = bestellPositionDAO.findAll();

           bPBez.setCellValueFactory(new PropertyValueFactory<BestellPosition, String>("bezeichnung"));
           bPAnzahl.setCellValueFactory(new PropertyValueFactory<BestellPosition, Integer>("anzahl"));
           bPEinzelPreis.setCellValueFactory(new PropertyValueFactory<BestellPosition, Double>("preis"));
           bPPreis.setCellValueFactory(new PropertyValueFactory<BestellPosition, Double>("berechneterPreis"));

           ObservableList<BestellPosition> bestellPositionObservableList = FXCollections.observableArrayList(bestellPositionList);


           tblBestellPosition.setItems(bestellPositionObservableList);
           for (BestellPosition bp2 : bestellPositionList) {
               System.out.println(bp2.getBerechneterPreis());
           }

       } catch (Exception e) {
           logger.info("Tabelle konnte nicht befuellt werden");
       }
   }

   @FXML
    public void rechnungBezahlt() {

           try {
               for (Bestellung b : bestellungListTemp) {
                   b.setBezahlt(true);
                    TischRechnung tr = new TischRechnung(LocalDate.now(), bestellungListTemp);

                    tischRechnungDAO.save(tr);
                     logger.info(tr);

                   bestellungDAO.update(b);
               }


               updateTable();


           } catch (Exception e) {
               logger.info("Konnte Bestellungen nicht auf bezahlt = true setzen");
           }

       }

//       public void berechneSummeRechnung(){
//
//        int summeRechnung = 0;
//        for (bestellung)
//

//       }



    @FXML
    public void zurueck(ActionEvent event) throws Exception {
        Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/MaInterface.fxml"));
        Scene ma_interface_scene = new Scene(ma_interface_parent);
        Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ma_stage.setScene(ma_interface_scene);
        ma_stage.show();
    }


}





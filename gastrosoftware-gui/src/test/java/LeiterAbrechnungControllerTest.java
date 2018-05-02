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
import slgp.gastrosoftware.model.Bestellung;
import slgp.gastrosoftware.model.Esswaren;
import slgp.gastrosoftware.model.Konsumartikel;
import slgp.gastrosoftware.model.Person;
import slgp.gastrosoftware.persister.BestellungDAO;
import slgp.gastrosoftware.persister.impl.BestellungDAOImpl;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

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

    @FXML
    private TextField txtUmsatz;

    @FXML
    private Button btnSpeichern;



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        BestellungDAO bestellungDaoTemp = new BestellungDAOImpl();

        try {
            mitarbeiterAuswahlLaden();
            List<Bestellung> bestellungList = bestellungDaoTemp.findAllBezahlt(true);

            ObservableList<Bestellung> bestellungObservableList = FXCollections.observableList(bestellungList);

            tblAbrechnung.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Bestellung>() {

                @Override
                public void changed(ObservableValue<? extends Bestellung> observable, Bestellung oldValue,
                                    Bestellung newValue) {
                    if (newValue != null) {
                        updateView();
                    }
                }
            });




            colTisch.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("tisch"));
            colBestellung.setCellValueFactory(new PropertyValueFactory<Bestellung, Integer>("anzahlKonsumartikel"));
            colDatum.setCellValueFactory(new PropertyValueFactory<Bestellung, String>("datum"));
            colSumme.setCellValueFactory(new PropertyValueFactory<Bestellung, Double>("SummePreisKonsumartikel"));

            tblAbrechnung.setItems(bestellungObservableList);



        } catch (Exception e) {
            logger.error("Fehler beim Abruf der BestellungsListe ", e);
        }


    }

    @FXML
    private void updateTabelle() {
            try {
                BestellungDAO bestellungDaoTemp = new BestellungDAOImpl();
                List <Bestellung> tempList = new ArrayList<>();
                List <Bestellung> alleBestellungList = bestellungDaoTemp.findAllBezahlt(true);

                Double summeUmsatz = null;

                for (Bestellung b : alleBestellungList){
                    if (b.getMitarbeiter().getName().equals(cmbMitarbeiter.getSelectionModel().getSelectedItem())){
                        tempList.add(b);
                    } else if (cmbMitarbeiter.getSelectionModel().getSelectedItem() == "Alle"){
                        tempList.add(b);
                    }
                }

                if (tempList.size() != 0){
                    Double summeBestellung = 0.00;
                    for (Bestellung b : tempList){
                        summeBestellung = summeBestellung + b.getSummePreisKonsumartikel();
                    }
                    String txtUmsatzSumme = String.valueOf(summeBestellung);
                    txtUmsatz.setText(txtUmsatzSumme);
                } else {
                    txtUmsatz.setText("0");
                }

                ObservableList<Bestellung> bestellungObservableList = FXCollections.observableArrayList(tempList);
                tblAbrechnung.setItems(bestellungObservableList);


            } catch (Exception e){
                System.out.println("Ein Fehler beim Updaten ist aufgetreten" + e);
            }


        }

    @FXML
    private void updateView() {

    }

    @FXML
    public void speichern(ActionEvent event) throws Exception {
        
        try {
            System.out.println("Speichern");

        } catch (Exception e) {
            logger.error("Fehler beim Speichern der Abrechnung: ", e);
        }

    }

    private void mitarbeiterAuswahlLaden() throws Exception {
        BestellungDAO bestellungDaoTemp = new BestellungDAOImpl();

        TreeSet <String> personAuswahl = new TreeSet<>();

        for (Bestellung b : bestellungDaoTemp.findAllBezahlt(true)){
            personAuswahl.add(b.getMitarbeiter().getName());
            personAuswahl.add("Alle");
        }


        ObservableList <String> mitarbeiterListe = FXCollections.observableArrayList(personAuswahl);
        cmbMitarbeiter.setItems(mitarbeiterListe);
        cmbMitarbeiter.getSelectionModel().select(0);





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



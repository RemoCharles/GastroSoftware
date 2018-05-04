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
import slgp.gastrosoftware.persister.BestellungDAO;
import slgp.gastrosoftware.persister.MAAbrechnungDAO;
import slgp.gastrosoftware.persister.MitarbeiterDAO;
import slgp.gastrosoftware.persister.impl.BestellungDAOImpl;
import slgp.gastrosoftware.persister.impl.MAAbrechnungDAOImpl;
import slgp.gastrosoftware.persister.impl.MitarbeiterDAOImpl;

import javax.swing.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class LeiterAbrechnungControllerTest implements Initializable {

    private static Logger logger = LogManager.getLogger(LeiterMitarbeiterControllerTest.class);

   @FXML
   private ComboBox<String> cmbMitarbeiter;

   @FXML
   private ComboBox<String> cmbAnzeigen;

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
    private void anzeigen (ActionEvent event) throws Exception {


    }



    @FXML
    public void speichern(ActionEvent event) throws Exception {
        BestellungDAO bestellungDAOTemp = new BestellungDAOImpl();
        MAAbrechnungDAO maAbrechnungDaoTemp = new MAAbrechnungDAOImpl();
        List <Bestellung> bestellungTemp = new ArrayList<>();
        MAAbrechnung maAbrTemp = new MAAbrechnung();

        try {

            if (cmbMitarbeiter.getSelectionModel().getSelectedItem() == "Alle"){

                lblError.setTextFill(Color.RED);
                lblError.setText("Bitte Person auswählen");

            } else {

                Mitarbeiter mitarbeiterA = new Mitarbeiter();
                double summeUmsatz = 0;

                for (Bestellung b : bestellungDAOTemp.findAllBezahlt(true)) {
                    if (b.getMitarbeiter().getName().equals(cmbMitarbeiter.getSelectionModel().getSelectedItem())) {
                        bestellungTemp.add(b);
                        mitarbeiterA = b.getMitarbeiter();
                        summeUmsatz = summeUmsatz + b.getSummePreisKonsumartikel();
                    }

                }

               /*System.out.println("Test----------");
                System.out.println(summeUmsatz);
                System.out.println(mitarbeiterA.toString()); */

                maAbrTemp = new MAAbrechnung(LocalDate.now(), summeUmsatz, mitarbeiterA);


                List <MAAbrechnung> tempAb = maAbrechnungDaoTemp.findAll();
                int count = 0;

                for (MAAbrechnung m : tempAb){
                    if (m.getDatum().equals(maAbrTemp.getDatum()) && m.getMitarbeiter().equals(maAbrTemp.getMitarbeiter())){
                        lblError.setTextFill(Color.RED);
                        lblError.setText("Mitarbeiterabrechnung bereits vorhanden");
                        count = 1;

                    }
                }

                if (count == 0){
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
        BestellungDAO bestellungDaoTemp = new BestellungDAOImpl();

        TreeSet <String> personAuswahl = new TreeSet<>();

        for (Bestellung b : bestellungDaoTemp.findAllBezahlt(true)){
            personAuswahl.add(b.getMitarbeiter().getName());
            personAuswahl.add("Alle");
        }


        ObservableList <String> mitarbeiterListe = FXCollections.observableArrayList(personAuswahl);
        cmbMitarbeiter.setItems(mitarbeiterListe);
        cmbMitarbeiter.getSelectionModel().select(0);
        cmbAnzeigen.setItems(mitarbeiterListe);
        cmbAnzeigen.getSelectionModel().select(0);


    }

    @FXML
    private void abrechnungAnz (ActionEvent event) throws Exception {

        if (cmbAnzeigen.getSelectionModel().getSelectedItem() == "Alle"){

            lblError.setTextFill(Color.RED);
            lblError.setText("Bitte Person auswählen");

        } else {
            //Mitarbeiter ermitteln zum übergeben in LeiterAbrechnungAnzeigen
        /*String nameSuche = cmbAnzeigen.getSelectionModel().getSelectedItem();

        System.out.println(nameSuche);

        MitarbeiterDAO mitarbeiterDAOTemp = new MitarbeiterDAOImpl();

        List <Mitarbeiter>  mitarbeiterList =mitarbeiterDAOTemp.findAll();

        Mitarbeiter maController = new Mitarbeiter();

        for (Mitarbeiter m : mitarbeiterList){
            System.out.println(m + " Ausgabe ");
            if (m.getName().equals(nameSuche)){
               maController = m;
               System.out.println("Name gefunden" + maController);
            }
        }
*/

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LeiterAbrechnungAnzeigen.fxml"));
            Parent ma_interface_parent = loader.load();

            //Mitarbeiter Übergabe
        /*LeiterAbrechnungAnzeigenControllerTest controller = loader.getController();
        controller.setMitarbeiter(maController);
        System.out.println(maController.toString());
*/
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



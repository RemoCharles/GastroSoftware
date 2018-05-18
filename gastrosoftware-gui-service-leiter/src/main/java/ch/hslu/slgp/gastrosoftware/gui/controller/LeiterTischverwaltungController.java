package ch.hslu.slgp.gastrosoftware.gui.controller;

import ch.hslu.slgp.gastrosoftware.model.Tisch;
import ch.hslu.slgp.gastrosoftware.rmi.api.RMIBestellService;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LeiterTischverwaltungController implements Initializable {

    @FXML
    Button btnZurueck;

    @FXML
    Button btnAktDeakt;

    @FXML
    private Label lblVorschlag;

    @FXML
    private Label lblError;

    @FXML
    private TableView<Tisch> tblTische;

    @FXML
    private TableColumn<Tisch, Integer> colTischNummer;

    @FXML
    private TableColumn<Tisch, Boolean> colVerfuegbarkeit;


    private static RMIBestellService bestellService = Context.getInstance().getBestellService();
    private static Logger logger = (Logger) LogManager.getLogger(LeiterTischverwaltungController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tabelleLaden();
    }

    @FXML
    private void deActivate(ActionEvent event) throws Exception {
        Tisch tisch = tblTische.getSelectionModel().getSelectedItem();
       if(tisch.getVerfuegbarkeit()){
           tisch.setVerfuegbarkeit(false);
           bestellService.tischAktualisieren(tisch);
       }else{
           tisch.setVerfuegbarkeit(true);
           bestellService.tischAktualisieren(tisch);
       }
       tabelleLaden();
    }

    private void tabelleLaden() {
       try {
           List<Tisch> tischList = bestellService.findTischAll();

           colTischNummer.setCellValueFactory(new PropertyValueFactory<Tisch, Integer>("tischNummer"));
           colVerfuegbarkeit.setCellValueFactory(new PropertyValueFactory<Tisch, Boolean>("verfuegbarkeit"));

           ObservableList<Tisch> tischListObsv = FXCollections.observableArrayList(tischList);
           tblTische.setItems(tischListObsv);

       }catch (Exception e){
           logger.info("Tabelle konnte nicht befuellt werden.");
           lblError.setText("Tabelle konnte nicht befuellt werden.");
       }
    }

    @FXML
    private void zurueck(ActionEvent event) throws IOException {
        Parent leiter_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
        Scene leiter_interface_scene = new Scene(leiter_interface_parent);
        Stage leiter_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        leiter_stage.setScene(leiter_interface_scene);
        leiter_stage.show();
    }

}

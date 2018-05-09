package slgp.gastrosoftware.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.MAAbrechnung;
import slgp.gastrosoftware.model.Mitarbeiter;
import slgp.gastrosoftware.persister.MAAbrechnungDAO;
import slgp.gastrosoftware.persister.impl.MAAbrechnungDAOImpl;


import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class LeiterAbrechnungAnzeigenController implements Initializable {

    private static Logger logger = LogManager.getLogger(LeiterAbrechnungAnzeigenController.class);

    //private Mitarbeiter mitarbeiterSuche = new Mitarbeiter();

    @FXML
    private Button btnZurueck;

    @FXML
    private Button btnLaden;

    @FXML
    private TableView<MAAbrechnung> tblAbrechnungAnzeigen;

    @FXML
    private TableColumn<MAAbrechnung, String> colDatum;

    @FXML
    private TableColumn<MAAbrechnung, Double> colUmsatz;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            mitarbeiterAbrechnungLaden();

        } catch (Exception e) {
            logger.error("Fehler beim Abruf der BestellungsListe ", e);
        }
    }

    @FXML
    private void mitarbeiterAbrechnungLaden() throws Exception {
        System.out.println("Methode wird geladen");
        MAAbrechnungDAO mitarbetierabr = new MAAbrechnungDAOImpl();

        //Wann wird eine Rechnung als Mitarbeiterabrechnung persistiert?
        List<MAAbrechnung> mabTemp = mitarbetierabr.findAll();
        List <MAAbrechnung> mabPerson = new ArrayList<>();

        for (MAAbrechnung m : mabTemp){
            Mitarbeiter mitarbeiterSuche = ContextMitarbeiter.getInstance().getMitarbeiter();

            if (m.getMitarbeiter().equals(mitarbeiterSuche)){
                mabPerson.add(m);
                logger.info(m + "---------------");
            } else {
                System.out.println("Mitarbeiter wurde nicht verglichen");
            }
        }

        ObservableList<MAAbrechnung> maAbrechnungListe = FXCollections.observableList(mabPerson);
        colDatum.setCellValueFactory(new PropertyValueFactory<MAAbrechnung, String>("datum"));
        colUmsatz.setCellValueFactory(new PropertyValueFactory<MAAbrechnung, Double>("umsatz"));
        tblAbrechnungAnzeigen.setItems(maAbrechnungListe);
    }

    public void zurueck(ActionEvent event) throws Exception{
        Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterAbrechnung.fxml"));
        Scene ma_interface_scene = new Scene(ma_interface_parent);
        Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        ma_stage.setScene(ma_interface_scene);
        ma_stage.show();
    }
}

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
import javafx.scene.control.Label;
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
    private static MAAbrechnungDAO maAbrechnungDAOTemp = new MAAbrechnungDAOImpl();

    @FXML
    private Button btnZurueck;

    @FXML
    private TableView<MAAbrechnung> tblAbrechnungAnzeigen;

    @FXML
    private TableColumn<MAAbrechnung, String> colDatum;

    @FXML
    private TableColumn<MAAbrechnung, Double> colUmsatz;

    @FXML
    private Label lblName;

    @FXML
    private Label lblVorname;

    @FXML
    private Label lblFunktion;

    @FXML
    private Label lblStrasse;

    @FXML
    private Label lblPlz;

    @FXML
    private Label lblOrt;

    @FXML
    private Label lblMail;

    @FXML
    private Label lblTelefon;

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

        //Wann wird eine Rechnung als Mitarbeiterabrechnung persistiert?
        List<MAAbrechnung> mabTemp = maAbrechnungDAOTemp.findAll();
        List <MAAbrechnung> mabPerson = new ArrayList<>();
        Mitarbeiter mitarbeiterSuche = ContextMitarbeiter.getInstance().getMitarbeiter();

        lblName.setText(mitarbeiterSuche.getName());
        lblVorname.setText(mitarbeiterSuche.getVorname());
        lblFunktion.setText(mitarbeiterSuche.getFunktion());
        lblStrasse.setText(mitarbeiterSuche.getAdresse().getStrasse());
        lblPlz.setText(String.valueOf(mitarbeiterSuche.getAdresse().getPlz()));
        lblOrt.setText(mitarbeiterSuche.getAdresse().getOrt());
        lblMail.setText(mitarbeiterSuche.getKontakt().getEmail());
        lblTelefon.setText(mitarbeiterSuche.getKontakt().getTelefon());

        for (MAAbrechnung m : mabTemp){

            if (m.getMitarbeiter().equals(mitarbeiterSuche)){
                mabPerson.add(m);
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

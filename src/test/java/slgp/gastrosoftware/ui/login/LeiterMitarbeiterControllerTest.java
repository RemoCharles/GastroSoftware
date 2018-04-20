package slgp.gastrosoftware.ui.login;

import java.util.ResourceBundle;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.sessions.Login;


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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import slgp.gastrosoftware.zentrale.persister.domain.Adresse;
import slgp.gastrosoftware.zentrale.persister.domain.Kontakt;
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.impl.PersonDAOImpl;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class LeiterMitarbeiterControllerTest implements Initializable {

	private static Logger logger = LogManager.getLogger(LeiterMitarbeiterControllerTest.class);

	@FXML
	private Label lblError;

	@FXML
	private ComboBox cmbRolle;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtVorname;

	@FXML
	private TextField txtStrasse;

	@FXML
	private TextField txtPlz;

	@FXML
	private TextField txtOrt;

	@FXML
	private TextField txtEmail;

	@FXML 
	private TextField txtTelefon;

	@FXML
	private TextField  txtUsername;

	@FXML
	private TextField txtKennwort;

	@FXML
	private TableView<BenutzerWrapper> tblPerson;
	
	@FXML
	private TableColumn<BenutzerWrapper, Integer> colNummer;

	@FXML
	private TableColumn<BenutzerWrapper, String> colName;

	@FXML
	private TableColumn<BenutzerWrapper, String> colVorname;

	@FXML
	private TableColumn<BenutzerWrapper, String> colStrasse;

	@FXML
	private TableColumn<BenutzerWrapper, Integer> colPlz;

	@FXML
	private TableColumn<BenutzerWrapper, String> colOrt;

	@FXML
	private TableColumn<BenutzerWrapper, String> colEmail;

	@FXML
	private TableColumn<BenutzerWrapper, String> colTelefon;

	@FXML
	private TableColumn<BenutzerWrapper, String> colUsername;

	@FXML
	private TableColumn<BenutzerWrapper, String> colKennwort;

	@FXML
	private TableColumn<BenutzerWrapper, String> colFunktion;

	@FXML
	private Button btnSpeichern;

	@FXML
	private Button btnLoeschen;

	@FXML
	public void speichern(ActionEvent event) throws Exception{

	}

	@FXML
	public void neuenBenutzerErfassen(ActionEvent event) throws Exception{

	}
	
	@FXML
	public void reset(ActionEvent event) throws Exception{

	}
	
	@FXML
	public void loeschen(ActionEvent event) throws Exception{

	}
	

	public void initialize (URL location, ResourceBundle resources) {
		try {
			
			PersonDAOImpl PersDAOImpl = new PersonDAOImpl();
			List <Person> allePersonenListe = PersDAOImpl.findAll();
		
			
			/* Tabelle konfigurieren */
			colNummer.setCellValueFactory(new PropertyValueFactory<BenutzerWrapper, Integer>("nummer"));
            colName.setCellValueFactory(new PropertyValueFactory<BenutzerWrapper, String>("name"));
            colVorname.setCellValueFactory(new PropertyValueFactory<BenutzerWrapper, String>("vorname"));
			colStrasse.setCellValueFactory(new PropertyValueFactory<BenutzerWrapper, String>("strasse"));
			colPlz.setCellValueFactory(new PropertyValueFactory<BenutzerWrapper, Integer>("plz"));
			colOrt.setCellValueFactory(new PropertyValueFactory<BenutzerWrapper, String>("ort"));
			colEmail.setCellValueFactory(new PropertyValueFactory<BenutzerWrapper, String>("email"));
			colTelefon.setCellValueFactory(new PropertyValueFactory<BenutzerWrapper, String>("telefon"));
			colUsername.setCellValueFactory(new PropertyValueFactory<BenutzerWrapper, String>("username"));
			colKennwort.setCellValueFactory(new PropertyValueFactory<BenutzerWrapper, String>("passwort"));
			colFunktion.setCellValueFactory(new PropertyValueFactory<BenutzerWrapper, String>("funktion"));
			
			tblPerson.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BenutzerWrapper>() {

                @Override
                public void changed(ObservableValue<? extends BenutzerWrapper> observable, BenutzerWrapper oldValue,
                        BenutzerWrapper newValue) {
                    if (newValue != null) {
                        updateView();
                    }
                }
            });
			
			updateTabelle();
			
			
		} catch (Exception e) {
			logger.error("Tabelle konnte nicht befüllt werden...");
		}
	}
	
	private void updateTabelle() {
		try {
			
			PersonDAOImpl PersDAOImpl = new PersonDAOImpl();
			List <Person> allePersonenListe = PersDAOImpl.findAll();

            if (allePersonenListe.size() > 0) {
                List<BenutzerWrapper> wrapperListe = new ArrayList<>();

                int nummer = 1;

                for (Person person : allePersonenListe) {
                    wrapperListe.add(new BenutzerWrapper(nummer++, person));
                }

                tblPerson.getItems().clear();
                tblPerson.getItems().addAll(wrapperListe);

                tblPerson.getSelectionModel().select(0);

                updateView();
            }
			
		}  catch (Exception e) {
            logger.error("Fehler bei der Aktualisierung der Tabelle: ", e);
            throw new RuntimeException(e);
        }
		
	}
	private void updateView() {
		
		lblError.setText("");

        if (tblPerson.getSelectionModel().getSelectedItem() == null) {

            // cmbRolle.getSelectionModel().clearSelection();
            txtName.setText("");
            txtVorname.setText("");
            txtStrasse.setText("");
            txtPlz.setText("");
            txtOrt.setText("");
            txtEmail.setText("");
            txtTelefon.setText("");
            txtUsername.setText("");
            txtKennwort.setText("");

        } else {

            Person person = tblPerson.getSelectionModel().getSelectedItem().getPerson();

            // cmbRolle.getSelectionModel().select(benutzer.getRolle());
            txtName.setText(person.getName());
            txtVorname.setText(person.getVorname());
            txtStrasse.setText(person.getAdresse().getStrasse());
            txtPlz.setText("" + person.getAdresse().getPlz());
            txtOrt.setText(person.getAdresse().getOrt());
            txtEmail.setText(person.getKontakt().getEmail());
            txtTelefon.setText(person.getKontakt().getTelefon());
            txtUsername.setText(person.getLogin().getUsername());
            txtKennwort.setText(person.getLogin().getPasswort());
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

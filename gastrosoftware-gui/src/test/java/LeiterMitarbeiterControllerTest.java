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
import slgp.gastrosoftware.model.Adresse;
import slgp.gastrosoftware.model.Kontakt;
import slgp.gastrosoftware.model.Login;
import slgp.gastrosoftware.model.Person;
import slgp.gastrosoftware.persister.impl.PersonDAOImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class LeiterMitarbeiterControllerTest implements Initializable {

	private static Logger logger = LogManager.getLogger(LeiterMitarbeiterControllerTest.class);

	@FXML
	private Label lblError;


	@FXML
	private TextField txtName;

	@FXML
	private TextField txtVorname;

	@FXML
	private TextField txtFunktion;

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
	private TableView<Person> tblPerson;

	@FXML
	private TableColumn<Person, Integer> colNummer;

	@FXML
	private TableColumn<Person, String> colName;

	@FXML
	private TableColumn<Person, String> colVorname;

	@FXML
	private TableColumn<Person, String> colStrasse;

	@FXML
	private TableColumn<Person, Integer> colPlz;

	@FXML
	private TableColumn<Person, String> colOrt;

	@FXML
	private TableColumn<Person, String> colEmail;

	@FXML
	private TableColumn<Person, String> colTelefon;

	@FXML
	private TableColumn<Person, String> colUsername;

	@FXML
	private TableColumn<Person, String> colKennwort;

	@FXML
	private TableColumn<Person, String> colFunktion;

	@FXML
	private Button btnSpeichern;

	@FXML
	private Button btnLoeschen;

	@FXML
	public void speichern(ActionEvent event) throws Exception{
		// Kontrolle ob dies funktioniert!


		if (eingabeValid()) {


				if (tblPerson.getSelectionModel().getSelectedItem() == null){


				// Neue Person anlegen
				String name = txtName.getText();
				String vorname = txtVorname.getText();
				String funktion = txtFunktion.getText();
				String strasse = txtStrasse.getText();
				int plz = Integer.parseInt(txtPlz.getText());
				String ort = txtOrt.getText();
				String email = txtEmail.getText();
				String telefon = txtTelefon.getText();
				String username = txtUsername.getText();
				String passwort = txtKennwort.getText();

				Person personSpeichern = new Person(name, vorname, funktion, new Adresse(strasse, plz, ort), new Kontakt(email, telefon), new Login(username, passwort));
				System.out.println(personSpeichern.toString());
				
				
				try {
					
					PersonDAOImpl persDAOImpl = new PersonDAOImpl();
					persDAOImpl.save(personSpeichern);
					updateTabelle();

			
				} catch (Exception e) {
					logger.error("Fehler beim Speichern der Person: ", e);
				}
				} else {
					String name = txtName.getText();
					String vorname = txtVorname.getText();
					String funktion = txtFunktion.getText();
					String strasse = txtStrasse.getText();
					int plz = Integer.parseInt(txtPlz.getText());
					String ort = txtOrt.getText();
					String email = txtEmail.getText();
					String telefon = txtTelefon.getText();
					String username = txtUsername.getText();
					String passwort = txtKennwort.getText();

					Person person = tblPerson.getSelectionModel().getSelectedItem();

					person.setName(name);
					person.setVorname(vorname);
					person.setFunktion(funktion);
					person.setAdresse(new Adresse(strasse, plz, ort));
					person.setKontakt(new Kontakt(email, telefon));
					person.setLogin(new Login (username, passwort));

					try {
						PersonDAOImpl persDAOImpl = new PersonDAOImpl();
						persDAOImpl.update(person);
						updateTabelle();

					} catch (Exception e){
						logger.error("Fehlre beim Updaten de Person: ", e);
					}
				}

		}
	}

	@FXML
	public void neuenBenutzerErfassen(ActionEvent event) throws Exception{

		reset();
	}

	@FXML
	public void eingabeReset(ActionEvent event) throws Exception{

		reset();

	}


	@FXML
	private void reset() {
		txtName.setText("");
		txtVorname.setText("");
		txtFunktion.setText("");
		txtStrasse.setText("");
		txtPlz.setText("");
		txtOrt.setText("");
		txtEmail.setText("");
		txtTelefon.setText("");
		txtUsername.setText("");
		txtKennwort.setText("");
	}



	@FXML
	public void loeschen(ActionEvent event) throws Exception{

		PersonDAOImpl persDAOImpl = new PersonDAOImpl(); 

		if(tblPerson.getSelectionModel().getSelectedItem() == null) {
			return;
		}

		Person person = tblPerson.getSelectionModel().getSelectedItem();

		// System.out.println(person);

		if (person != null) {
			try {
				persDAOImpl.delete(person);
				updateTabelle();
			} catch (Exception e) {
				logger.error("Fehler beim Löschen der Person: ", e);
			}
		}
	}

	// Ueberpruefung ob die Textfelder ausgefüllt sind
	private boolean eingabeValid() {
		lblError.setText("");
		if (isValid(txtName.getText()) && isValid(txtVorname.getText()) && isValid(txtFunktion.getText()) && isValid(txtStrasse.getText()) 
				&& isValid(txtPlz.getText()) && isValid(txtOrt.getText()) && isValid(txtEmail.getText()) 
				&& isValid(txtTelefon.getText()) && isValid(txtEmail.getText()) && isValid(txtKennwort.getText())
				&& isValid(txtUsername.getText())) {

			// PLZ Kontrolle
			try {
				Integer.parseInt(txtPlz.getText());
				return true;
			} catch (NumberFormatException e) {
				lblError.setText("PLZ nicht korrekt eingegeben");
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

	public void initialize (URL location, ResourceBundle resources) {
		try {

			PersonDAOImpl persDAOImpl = new PersonDAOImpl();
			List <Person> allePersonenListe = persDAOImpl.findAll();

			/* Tabelle konfigurieren */
			colNummer.setCellValueFactory(new PropertyValueFactory<Person, Integer>("nummer"));
			// System.out.println("---------------------Test 4");
			colName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
			colVorname.setCellValueFactory(new PropertyValueFactory<Person, String>("vorname"));
			colStrasse.setCellValueFactory(new PropertyValueFactory<Person, String>("strasse"));

			colPlz.setCellValueFactory(new PropertyValueFactory<Person, Integer>("plz"));
			colOrt.setCellValueFactory(new PropertyValueFactory<Person, String>("ort"));
			colEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
			colTelefon.setCellValueFactory(new PropertyValueFactory<Person, String>("telefon"));

			colUsername.setCellValueFactory(new PropertyValueFactory<Person, String>("username"));

			colKennwort.setCellValueFactory(new PropertyValueFactory<Person, String>("passwort"));

			colFunktion.setCellValueFactory(new PropertyValueFactory<Person, String>("funktion"));

			ObservableList<Person> observPersonen = FXCollections.observableArrayList(allePersonenListe);
			tblPerson.setItems(observPersonen);

			//Listener
			tblPerson.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {

				@Override
				public void changed(ObservableValue<? extends Person> observable, Person oldValue,
						Person newValue) {
					if (newValue != null) {
						updateView();
					}
				}
			});

			updateTabelle();


		} catch (Exception e) {
			logger.error("Tabelle konnte nicht befüllt werden...", e);
		}
	}

	private void updateTabelle() {
		try {

			PersonDAOImpl PersDAOImpl = new PersonDAOImpl();
			List <Person> allePersonenListe = PersDAOImpl.findAll();


			tblPerson.getItems().clear();
			tblPerson.getItems().addAll(allePersonenListe);

			tblPerson.getSelectionModel().select(0);


			updateView();


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
			txtFunktion.setText("");
			txtStrasse.setText("");
			txtPlz.setText("");
			txtOrt.setText("");
			txtEmail.setText("");
			txtTelefon.setText("");
			txtUsername.setText("");
			txtKennwort.setText("");

		} else {

			Person person = tblPerson.getSelectionModel().getSelectedItem();

			// cmbRolle.getSelectionModel().select(benutzer.getRolle());
			txtName.setText(person.getName());
			txtVorname.setText(person.getVorname());
			txtFunktion.setText(person.getFunktion());
			txtStrasse.setText(person.getAdresse().getStrasse());
			txtPlz.setText("" + person.getAdresse().getPlz());
			txtOrt.setText(person.getAdresse().getOrt());
			txtEmail.setText(person.getKontakt().getEmail());
			txtTelefon.setText(person.getKontakt().getTelefon());
			txtUsername.setText(person.getUsername());
			txtKennwort.setText(person.getPasswort());
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

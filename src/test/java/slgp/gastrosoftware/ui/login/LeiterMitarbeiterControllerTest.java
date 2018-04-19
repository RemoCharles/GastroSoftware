package slgp.gastrosoftware.ui.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.sessions.Login;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import slgp.gastrosoftware.zentrale.persister.domain.Adresse;
import slgp.gastrosoftware.zentrale.persister.domain.Kontakt;
import slgp.gastrosoftware.zentrale.persister.domain.Person;

public class LeiterMitarbeiterControllerTest {
	
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
	private TableView<Person> tblBenutzer;
	
	@FXML
	private TableColumn<Person, String> colName;
	
	@FXML
	private TableColumn<Person, String> colVorname;
	
	@FXML
	private TableColumn<Adresse, String> colStrasse;
	
	@FXML
	private TableColumn<Adresse, Integer> colPlz;
	
	@FXML
	private TableColumn<Adresse, String> colOrt;
	
	@FXML
	private TableColumn<Kontakt, String> colEmail;
	
	@FXML
	private TableColumn<Kontakt, String> colTelefon;
	
	@FXML
	private TableColumn<Login, String> colUsername;
	
	@FXML
	private TableColumn<Login, String> colKennwort;
	
	@FXML
	private TableColumn<Person, String> colFunktion;
	
	@FXML
	private Button btnSpeichern;
	
	@FXML
	private Button btnLoeschen;
	

	
	
	
	
}

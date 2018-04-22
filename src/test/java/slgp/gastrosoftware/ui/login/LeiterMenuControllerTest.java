package slgp.gastrosoftware.ui.login;

import java.net.URL;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;

public class LeiterMenuControllerTest implements Initializable {

	private static Logger logger = LogManager.getLogger(LeiterMenuControllerTest.class);

	@FXML
	private ComboBox<String> cmbKat;

	@FXML
	private TableView<Konsumartikel> tblKonsumartikel;

	@FXML
	private TableColumn<Konsumartikel, String> konsKat;

	@FXML
	private TableColumn<Konsumartikel, String> konsBez;

	@FXML
	private TableColumn<Konsumartikel, Double> konsPr;




	@FXML
	public void zurueck(ActionEvent event) throws Exception {
		Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
		Scene ma_interface_scene = new Scene(ma_interface_parent);
		Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ma_stage.setScene(ma_interface_scene);
		ma_stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		List <String> wochenTage = new ArrayList<>();
		wochenTage.add("Montag");
		wochenTage.add("Dienstag");
		wochenTage.add("Mittwoch");
		wochenTage.add("Donnerstag");
		wochenTage.add("Freitag");
		wochenTage.add("Samstag");
		wochenTage.add("Sonntag");

		ObservableList<String> observWochenTage = FXCollections.observableArrayList(wochenTage);

		cmbKat.setItems(observWochenTage);
	}

	@FXML
	public void updateTable(ActionEvent event) throws Exception{

	}












}



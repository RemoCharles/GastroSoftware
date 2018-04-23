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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.Konsumartikel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LeiterMenuControllerTest implements Initializable {

	private static Logger logger = LogManager.getLogger(LeiterMenuControllerTest.class);

	@FXML
	private ComboBox<String> cmbWochentage;

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
		Parent leiter_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
		Scene leiter_interface_scene = new Scene(leiter_interface_parent);
		Stage leiter_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		leiter_stage.setScene(leiter_interface_scene);
		leiter_stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		List<String> wochenTage = new ArrayList<>();
		wochenTage.add("Montag");
		wochenTage.add("Dienstag");
		wochenTage.add("Mittwoch");
		wochenTage.add("Donnerstag");
		wochenTage.add("Freitag");
		wochenTage.add("Samstag");
		wochenTage.add("Sonntag");

		ObservableList<String> observWochenTage = FXCollections.observableArrayList(wochenTage);

		cmbWochentage.setItems(observWochenTage);
	}

	@FXML
	public void updateTable(ActionEvent event) throws Exception {

	}

}

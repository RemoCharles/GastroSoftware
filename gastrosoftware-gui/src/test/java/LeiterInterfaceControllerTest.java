import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LeiterInterfaceControllerTest implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	@FXML
	private void menuAnzeigen(ActionEvent event) throws Exception {
		Parent leiter_menu_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterMenu.fxml"));
		Scene leiter_menu_scene = new Scene(leiter_menu_parent);
		Stage leiter_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
		leiter_stage.setScene(leiter_menu_scene);
		leiter_stage.show();
	}
	
	@FXML
	private void mitarbeiterAnzeigen(ActionEvent event) throws Exception {
		Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterMitarbeiter.fxml"));
		Scene ma_interface_scene = new Scene(ma_interface_parent);
		Stage ma_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
		ma_stage.setScene(ma_interface_scene);
		ma_stage.show();
	}
	@FXML
	private void konsumartikelAnzeigen(ActionEvent event) throws Exception {
		Parent saal1_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Saal1.fxml"));
		Scene saal1_interface_scene = new Scene(saal1_interface_parent);
		Stage saal1_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
		saal1_stage.setScene(saal1_interface_scene);
		saal1_stage.show();
	}
	@FXML
	private void abrechnungAnzeigen(ActionEvent event) throws Exception {
		Parent saal1_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterAbrechnung.fxml"));
		Scene saal1_interface_scene = new Scene(saal1_interface_parent);
		Stage saal1_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
		saal1_stage.setScene(saal1_interface_scene);
		saal1_stage.show();
	}
	@FXML
	private void tischVerwaltungAnzeigen(ActionEvent event) throws Exception {
		Parent saal1_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Saal1.fxml"));
		Scene saal1_interface_scene = new Scene(saal1_interface_parent);
		Stage saal1_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
		saal1_stage.setScene(saal1_interface_scene);
		saal1_stage.show();
	}
	@FXML
	private void logout(ActionEvent event) throws Exception {
		System.exit(0);
	}
}

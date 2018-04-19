package slgp.gastrosoftware.ui.login;

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
	private void menuOnAction(ActionEvent event) throws Exception {
		Parent saal1_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Saal1.fxml"));
		Scene saal1_interface_scene = new Scene(saal1_interface_parent);
		Stage saal1_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
		saal1_stage.setScene(saal1_interface_scene);
		saal1_stage.show();
	}
}

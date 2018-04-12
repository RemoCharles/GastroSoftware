package slgp.gastrosoftware.ui.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class MaInterfaceControllerTest implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	
	@FXML
	private void saal1Action(ActionEvent event) throws Exception {
		Parent saal1_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Saal1.fxml"));
		Scene saal1_interface_scene = new Scene(saal1_interface_parent);
		Stage saal1_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
		saal1_stage.setScene(saal1_interface_scene);
		saal1_stage.show();
	}
	
	@FXML
	private void terrasseAction(ActionEvent event) throws Exception {
		Parent terrasse_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Terrasse1.fxml"));
		Scene terrasse_interface_scene = new Scene(terrasse_interface_parent);
		Stage terrasse_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
		terrasse_stage.setScene(terrasse_interface_scene);
		terrasse_stage.show();
	}
	
	@FXML
	private void logout(ActionEvent event) throws Exception {
		System.exit(0);
	}
	

}

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
import slgp.gastrosoftware.zentrale.persister.impl.LoginDAOImpl;

public class LoginControllerTest implements Initializable{

	@FXML
	// Variablen
	Button genLogi;

	@FXML
	private TextField genBenu;

	@FXML
	private TextField genPass;

	@FXML
	private Label genPwEr;
	
	@FXML
	private Button maiTisc1;
	
	@FXML
	private Button genAbbr;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	// Userlogin Buttons
	@FXML
	private void loginaction(ActionEvent event) throws Exception {

		LoginDAOImpl login = new LoginDAOImpl();

		if(login.pruefeLogin(genBenu.getText(), genPass.getText())==true) {

			if(login.getFunktionPerson(genBenu.getText(), genPass.getText())=="Kuechenpersonal") {
				Parent kueche_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/KuecheInterface.fxml"));
				Scene kueche_interface_scene = new Scene(kueche_interface_parent);
				Stage kueche_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
				kueche_stage.setScene(kueche_interface_scene);
				kueche_stage.show();

			}else if (login.getFunktionPerson(genBenu.getText(), genPass.getText())=="Servicepersonal") {
				Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/MaInterface.fxml"));
				Scene ma_interface_scene = new Scene(ma_interface_parent);
				Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				ma_stage.setScene(ma_interface_scene);
				ma_stage.show();

			}else if (login.getFunktionPerson(genBenu.getText(), genPass.getText())=="Barpersonal") {
				Parent bar_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/BarInterface.fxml"));
				Scene bar_interface_scene = new Scene(bar_interface_parent);
				Stage bar_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
				bar_stage.setScene(bar_interface_scene);
				bar_stage.show();

			}else if (login.getFunktionPerson(genBenu.getText(), genPass.getText())=="Leiter"){
				Parent leiter_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
				Scene leiter_interface_scene = new Scene(leiter_interface_parent);
				Stage leiter_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
				leiter_stage.setScene(leiter_interface_scene);
				leiter_stage.show();

			}else {
				genPwEr.setText("Bitte wenden Sie sich an den Administrator...");
			}
		}else {
			genPwEr.setText("Benutzername oder Passwort falsch..");
		}
	}


	@FXML
	private void exitaction(ActionEvent event) throws IOException {

		System.exit(0);
	}
	
}
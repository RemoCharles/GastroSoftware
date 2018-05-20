package ch.hslu.slgp.gastrosoftware.gui.controller;

import ch.hslu.slgp.gastrosoftware.model.Tisch;
import ch.hslu.slgp.gastrosoftware.rmi.api.RMIBestellService;
import ch.hslu.slgp.gastrosoftware.rmi.context.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class SaalEinsController implements Initializable{
	@FXML
	public Button tisch1;
	@FXML
	private Button tisch2;
	@FXML
	private Button tisch3;
	@FXML
	private Button tisch4;
	@FXML
	private Button tisch5;
	@FXML
	private Button tisch6;
	@FXML
	private Button tisch7;
	@FXML
	private Button tisch8;
	@FXML
	private Button tisch9;
	@FXML
	private Button tisch10;
	@FXML
	private Button tisch11;
	@FXML
	private Button tisch12;
	@FXML
	private Button tisch13;
	@FXML
	private Button tisch14;
	@FXML
	private Button tisch15;
	@FXML
	private Button tisch16;
	@FXML
	private Button tisch17;
	@FXML
	private Button tisch18;
	@FXML
	private Button tisch19;
	@FXML
	private Button tisch20;
	@FXML
	private Button tisch21;
	@FXML
	private Button tisch22;
	@FXML
	private Button tisch23;
	@FXML
	private Button tisch24;
	@FXML
	private Button tisch25;

	private static RMIBestellService bestellService = Context.getInstance().getBestellService();
	private static Logger logger = LogManager.getLogger(SaalEinsController.class);
	List<Button> buttons = new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tischeLaden();
	}
	
	
	@FXML
	private void tischAction(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TischAnzeigen.fxml"));
		Parent tisch_anzeigen_parent = loader.load();

//		Handschuh Master-Update!
		TischAnzeigenController controller =  loader.getController();
		controller.setTischNummer(Integer.parseInt(((Button)event.getSource()).getText()));

		Scene tisch_anzeigen_scene = new Scene(tisch_anzeigen_parent);
		Stage tisch_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		tisch_stage.setScene(tisch_anzeigen_scene);
		tisch_stage.show();
		
		
		
	}

	@FXML
	private void zurueck(ActionEvent event) throws IOException {
		Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/MaInterface.fxml"));
		Scene ma_interface_scene = new Scene(ma_interface_parent);
		Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ma_stage.setScene(ma_interface_scene);
		ma_stage.show();
	}
	
	@FXML
	private void nextSeite(ActionEvent event) throws IOException {
		Parent saal2_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Saal2.fxml"));
		Scene saal2_interface_scene = new Scene(saal2_interface_parent);
		Stage saal2_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		saal2_stage.setScene(saal2_interface_scene);
		saal2_stage.show();
	}

	@FXML
	private void lastSeite(ActionEvent event) throws IOException {
		Parent saal2_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Saal1.fxml"));
		Scene saal2_interface_scene = new Scene(saal2_interface_parent);
		Stage saal2_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		saal2_stage.setScene(saal2_interface_scene);
		saal2_stage.show();
	}

	public void tischeLaden(){
		try{
			buttons.add(tisch1); buttons.add(tisch2); buttons.add(tisch3); buttons.add(tisch4); buttons.add(tisch5); buttons.add(tisch6); buttons.add(tisch7); buttons.add(tisch8); buttons.add(tisch9); buttons.add(tisch10);
			buttons.add(tisch11); buttons.add(tisch12); buttons.add(tisch13); buttons.add(tisch14); buttons.add(tisch15); buttons.add(tisch16); buttons.add(tisch17); buttons.add(tisch18); buttons.add(tisch19); buttons.add(tisch20);
			buttons.add(tisch21); buttons.add(tisch22); buttons.add(tisch23); buttons.add(tisch24); buttons.add(tisch25);

			List<Tisch> tischList = bestellService.findTischAll();
			for(Tisch t : tischList){
				if(t.getVerfuegbarkeit()==false){
					int tischNummer = t.getTischNummer();

					for(Button b : buttons){
						int buttonNummer = Integer.parseInt(b.getText());
						if(buttonNummer==tischNummer){
							b.setDisable(true);
						}
					}
				}
			}
		}catch(Exception e){
			logger.info("TischList konnte nicht geladen werden.", e);
		}
	}

	
}

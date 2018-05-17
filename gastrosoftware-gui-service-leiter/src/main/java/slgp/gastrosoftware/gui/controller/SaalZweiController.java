package slgp.gastrosoftware.gui.controller;

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
import slgp.gastrosoftware.RMIBestellService;
import slgp.gastrosoftware.Context;
import slgp.gastrosoftware.model.Tisch;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class SaalZweiController implements Initializable{
	@FXML
	public Button tisch26;
	@FXML
	private Button tisch27;
	@FXML
	private Button tisch28;
	@FXML
	private Button tisch29;
	@FXML
	private Button tisch30;
	@FXML
	private Button tisch31;
	@FXML
	private Button tisch32;
	@FXML
	private Button tisch33;
	@FXML
	private Button tisch34;
	@FXML
	private Button tisch35;
	@FXML
	private Button tisch36;
	@FXML
	private Button tisch37;
	@FXML
	private Button tisch38;
	@FXML
	private Button tisch39;
	@FXML
	private Button tisch40;
	@FXML
	private Button tisch41;
	@FXML
	private Button tisch42;
	@FXML
	private Button tisch43;
	@FXML
	private Button tisch44;
	@FXML
	private Button tisch45;
	@FXML
	private Button tisch46;
	@FXML
	private Button tisch47;
	@FXML
	private Button tisch48;
	@FXML
	private Button tisch49;
	@FXML
	private Button tisch50;

	private static RMIBestellService bestellService = Context.getInstance().getBestellService();
	private static Logger logger = LogManager.getLogger(SaalZweiController.class);
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
			buttons.add(tisch26); buttons.add(tisch27); buttons.add(tisch28); buttons.add(tisch29); buttons.add(tisch30); buttons.add(tisch31); buttons.add(tisch32); buttons.add(tisch33); buttons.add(tisch34); buttons.add(tisch35);
			buttons.add(tisch36); buttons.add(tisch37); buttons.add(tisch38); buttons.add(tisch39); buttons.add(tisch40); buttons.add(tisch41); buttons.add(tisch42); buttons.add(tisch43); buttons.add(tisch44); buttons.add(tisch45);
			buttons.add(tisch46); buttons.add(tisch47); buttons.add(tisch48); buttons.add(tisch49); buttons.add(tisch50);

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
			logger.info("TischList konnte nicht geladen werden.");
		}
	}


	
}

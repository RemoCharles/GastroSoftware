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

public class TerrasseEinsController implements Initializable{
	@FXML
	public Button tisch51;
	@FXML
	private Button tisch52;
	@FXML
	private Button tisch53;
	@FXML
	private Button tisch54;
	@FXML
	private Button tisch55;
	@FXML
	private Button tisch56;
	@FXML
	private Button tisch57;
	@FXML
	private Button tisch58;
	@FXML
	private Button tisch59;
	@FXML
	private Button tisch60;
	@FXML
	private Button tisch61;
	@FXML
	private Button tisch62;
	@FXML
	private Button tisch63;
	@FXML
	private Button tisch64;
	@FXML
	private Button tisch65;
	@FXML
	private Button tisch66;
	@FXML
	private Button tisch67;
	@FXML
	private Button tisch68;
	@FXML
	private Button tisch69;
	@FXML
	private Button tisch70;
	@FXML
	private Button tisch71;
	@FXML
	private Button tisch72;
	@FXML
	private Button tisch73;
	@FXML
	private Button tisch74;
	@FXML
	private Button tisch75;
	@FXML
	private Button tisch76;
	@FXML
	private Button tisch77;
	@FXML
	private Button tisch78;
	@FXML
	private Button tisch79;
	@FXML
	private Button tisch80;

	private static RMIBestellService bestellService = Context.getInstance().getBestellService();
	private static Logger logger = LogManager.getLogger(TerrasseEinsController.class);
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
		Parent terrasse1_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Terrasse2.fxml"));
		Scene terrasse1_interface_scene = new Scene(terrasse1_interface_parent);
		Stage terrasse1_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		terrasse1_stage.setScene(terrasse1_interface_scene);
		terrasse1_stage.show();
	}

	@FXML
	private void lastSeite(ActionEvent event) throws IOException {
		Parent terrasse2_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Terrasse1.fxml"));
		Scene terrasse2_interface_scene = new Scene(terrasse2_interface_parent);
		Stage terrasse2_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		terrasse2_stage.setScene(terrasse2_interface_scene);
		terrasse2_stage.show();
	}

public void tischeLaden(){
	try{
		buttons.add(tisch51); buttons.add(tisch52); buttons.add(tisch53); buttons.add(tisch54); buttons.add(tisch55); buttons.add(tisch56); buttons.add(tisch57); buttons.add(tisch58); buttons.add(tisch59); buttons.add(tisch60);
		buttons.add(tisch61); buttons.add(tisch62); buttons.add(tisch63); buttons.add(tisch64); buttons.add(tisch65); buttons.add(tisch66); buttons.add(tisch67); buttons.add(tisch68); buttons.add(tisch69); buttons.add(tisch70);
		buttons.add(tisch71); buttons.add(tisch72); buttons.add(tisch73); buttons.add(tisch74); buttons.add(tisch75); buttons.add(tisch76); buttons.add(tisch77); buttons.add(tisch78); buttons.add(tisch79); buttons.add(tisch80);

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

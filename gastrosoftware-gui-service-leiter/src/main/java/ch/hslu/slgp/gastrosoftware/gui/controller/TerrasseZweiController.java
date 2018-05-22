package ch.hslu.slgp.gastrosoftware.gui.controller;

import ch.hslu.slgp.gastrosoftware.gui.controller.TischAnzeigenController;
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

public class TerrasseZweiController implements Initializable{
	@FXML
	public Button tisch81;
	@FXML
	private Button tisch82;
	@FXML
	private Button tisch83;
	@FXML
	private Button tisch84;
	@FXML
	private Button tisch85;
	@FXML
	private Button tisch86;
	@FXML
	private Button tisch87;
	@FXML
	private Button tisch88;
	@FXML
	private Button tisch89;
	@FXML
	private Button tisch90;
	@FXML
	private Button tisch91;
	@FXML
	private Button tisch92;
	@FXML
	private Button tisch93;
	@FXML
	private Button tisch94;
	@FXML
	private Button tisch95;
	@FXML
	private Button tisch96;
	@FXML
	private Button tisch97;
	@FXML
	private Button tisch98;
	@FXML
	private Button tisch99;
	@FXML
	private Button tisch100;
	@FXML
	private Button tisch101;
	@FXML
	private Button tisch102;
	@FXML
	private Button tisch103;
	@FXML
	private Button tisch104;
	@FXML
	private Button tisch105;
	@FXML
	private Button tisch106;
	@FXML
	private Button tisch107;
	@FXML
	private Button tisch108;
	@FXML
	private Button tisch109;
	@FXML
	private Button tisch110;

	private static RMIBestellService bestellService = Context.getInstance().getBestellService();
	private static Logger logger = LogManager.getLogger(TerrasseZweiController.class);
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
			buttons.add(tisch81); buttons.add(tisch82); buttons.add(tisch83); buttons.add(tisch84); buttons.add(tisch85); buttons.add(tisch86); buttons.add(tisch87); buttons.add(tisch88); buttons.add(tisch89); buttons.add(tisch90);
			buttons.add(tisch91); buttons.add(tisch92); buttons.add(tisch93); buttons.add(tisch94); buttons.add(tisch95); buttons.add(tisch96); buttons.add(tisch97); buttons.add(tisch98); buttons.add(tisch99); buttons.add(tisch100);
			buttons.add(tisch101); buttons.add(tisch102); buttons.add(tisch103); buttons.add(tisch104); buttons.add(tisch105); buttons.add(tisch106); buttons.add(tisch107); buttons.add(tisch108); buttons.add(tisch109); buttons.add(tisch110);

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

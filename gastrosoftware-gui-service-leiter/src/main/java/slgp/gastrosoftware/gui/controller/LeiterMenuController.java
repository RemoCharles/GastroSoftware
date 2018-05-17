package slgp.gastrosoftware.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.RMIKonsumartikelService;
import slgp.gastrosoftware.RMIMenuService;
import slgp.gastrosoftware.gui.Context;
import slgp.gastrosoftware.model.Esswaren;
import slgp.gastrosoftware.model.Tagesmenu;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LeiterMenuController implements Initializable {

	private static Logger logger = LogManager.getLogger(LeiterMenuController.class);

  private static RMIKonsumartikelService konsumartikelService = Context.getInstance().getKonsumartikelService();
    private static RMIMenuService menuService = Context.getInstance().getMenuService();

    @FXML
	private ComboBox<String> cmbWochentage;

	@FXML
	private TableView<Esswaren> tblKonsumartikel;

	@FXML
	private TableColumn<Esswaren, String> colKat;

	@FXML
	private TableColumn<Esswaren, String> colBez;

	@FXML
	private TableColumn<Esswaren, Double> colPreis;

	@FXML
	private Button btnErstellen;

	@FXML
	private Button btnLoeschen;

	@FXML
	private Button btnHinzufuegen;

	@FXML
	private Label lblError;

	@FXML
	private TableView<Esswaren> tblMenu;

	@FXML
	private TableColumn<Esswaren, String> konsMenuKat;

	@FXML
	private TableColumn<Esswaren, String> konsMenuBez;

	@FXML
	private TableColumn<Esswaren, Double> konsMenuPr;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			konsMenuBez.setCellValueFactory(new PropertyValueFactory<Esswaren, String>("bezeichnung"));
			konsMenuKat.setCellValueFactory(new PropertyValueFactory<Esswaren, String>("kategorie"));
			konsMenuPr.setCellValueFactory(new PropertyValueFactory<Esswaren, Double>("preis"));
			wochentagAuswaehlen();
			tabelleLaden();

		} catch (Exception e) {
			logger.error("Tabelle konnte nicht befüllt werden...", e);
		}
	}
	@FXML
	private void loeschen(ActionEvent event) throws Exception{
		if (tblMenu.getSelectionModel().getSelectedItem() == null) {
			return;
		}
		Esswaren esswaren = tblMenu.getSelectionModel().getSelectedItem();
		List<Tagesmenu> tgList = menuService.findTagesmenuAll();

		if (esswaren != null) {
			try {
				List<Esswaren> esswarenList = new ArrayList<>(tblMenu.getItems());
				Tagesmenu tagesmenuVorLoeschen = new Tagesmenu(cmbWochentage.getSelectionModel().getSelectedItem(), esswarenList);
				for (Tagesmenu t : tgList){
					if(t.equals(tagesmenuVorLoeschen)){
						menuService.tagesmenuLoeschen(t);
					}
				}
				esswarenList.remove(esswaren);
				ObservableList<Esswaren> esswarenListObsv = FXCollections.observableArrayList(esswarenList);
				tblMenu.setItems(esswarenListObsv);
				List<Esswaren> tagesMenuList = new ArrayList<>(tblMenu.getItems());
				Tagesmenu tagesmenu = new Tagesmenu(cmbWochentage.getSelectionModel().getSelectedItem(), tagesMenuList);
				menuService.tagesmenuHinzufuegen(tagesmenu);
				lblError.setText("Tagesmenü erfolgreich angepasst.");

			} catch (Exception e) {
				logger.error("Fehler beim Löschen der Essware: ", e);
			}
		}
	}

	@FXML
	private void hinzufuegen(ActionEvent event) {
		ObservableList<Esswaren> esswarenObservableList = FXCollections.observableArrayList();
		Esswaren essware = tblKonsumartikel.getSelectionModel().getSelectedItem();
		List<Esswaren> vorhandeneMenuList = tblMenu.getItems();
		if (vorhandeneMenuList.size() > 0) {
		for(Esswaren e : vorhandeneMenuList) {
			if(e.getBezeichnung().equals(essware.getBezeichnung())){
				lblError.setText("Sie haben diesen Artikel bereits im Tagesmenü.");
			}
		}
			esswarenObservableList.addAll(vorhandeneMenuList);
			esswarenObservableList.add(essware);
			tblMenu.setItems(esswarenObservableList);
		} else {
			esswarenObservableList.add(essware);
			tblMenu.setItems(esswarenObservableList);
		}
	}


	@FXML
	private void erstellen(ActionEvent event) {
		try {
			List<Esswaren> tagesMenuList = new ArrayList<>(tblMenu.getItems());
			String wochenTag = cmbWochentage.getSelectionModel().getSelectedItem();
			if(tagesMenuList.size() == 0){
				lblError.setText("Dieses Tagesmenü enthält keine Elemente.");
			}
				List<Tagesmenu> tagesMenuListDB = menuService.findyTagesmenuByWochenTag(wochenTag);
				Tagesmenu tagesmenu = new Tagesmenu(wochenTag, tagesMenuList);
				for(Tagesmenu t : tagesMenuListDB){
						menuService.tagesmenuLoeschen(t);
				}
				menuService.tagesmenuHinzufuegen(tagesmenu);
				lblError.setText("Tagesmenü erfolgreich hinzugefügt.");

		} catch (Exception e) {
			logger.error("Fehler beim Speichern des Tagesmenu: ", e);
		}
	}


	private void wochentagAuswaehlen() throws Exception {
		try {
			// ComboBox füllen
			List<String> wochenTag = new ArrayList<>();

			wochenTag.add("Montag");
			wochenTag.add("Dienstag");
			wochenTag.add("Mittwoch");
			wochenTag.add("Donnerstag");
			wochenTag.add("Freitag");
			wochenTag.add("Samstag");
			wochenTag.add("Sonntag");

			ObservableList<String> wochenTagObservableList = FXCollections.observableArrayList(wochenTag);
			cmbWochentage.setItems(wochenTagObservableList);
			cmbWochentage.getSelectionModel().select(0);
		} catch (Exception e) {
			logger.info("Kategorienauswahl konnte nicht geladen werden.");
		}

	}

	public void tabelleLaden() throws Exception {
		tagesmenuLaden();
		List<Esswaren> esswarenList = konsumartikelService.findEsswarenAll();
		List<Esswaren> esswarenListTemp = new ArrayList<>();
		for(Esswaren e : esswarenList){
			if(e.getVerfuegbar()){
				esswarenListTemp.add(e);
			}
		}
		colBez.setCellValueFactory(new PropertyValueFactory<Esswaren, String>("bezeichnung"));
		colKat.setCellValueFactory(new PropertyValueFactory<Esswaren, String>("kategorie"));
		colPreis.setCellValueFactory(new PropertyValueFactory<Esswaren, Double>("preis"));
		ObservableList<Esswaren> esswarenObservableList = FXCollections.observableArrayList(esswarenListTemp);
		tblKonsumartikel.setItems(esswarenObservableList);
	}

	public void tagesmenuLaden(){
		try {
			lblError.setText("");
			String wochenTag = cmbWochentage.getSelectionModel().getSelectedItem();
			List<Tagesmenu> tagesMenuList = menuService.findyTagesmenuByWochenTag(wochenTag);
			ObservableList<Esswaren> tagesMenuListObsv = FXCollections.observableArrayList();
			for (Tagesmenu tagM : tagesMenuList) {
					tagesMenuListObsv.addAll(tagM.getListeKonsumartikel());
			}
			tblMenu.setItems(tagesMenuListObsv);

		} catch (Exception e) {
			logger.error("Fehler bei der Aktualisierung der Tabelle: ", e);
		}

	}

	@FXML
	public void zurueck(ActionEvent event) throws Exception {
		Parent leiter_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
		Scene leiter_interface_scene = new Scene(leiter_interface_parent);
		Stage leiter_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		leiter_stage.setScene(leiter_interface_scene);
		leiter_stage.show();
	}

}
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.glass.ui.Menu;

import slgp.gastrosoftware.model.BestellPosition;
import slgp.gastrosoftware.model.Esswaren;
import slgp.gastrosoftware.model.Konsumartikel;
import slgp.gastrosoftware.model.Menukarte;
import slgp.gastrosoftware.model.Person;
import slgp.gastrosoftware.model.Tagesmenu;
import slgp.gastrosoftware.persister.BestellPositionDAO;
import slgp.gastrosoftware.persister.impl.BestellPositionDAOImpl;
import slgp.gastrosoftware.persister.impl.EsswarenDAOImpl;
import slgp.gastrosoftware.persister.impl.KonsumartikelDAOImpl;
import slgp.gastrosoftware.persister.impl.MenukarteDAOImpl;
import slgp.gastrosoftware.persister.impl.PersonDAOImpl;
import slgp.gastrosoftware.persister.impl.TagesmenuDAOImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

public class LeiterMenuControllerTest implements Initializable {

	private static Logger logger = LogManager.getLogger(LeiterMenuControllerTest.class);
	private static TagesmenuDAOImpl tagesmenuDAO = new TagesmenuDAOImpl();
	private static EsswarenDAOImpl esswarenDAO = new EsswarenDAOImpl();
	List<Esswaren> menu = new ArrayList<>();

	@FXML
	private ComboBox<String> cmbWochentage;

	@FXML
	private TableView<Esswaren> tblKonsumartikel;

	@FXML
	private TableColumn<Esswaren, String> bPKat;

	@FXML
	private TableColumn<Esswaren, String> bPBez;

	@FXML
	private TableColumn<Esswaren, Double> bPPreis;

	@FXML
	private void menuErstellen(ActionEvent event) throws Exception {

	}

	@FXML
	private Button btnErstellen;

	@FXML
	private Button btnLoeschen;

	@FXML
	private Button btnHinzufuegen;

	@FXML
	private TableView<Esswaren> tblMenu;

	@FXML
	private TableColumn<Esswaren, String> konsMenuKat;

	@FXML
	private TableColumn<Esswaren, String> konsMenuBez;

	@FXML
	private TableColumn<Esswaren, Double> konsMenuPr;

	@FXML
	private void loeschen(ActionEvent event) {

	}

	@FXML
	private void hinzufuegen(ActionEvent event) {

	}

	@FXML
	private void erstellen(ActionEvent event) {

	}

	@FXML
	public void zurueck(ActionEvent event) throws Exception {
		Parent leiter_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
		Scene leiter_interface_scene = new Scene(leiter_interface_parent);
		Stage leiter_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		leiter_stage.setScene(leiter_interface_scene);
		leiter_stage.show();
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
			if (wochenTagObservableList.size() > 0) {
				cmbWochentage.getSelectionModel().select(0);
			}
		} catch (Exception e) {
			logger.info("Kategorienauswahl konnte nicht geladen werden.");
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		try {
			List<Tagesmenu> tagesmenuMontag = tagesmenuDAO.findyByWochenTag("Montag");
			List<Tagesmenu> tagesmenuDienstag = tagesmenuDAO.findyByWochenTag("Dienstag");
			List<Tagesmenu> tagesmenuMittwoch = tagesmenuDAO.findyByWochenTag("Mittwoch");
			List<Tagesmenu> tagesmenuDonnerstag = tagesmenuDAO.findyByWochenTag("Donnerstag");
			List<Tagesmenu> tagesmenuFreitag = tagesmenuDAO.findyByWochenTag("Freitag");
			List<Tagesmenu> tagesmenuSamstag = tagesmenuDAO.findyByWochenTag("Samstag");
			List<Tagesmenu> tagesmenuSonntag = tagesmenuDAO.findyByWochenTag("Sonntag");

		} catch (Exception e) {
			logger.error("Fehler beim Laden der Tabelle: ", e);
			throw new RuntimeException();
		}
		try {
			wochentagAuswaehlen();
			tabelleLaden();

		} catch (Exception e) {
			logger.error("Tabelle konnte nicht befüllt werden...", e);
		}

		tblKonsumartikel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Esswaren>() {
			public void changed(ObservableValue<? extends Esswaren> observable, Esswaren oldValue, Esswaren newValue) {
				System.out.println("selection changed");
				tblKonsumartikel.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			}
		});

		btnLoeschen.setOnAction(new EventHandler<ActionEvent>() {
			// Löschen von ausgwählten Esswaren aus tblMenu
			@Override
			public void handle(ActionEvent event) {
				final int selectedIdx = tblMenu.getSelectionModel().getSelectedIndex();
				if (selectedIdx != -1) {
					Konsumartikel itemToRemove = tblMenu.getSelectionModel().getSelectedItem();

					final int newSelectedIdx = (selectedIdx == tblMenu.getItems().size() - 1) ? selectedIdx - 1
							: selectedIdx;
					tblMenu.getSelectionModel().select(newSelectedIdx);
					tblMenu.getItems().remove(selectedIdx);
					// removes the item from the array
					System.out.println("selectIdx: " + selectedIdx);
					System.out.println("item: " + itemToRemove);
					menu.remove(selectedIdx);

				}
			}
		});
		btnHinzufuegen.setOnAction(new EventHandler<ActionEvent>() {
			// Hinzufügen von ausgwählten Esswaren aus tblKonsumartikel in tblMenu
			@Override
			public void handle(ActionEvent event) {
				menu.addAll(tblKonsumartikel.getSelectionModel().getSelectedItems());
				konsMenuBez.setCellValueFactory(new PropertyValueFactory<Esswaren, String>("bezeichnung"));
				konsMenuKat.setCellValueFactory(new PropertyValueFactory<Esswaren, String>("kategorie"));
				konsMenuPr.setCellValueFactory(new PropertyValueFactory<Esswaren, Double>("preis"));

				ObservableList<Esswaren> esswarenObservableList = FXCollections.observableArrayList(menu);
				tblMenu.setItems(esswarenObservableList);

			}

		});
		btnErstellen.setOnAction(new EventHandler<ActionEvent>() {
			// Speichern von ausgewählten Esswaren in tblMenu
			@Override
			public void handle(ActionEvent event) {
				try {
					List<Esswaren> tagesMenuList = tblMenu.getItems();
					Tagesmenu tagesmenu = new Tagesmenu(cmbWochentage.getSelectionModel().getSelectedItem(), tagesMenuList);
					System.out.println(tagesmenu);
					tagesmenuDAO.save(tagesmenu);
				} catch (Exception e) {
					logger.error("Fehler beim Speichern des Tagesmenu: ", e);
				}
			}
		});
	}

	public void tabelleLaden() throws Exception {

		List<Esswaren> esswarenList = esswarenDAO.findAll();

		bPBez.setCellValueFactory(new PropertyValueFactory<Esswaren, String>("bezeichnung"));
		bPKat.setCellValueFactory(new PropertyValueFactory<Esswaren, String>("kategorie"));
		bPPreis.setCellValueFactory(new PropertyValueFactory<Esswaren, Double>("preis"));

		ObservableList<Esswaren> esswarenObservableList = FXCollections.observableArrayList(esswarenList);

		tblKonsumartikel.setItems(esswarenObservableList);
		tagesmenuLaden();

	}

	public void tagesmenuLaden() throws Exception {
		try {
			tblMenu.getItems().clear();
			List<Tagesmenu> tagesmenuList = tagesmenuDAO.findAll();
			ObservableList<Esswaren> tagesMenuList = FXCollections.observableArrayList();

			String wochenTag = cmbWochentage.getSelectionModel().getSelectedItem();
			for (Tagesmenu tagM : tagesmenuList) {
				if (tagM.getWochenTag().equals(wochenTag)) {
					tagesMenuList.addAll(tagM.getListeKonsumartikel());
				}
			}
			tblMenu.setItems(tagesMenuList);

			// tblMenu.getItems().add(tagesmenuList);

			// tblMenu.getSelectionModel().select(0);

		} catch (Exception e) {
			logger.error("Fehler bei der Aktualisierung der Tabelle: ", e);
			throw new RuntimeException(e);
		}

	}

}

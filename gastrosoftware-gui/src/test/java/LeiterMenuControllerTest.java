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
import slgp.gastrosoftware.model.Konsumartikel;
import slgp.gastrosoftware.model.Menukarte;
import slgp.gastrosoftware.model.Person;
import slgp.gastrosoftware.persister.impl.KonsumartikelDAOImpl;
import slgp.gastrosoftware.persister.impl.MenukarteDAOImpl;
import slgp.gastrosoftware.persister.impl.PersonDAOImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LeiterMenuControllerTest implements Initializable {

	private static Logger logger = LogManager.getLogger(LeiterMenuControllerTest.class);
	private static KonsumartikelDAOImpl konsumartikelDAO = new KonsumartikelDAOImpl();

	@FXML
	private ComboBox<String> cmbWochentage;

	@FXML
	private TableView<Konsumartikel> tblKonsumartikel;

	@FXML
	private TableColumn<Konsumartikel, String> bPKat;

	@FXML
	private TableColumn<Konsumartikel, String> bPBez;

	@FXML
	private TableColumn<Konsumartikel, Double> bPPreis;

	@FXML
	private void menuErstellen(ActionEvent event) throws Exception {

	}

	@FXML
	private Button btnLoeschen;

	@FXML
	private Button btnHinzufuegen;

	@FXML
	private TableView<Konsumartikel> tblMenu;

	@FXML
	private TableColumn<Konsumartikel, String> konsMenuKat;

	@FXML
	private TableColumn<Konsumartikel, String> konsMenuBez;

	@FXML
	private TableColumn<Konsumartikel, Double> konsMenuPr;

	@FXML
	private void loeschen(ActionEvent event) {

	}

	@FXML
	private void hinzufuegen(ActionEvent event) {

	}

	@FXML
	public void zurueck(ActionEvent event) throws Exception {
		Parent leiter_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
		Scene leiter_interface_scene = new Scene(leiter_interface_parent);
		Stage leiter_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		leiter_stage.setScene(leiter_interface_scene);
		leiter_stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			List<Konsumartikel> konsumartikelList = konsumartikelDAO.findAll();

			bPBez.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("bezeichnung"));
			bPKat.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("kategorie"));
			bPPreis.setCellValueFactory(new PropertyValueFactory<Konsumartikel, Double>("preis"));

			ObservableList<Konsumartikel> konsumartikelObservableList = FXCollections
					.observableArrayList(konsumartikelList);

			tblKonsumartikel.setItems(konsumartikelObservableList);

		} catch (Exception e) {
			logger.error("Tabelle konnte nicht befüllt werden...", e);
		}

		List<Konsumartikel> menu = new ArrayList<>();

		tblKonsumartikel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Konsumartikel>() {
			public void changed(ObservableValue<? extends Konsumartikel> observable, Konsumartikel oldValue,
					Konsumartikel newValue) {
				System.out.println("selection changed");
				tblKonsumartikel.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			}
		});

		List<String> wochenTage = new ArrayList<>();
		wochenTage.add("Montag");
		wochenTage.add("Dienstag");
		wochenTage.add("Mittwoch");
		wochenTage.add("Donnerstag");
		wochenTage.add("Freitag");
		wochenTage.add("Samstag");
		wochenTage.add("Sonntag");

		ObservableList<String> observWochenTage = FXCollections.observableArrayList(wochenTage);

		cmbWochentage.setItems(observWochenTage);

		btnLoeschen.setOnAction(new EventHandler<ActionEvent>() {
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

			@Override
			public void handle(ActionEvent event) {
				menu.addAll(tblKonsumartikel.getSelectionModel().getSelectedItems());
				konsMenuBez.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("bezeichnung"));
				konsMenuKat.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("kategorie"));
				konsMenuPr.setCellValueFactory(new PropertyValueFactory<Konsumartikel, Double>("preis"));

				ObservableList<Konsumartikel> konsumartikelObservableList = FXCollections.observableArrayList(menu);
				tblMenu.setItems(konsumartikelObservableList);

			}

		});
	}

}

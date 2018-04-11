package slgp.gastrosoftware.ui.login;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.impl.KonsumartikelDAOImpl;

public class TischAnzeigenControllerTest implements Initializable {
	 private static final Logger logger = LogManager.getLogger(TischAnzeigenControllerTest.class);
	 
	@FXML
	private ComboBox<String> cmbKat;
	
	@FXML
	private TableView<Konsumartikel> tblKonsumartikel;
	
	@FXML
	private TableColumn<Konsumartikel, String> konsKat;
	
	@FXML
	private Button butAkt;
	
	@FXML
	private TableColumn<Konsumartikel, String> konsBez;
	
	@FXML
	private TableColumn<Konsumartikel, Double> konsPr;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			tblBefuellen();
		} catch (Exception e) {
			logger.error("Tabelle konnte nicht befüllt werden...");
		}
		
	}
		
	public void tblBefuellen() throws Exception {

		try {	
			
			
			/* Konsumartikel initialisieren */
			KonsumartikelDAOImpl KonsDAOImpl = new KonsumartikelDAOImpl();
			List<Konsumartikel> alleKonsumartikelListe = KonsDAOImpl.findAll() ;

			TreeSet<String> konsumartikelBezeichnung = new TreeSet<>();

			for (Konsumartikel kBez : alleKonsumartikelListe) {
				konsumartikelBezeichnung.add(kBez.getKategorie());
			}

			ObservableList<String> konsumArtikelBezeichnungsListe = FXCollections.observableArrayList();
			konsumArtikelBezeichnungsListe.addAll(konsumartikelBezeichnung);
			cmbKat.setItems(konsumArtikelBezeichnungsListe);

			if (konsumArtikelBezeichnungsListe.size() > 0) {
				cmbKat.getSelectionModel().select(0);
			}

			/* TableView konfigurieren */
			konsKat.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("kategorie"));
			konsBez.setCellValueFactory(new PropertyValueFactory<Konsumartikel, String>("bezeichnung"));
			konsPr.setCellValueFactory(new PropertyValueFactory<Konsumartikel, Double>("preis"));
			
			ObservableList<Konsumartikel> konsumartikelListe = FXCollections.observableArrayList();
			konsumartikelListe.addAll(alleKonsumartikelListe);
			tblKonsumartikel.setItems(konsumartikelListe);

//			updateTable();

		} catch (Exception e) {
			throw new RuntimeException();
		}

	}


}

//	@FXML
//	private void updateTable() {
//
//		try {
//
//			/* cmbProduktTyp initialisieren */
//			List<ProduktTyp> alleProduktTypListe = Context.getInstance().getMoebelhausLagerService().produktTypAlle();
//
//			if (cmbProduktTyp.getSelectionModel().getSelectedItem() != null) {
//
//				List<ProduktTyp> tempListe = new ArrayList<>();
//
//				/* Suche alle ProduktTyp-Objekte für den gewählten Namen */
//				for (ProduktTyp pTyp : alleProduktTypListe) {
//
//					if (pTyp.getName().equals(cmbProduktTyp.getSelectionModel().getSelectedItem())) {
//						tempListe.add(pTyp);
//					}
//				}
//
//				/*
//				 * In 'tempListe' stehen alle PrduktTyp-Objekte, deren Name identisch mit dem
//				 * gewählten Namen ist. Für jedes dieser Objekte muss jetzt ein
//				 * 'ProduktTypWrapper' Objekt erstellt werden
//				 */
//
//				ObservableList<ProduktTypWrapper> bestandListe = FXCollections.observableArrayList();
//
//				for (int i = 0; i < tempListe.size(); i++) {
//					int nr = i + 1;
//					ProduktTyp pTyp = tempListe.get(i);
//
//					/* Anzahl bestimmen */
//					int anzahl = Context.getInstance().getMoebelhausLagerService().produktBestand(pTyp.getTypCode())
//							.size();
//
//					bestandListe.add(new ProduktTypWrapper(nr, pTyp, anzahl));
//				}
//
//				tblAktuellerBestand.setItems(bestandListe);
//			}
//		} catch (Exception e) {
//			logger.error("Fehler beim Updaten der Tabelle: ", e);
//			throw new RuntimeException();
//		}
//
//	}
	


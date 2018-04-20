package slgp.gastrosoftware.ui.login;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import slgp.gastrosoftware.zentrale.persister.api.BestellungDAO;
import slgp.gastrosoftware.zentrale.persister.domain.BestellPosition;
import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.impl.BestellungDAOImpl;
import slgp.gastrosoftware.zentrale.persister.impl.KonsumartikelDAOImpl;

public class KuecheInterfaceControllerTest implements Initializable{
	private static final Logger logger = LogManager.getLogger(TischAnzeigenControllerTest.class);
	private static 	BestellungDAO bestellungen = new BestellungDAOImpl();

	@FXML
	private Button btBereit;

	@FXML
	private TableView<BestellPosition> tblOffeneBest;

	@FXML
	private TableColumn<BestellPosition, String> colKonsumart;
	
	@FXML
	private TableColumn<BestellPosition, Integer> colAnz;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			
			/* Bestellung initialisieren */
			List<Bestellung> alleBestellungenListe = bestellungen.findAll();
			List<Bestellung> unzubereiteteBestellungenListe = new ArrayList<>();
			for(Bestellung best : alleBestellungenListe) {
				if(best.getZubereitet()==(false)) {
					unzubereiteteBestellungenListe.add(best);
				}
			}


			List<BestellPosition> tempKonsList = new ArrayList<>();

			for(Bestellung b : unzubereiteteBestellungenListe) {
				for(BestellPosition bP : b.getKonsumartikel()) {
					if(bP.getKonsumartikel() instanceof Esswaren) {
						Konsumartikel k = bP.getKonsumartikel();
						tempKonsList.add(bP);
					}
				}
			}




			/* TableView konfigurieren */
			// Objekt welches in List enthalten ist in Tabelle schreiben
			colKonsumart.setCellValueFactory(new PropertyValueFactory<BestellPosition, String>("bezeichnung"));
			colAnz.setCellValueFactory(new PropertyValueFactory<BestellPosition, Integer>("anzahl"));
			ObservableList<BestellPosition> bestellungenListe = FXCollections.observableArrayList();
			bestellungenListe.addAll(tempKonsList);
			tblOffeneBest.setItems(bestellungenListe);

		} catch (Exception e) {
			logger.error("Tabelle konnte nicht befüllt werden...");
		}
	}


	@FXML
	private void artBereit(ActionEvent event) throws Exception{
//		 if (tblOffeneBest.getSelectionModel().getSelectedItem() == null) {
//	            return;
//	        }
//
//	        Konsumartikel esswaren = tblOffeneBest.getSelectionModel().getSelectedItem();
//
//	        if (Esswaren != null) {
//	            try {
//	                Context.getInstance().getMoebelhausLagerService().lieferantenLoeschen(lieferant);
//	                
//	            } catch (Exception e) {
//	                
//	            }
//	        }
	}



	@FXML
	private void logout(ActionEvent event) throws Exception {
		System.exit(0);
	}
}

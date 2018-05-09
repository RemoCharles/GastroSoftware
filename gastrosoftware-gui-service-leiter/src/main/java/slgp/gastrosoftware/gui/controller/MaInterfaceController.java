package slgp.gastrosoftware.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
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
import slgp.gastrosoftware.model.BestellPosition;
import slgp.gastrosoftware.model.Konsumartikel;
import slgp.gastrosoftware.persister.BestellPositionDAO;
import slgp.gastrosoftware.persister.impl.BestellPositionDAOImpl;

import javax.xml.bind.annotation.XmlList;

import static java.util.concurrent.TimeUnit.SECONDS;


public class MaInterfaceController implements Initializable{

	@FXML
	private TableView<BestellPosition> tblBestellungen;

	@FXML
	private Button btnServiert;

	@FXML
	private TableColumn<BestellPosition, String> colBez;

	@FXML
	private TableColumn<BestellPosition, Integer> colTisch;

	private static final Logger logger = LogManager.getLogger(MaInterfaceController.class);
	private static BestellPositionDAO bestellPositionDAO = new BestellPositionDAOImpl();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colBez.setCellValueFactory(new PropertyValueFactory<BestellPosition, String>("bezeichnung"));
		colTisch.setCellValueFactory(new PropertyValueFactory<BestellPosition, Integer>("tischNummer"));
		tabelleFuellen();
		tabelleAktualisieren();
	}

	@FXML
	private void serviert(ActionEvent event) throws Exception{
		BestellPosition bP = tblBestellungen.getSelectionModel().getSelectedItem();
		bP.setZubereitet(false);
		bestellPositionDAO.update(bP);
		tabelleFuellen();
	}

	private void tabelleFuellen() {
		try {
			List<BestellPosition> bestellPositionList = bestellPositionDAO.findAll();
			List<BestellPosition> offeneBestellungenList = new ArrayList<>();
			for (BestellPosition bP : bestellPositionList) {
				if (bP.getZubereitet()) {
					offeneBestellungenList.add(bP);
				}
			}
			ObservableList<BestellPosition> offeneBestellungenObsv = FXCollections.observableArrayList(offeneBestellungenList);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    tblBestellungen.setItems(offeneBestellungenObsv);
                }
            });

		}catch(Exception e){
			logger.info("Tabelle konnte nicht befèllt werden.");
		}
	}

	@FXML
	private void saal1Action(ActionEvent event) throws Exception {
		Parent saal1_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Saal1.fxml"));
		Scene saal1_interface_scene = new Scene(saal1_interface_parent);
		Stage saal1_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
		saal1_stage.setScene(saal1_interface_scene);
		saal1_stage.show();
	}
	
	@FXML
	private void terrasseAction(ActionEvent event) throws Exception {
		Parent terrasse_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/Terrasse1.fxml"));
		Scene terrasse_interface_scene = new Scene(terrasse_interface_parent);
		Stage terrasse_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
		terrasse_stage.setScene(terrasse_interface_scene);
		terrasse_stage.show();
	}
	
	@FXML
	private void logout(ActionEvent event) throws Exception {
		Parent login_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/UserLogin.fxml"));
		Scene login_interface_scene = new Scene(login_interface_parent);
		Stage login_stage = (Stage)  ((Node) event.getSource()).getScene().getWindow();
		login_stage.setScene(login_interface_scene);
		login_stage.show();
	}

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void tabelleAktualisieren() {
        final Runnable aktualisieren = new Runnable() {
            public void run() {
                try {
                    tabelleFuellen();
                    System.out.println("aktualisiert");
                } catch (Exception e) {
                    logger.info("Tabelle konnte nicht befüllt werden...");
                }
            }
        };
        final ScheduledFuture<?> aktualisierungsHandle = scheduler.scheduleAtFixedRate(aktualisieren, 5, 5, SECONDS);

    }

}

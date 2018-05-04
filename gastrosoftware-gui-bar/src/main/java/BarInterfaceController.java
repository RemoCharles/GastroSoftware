import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.BestellPosition;
import slgp.gastrosoftware.model.Bestellung;
import slgp.gastrosoftware.model.Getraenke;
import slgp.gastrosoftware.persister.BestellPositionDAO;
import slgp.gastrosoftware.persister.BestellungDAO;
import slgp.gastrosoftware.persister.impl.BestellPositionDAOImpl;
import slgp.gastrosoftware.persister.impl.BestellungDAOImpl;

import java.net.URL;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

public class BarInterfaceController implements Initializable {
    private static final Logger logger = LogManager.getLogger(BarInterfaceController.class);
    private static BestellungDAO bestellungen = new BestellungDAOImpl();
    private static BestellPositionDAO bestellPositionDAO = new BestellPositionDAOImpl();

    @FXML
    private Button btBereit;

    @FXML
    private TableView<BestellPosition> tblOffeneBest;

    @FXML
    private TableColumn<BestellPosition, String> colKonsumart;

    @FXML
    private TableColumn<BestellPosition, Integer> colAnz;

    @FXML
    private TableColumn<BestellPosition, Integer> colTischNr;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            tabelleBefuellen();
            tabelleAktualisieren();
        } catch (Exception e) {
            logger.error("Tabelle konnte nicht befüllt werden...");
        }
    }


    @FXML
    private void artBereit(ActionEvent event) throws Exception {
        if (tblOffeneBest.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        BestellPosition bP = tblOffeneBest.getSelectionModel().getSelectedItem();

        if (bP != null) {
            try {
                bP.setZubereitet(true);
                bestellPositionDAO.update(bP);
                tabelleBefuellen();

            } catch (Exception e) {
                logger.info("Bestellposition konnte nicht aktualisiert werden...");
            }
        }
    }


    @FXML
    private void logout(ActionEvent event) throws Exception {
        System.exit(0);
    }

    private void tabelleBefuellen() throws Exception {
        /* Bestellung initialisieren */
        List<Bestellung> alleBestellungenListe = bestellungen.findAll();
        List<BestellPosition> tempKonsList = new ArrayList<>();

        for (Bestellung b : alleBestellungenListe) {
            for (BestellPosition bP : b.getKonsumartikel()) {
                if (bP.getKonsumartikel() instanceof Getraenke && bP.getZubereitet() == false) {
                    tempKonsList.add(bP);
                }
            }
        }
        /* TableView konfigurieren */
        // Objekt welches in List enthalten ist in Tabelle schreiben
        colKonsumart.setCellValueFactory(new PropertyValueFactory<BestellPosition, String>("bezeichnung"));
        colAnz.setCellValueFactory(new PropertyValueFactory<BestellPosition, Integer>("anzahl"));
        colTischNr.setCellValueFactory(new PropertyValueFactory<BestellPosition, Integer>("tischNummer"));
        ObservableList<BestellPosition> bestellungenListe = FXCollections.observableArrayList(tempKonsList);
        tblOffeneBest.setItems(bestellungenListe);
    }


    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public void tabelleAktualisieren() {
        final Runnable aktualisieren = new Runnable() {
            public void run() {
                try {
                    tabelleBefuellen();
                    System.out.println("aktualisiert");
                } catch (Exception e) {
                    logger.info("Tabelle konnte nicht befüllt werden...");
                }
            }
        };
        final ScheduledFuture<?> aktualisierungsHandle = scheduler.scheduleAtFixedRate(aktualisieren, 5, 5, SECONDS);

    }
}






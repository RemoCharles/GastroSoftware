
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import slgp.gastrosoftware.model.BestellPosition;
import slgp.gastrosoftware.model.Bestellung;
import slgp.gastrosoftware.persister.BestellPositionDAO;
import slgp.gastrosoftware.persister.BestellungDAO;
import slgp.gastrosoftware.persister.impl.BestellPositionDAOImpl;
import slgp.gastrosoftware.persister.impl.BestellungDAOImpl;

import java.util.List;

public class Application extends javafx.application.Application {
	private static Logger logger = (Logger) LogManager.getLogger(Application.class);

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
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/BarInterface.fxml"));
		primaryStage.setTitle("Gastro Software");
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, 900, 600));
		primaryStage.show();
	}

	public static void main(String[] args) throws Exception {
		Util.resetDb();
		Util.erstellePersonenListe();
		Util.createKonsumartikelListe();
		Util.createBestellPositionAlleKonsumartikel();
		//Util.createBestellungListe();
		List<Bestellung> liste = Util.createBestellungListe();
		for(Bestellung b : liste) {
			logger.info(b);
		}
		Util.createTischRechnung();
		launch(args);
	}
}

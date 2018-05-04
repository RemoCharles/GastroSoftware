package slgp.gastrosoftware.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import slgp.gastrosoftware.model.Bestellung;

import java.util.List;

public class Application extends javafx.application.Application {
	private static Logger logger = (Logger) LogManager.getLogger(Application.class);

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserLogin.fxml"));
		primaryStage.setTitle("Gastro Software");
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, 600, 400));
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

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import slgp.gastrosoftware.model.Bestellung;
import util.Util;

import java.util.List;

public class GastroSoftwareGUITest extends Application {
	private static Logger logger = (Logger) LogManager.getLogger(GastroSoftwareGUITest.class);

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
		Util.createBestellungListe();

		launch(args);
	}
}

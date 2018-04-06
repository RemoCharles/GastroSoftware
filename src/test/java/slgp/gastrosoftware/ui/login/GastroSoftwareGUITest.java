package slgp.gastrosoftware.ui.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.util.DbHelper;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;



public class GastroSoftwareGUITest extends Application {
	private static Logger logger = (Logger) LogManager.getLogger(GastroSoftwareGUITest.class);

	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserLogin.fxml"));
		primaryStage.setTitle("Gastro Software");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
	}


	public static void main(String[] args) throws Exception {

		List<Person> personen;
		personen = Util.erstellePersonenListe();
		DbHelper.personenSpeichern(personen);

		launch(args);
	}
}

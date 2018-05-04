package slgp.gastrosoftware.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import slgp.gastrosoftware.model.Mitarbeiter;
import slgp.gastrosoftware.model.Tisch;
import slgp.gastrosoftware.persister.LoginDAO;
import slgp.gastrosoftware.persister.MitarbeiterDAO;
import slgp.gastrosoftware.persister.TischDAO;
import slgp.gastrosoftware.persister.impl.LoginDAOImpl;
import slgp.gastrosoftware.persister.impl.MitarbeiterDAOImpl;
import slgp.gastrosoftware.persister.impl.TischDAOImpl;

import java.util.List;

public class ServiceLeiterApplication extends javafx.application.Application {
    private static Logger logger = (Logger) LogManager.getLogger(ServiceLeiterApplication.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserLogin.fxml"));
        primaryStage.setTitle("Gastro Software");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
//		Util.resetDb();
//		Util.erstellePersonenListe();
//		Util.createKonsumartikelListe();
//		Util.createBestellPositionAlleKonsumartikel();
//		//Util.createBestellungListe();
//
//		List<Bestellung> liste = Util.createBestellungListe();
//		for(Bestellung b : liste) {
//			logger.info(b);
//		}
//		Util.createTischRechnung();


        MitarbeiterDAO mitarbeiterDAO = new MitarbeiterDAOImpl();
        for (Mitarbeiter mitarbeiter : mitarbeiterDAO.findAll()) {
            logger.info(mitarbeiter);
        }
        launch(args);


    }
}

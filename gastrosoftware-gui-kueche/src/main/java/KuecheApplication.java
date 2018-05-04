import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import slgp.gastrosoftware.gui.Util;
import slgp.gastrosoftware.model.Bestellung;

import java.util.List;

public class KuecheApplication extends javafx.application.Application {
    private static Logger logger = (Logger) LogManager.getLogger(KuecheApplication.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/KuecheInterface.fxml"));
        primaryStage.setTitle("Gastro Software");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
//        Util.resetDb();
////        Util.erstellePersonenListe();
////        Util.createKonsumartikelListe();
////        Util.createBestellPositionAlleKonsumartikel();
////        //Util.createBestellungListe();
////
////        List<Bestellung> liste = Util.createBestellungListe();
////        for (Bestellung b : liste) {
////            logger.info(b);
////        }
////        Util.createTischRechnung();
        launch(args);
    }

}

package slgp.gastrosoftware.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import slgp.gastrosoftware.RMIPersonService;
import slgp.gastrosoftware.model.Mitarbeiter;

public class ServiceLeiterApplication extends javafx.application.Application {
    private static Logger logger = (Logger) LogManager.getLogger(ServiceLeiterApplication.class);

    private static RMIPersonService personService = Context.getInstance().getPersonService();
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserLogin.fxml"));
        primaryStage.setTitle("Gastro Software");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {

        for (Mitarbeiter mitarbeiter : personService.findMitarbeiterAll()) {
            logger.info(mitarbeiter);
        }
        launch(args);


    }
}

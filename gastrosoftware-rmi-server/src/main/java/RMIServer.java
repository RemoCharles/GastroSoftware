import impl.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.*;

import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class RMIServer extends Application {

    private static Logger logger = LogManager.getLogger(RMIServer.class);

    /* Name der Config-Datei */
    private final static String CONFIG_FILE_NAME = "rmi_server.properties";

    /* Default Port-Nummer 1099 */
    private final static int DEFAULT_PORT_NUMMER = 1099;

    public static void main(String[] args) {
        try {
            startRMI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        launch(args);
    }

    private static int getRmiPort() throws Exception {
        int portNummer = 0;
        Properties props = new Properties();
        URL url = Thread.currentThread().getContextClassLoader().getResource(CONFIG_FILE_NAME);
        InputStream is = null;

        try {
            is = url.openStream();
            props.load(is);
            String strPortNummer = props.getProperty("rmi.portnummer", "");
            if (strPortNummer.equals("")) {
                portNummer = DEFAULT_PORT_NUMMER;
            } else {

                try {
                    portNummer = Integer.parseInt(strPortNummer);
                } catch (NumberFormatException e) {
                    logger.error("Port-Nummer Angabe nicht korrekt: ", e);
                    throw e;
                }
            }

            return portNummer;

        } catch (Exception e) {
            logger.error("Fehler beim Einlesen von Config-Angaben: ", e);
            throw e;
        }
    }

    private static void startRMI() throws Exception {

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            if (registry != null) {
                RMIPersonService personServiceRO = new RMIPersonServiceImpl();
                registry.rebind(RMIPersonService.RO_NAME, personServiceRO);
                logger.info("Remote Object \'" + RMIPersonService.RO_NAME + "\' bound!");

                RMIBestellService bestellServiceRO = new RMIBestellServiceImpl();
                registry.rebind(RMIBestellService.RO_NAME, bestellServiceRO);
                logger.info("Remote Object \'" + RMIBestellService.RO_NAME + "\' bound!");

                RMIKonsumartikelService konsumartikelServiceRO = new RMIKonsumartikelServiceImpl();
                registry.rebind(RMIKonsumartikelService.RO_NAME, konsumartikelServiceRO);
                logger.info("Remote Object \'" + RMIKonsumartikelService.RO_NAME + "\' bound!");

                RMIMenuService menuServiceRO = new RMIMenuServiceImpl();
                registry.rebind(RMIMenuService.RO_NAME, menuServiceRO);
                logger.info("Remote Object \'" + RMIMenuService.RO_NAME + "\' bound!");

                RMIRechnungService rechnungServiceRO = new RMIRechnungServiceImpl();
                registry.rebind(RMIRechnungService.RO_NAME, rechnungServiceRO);
                logger.info("Remote Object \'" + RMIRechnungService.RO_NAME + "\' bound!");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/RMIController.fxml"));
        primaryStage.setTitle("Gastro Software");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 300, 200));
        primaryStage.getIcons().add(new Image("/restaurant.png"));
        primaryStage.show();

        //TODO: Wie registry unbinden nach Window close?
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
    }




}
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

import javafx.print.Printer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RMIServer {

    private static Logger logger = LogManager.getLogger(RMIServer.class);

    /* Name der Config-Datei */
    private final static String CONFIG_FILE_NAME = "rmi_server.properties";

    /* Default Port-Nummer 1099 */
    private final static int DEFAULT_PORT_NUMMER = 1099;

    public static void main(String[] args)  {

        // TODO - Entferntes Objekt erstellen und bei dem Namensdienst
        // (rmiregistry) anmelden (binding)

//        PDFPrinter pdfPrinter = new PrinterService();

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            if (registry != null) {
//                registry.rebind(PrinterService.TischRechnung_RO, );
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }


        // HINWEIS:
        // Die Property-Datei musss evtl. angepasst werden ...
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

}

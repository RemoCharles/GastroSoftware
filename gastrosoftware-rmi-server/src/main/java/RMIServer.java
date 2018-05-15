import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.*;

import javax.swing.*;
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class RMIServer {
    //TODO: RMI Konfigurieren

    private static Logger logger = LogManager.getLogger(RMIServer.class);

    /* Name der Config-Datei */
    private final static String CONFIG_FILE_NAME = "rmi_server.properties";

    /* Default Port-Nummer 1099 */
    private final static int DEFAULT_PORT_NUMMER = 1099;

    public static void main(String[] args)  {

//        System.setProperty("java.security.policy", "file:.checker.policy");
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            if (registry != null) {
                registry.rebind(RMIPersonService.RO_NAME, registry);
                registry.rebind(RMIBestellService.RO_NAME, registry);
                registry.rebind(RMIKonsumartikelService.RO_NAME, registry);
                registry.rebind(RMIMenuService.RO_NAME, registry);
                registry.rebind(RMIRechnungService.RO_NAME, registry);
                JOptionPane.showMessageDialog(null,
                        "Ok to stop","GastroSoftwareRMI" , JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;

public class Context {

    private static Logger logger = LogManager.getLogger(Context.class);

    private static final String PROPERTY_FILE_NAME = "rmi_client.properties";
    private static final String POLICY_FILE_NAME = "rmi_client.policy";

    private static Context INSTANCE = new Context();

    private RMIRechnungService rechnungService;
    private RMIMenuService menuService;
    private RMIKonsumartikelService konsumartikelService;
    private RMIBestellService bestellService;
    private RMIPersonService personService;

    private Context() {
    }

    public static Context getInstance() {
        return INSTANCE;
    }

    public RMIRechnungService getRechnungService() {

        int portNr = 0;

        if (rechnungService == null) {

            try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {

                setSecurityManager();

                Properties props = new Properties();

                if (is == null) {
                    throw new RuntimeException(
                            "Die Property-Datei \'" + PROPERTY_FILE_NAME + "\' konnte nicht gefunden werden!");
                } else {

                    props.load(is);

                    String ip = props.getProperty("rmi.server.ip");
                    String strPort = props.getProperty("rmi.registry.port");

                    try {
                        portNr = Integer.parseInt(strPort);
                        Registry reg = LocateRegistry.getRegistry(ip, portNr);

                        if (reg != null) {
                            String url = "rmi://" + ip + ":" + portNr + "/" + RMIRechnungService.RO_NAME;

                            rechnungService = (RMIRechnungService) Naming.lookup(url);

                        } else {
                            String msg = "Die Reference auf RMI-Registry konnte auf " + ip + ":" + portNr
                                    + " nicht geholt werden!";
                            logger.error(msg);
                            throw new RuntimeException(msg);
                        }

                    } catch (NumberFormatException nfe) {
                        String msg = "Die Portnummer-Angabe \'" + strPort + "\' ist nicht korrekt";
                        logger.error(msg, nfe);
                        throw new RuntimeException(nfe);
                    }
                }
            } catch (Exception e) {
                String msg = "Fehler beim Holen des RmiLoginRO:";
                logger.error(msg, e);
                throw new RuntimeException(msg);
            }
        }

        return rechnungService;
    }

    public RMIMenuService getMenuService() {

        int portNr = 0;

        if (menuService == null) {

            try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {

                setSecurityManager();

                Properties props = new Properties();

                if (is == null) {
                    throw new RuntimeException(
                            "Die Property-Datei \'" + PROPERTY_FILE_NAME + "\' konnte nicht gefunden werden!");
                } else {

                    props.load(is);

                    String ip = props.getProperty("rmi.server.ip");
                    String strPort = props.getProperty("rmi.registry.port");

                    try {
                        portNr = Integer.parseInt(strPort);
                        Registry reg = LocateRegistry.getRegistry(ip, portNr);

                        if (reg != null) {
                            String url = "rmi://" + ip + ":" + portNr + "/" + RMIMenuService.RO_NAME;

                            menuService = (RMIMenuService) Naming.lookup(url);

                        } else {
                            String msg = "Die Reference auf RMI-Registry konnte auf " + ip + ":" + portNr
                                    + " nicht geholt werden!";
                            logger.error(msg);
                            throw new RuntimeException(msg);
                        }

                    } catch (NumberFormatException nfe) {
                        String msg = "Die Portnummer-Angabe \'" + strPort + "\' ist nicht korrekt";
                        logger.error(msg, nfe);
                        throw new RuntimeException(nfe);
                    }
                }
            } catch (Exception e) {
                String msg = "Fehler beim Holen des RmiLoginRO:";
                logger.error(msg, e);
                throw new RuntimeException(msg);
            }
        }

        return menuService;
    }

    public RMIKonsumartikelService getKonsumartikelService() {

        int portNr = 0;

        if (konsumartikelService == null) {

            try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {

                setSecurityManager();

                Properties props = new Properties();

                if (is == null) {
                    throw new RuntimeException(
                            "Die Property-Datei \'" + PROPERTY_FILE_NAME + "\' konnte nicht gefunden werden!");
                } else {

                    props.load(is);

                    String ip = props.getProperty("rmi.server.ip");
                    String strPort = props.getProperty("rmi.registry.port");

                    try {
                        portNr = Integer.parseInt(strPort);
                        Registry reg = LocateRegistry.getRegistry(ip, portNr);

                        if (reg != null) {
                            String url = "rmi://" + ip + ":" + portNr + "/" + RMIKonsumartikelService.RO_NAME;

                            konsumartikelService = (RMIKonsumartikelService) Naming.lookup(url);

                        } else {
                            String msg = "Die Reference auf RMI-Registry konnte auf " + ip + ":" + portNr
                                    + " nicht geholt werden!";
                            logger.error(msg);
                            throw new RuntimeException(msg);
                        }

                    } catch (NumberFormatException nfe) {
                        String msg = "Die Portnummer-Angabe \'" + strPort + "\' ist nicht korrekt";
                        logger.error(msg, nfe);
                        throw new RuntimeException(nfe);
                    }
                }
            } catch (Exception e) {
                String msg = "Fehler beim Holen des RmiLoginRO:";
                logger.error(msg, e);
                throw new RuntimeException(msg);
            }
        }

        return konsumartikelService;
    }

    public RMIBestellService getBestellService() {

        int portNr = 0;

        if (bestellService == null) {

            try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {

                setSecurityManager();

                Properties props = new Properties();

                if (is == null) {
                    throw new RuntimeException(
                            "Die Property-Datei \'" + PROPERTY_FILE_NAME + "\' konnte nicht gefunden werden!");
                } else {

                    props.load(is);

                    String ip = props.getProperty("rmi.server.ip");
                    String strPort = props.getProperty("rmi.registry.port");

                    try {
                        portNr = Integer.parseInt(strPort);
                        Registry reg = LocateRegistry.getRegistry(ip, portNr);

                        if (reg != null) {
                            String url = "rmi://" + ip + ":" + portNr + "/" + RMIBestellService.RO_NAME;

                            bestellService = (RMIBestellService) Naming.lookup(url);

                        } else {
                            String msg = "Die Reference auf RMI-Registry konnte auf " + ip + ":" + portNr
                                    + " nicht geholt werden!";
                            logger.error(msg);
                            throw new RuntimeException(msg);
                        }

                    } catch (NumberFormatException nfe) {
                        String msg = "Die Portnummer-Angabe \'" + strPort + "\' ist nicht korrekt";
                        logger.error(msg, nfe);
                        throw new RuntimeException(nfe);
                    }
                }
            } catch (Exception e) {
                String msg = "Fehler beim Holen des RmiLoginRO:";
                logger.error(msg, e);
                throw new RuntimeException(msg);
            }
        }

        return bestellService;
    }

    public RMIPersonService getPersonService() {

        int portNr = 0;

        if (personService == null) {

            try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {

                setSecurityManager();

                Properties props = new Properties();

                if (is == null) {
                    throw new RuntimeException(
                            "Die Property-Datei \'" + PROPERTY_FILE_NAME + "\' konnte nicht gefunden werden!");
                } else {

                    props.load(is);

                    String ip = props.getProperty("rmi.server.ip");
                    String strPort = props.getProperty("rmi.registry.port");

                    try {
                        portNr = Integer.parseInt(strPort);
                        Registry reg = LocateRegistry.getRegistry(ip, portNr);

                        if (reg != null) {
                            String url = "rmi://" + ip + ":" + portNr + "/" + RMIPersonService.RO_NAME;

                            personService = (RMIPersonService) Naming.lookup(url);

                        } else {
                            String msg = "Die Reference auf RMI-Registry konnte auf " + ip + ":" + portNr
                                    + " nicht geholt werden!";
                            logger.error(msg);
                            throw new RuntimeException(msg);
                        }

                    } catch (NumberFormatException nfe) {
                        String msg = "Die Portnummer-Angabe \'" + strPort + "\' ist nicht korrekt";
                        logger.error(msg, nfe);
                        throw new RuntimeException(nfe);
                    }
                }
            } catch (Exception e) {
                String msg = "Fehler beim Holen des RmiLoginRO:";
                logger.error(msg, e);
                throw new RuntimeException(msg);
            }
        }

        return personService;
    }


    /* Diese Methode setzt den SecurityManager */
    private void setSecurityManager() throws IOException {

        /*
         * Hinweis: die rmi-policy File ist entweder im Verzeichnis 'src' oder in
         * 'resources', wenn man mit maven arbeitet
         */

        InputStream is = this.getClass().getClassLoader().getResourceAsStream(POLICY_FILE_NAME);

        File tempFile = File
                .createTempFile(System.getProperty("user.home") + File.separator + "moebelhandel_rmi_policy", "tmp");

        FileOutputStream fos = new FileOutputStream(tempFile);

        /* Inhalt der policy-Datei in 'tempFile' kopieren */
        int n = 0;

        while ((n = is.read()) != -1) {
            fos.write(n);
        }

        is.close();
        fos.close();

        String pathToTempPolicyFile = tempFile.getAbsolutePath();

        tempFile.deleteOnExit();

        if (System.getSecurityManager() == null) {
            /* Policy-File muss im ROOT-Verzeichnis des Projekts sein! */
            System.setProperty("java.security.policy", pathToTempPolicyFile);
            System.setSecurityManager(new SecurityManager());
        }

    }
}
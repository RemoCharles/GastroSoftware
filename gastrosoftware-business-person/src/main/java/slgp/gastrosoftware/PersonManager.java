package slgp.gastrosoftware;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.Login;
import slgp.gastrosoftware.model.Mitarbeiter;
import slgp.gastrosoftware.model.Person;
import slgp.gastrosoftware.persister.LoginDAO;
import slgp.gastrosoftware.persister.MitarbeiterDAO;
import slgp.gastrosoftware.persister.PersonDAO;
import slgp.gastrosoftware.persister.impl.LoginDAOImpl;
import slgp.gastrosoftware.persister.impl.MitarbeiterDAOImpl;
import slgp.gastrosoftware.persister.impl.PersonDAOImpl;

import java.util.List;

public class PersonManager implements PersonService {


    private static Logger logger = LogManager.getLogger(PersonManager.class);

    private static PersonManager INSTANCE = new PersonManager();

    public static PersonManager getInstance() {
        return INSTANCE;
    }

    private LoginDAO loginDAO;

    public LoginDAO getLoginDAO() {
        if(loginDAO== null) {
            loginDAO = new LoginDAOImpl();
        }
        return loginDAO;
    }

    private PersonDAO personDAO;

    public PersonDAO getPersonDAO() {

        if (personDAO == null) {
            personDAO = new PersonDAOImpl();
        }
        return personDAO;
    }

    public MitarbeiterDAO mitarbeiterDAO;

    public MitarbeiterDAO getMitarbeiterDAO(){
        if (mitarbeiterDAO == null){
            mitarbeiterDAO = new MitarbeiterDAOImpl();
        }
        return mitarbeiterDAO;
    }

    @Override
    public boolean pruefeLogin(String username, String passwort) throws Exception {
        try {
            return getLoginDAO().pruefeLogin(username, passwort);
        } catch (Exception e) {
            String msg = "Es konnte kein Login gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }    }

    @Override
    public String getFunktionPerson(String username, String passwort) throws Exception {
        try {
            return getLoginDAO().getFunktionPerson(username, passwort);
        } catch (Exception e) {
            String msg = "Es Konnte keine Funktion gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Login> findLoginAll() throws Exception {
        try {
            return getLoginDAO().findAll();
        } catch (Exception e) {
            String msg = "Es Konnte kein Login gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Person> findPersonAll() throws Exception {
        try {
            return getPersonDAO().findAll();
        } catch (Exception e) {
            String msg = "Es Konnte keine Person gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Mitarbeiter> findMitarbeiterAll() throws Exception {
        try {
            return getMitarbeiterDAO().findAll();
        } catch (Exception e) {
            String msg = "Es Konnte kein Mitarbeiter gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }    }

    @Override
    public Person personHinzufuegen(Person person) throws Exception {
        try {
            return getPersonDAO().save(person);
        } catch (Exception e) {
            String msg = "person \'" + person.getName() + " " + person.getVorname()
                    + "\' konnte nicht hinzugefügt werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Person personAktualisieren(Person person) throws Exception {
        try {
            return getPersonDAO().update(person);
        } catch (Exception e) {
            String msg = "person \'" + person.getName() + " " + person.getVorname()
                    + "\' konnte nicht aktualisiert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public void personLoeschen(Person person) throws Exception {
        try {
            getPersonDAO().delete(person);
        } catch (Exception e) {
            String msg = "person \'" + person.getName() + " " + person.getVorname()
                    + "\' konnte nicht gelöscht werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Person> findPersonByNachname(String nachname) throws Exception {
        try {
            return getPersonDAO().findByNachname(nachname);
        } catch (Exception e) {
            String msg = "Person \'" + nachname + "\' konnte nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Person> findPersonByVorname(String vorname) throws Exception {
        try {
            return getPersonDAO().findByVorname(vorname);
        } catch (Exception e) {
            String msg = "Person \'" + vorname + "\' konnte nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Person> findPersonByNachnameUndVorname(String nachname, String vorname) throws Exception {
        try {
            return getPersonDAO().findByNachnameUndVorname(nachname, vorname);
        } catch (Exception e) {
            String msg = "Person \'" + nachname + "\' konnte nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Person findPersonByUsername(String username) throws Exception {
        try {
            return getPersonDAO().findByUsername(username);
        } catch (Exception e) {
            String msg = "Person \'" + username + "\' konnte nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Mitarbeiter mitarbeiterHinzufuegen(Mitarbeiter mitarbeiter) throws Exception {
        try {
            return getMitarbeiterDAO().save(mitarbeiter);
        } catch (Exception e) {
            String msg = "Mitarbeiter \'" + mitarbeiter.getName() + " " + mitarbeiter.getVorname()
                    + "\' konnte nicht hinzugefügt werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Mitarbeiter mitarbeiterAktualisieren(Mitarbeiter mitarbeiter) throws Exception {
        try {
            return getMitarbeiterDAO().update(mitarbeiter);
        } catch (Exception e) {
            String msg = "Mitarbeiter \'" + mitarbeiter.getName() + " " + mitarbeiter.getVorname()
                    + "\' konnte nicht aktualisiert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public void mitarbeiterLoeschen(Mitarbeiter mitarbeiter) throws Exception {
        try {
            getMitarbeiterDAO().delete(mitarbeiter);
        } catch (Exception e) {
            String msg = "Mitarbeiter \'" + mitarbeiter.getName() + " " + mitarbeiter.getVorname()
                    + "\' konnte nicht gelöscht werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Mitarbeiter> findMitarbeiterByNachname(String nachname) throws Exception {
        try {
            return getMitarbeiterDAO().findByNachname(nachname);
        } catch (Exception e) {
            String msg = "Mitarbeiter mit Nachname\'" + nachname +  "\' konnte nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Mitarbeiter> findMitarbeiterByVorname(String vorname) throws Exception {
        try {
            return getMitarbeiterDAO().findByVorname(vorname);
        } catch (Exception e) {
            String msg = "Mitarbeiter mit Vorname\'" + vorname +  "\' konnte nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Mitarbeiter> findMitarbeiterByNachnameUndVorname(String nachname, String vorname) throws Exception {
        try {
            return getMitarbeiterDAO().findByNachnameUndVorname(nachname, vorname);
        } catch (Exception e) {
            String msg = "Mitarbeiter mit Vorname\'" + vorname + " und Nachnamen " + nachname+ "\' konnte nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Mitarbeiter findMitarbeiterByUsername(String username) throws Exception {
        try {
            return getMitarbeiterDAO().findByUsername(username);
        } catch (Exception e) {
            String msg = "Mitarbeiter mit Username\'" + username +  "\' konnte nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }
}
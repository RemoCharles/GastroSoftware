package slgp.gastrosoftware;

import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.Mitarbeiter;
import slgp.gastrosoftware.model.Person;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import slgp.gastrosoftware.persister.MitarbeiterDAO;
import slgp.gastrosoftware.persister.PersonDAO;
import slgp.gastrosoftware.persister.impl.MitarbeiterDAOImpl;
import slgp.gastrosoftware.persister.impl.PersonDAOImpl;

public class PersonManager implements PersonService {


    private static Logger logger = LogManager.getLogger(PersonManager.class);

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
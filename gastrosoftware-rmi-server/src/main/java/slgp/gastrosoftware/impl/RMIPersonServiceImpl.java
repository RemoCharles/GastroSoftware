package slgp.gastrosoftware.impl;

import slgp.gastrosoftware.PersonManager;
import slgp.gastrosoftware.PersonService;
import slgp.gastrosoftware.RMIPersonService;
import slgp.gastrosoftware.model.Login;
import slgp.gastrosoftware.model.Mitarbeiter;
import slgp.gastrosoftware.model.Person;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIPersonServiceImpl extends UnicastRemoteObject implements RMIPersonService {

    private PersonService personService;

    public RMIPersonServiceImpl() throws RemoteException {
    }

    public PersonService getPersonService() {
        if (personService == null) {
            personService = new PersonManager();
        }
        return personService;
    }

    @Override
    public boolean pruefeLogin(String username, String passwort) throws Exception {
        return getPersonService().pruefeLogin(username, passwort);
    }

    @Override
    public String getFunktionPerson(String username, String passwort) throws Exception {
        return getPersonService().getFunktionPerson(username, passwort);
    }

    @Override
    public List<Login> findLoginAll() throws Exception {
        return getPersonService().findLoginAll();
    }

    @Override
    public List<Person> findPersonAll() throws Exception {
        return getPersonService().findPersonAll();
    }

    @Override
    public List<Mitarbeiter> findMitarbeiterAll() throws Exception {
        return getPersonService().findMitarbeiterAll();
    }

    @Override
    public Person personHinzufuegen(Person person) throws Exception {
        return getPersonService().personHinzufuegen(person);
    }

    @Override
    public Person personAktualisieren(Person person) throws Exception {
        return getPersonService().personAktualisieren(person);
    }

    @Override
    public Mitarbeiter mitarbeiterHinzufuegen(Mitarbeiter mitarbeiter) throws Exception {
        return getPersonService().mitarbeiterHinzufuegen(mitarbeiter);
    }

    @Override
    public Mitarbeiter mitarbeiterAktualisieren(Mitarbeiter mitarbeiter) throws Exception {
        return getPersonService().mitarbeiterAktualisieren(mitarbeiter);
    }

    @Override
    public void personLoeschen(Person person) throws Exception {
        getPersonService().personLoeschen(person);
    }

    @Override
    public List<Person> findPersonByNachname(String nachname) throws Exception {
        return getPersonService().findPersonByNachname(nachname);
    }

    @Override
    public List<Person> findPersonByVorname(String vorname) throws Exception {
        return getPersonService().findPersonByVorname(vorname);
    }

    @Override
    public List<Person> findPersonByNachnameUndVorname(String nachname, String vorname) throws Exception {
        return getPersonService().findPersonByNachnameUndVorname(nachname, vorname);
    }

    @Override
    public Person findPersonByUsername(String username) throws Exception {
        return getPersonService().findPersonByUsername(username);
    }

    @Override
    public void mitarbeiterLoeschen(Mitarbeiter mitarbeiter) throws Exception {
        getPersonService().mitarbeiterLoeschen(mitarbeiter);
    }

    @Override
    public List<Mitarbeiter> findMitarbeiterByNachname(String nachname) throws Exception {
        return getPersonService().findMitarbeiterByNachname(nachname);
    }

    @Override
    public List<Mitarbeiter> findMitarbeiterByVorname(String vorname) throws Exception {
        return getPersonService().findMitarbeiterByVorname(vorname);
    }

    @Override
    public List<Mitarbeiter> findMitarbeiterByNachnameUndVorname(String nachname, String vorname) throws Exception {
        return getPersonService().findMitarbeiterByNachnameUndVorname(nachname, vorname);
    }

    @Override
    public Mitarbeiter findMitarbeiterByUsername(String username) throws Exception {
        return getPersonService().findMitarbeiterByUsername(username);
    }
}

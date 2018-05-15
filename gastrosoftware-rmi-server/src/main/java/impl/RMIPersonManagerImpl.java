package impl;

import slgp.gastrosoftware.PersonManager;
import slgp.gastrosoftware.PersonService;
import slgp.gastrosoftware.model.Mitarbeiter;
import slgp.gastrosoftware.model.Person;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIPersonManagerImpl extends UnicastRemoteObject implements PersonService{

    private PersonService personService;

    public RMIPersonManagerImpl() throws RemoteException {

    }

    public PersonService getPersonService() {
        if (personService == null) {
            personService = new PersonManager();
        }
        return personService;
    }

    @Override
    public Person personHinzufuegen(Person person) throws Exception {
        return personService.personHinzufuegen(person);
    }

    @Override
    public Person personAktualisieren(Person person) throws Exception {
        return personService.personAktualisieren(person);
    }

    @Override
    public Mitarbeiter mitarbeiterHinzufuegen(Mitarbeiter mitarbeiter) throws Exception {
        return personService.mitarbeiterHinzufuegen(mitarbeiter);
    }

    @Override
    public Mitarbeiter mitarbeiterAktualisieren(Mitarbeiter mitarbeiter) throws Exception {
        return personService.mitarbeiterAktualisieren(mitarbeiter);
    }

    @Override
    public void personLoeschen(Person person) throws Exception {
        personService.personLoeschen(person);
    }

    @Override
    public List<Person> findPersonByNachname(String nachname) throws Exception {
        return personService.findPersonByNachname(nachname);
    }

    @Override
    public List<Person> findPersonByVorname(String vorname) throws Exception {
        return personService.findPersonByVorname(vorname);
    }

    @Override
    public List<Person> findPersonByNachnameUndVorname(String nachname, String vorname) throws Exception {
        return personService.findPersonByNachnameUndVorname(nachname, vorname);
    }

    @Override
    public Person findPersonByUsername(String username) throws Exception {
        return personService.findPersonByUsername(username);
    }

    @Override
    public void mitarbeiterLoeschen(Mitarbeiter mitarbeiter) throws Exception {
        personService.mitarbeiterLoeschen(mitarbeiter);
    }

    @Override
    public List<Mitarbeiter> findMitarbeiterByNachname(String nachname) throws Exception {
        return personService.findMitarbeiterByNachname(nachname);
    }

    @Override
    public List<Mitarbeiter> findMitarbeiterByVorname(String vorname) throws Exception {
        return personService.findMitarbeiterByVorname(vorname);
    }

    @Override
    public List<Mitarbeiter> findMitarbeiterByNachnameUndVorname(String nachname, String vorname) throws Exception {
        return personService.findMitarbeiterByNachnameUndVorname(nachname, vorname);
    }

    @Override
    public Mitarbeiter findMitarbeiterByUsername(String username) throws Exception {
        return personService.findMitarbeiterByUsername(username);
    }
}

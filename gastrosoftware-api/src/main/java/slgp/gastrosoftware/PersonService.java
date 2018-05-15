package slgp.gastrosoftware;

import slgp.gastrosoftware.model.Mitarbeiter;
import slgp.gastrosoftware.model.Person;

import java.util.List;

public interface PersonService {

    /**
     * Speichert neue Person
     *
     * @param person
     * @return
     * @throws Exception
     */
    Person personHinzufuegen(Person person) throws Exception;

    /**
     * Aktualisiert Person
     *
     * @param person
     * @return
     * @throws Exception
     */
    Person personAktualisieren(Person person) throws Exception;
    /**
     * Speichert neuen mitarbeiter
     *
     * @param mitarbeiter
     * @return
     * @throws Exception
     */
    Mitarbeiter mitarbeiterHinzufuegen(Mitarbeiter mitarbeiter) throws Exception;
    /**
     * Aktualisiert mitarbeiter
     *
     * @param mitarbeiter
     * @return
     * @throws Exception
     */
    Mitarbeiter mitarbeiterAktualisieren(Mitarbeiter mitarbeiter) throws Exception;
    /**
     * löscht Person
     *
     * @param person
     * @return
     * @throws Exception
     */
    void personLoeschen(Person person) throws Exception;

    /**
     * Liefert alle Personen für den übergebenen Nachnamen zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param nachname
     * @return
     * @throws Exception
     */
    List<Person> findPersonByNachname(String nachname) throws Exception;

    /**
     * Liefert alle Personen für den übergebenen Vornamen zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param vorname
     * @return
     * @throws Exception
     */

    List<Person> findPersonByVorname(String vorname) throws Exception;


    /**
     * Liefert alle Personen für den übergebenen Nachname und Vorname zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param nachname, vorname
     * @return
     * @throws Exception
     */
    List<Person> findPersonByNachnameUndVorname(String nachname, String vorname) throws Exception;

    /**
     * Liefert alle Personen für den übergebenen Username zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param username
     * @return
     * @throws Exception
     */
    Person findPersonByUsername(String username) throws Exception;


    void mitarbeiterLoeschen(Mitarbeiter mitarbeiter) throws Exception;

    /**
     * Liefert alle Mitarbeiter für den übergebenen Nachnamen zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param nachname
     * @return
     * @throws Exception
     */
    List<Mitarbeiter> findMitarbeiterByNachname(String nachname) throws Exception;

    /**
     * Liefert alle Mitarbeiter für den übergebenen Vornamen zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param vorname
     * @return
     * @throws Exception
     */

    List<Mitarbeiter> findMitarbeiterByVorname(String vorname) throws Exception;


    /**
     * Liefert alle Mitarbeiter für den übergebenen Nachname und Vorname zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param nachname, vorname
     * @return
     * @throws Exception
     */
    List<Mitarbeiter> findMitarbeiterByNachnameUndVorname(String nachname, String vorname) throws Exception;

    /**
     * Liefert alle Mitarbeiter für den übergebenen Username zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param username
     * @return
     * @throws Exception
     */

    Mitarbeiter findMitarbeiterByUsername(String username) throws Exception;
}

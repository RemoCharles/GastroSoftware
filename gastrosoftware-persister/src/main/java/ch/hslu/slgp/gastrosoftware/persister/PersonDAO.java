package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.Person;

import java.util.List;

public interface PersonDAO extends GenericPersisterDAO<Person> {
    /**
     * Liefert alle Personen für den übergebenen Nachnamen zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param nachname
     * @return
     * @throws Exception
     */
    List<Person> findByNachname(String nachname) throws Exception;

    /**
     * Liefert alle Personen für den übergebenen Vornamen zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param vorname
     * @return
     * @throws Exception
     */

    List<Person> findByVorname(String vorname) throws Exception;


    /**
     * Liefert alle Personen für den übergebenen Nachname und Vorname zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param nachname, vorname
     * @return
     * @throws Exception
     */
    List<Person> findByNachnameUndVorname(String nachname, String vorname) throws Exception;

    /**
     * Liefert alle Personen für den übergebenen Username zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     *
     * @param username
     * @return
     * @throws Exception
     */

    Person findByUsername(String username) throws Exception;


}

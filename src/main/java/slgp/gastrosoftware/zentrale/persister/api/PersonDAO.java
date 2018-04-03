package slgp.gastrosoftware.zentrale.persister.api;

import java.util.List;

import slgp.gastrosoftware.zentrale.persister.domain.Person;

public interface PersonDAO extends GenericPersisterDAO<Person>{
	
	List<Person> findByNachname(String nachname) throws Exception;
	/**
     * Liefert alle Personen für den übergebenen Vornamen zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     * 
     * @param vorname
     * @return
     * @throws Exception
     */
	
	List<Person> findByVorname(String vorname)throws Exception;
	
	/**
     * Liefert alle Personen für den übergebenen Vornamen zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     * 
     * @param vorname
     * @return
     * @throws Exception
     */
	
	List<Person> findByNachnameUndVorname(String nachname, String vorname) throws Exception;
	
	/**
     * Liefert alle Personen für den übergebenen Nachname und Vorname zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     * 
     * @param vorname
     * @return
     * @throws Exception
     */
	
	Person findByUsername(String username)throws Exception;
	
	/**
     * Liefert alle Personen für den übergebenen Username zurück, falls welche
     * vorhanden, sonst eine leere Liste.
     * 
     * @param vorname
     * @return
     * @throws Exception
     */
	
	
	

}

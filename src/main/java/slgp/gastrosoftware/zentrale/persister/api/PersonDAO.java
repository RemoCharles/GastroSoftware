package slgp.gastrosoftware.zentrale.persister.api;

import java.util.List;

import slgp.gastrosoftware.zentrale.persister.domain.Person;

public interface PersonDAO extends GenericPersisterDAO<Person>{
	
	List<Person> findByNachname(String nachname) throws Exception;
	/**
     * Liefert alle Personen f�r den �bergebenen Vornamen zur�ck, falls welche
     * vorhanden, sonst eine leere Liste.
     * 
     * @param vorname
     * @return
     * @throws Exception
     */
	
	List<Person> findByVorname(String vorname)throws Exception;
	
	/**
     * Liefert alle Personen f�r den �bergebenen Vornamen zur�ck, falls welche
     * vorhanden, sonst eine leere Liste.
     * 
     * @param vorname
     * @return
     * @throws Exception
     */
	
	List<Person> findByNachnameUndVorname(String nachname, String vorname) throws Exception;
	
	/**
     * Liefert alle Personen f�r den �bergebenen Nachname und Vorname zur�ck, falls welche
     * vorhanden, sonst eine leere Liste.
     * 
     * @param vorname
     * @return
     * @throws Exception
     */
	
	Person findByUsername(String username)throws Exception;
	
	/**
     * Liefert alle Personen f�r den �bergebenen Username zur�ck, falls welche
     * vorhanden, sonst eine leere Liste.
     * 
     * @param vorname
     * @return
     * @throws Exception
     */
	
	
	

}

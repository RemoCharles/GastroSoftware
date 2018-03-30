package slgp.gastrosoftware.zentrale.persister.api;

import java.util.List;

import slgp.gastrosoftware.zentrale.persister.domain.Person;

public interface PersonDAO extends GenericPersisterDAO<Person>{
	
	List<Person> findByNachname(String nachname) throws Exception;
	/**
     * Lieferat alle Personen f�r den �bergebenen Vornamen zur�ck, falls welche
     * vorhanden, sonst eine leere Liste.
     * 
     * @param vorname
     * @return
     * @throws Exception
     */

}

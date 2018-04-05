package slgp.gastrosoftware.zentrale.persister.api;

import java.util.List;

import slgp.gastrosoftware.zentrale.persister.domain.Login;

public interface LoginDAO extends GenericPersisterDAO<Login> {
	/**
     * Liefert das Passwort zum entsprechenden Username zurueck.
     * 
     * @param username
      @return
      @throws Exception
     */
	boolean pruefeLogin (String username, String passwort) throws Exception;
	/**
     * Liefert die Funktion des Mitarbeites zurück
     * 
     * @param username
      @return
      @throws Exception
     */
	
	String getFunktionPerson(String username, String passwort) throws Exception;
	
	
}
package slgp.gastrosoftware.persister;

import slgp.gastrosoftware.model.Login;

import java.util.List;

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
     * Liefert die Funktion des Mitarbeites zur�ck
     * 
     * @param username
      @return
      @throws Exception
     */
	String getFunktionPerson(String username, String passwort) throws Exception;
	 /**
     * Liefert alle Kontakte zurück
     *
     * @return
     * @throws Exception
     */
	List<Login> findAll() throws Exception;
	
	
	
}
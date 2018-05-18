package ch.hslu.slgp.gastrosoftware.persister;


import ch.hslu.slgp.gastrosoftware.model.Tagesmenu;

import java.util.List;

public interface TagesmenuDAO extends GenericPersisterDAO<Tagesmenu>{
	 /**
     * Liefert das Tagesmenu zu einem gew�nschten Wochentag zur�ck.
     * 
     * @param wochenTag
     * @return
     * @throws Exception
     */
	List<Tagesmenu> findyByWochenTag(String wochenTag) throws Exception;
	/**
	 * Liefert alle Tagesmenu zurueck.
	 *
	 * @param
	 * @return
	 * @throws Exception
	 */

	List<Tagesmenu> findAll() throws Exception;


}
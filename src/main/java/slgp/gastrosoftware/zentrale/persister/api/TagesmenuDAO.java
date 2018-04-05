package slgp.gastrosoftware.zentrale.persister.api;

import java.util.List;

import slgp.gastrosoftware.zentrale.persister.domain.Tagesmenu;

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

	List<Tagesmenu> showAll() throws Exception;


}
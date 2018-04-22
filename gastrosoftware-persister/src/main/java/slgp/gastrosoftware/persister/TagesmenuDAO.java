package slgp.gastrosoftware.persister;

import java.util.List;

import slgp.gastrosoftware.model.Tagesmenu;

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
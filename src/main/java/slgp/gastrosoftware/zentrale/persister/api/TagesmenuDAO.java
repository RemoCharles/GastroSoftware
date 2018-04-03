package slgp.gastrosoftware.zentrale.persister.api;

import java.util.List;

import slgp.gastrosoftware.zentrale.persister.domain.Tagesmenu;

public interface TagesmenuDAO extends GenericPersisterDAO<Tagesmenu>{
	 /**
     * Liefert das Tagesmenu zu einem gewünschten Wochentag oder das von Heute zurück.
     * 
     * @param wochenTag
     * @return
     * @throws Exception
     */
	
	List<Tagesmenu> findyByWochenTag(String wochenTag) throws Exception;
	List<Tagesmenu> show() throws Exception;

}

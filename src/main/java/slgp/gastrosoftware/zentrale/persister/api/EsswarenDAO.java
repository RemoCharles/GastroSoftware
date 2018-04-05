package slgp.gastrosoftware.zentrale.persister.api;

import java.util.List;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;


public interface EsswarenDAO extends GenericPersisterDAO<Esswaren> {

    /**
     * Liefert alle Konsumartikel zurück
     *
     * @return
     * @throws Exception
     */
	List<Konsumartikel> showAll() throws Exception;
}

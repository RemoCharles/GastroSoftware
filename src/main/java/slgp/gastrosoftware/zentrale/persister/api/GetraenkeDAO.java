package slgp.gastrosoftware.zentrale.persister.api;

import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;

import java.util.List;

public interface GetraenkeDAO extends GenericPersisterDAO<Getraenke> {
    /**
     * Liefert alle Konsumartikel zurück
     *
     * @return
     * @throws Exception
     */
    List<Konsumartikel> showAll() throws Exception;

}

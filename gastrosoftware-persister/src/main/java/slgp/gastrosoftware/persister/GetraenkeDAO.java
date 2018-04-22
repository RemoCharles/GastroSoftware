package slgp.gastrosoftware.persister;

import slgp.gastrosoftware.model.Getraenke;
import java.util.List;

public interface GetraenkeDAO extends GenericPersisterDAO<Getraenke> {
    /**
     * Liefert alle Konsumartikel zurück
     *
     * @return
     * @throws Exception
     */
    List<Getraenke> findAll() throws Exception;

}

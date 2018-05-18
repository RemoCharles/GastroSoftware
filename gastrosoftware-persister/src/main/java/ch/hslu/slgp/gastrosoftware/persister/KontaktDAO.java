package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.Kontakt;

import java.util.List;

public interface KontaktDAO extends GenericPersisterDAO<Kontakt>  {
    /**
     * Liefert alle Kontakte zurück
     *
     * @return
     * @throws Exception
     */
    List<Kontakt> findAll() throws Exception;
}

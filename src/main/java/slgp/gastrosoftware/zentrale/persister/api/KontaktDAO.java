package slgp.gastrosoftware.zentrale.persister.api;

import slgp.gastrosoftware.zentrale.persister.domain.Kontakt;

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

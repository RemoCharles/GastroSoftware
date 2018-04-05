package slgp.gastrosoftware.zentrale.persister.api;

import slgp.gastrosoftware.zentrale.persister.domain.Tisch;

import java.util.List;

public interface TischDAO extends GenericPersisterDAO<Tisch> {
    /**
     * Liefert alle Tische zurueck.
     *
     * @param
     * @return
     * @throws Exception
     */

    List<Tisch> findAll() throws Exception;
}

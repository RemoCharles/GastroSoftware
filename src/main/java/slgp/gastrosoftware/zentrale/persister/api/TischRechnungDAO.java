package slgp.gastrosoftware.zentrale.persister.api;

import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.TischRechnung;
import sun.net.www.content.text.Generic;

import java.util.List;

public interface TischRechnungDAO extends GenericPersisterDAO<TischRechnung> {
    /**
     * Liefert alle Tischrechnungen zurück
     *
     * @return
     * @throws Exception
     */
    List<TischRechnung> findAll() throws Exception;
}

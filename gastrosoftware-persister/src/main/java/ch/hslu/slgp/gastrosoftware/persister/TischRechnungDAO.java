package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.TischRechnung;

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

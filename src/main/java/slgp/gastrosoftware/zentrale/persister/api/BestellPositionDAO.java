package slgp.gastrosoftware.zentrale.persister.api;


import slgp.gastrosoftware.zentrale.persister.domain.BestellPosition;
import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface für Persistierung von BestellPositionen-Entities.
 */
public interface BestellPositionDAO extends GenericPersisterDAO<BestellPosition> {

    /**
     * Liefert alle Bestellpositionen zurück
     * @param
     * @return
     * @throws Exception
     */
    List<BestellPosition> findAll() throws Exception;

}



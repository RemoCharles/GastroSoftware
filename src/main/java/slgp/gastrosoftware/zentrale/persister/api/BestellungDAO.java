package slgp.gastrosoftware.zentrale.persister.api;


import java.time.LocalDate;
import java.util.List;

import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;
/**
 * Interface f�r Persistierung von Bestellung-Entities.
 * 
 *
 * @author sucur
 * 
 */
public interface BestellungDAO extends GenericPersisterDAO<Bestellung> {

    /**
     * Liefert alle Bestellungen zur�ck, welche die Filiale an dem �bergebeneen
     * Datum get�tigt hat, falls welche gefunden worden sind, sonst eine leere liste.
     * 
     * @param datum
     * @return
     * @throws Exception
     */
    List<Bestellung> findByDatum(LocalDate datum) throws Exception;
}


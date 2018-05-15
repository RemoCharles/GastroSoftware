package slgp.gastrosoftware.persister;


import java.time.LocalDate;
import java.util.List;

import slgp.gastrosoftware.model.Bestellung;
/**
 * Interface für Persistierung von Bestellung-Entities.
 * 
 *
 * @author sucur
 * 
 */
public interface BestellungDAO extends GenericPersisterDAO<Bestellung> {

    /**
     * Liefert alle Bestellungen zur�ck, welche die Filiale an dem �bergebeneen
     * Datum getätigt hat, falls welche gefunden worden sind, sonst eine leere liste.
     * 
     * @param datum
     * @return
     * @throws Exception
     */
    List<Bestellung> findByDatum(LocalDate datum) throws Exception;

    /**
     * Liefert alle bezahlte Bestellungen zurück
     */

    List<Bestellung> findAllBezahlt(boolean bezahlt) throws Exception;

    List<Bestellung> findByTischNummer(Integer tischNummer) throws Exception;

}



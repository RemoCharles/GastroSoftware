package slgp.gastrosoftware;

import slgp.gastrosoftware.model.Tisch;
import slgp.gastrosoftware.model.BestellPosition;
import slgp.gastrosoftware.model.Bestellung;

import java.time.LocalDate;
import java.util.List;

public interface BestellService {
    /**
     * Liefert alle Bestellungen zurück, falls welche gefunden worden sind, sonst eine leere liste.
     *
     * @param
     * @return
     * @throws Exception
     */
    List<Bestellung> findBestellungALl() throws Exception;

    /**
     * Liefert alle Bestellungen zur�ck, welche die Filiale an dem �bergebeneen
     * Datum getätigt hat, falls welche gefunden worden sind, sonst eine leere liste.
     *
     * @param datum
     * @return
     * @throws Exception
     */
    List<Bestellung> findBestellungByDatum(LocalDate datum) throws Exception;

    /**
     * Liefert alle bezahlte Bestellungen zurück
     */
    List<Bestellung> findBestellungAllBezahlt(boolean bezahlt) throws Exception;

    /**
     * Liefert alle Bestellungen eines Tisches zurück
     */
    List<Bestellung> findBestellungByTischNummer(Integer tischNummer) throws Exception;

    /**
     * Liefert alle Tische zurueck.
     *
     * @param
     * @return
     * @throws Exception
     */
    List<Tisch> findTischAll() throws Exception;

    /**
     * Liefert Tisch nach tischNummer.
     *
     * @param tischNummer
     * @return
     * @throws Exception
     */
    Tisch findTischByTischNummer(int tischNummer) throws Exception;

    /**
     * Liefert alle Bestellpositionen zurück
     * @param
     * @return
     * @throws Exception
     */
    List<BestellPosition> findBestellPositionAll() throws Exception;
}

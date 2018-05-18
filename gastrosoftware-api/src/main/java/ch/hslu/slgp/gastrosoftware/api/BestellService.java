package ch.hslu.slgp.gastrosoftware.api;

import ch.hslu.slgp.gastrosoftware.model.Bestellung;
import ch.hslu.slgp.gastrosoftware.model.Tisch;
import ch.hslu.slgp.gastrosoftware.model.BestellPosition;


import java.time.LocalDate;
import java.util.List;

public interface BestellService {

    /**
     * Speichert neue Bestellung
     *
     * @param bestellung
     * @return
     * @throws Exception
     */
    Bestellung bestellungHinzufuegen(Bestellung bestellung) throws Exception;

    /**
     * Aktualisiert Bestellung
     *
     * @param bestellung
     * @return
     * @throws Exception
     */
    Bestellung bestellungAktualisieren(Bestellung bestellung) throws Exception;

    /**
     * löscht Bestellung
     *
     * @param bestellung
     * @return
     * @throws Exception
     */
    void bestellungLoeschen(Bestellung bestellung) throws Exception;

    /**
     * Liefert alle Bestellungen zurück, falls welche gefunden worden sind, sonst eine leere liste.
     *
     * @param
     * @return
     * @throws Exception
     */
    List<Bestellung> findBestellungAll() throws Exception;

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

    /**
     * Speichert neuen Tisch
     *
     * @param tisch
     * @return
     * @throws Exception
     */
    Tisch tischHinzufuegen(Tisch tisch) throws Exception;

    /**
     * Aktualisiert Tisch
     *
     * @param tisch
     * @return
     * @throws Exception
     */
    Tisch tischAktualisieren(Tisch tisch) throws Exception;

    /**
     * löscht Tisch
     *
     * @param tisch
     * @return
     * @throws Exception
     */
    void tischLoeschen(Tisch tisch) throws Exception;

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
     * Speichert neuen BestellPosition
     *
     * @param bestellPosition
     * @return
     * @throws Exception
     */
    BestellPosition bestellPositionHinzufuegen(BestellPosition bestellPosition) throws Exception;

    /**
     * Aktualisiert Bestellposition
     *
     * @param bestellPosition
     * @return
     * @throws Exception
     */
    BestellPosition bestellPositionAktualisieren(BestellPosition bestellPosition) throws Exception;

    /**
     * löscht Bestellposition
     *
     * @param bestellPosition
     * @return
     * @throws Exception
     */
    void bestellPositionLoeschen(BestellPosition bestellPosition) throws Exception;

    /**
     * Liefert alle Bestellpositionen zurück
     *
     * @param
     * @return
     * @throws Exception
     */
    List<BestellPosition> findBestellPositionAll() throws Exception;
}

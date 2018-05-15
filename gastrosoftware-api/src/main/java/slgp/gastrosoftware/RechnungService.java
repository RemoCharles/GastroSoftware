package slgp.gastrosoftware;

import slgp.gastrosoftware.model.Tisch;
import slgp.gastrosoftware.model.TischRechnung;
import slgp.gastrosoftware.model.MAAbrechnung;

import java.util.List;

public interface RechnungService {
    /**
     * Speichert neue Tischrechnung
     *
     * @param tischRechnung
     * @return
     * @throws Exception
     */
    TischRechnung tischRechnungHinzufuegen(TischRechnung tischRechnung) throws Exception;
    /**
     * Aktualisiert Tischrechnung
     *
     * @param tischRechnung
     * @return
     * @throws Exception
     */
    TischRechnung tischRechnungAktualisieren(TischRechnung tischRechnung) throws Exception;
    /**
     * löscht Tischrechnung
     *
     * @param tischRechnung
     * @return
     * @throws Exception
     */
    void tischRechnungLoeschen(TischRechnung tischRechnung) throws Exception;

    /**
     * Liefert alle Tischrechnungen zurück
     *
     * @return
     * @throws Exception
     */
    List<TischRechnung> findTischRechnungAll() throws Exception;

    /**
     * Speichert neue Mitarbeiter Abrechnung
     *
     * @param maAbrechnung
     * @return
     * @throws Exception
     */
    MAAbrechnung maAbrechnungHinzufuegen(MAAbrechnung maAbrechnung) throws Exception;
    /**
     * Aktualisiert Mitarbeiterabrechnung
     *
     * @param maAbrechnung
     * @return
     * @throws Exception
     */
    MAAbrechnung maAbrechnungAktualisieren(MAAbrechnung maAbrechnung) throws Exception;
    /**
     * löscht Mitarbeiter Abrechnung
     *
     * @param maAbrechnung
     * @return
     * @throws Exception
     */
    void maAbrechnungLoeschen(MAAbrechnung maAbrechnung) throws Exception;

    /**
     * Liefert Mitarbeiter Abrechnungen zurück.
     *
     * @return
     * @throws Exception
     */
    List<MAAbrechnung> findMAAbrechnungAll() throws Exception;
}

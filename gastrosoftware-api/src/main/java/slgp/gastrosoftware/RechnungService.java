package slgp.gastrosoftware;

import slgp.gastrosoftware.model.TischRechnung;
import slgp.gastrosoftware.model.MAAbrechnung;

import java.util.List;

public interface RechnungService {
    /**
     * Liefert alle Tischrechnungen zurück
     *
     * @return
     * @throws Exception
     */
    List<TischRechnung> findTischRechnungAll() throws Exception;

    /**
     * Liefert Mitarbeiter Abrechnungen zurück.
     *
     * @return
     * @throws Exception
     */
    List<MAAbrechnung> findMAAbrechnungAll() throws Exception;
}

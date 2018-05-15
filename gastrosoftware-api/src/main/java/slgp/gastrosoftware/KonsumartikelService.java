package slgp.gastrosoftware;

import slgp.gastrosoftware.model.Getraenke;
import slgp.gastrosoftware.model.Konsumartikel;
import slgp.gastrosoftware.model.Esswaren;

import java.util.List;

public interface KonsumartikelService {
    /**
     * Liefert den Konsumartikel zurück, dessen Bezeichnung übergeben wurde.
     *
     * @param bezeichnung
     * @return
     * @throws Exception
     */
    Konsumartikel findKonsumartikelByBezeichnung(String bezeichnung) throws Exception;

    /**
     * Liefert alle Konsumartikel zurück der angegebenen Kategorie
     *
     * @param kategorie
     * @return
     * @throws Exception
     */
    List<Konsumartikel> findKonsumartikelByKategorie(String kategorie) throws Exception;

    /**
     * Liefert alle Konsumartikel zurück
     *
     * @return
     * @throws Exception
     */
    List<Konsumartikel> findKonsumartikelAll() throws Exception;

    /**
     * Liefert alle Konsumartikel zurück
     *
     * @return
     * @throws Exception
     */
    List<Getraenke> findGetraenkeAll() throws Exception;

    /**
     * Liefert alle Esswaren zurück
     *
     * @return
     * @throws Exception
     */
    List<Esswaren> findEsswarenAll() throws Exception;
}

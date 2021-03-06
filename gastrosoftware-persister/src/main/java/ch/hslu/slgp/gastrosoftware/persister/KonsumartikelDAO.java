package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.Konsumartikel;

import java.util.List;

public interface KonsumartikelDAO extends GenericPersisterDAO<Konsumartikel> {

    /**
     * Liefert den Konsumartikel zurück, dessen Bezeichnung übergeben wurde.
     *
     * @param bezeichnung
     * @return
     * @throws Exception
     */
    Konsumartikel findByBezeichnung(String bezeichnung) throws Exception;

    /**
     * Liefert alle Konsumartikel zurück der angegebenen Kategorie
     *
     * @param kategorie
     * @return
     * @throws Exception
     */
    List<Konsumartikel> findByKategorie(String kategorie) throws Exception;

    /**
     * Liefert alle Konsumartikel zurück
     *
     * @return
     * @throws Exception
     */
    List<Konsumartikel> findAll() throws Exception;
}

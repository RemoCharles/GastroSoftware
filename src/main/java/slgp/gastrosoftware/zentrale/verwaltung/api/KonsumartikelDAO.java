package slgp.gastrosoftware.zentrale.verwaltung.api;

import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;

import java.util.List;

public interface KonsumartikelDAO<T> extends GenericPersisterDAO<Konsumartikel> {

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
     * Liefert alle Konsumartikel zurück der angegebenen Kategorie
     *
     * @return
     * @throws Exception
     */
    List<Konsumartikel> showAll() throws Exception;
}

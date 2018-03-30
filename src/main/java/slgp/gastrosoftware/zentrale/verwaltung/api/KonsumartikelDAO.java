package slgp.gastrosoftware.zentrale.verwaltung.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.zentrale.persister.domain.artikel.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;
import slgp.gastrosoftware.zentrale.verwaltung.impl.GenericPersisterDAOImpl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
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

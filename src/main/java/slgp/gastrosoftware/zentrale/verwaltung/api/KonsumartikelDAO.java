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

public interface KonsumArtikelDAO<T> extends GenericPersisterDAO<Konsumartikel> {

    /**
     * Liefert den Benutzer zurück, dessen Benutzername übergeben wurde.
     *
     * @param benutzername
     * @return
     * @throws Exception
     */
    Konsumartikel findBy(String benutzername) throws Exception;

    /**
     * Liefert alle Benutzer zurück, welche die Rolle vom übergebenen Typ haben,
     * falls welche vorhanden, sonst eine leere Liste.
     *
     * @param rolleTyp
     * @return
     * @throws Exception
     */
    List<Konsumartikel> findByRolleTyp(RolleTyp rolleTyp) throws Exception;

    /**
     * Liefert den Benutzer zurück, dessen Nachname und Vorname übergeben
     * wurden.
     *
     * @param nachname
     * @param vorname
     * @return
     * @throws Exception
     */
    List<Konsumartikel> findByNachnameUndVorname(String nachname, String vorname) throws Exception;
}

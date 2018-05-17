package slgp.gastrosoftware.persister;
import slgp.gastrosoftware.model.Tisch;

import java.util.List;

public interface TischDAO extends GenericPersisterDAO<Tisch> {
    /**
     * Liefert alle Tische zurueck.
     *
     * @param
     * @return
     * @throws Exception
     */
    List<Tisch> findAll() throws Exception;

    /**
     * Liefert Tisch nach tischNummer.
     *
     * @param tischNummer
     * @return
     * @throws Exception
     */
    Tisch findByTischNummer(int tischNummer) throws Exception;
}

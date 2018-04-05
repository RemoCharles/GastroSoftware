package slgp.gastrosoftware.zentrale.persister.api;

import slgp.gastrosoftware.zentrale.persister.domain.MAAbrechnung;

import java.util.List;

public interface MAAbrechnungDAO extends GenericPersisterDAO<MAAbrechnung> {
    /**
     * Liefert Mitarbeiter Abrechnungen zurück.
     *
     * @return
     * @throws Exception
     */
    List<MAAbrechnung> findAll() throws Exception;
}

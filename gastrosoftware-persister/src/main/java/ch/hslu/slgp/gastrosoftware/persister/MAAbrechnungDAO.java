package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.MAAbrechnung;

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

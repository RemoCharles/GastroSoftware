package slgp.gastrosoftware.persister;

import slgp.gastrosoftware.model.Esswaren;

import java.util.List;

public interface EsswarenDAO extends GenericPersisterDAO<Esswaren>{
    /**
     * Liefert alle Esswaren zurück
     *
     * @return
     * @throws Exception
     */
    List<Esswaren> findAll() throws Exception;
}

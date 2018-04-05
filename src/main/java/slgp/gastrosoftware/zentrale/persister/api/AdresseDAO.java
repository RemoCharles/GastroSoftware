package slgp.gastrosoftware.zentrale.persister.api;

import slgp.gastrosoftware.zentrale.persister.domain.Adresse;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;

import java.util.List;

public interface AdresseDAO  extends GenericPersisterDAO<Adresse>{
    /**
     * Liefert alle Adressen zurueck
     *
     * @return
     * @throws Exception
     */
    List<Adresse> findAll() throws Exception;
}
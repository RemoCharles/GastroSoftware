package slgp.gastrosoftware.persister;

import slgp.gastrosoftware.model.Adresse;

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
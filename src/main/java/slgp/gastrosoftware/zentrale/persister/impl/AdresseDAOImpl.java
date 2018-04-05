package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.api.AdresseDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Adresse;

public class AdresseDAOImpl extends GenericPersisterDAOImpl<Adresse> implements AdresseDAO {
    public AdresseDAOImpl(Class<Adresse> type) {
        super(type);
    }
}

package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.api.KontaktDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Kontakt;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class KontaktDAOImpl extends GenericPersisterDAOImpl<Kontakt> implements KontaktDAO {
    public KontaktDAOImpl(Class<Kontakt> type) {
        super(type);
    }

    @Override
    public List<Kontakt> findAll() throws Exception{
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Kontakt> query = em.createNamedQuery("Kontakt.findAll", Kontakt.class);

        List<Kontakt> liste = query.getResultList();

        em.close();

        return liste;
    }

}

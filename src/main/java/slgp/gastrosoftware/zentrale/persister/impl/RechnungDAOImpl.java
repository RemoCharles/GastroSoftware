package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.api.RechnungDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Rechnung;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RechnungDAOImpl  extends GenericPersisterDAOImpl<Rechnung> implements RechnungDAO {

    public RechnungDAOImpl() {
        super(Rechnung.class);
    }

    public List<Rechnung> findByDatum(LocalDate datum) throws Exception {

        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Rechnung> query = em.createNamedQuery("Rechnung.findByDatum", Rechnung.class);

        query.setParameter("datum", datum);

        List<Rechnung> liste = query.getResultList();

        em.close();

        return liste != null ? liste : new ArrayList<Rechnung>();

    }
}

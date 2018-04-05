package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.api.BestellungDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;
import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class BestellungDAOImpl implements BestellungDAO {
    public List<Bestellung> findByDatum(LocalDate datum) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Bestellung> query = em.createNamedQuery("Bestellung.findByDatum", Bestellung.class);

        query.setParameter("datum", datum);

        List<Bestellung> liste = query.getResultList();

        em.close();

        return liste;
    }

    @Override
    public Bestellung save(Bestellung entity) throws Exception {
        return null;
    }

    @Override
    public Bestellung update(Bestellung entity) throws Exception {
        return null;
    }

    @Override
    public void delete(Bestellung entity) throws Exception {

    }

    @Override
    public void deleteById(long id) throws Exception {

    }

    @Override
    public Bestellung findById(long id) throws Exception {
        return null;
    }

    @Override
    public List<Bestellung> findAll() throws Exception {
        return null;
    }
}

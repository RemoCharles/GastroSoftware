package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GetraenkeDAOImpl {

    public List<Getraenke> showAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Getraenke> query = em.createNamedQuery("Getraenke.findAll", Getraenke.class);

        List<Getraenke> liste = query.getResultList();

        em.close();

        return liste;

    }
}

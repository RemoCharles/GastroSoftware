package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EsswarenDAOImpl {
    public List<Esswaren> findAll() throws Exception{
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Esswaren> query = em.createNamedQuery("Esswaren.findAll", Esswaren.class);

        List<Esswaren> liste = query.getResultList();

        em.close();

        return liste;
    }
}

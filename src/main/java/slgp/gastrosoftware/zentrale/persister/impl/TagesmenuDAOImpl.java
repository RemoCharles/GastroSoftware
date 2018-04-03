package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.domain.Tagesmenu;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TagesmenuDAOImpl {
    public List<Tagesmenu> findyByWochenTag(String wochenTag) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Tagesmenu> query = em.createNamedQuery("Tagesmenu.findyByWochenTag", Tagesmenu.class);

        query.setParameter("wochenTag", wochenTag);

        List<Tagesmenu> liste = query.getResultList();

        em.close();

        return liste;
    }
    public List<Tagesmenu> showAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Tagesmenu> query = em.createNamedQuery("Tagesmenu.findAll", Tagesmenu.class);

        List<Tagesmenu> liste = query.getResultList();

        em.close();

        return liste;

    }
}

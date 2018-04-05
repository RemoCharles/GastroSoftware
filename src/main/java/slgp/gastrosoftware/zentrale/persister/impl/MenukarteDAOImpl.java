package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.api.MenukarteDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.domain.Menukarte;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MenukarteDAOImpl extends GenericPersisterDAOImpl<Menukarte> implements MenukarteDAO {
    public MenukarteDAOImpl(Class<Menukarte> type) {
        super(type);
    }

    public List<Menukarte> showAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Menukarte> query = em.createNamedQuery("Menukarte.findAll", Menukarte.class);

        List<Menukarte> liste = query.getResultList();

        em.close();

        return liste;

    }
}

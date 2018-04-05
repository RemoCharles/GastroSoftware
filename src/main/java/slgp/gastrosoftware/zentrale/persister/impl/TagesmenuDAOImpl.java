package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.api.TagesmenuDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.domain.Tagesmenu;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TagesmenuDAOImpl extends GenericPersisterDAOImpl<Tagesmenu> implements TagesmenuDAO {
//    public TagesmenuDAOImpl(Class<Tagesmenu> type) {
//        super(type);
//    }
	
	public TagesmenuDAOImpl() {
		super(Tagesmenu.class);
	}

    public List<Tagesmenu> findyByWochenTag(String wochenTag) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Tagesmenu> query = em.createNamedQuery("Tagesmenu.findyByWochenTag", Tagesmenu.class);

        query.setParameter("wochenTag", wochenTag);

        List<Tagesmenu> liste = query.getResultList();

        em.close();

        return liste;
    }


    @Override
    public Tagesmenu save(Tagesmenu entity) throws Exception {
        return null;
    }

    @Override
    public Tagesmenu update(Tagesmenu entity) throws Exception {
        return null;
    }

    @Override
    public void delete(Tagesmenu entity) throws Exception {

    }

    @Override
    public void deleteById(long id) throws Exception {

    }

    @Override
    public Tagesmenu findById(long id) throws Exception {
        return null;
    }

    @Override
    public List<Tagesmenu> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Tagesmenu> query = em.createNamedQuery("Tagesmenu.findAll", Tagesmenu.class);

        List<Tagesmenu> liste = query.getResultList();

        em.close();

        return liste;
    }
}

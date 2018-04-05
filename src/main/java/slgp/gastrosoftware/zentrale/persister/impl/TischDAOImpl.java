package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.api.TischDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Tagesmenu;
import slgp.gastrosoftware.zentrale.persister.domain.Tisch;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TischDAOImpl extends GenericPersisterDAOImpl<Tisch> implements TischDAO {
    public TischDAOImpl(Class<Tisch> type) {
        super(type);
    }

    public TischDAOImpl(){
        super(Tisch.class);
    }

    @Override
    public Tisch save(Tisch entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public Tisch update(Tisch entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(Tisch entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public void deleteById(long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public Tisch findById(long id) throws Exception {
        return super.findById(id);
    }

    @Override
    public List<Tisch> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Tisch> query = em.createNamedQuery("Tisch.findAll", Tisch.class);

        List<Tisch> liste = query.getResultList();

        em.close();

        return liste;
    }
}

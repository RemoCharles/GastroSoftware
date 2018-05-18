package ch.hslu.slgp.gastrosoftware.persister.impl;

import ch.hslu.slgp.gastrosoftware.model.TischRechnung;
import ch.hslu.slgp.gastrosoftware.persister.TischRechnungDAO;
import ch.hslu.slgp.gastrosoftware.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class TischRechnungDAOImpl extends GenericPersisterDAOImpl<TischRechnung> implements TischRechnungDAO {

    public TischRechnungDAOImpl(Class<TischRechnung> type) {
        super(type);
    }

    public TischRechnungDAOImpl(){
        super(TischRechnung.class);
    }

    @Override
    public TischRechnung save(TischRechnung entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public TischRechnung update(TischRechnung entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(TischRechnung entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public void deleteById(long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public TischRechnung findById(long id) throws Exception {
        return super.findById(id);
    }

    @Override
    public List<TischRechnung> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();
        TypedQuery<TischRechnung> query = em.createNamedQuery("TischRechnung.findAll", TischRechnung.class);
        List<TischRechnung> liste = query.getResultList();
        em.close();
        return liste;
    }
}

package slgp.gastrosoftware.persister.impl;

import slgp.gastrosoftware.model.Esswaren;
import slgp.gastrosoftware.persister.EsswarenDAO;
import slgp.gastrosoftware.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EsswarenDAOImpl extends GenericPersisterDAOImpl<Esswaren> implements EsswarenDAO {

    public EsswarenDAOImpl(Class<Esswaren> type) {
        super(type);
    }

    public EsswarenDAOImpl() {
        super(Esswaren.class);
    }

    @Override
    public Esswaren save(Esswaren entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public Esswaren update(Esswaren entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(Esswaren entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public void deleteById(long id) throws Exception {

    }

    @Override
    public Esswaren findById(long id) throws Exception {
        return super.findById(id);
    }

    @Override
    public List<Esswaren> findAll() throws Exception{
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Esswaren> query = em.createNamedQuery("Esswaren.findAll", Esswaren.class);

        List<Esswaren> liste = query.getResultList();

        em.close();

        return liste;
    }
}

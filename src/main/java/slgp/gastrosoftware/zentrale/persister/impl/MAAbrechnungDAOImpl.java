package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.api.MAAbrechnungDAO;
import slgp.gastrosoftware.zentrale.persister.domain.MAAbrechnung;
import slgp.gastrosoftware.zentrale.persister.domain.Menukarte;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MAAbrechnungDAOImpl extends GenericPersisterDAOImpl<MAAbrechnung> implements MAAbrechnungDAO {

    public MAAbrechnungDAOImpl(Class<MAAbrechnung> type) {
        super(type);
    }

    @Override
    public MAAbrechnung save(MAAbrechnung entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public MAAbrechnung update(MAAbrechnung entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(MAAbrechnung entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public void deleteById(long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public MAAbrechnung findById(long id) throws Exception {
        return super.findById(id);
    }

    @Override
    public List<MAAbrechnung> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<MAAbrechnung> query = em.createNamedQuery("MAAbrechnung.findAll", MAAbrechnung.class);

        List<MAAbrechnung> liste = query.getResultList();

        em.close();

        return liste;
    }
}

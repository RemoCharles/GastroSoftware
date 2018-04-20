package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.api.BestellPositionDAO;
import slgp.gastrosoftware.zentrale.persister.domain.BestellPosition;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class BestellPositionDAOImpl extends GenericPersisterDAOImpl<BestellPosition> implements BestellPositionDAO {

    public BestellPositionDAOImpl(Class<BestellPosition> type) {
        super(type);
    }

	  public BestellPositionDAOImpl() {
		  super(BestellPosition.class);
	  }


    @Override
    public List<BestellPosition> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();
        TypedQuery<BestellPosition> query = em.createNamedQuery("BestellPosition.findAll", BestellPosition.class);
        List<BestellPosition> list = query.getResultList();
        em.close();
        return list;
    }

    @Override
    public BestellPosition save(BestellPosition entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public BestellPosition update(BestellPosition entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(BestellPosition entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public void deleteById(long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public BestellPosition findById(long id) throws Exception {
        return super.findById(id);
    }
}

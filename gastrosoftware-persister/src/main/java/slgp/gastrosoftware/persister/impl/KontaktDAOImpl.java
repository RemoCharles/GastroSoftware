package slgp.gastrosoftware.persister.impl;

import slgp.gastrosoftware.model.Kontakt;
import slgp.gastrosoftware.persister.KontaktDAO;
import slgp.gastrosoftware.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class KontaktDAOImpl extends GenericPersisterDAOImpl<Kontakt> implements KontaktDAO {
    public KontaktDAOImpl(Class<Kontakt> type) {
        super(type);
    }

    public KontaktDAOImpl(){
        super(Kontakt.class);
    }

    @Override
    public Kontakt save(Kontakt entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public Kontakt update(Kontakt entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(Kontakt entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public List<Kontakt> findAll() throws Exception{
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Kontakt> query = em.createNamedQuery("Kontakt.findAll", Kontakt.class);

        List<Kontakt> liste = query.getResultList();

        em.close();

        return liste;
    }

}

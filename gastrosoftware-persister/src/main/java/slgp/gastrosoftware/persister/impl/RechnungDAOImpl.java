package slgp.gastrosoftware.persister.impl;

import slgp.gastrosoftware.model.Rechnung;
import slgp.gastrosoftware.persister.RechnungDAO;
import slgp.gastrosoftware.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RechnungDAOImpl extends GenericPersisterDAOImpl<Rechnung> implements RechnungDAO {

    public List<Rechnung> findByDatum(LocalDate datum) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();
        TypedQuery<Rechnung> query = em.createNamedQuery("Rechnung.findByDatum", Rechnung.class);
        query.setParameter("datum", datum);
        List<Rechnung> liste = query.getResultList();
        em.close();
        return liste != null ? liste : new ArrayList<Rechnung>();
    }

    public RechnungDAOImpl(Class<Rechnung> type) {
        super(type);
    }

    public RechnungDAOImpl(){
        super(Rechnung.class);
    }

    @Override
    public Rechnung save(Rechnung entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public Rechnung update(Rechnung entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(Rechnung entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public void deleteById(long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public Rechnung findById(long id) throws Exception {
        return super.findById(id);
    }

    @Override
    public List<Rechnung> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Rechnung> query = em.createNamedQuery("Rechnung.findAll", Rechnung.class);

        List<Rechnung> liste = query.getResultList();

        em.close();

        return liste;
    }
}

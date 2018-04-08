package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.api.GetraenkeDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class GetraenkeDAOImpl extends GenericPersisterDAOImpl<Getraenke> implements GetraenkeDAO {

    public GetraenkeDAOImpl(Class<Getraenke> type) {
        super(type);
    }

    public GetraenkeDAOImpl() {
        super(Getraenke.class);
    }

    @Override
    public Getraenke save(Getraenke entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public Getraenke update(Getraenke entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(Getraenke entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public void deleteById(long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public Getraenke findById(long id) throws Exception {
        return super.findById(id);
    }


    @Override
    public List<Getraenke> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Getraenke> query = em.createNamedQuery("Getraenke.findAll", Getraenke.class);

        List<Getraenke> liste = query.getResultList();

        em.close();

        return liste;
    }
}

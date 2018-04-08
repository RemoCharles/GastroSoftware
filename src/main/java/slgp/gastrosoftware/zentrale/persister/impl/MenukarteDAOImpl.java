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

    public MenukarteDAOImpl(){
        super(Menukarte.class);
    }

    public List<Menukarte> showAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Menukarte> query = em.createNamedQuery("Menukarte.findAll", Menukarte.class);

        List<Menukarte> liste = query.getResultList();

        em.close();

        return liste;

    }

    @Override
    public Menukarte save(Menukarte entity) throws Exception {
        return super.save(entity);
    }

    @Override
    public Menukarte update(Menukarte entity) throws Exception {
        return super.update(entity);
    }

    @Override
    public void delete(Menukarte entity) throws Exception {
        super.delete(entity);
    }

    @Override
    public void deleteById(long id) throws Exception {
        super.deleteById(id);
    }

    @Override
    public Menukarte findById(long id) throws Exception {
        return super.findById(id);
    }

    @Override
    public List<Menukarte> findAll() throws Exception {
        return super.findAll();
    }
}

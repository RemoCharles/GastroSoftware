package ch.hslu.slgp.gastrosoftware.persister.impl;

import ch.hslu.slgp.gastrosoftware.model.Menukarte;
import ch.hslu.slgp.gastrosoftware.persister.MenukarteDAO;
import ch.hslu.slgp.gastrosoftware.persister.util.JpaUtil;

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

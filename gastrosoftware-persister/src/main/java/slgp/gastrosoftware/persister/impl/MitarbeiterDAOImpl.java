package slgp.gastrosoftware.persister.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.persister.MitarbeiterDAO;
import slgp.gastrosoftware.model.Mitarbeiter;
import slgp.gastrosoftware.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class MitarbeiterDAOImpl extends GenericPersisterDAOImpl<Mitarbeiter> implements MitarbeiterDAO {

    private static final Logger logger = LogManager.getLogger(MitarbeiterDAOImpl.class);

    public MitarbeiterDAOImpl() {
        super(Mitarbeiter.class);
    }

    public MitarbeiterDAOImpl(Class type) {
        super(type);
    }

    @Override
    public List<Mitarbeiter> findByNachname(String nachname) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Mitarbeiter> query = em.createNamedQuery("Mitarbeiter.findByNachname", Mitarbeiter.class);

        query.setParameter("nachname", nachname);

        List<Mitarbeiter> liste = query.getResultList();

        em.close();

        return liste != null ? liste : new ArrayList<Mitarbeiter>();
    }

    @Override
    public List<Mitarbeiter> findByVorname(String vorname) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();
        TypedQuery<Mitarbeiter> query = em.createNamedQuery("Mitarbeiter.findByVorname", Mitarbeiter.class);
        query.setParameter("vorname", vorname);
        List<Mitarbeiter> liste = query.getResultList();
        em.close();
        return liste != null ? liste : new ArrayList <Mitarbeiter>();
    }

    @Override
    public List<Mitarbeiter> findByNachnameUndVorname(String nachname, String vorname) throws Exception {
        return null;
    }

    @Override
    public Mitarbeiter findByUsername(String username) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();
        TypedQuery <Mitarbeiter> query = em.createNamedQuery("Mitarbeiter.findByUsername", Mitarbeiter.class);
        query.setParameter("username", username);
        List <Mitarbeiter> liste = query.getResultList();
        em.close();

        if (liste.isEmpty()) {
            return null;
        } else if (liste.size() == 1) {
            return liste.get(0);
        } else {
            String message = "Mehr als eine Mitarbeiter-Entity mit Username: " + username + " gefunden";
            logger.error(message);
            throw new IllegalStateException(message);
        }
    }

    @Override
    public List<Mitarbeiter> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();
        TypedQuery<Mitarbeiter> query = em.createNamedQuery("Mitarbeiter.findAll", Mitarbeiter.class);
        List<Mitarbeiter> liste = query.getResultList();
        em.close();
        return liste;
    }
}

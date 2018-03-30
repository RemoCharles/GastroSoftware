package slgp.gastrosoftware.zentrale.persister.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.zentrale.persister.api.KonsumartikelDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class KonsumartikelDAOimpl extends GenericPersisterDAOImpl<Konsumartikel> implements KonsumartikelDAO {
    private static final Logger logger = LogManager.getLogger(KonsumartikelDAOimpl.class);

    public KonsumartikelDAOimpl() {
        super(Konsumartikel.class);
    }

    @Override
    public Konsumartikel findByBezeichnung(String bezeichnung) throws Exception {

        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Konsumartikel> query = em.createNamedQuery("Konsumartikel.findByBezeichnung", Konsumartikel.class);

        query.setParameter("bezeichnung", bezeichnung);

        List<Konsumartikel> liste = query.getResultList();

        em.close();

        if (liste.isEmpty()) {
            return null;
        } else if (liste.size() == 1) {
            return liste.get(0);
        } else {
            String message = "Mehr als eine Benutzer-Entity mit dem Benutzernamen \'" + bezeichnung + "\' gefunden";
            logger.error(message);
            throw new IllegalStateException(message);
        }
    }

    @Override
    public List<Konsumartikel> findByKategorie(String kategorie) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Konsumartikel> query = em.createNamedQuery("Konsumartikel.findByKategorie", Konsumartikel.class);

        query.setParameter("kategorie", kategorie);

        List<Konsumartikel> liste = query.getResultList();

        em.close();

        return liste;
    }

    @Override
    public List<Konsumartikel> showAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Konsumartikel> query = em.createNamedQuery("Konsumartikel.findAll", Konsumartikel.class);

        List<Konsumartikel> liste = query.getResultList();

        em.close();

        return liste;
    }

}

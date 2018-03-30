package slgp.gastrosoftware.zentrale.persister.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;

import static junit.framework.TestCase.assertTrue;

public class Konsumartikel {
    private static Logger logger = LogManager.getLogger(Konsumartikel.class);

    public void esswarenErfassen(Esswaren artikel) {
        EntityManager em = JpaUtil.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(artikel);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Essware konnte nicht erfasst werden!", e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

    public void getraenkErfassen(Getraenke artikel) {
        EntityManager em = JpaUtil.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(artikel);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Getraenk konnte nicht erfasst werden!", e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
    }

}

package slgp.gastrosoftware.zentrale.persister.domain;

import org.junit.Test;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class PersonTest {
    @Test
    public void testSpeichern() {

        EntityManager em = JpaUtil.createEntityManager();
        em.getTransaction().begin();

        Person pers = new Person("Müller", "Marco");

        try {
            em.persist(pers);

            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Person konnte nicht gespeichert werden" + e);

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
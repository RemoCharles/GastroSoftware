package slgp.gastrosoftware.zentrale.persister.util;


import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class DbHelper {
	
	private static Logger logger = LogManager.getLogger(DbHelper.class);

    public static void deleteEsswaren() {

        EntityManager em = null;
        TypedQuery<Esswaren> tQuery = null;
        List<Esswaren> liste;

        // TODO - vervollstaendigen ...

    }
    
    public static void personenSpeichern(List <Person> list) {
    	
		EntityManager em = JpaUtil.createEntityManager();
		em.getTransaction().begin();
		
		try {
			for (Person p: list) {
				em.persist(p);
				//logger.info("Person gespeichert" + p.toString());
			}

			em.getTransaction().commit();

		} catch (Exception e) {
			logger.error("Person konnte nicht gepseichert werden" + e);
			System.out.println("Person konnte nicht gespeichert werden" + e);

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if(em.isOpen()) {
				em.close();
			}
		}
    	
    }

}

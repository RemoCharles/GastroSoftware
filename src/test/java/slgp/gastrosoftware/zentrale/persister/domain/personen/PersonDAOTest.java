package slgp.gastrosoftware.zentrale.persister.domain.personen;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.impl.PersonDAOImpl;
import slgp.gastrosoftware.zentrale.persister.impl.Util;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

public class PersonDAOTest {
	
	private static Logger logger = LogManager.getLogger(PersonTest.class);

	private static List<Person> personen;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		personen = Util.erstellePersonenListe();
	}

	@Test
	public void testFindByNachname() throws Exception {
		
		logger.info("Methode wird auferufen");
		EntityManager em = JpaUtil.createEntityManager();
		logger.info("EntityManager erstellt");
		em.getTransaction().begin();
		
		try {
			for (Person p: personen) {
				em.persist(p);
				logger.info("Person gespeichert" + p.toString());
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
		
		PersonDAOImpl test1 = new PersonDAOImpl();
		List<Person> ausgeben = new ArrayList<>();
		ausgeben = test1.findByNachname("Meier");
		
		for(int i=0;i<ausgeben.size();i++){

            System.out.print(ausgeben.get(i).toString());
            System.out.println("----------------------------");
            
        }
	}

}

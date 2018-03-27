package slgp.gastrosoftware.zentrale.persister.domain.personen;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import slgp.gastrosoftware.zentrale.persister.domain.personen.*;
import slgp.gastrosoftware.zentrale.persister.impl.Util;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

public class PersonTest {

	private static Logger logger = LogManager.getLogger(PersonTest.class);

	private static List<Person> personen;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		personen = Util.erstellePersonenListe();
	}


	@Test
	public void testPersonSpeichern() {
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

		em = JpaUtil.createEntityManager();
		List <Person> pListe = em.createQuery("SELECT a FROM Person a ORDER BY a.id", Person.class).getResultList();
		assertTrue(pListe.size() == personen.size());
		em.close();
	}


	@Test
	public void testPersonLoeschen() {
		String nameSuche = "Meier";
		String vornameSuche = "Marco";
		

		EntityManager em = JpaUtil.createEntityManager();
		em.getTransaction().begin();

		try {
			for (int i = 0; i < personen.size(); ++i) {
				if (personen.get(i).getName().equals(nameSuche) && personen.get(i).getVorname().equals(vornameSuche)) {
					logger.info("Person wurde gefunden" + personen.get(i).getId());
					Person personVonDb = em.find(Person.class, personen.get(i).getId());
					em.remove(personVonDb);
					em.getTransaction().commit();
				}

			}
			
		} catch (Exception e) {
			logger.error("Person konnte nicht gelöscht werden" + e);
			System.out.println("Person konnte nicht gelöscht werden" + e);

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if(em.isOpen()) {
				em.close();
			}
		}
		
		em = JpaUtil.createEntityManager();
		List <Person> pListe = em.createQuery("SELECT a FROM Person a ORDER BY a.id", Person.class).getResultList();
		assertTrue(pListe.size() == 2);
		em.close();
		
	}
	
	@Test
	public void testPersonAdresseAendern() {
		String nameAendern = "Münch";
		String vornameAendern = "Jana";
		Adresse adresseAendern = new Adresse("Miternachtsstrasse 8", 6004, "Ebikon");
		
		EntityManager em = JpaUtil.createEntityManager();
		em.getTransaction().begin();
		
		try {
			for (int i = 0; i < personen.size(); ++i) {
				if (personen.get(i).getName().equals(nameAendern) && personen.get(i).getVorname().equals(vornameAendern)) {
					logger.info("Person wurde gefunden" + personen.get(i).getId());
					Person personVonDb = em.find(Person.class, personen.get(i).getId());
					personVonDb.setAdresse(adresseAendern);
					em.merge(personVonDb);
					em.getTransaction().commit();
				}

			}
			
		} catch (Exception e) {
			logger.error("Adresse konnte nicht geändert werden" + e);
			System.out.println("Adresse konnte nicht geändert werden" + e);

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if(em.isOpen()) {
				em.close();
			}
		}
		
		em = JpaUtil.createEntityManager();
		
		
		
	}

}


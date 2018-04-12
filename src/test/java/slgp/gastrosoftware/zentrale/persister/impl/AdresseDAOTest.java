package slgp.gastrosoftware.zentrale.persister.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.AdresseDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Adresse;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

public class AdresseDAOTest {

	private static AdresseDAO aAdresse = new AdresseDAOImpl();

	private static Logger logger = LogManager.getLogger(AdresseDAOTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		Util.resetDb();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		Util.deleteAllPersonen();
	}
	
	@Before
	public void setUp() throws Exception{
		Util.deleteAllPersonen();
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	public void init() throws Exception{
		Util.erstellePersonenListe();
	}


	@Test
	public void testfindAll() throws Exception {
		init();

		assertTrue(aAdresse.findAll().size() == Util.INIT_SIZE_PERSONEN);

		// Ausgabe der Adressen
		EntityManager em = JpaUtil.createEntityManager();

		List <Adresse> alleAdressen = em.createNamedQuery("Adresse.findAll", Adresse.class).getResultList();

		for (Adresse a: alleAdressen) {
			logger.info("Adresse welche gefunden wurden: " + a);
		}
	}

}




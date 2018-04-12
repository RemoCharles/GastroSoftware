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
import slgp.gastrosoftware.zentrale.persister.api.KontaktDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Kontakt;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

public class KontaktDAOTest {
	
	private static KontaktDAO kKontakt = new KontaktDAOImpl();
	
	private static Logger logger = LogManager.getLogger(KontaktDAOTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		Util.resetDb();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Util.deleteAllMitarbeiter();
		Util.deleteAllPersonen();
	}
	
	@Before
	public void setUp() throws Exception {
		Util.deleteAllMitarbeiter();
		Util.deleteAllPersonen();
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	public void init() throws Exception {
		Util.erstellePersonenListe();
	}
	
	@Test
	public void testfindAll() throws Exception  {
		init();
		System.out.println("Kontrolle der Personen:  " + kKontakt.findAll().size());
		System.out.println(Util.INIT_SIZE_PERSONEN);
		
		
		// assertTrue(kKontakt.findAll().size() == Util.INIT_SIZE_PERSONEN);
		
		// Ausgabe der Kontakte
		EntityManager em = JpaUtil.createEntityManager();
		
		List <Kontakt> alleKontakte = em.createNamedQuery("Kontakt.findAll", Kontakt.class).getResultList();
		
		for (Kontakt k: alleKontakte) {
			logger.info("Kontakt welcher gefunden wurden: " + k);
		}
	}

}

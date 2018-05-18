package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.Adresse;
import ch.hslu.slgp.gastrosoftware.persister.impl.AdresseDAOImpl;
import ch.hslu.slgp.gastrosoftware.persister.util.JpaUtil;
import ch.hslu.slgp.gastrosoftware.persister.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertTrue;

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




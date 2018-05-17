package slgp.gastrosoftware.persister;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.model.Adresse;
import slgp.gastrosoftware.persister.impl.AdresseDAOImpl;
import slgp.gastrosoftware.persister.util.JpaUtil;
import slgp.gastrosoftware.persister.util.Util;

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




package slgp.gastrosoftware.persister;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.persister.impl.KontaktDAOImpl;
import slgp.gastrosoftware.persister.util.JpaUtil;
import slgp.gastrosoftware.model.Kontakt;
import slgp.gastrosoftware.persister.util.Util;

import javax.persistence.EntityManager;
import java.util.List;

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
		// assertTrue(kKontakt.findAll().size() == util.INIT_SIZE_PERSONEN);
		// Ausgabe der Kontakte
		EntityManager em = JpaUtil.createEntityManager();
		
		List <Kontakt> alleKontakte = kKontakt.findAll();
		
		for (Kontakt k: alleKontakte) {
			logger.info("Kontakt welcher gefunden wurden: " + k);
		}
	}

}

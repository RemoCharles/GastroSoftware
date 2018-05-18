package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.Kontakt;
import ch.hslu.slgp.gastrosoftware.model.Person;
import ch.hslu.slgp.gastrosoftware.persister.impl.PersonDAOImpl;
import ch.hslu.slgp.gastrosoftware.persister.util.JpaUtil;
import ch.hslu.slgp.gastrosoftware.persister.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PersonDAOTest {

	private static PersonDAO pPerson = new PersonDAOImpl();

	private static Logger logger = LogManager.getLogger(PersonDAOTest.class);


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public final void testSave() throws Exception {
		init();
		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);
	}

	@Test
	public final void testUpdate() throws Exception {
		init();

		logger.info("Personen nach Initialisierung --------------------------------------------------");
		for (Person p : pPerson.findAll()) {
			logger.info(p);
		}
		logger.info("--------------------------------");

		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);
		int size = pPerson.findAll().size();
		Person lastPerson = pPerson.findAll().get(size -1);
		logger.info("Person vor Änderung: " + lastPerson.toString());
		logger.info("--------------------------------");
		Kontakt kontaktAusDB = lastPerson.getKontakt();
		Kontakt kontaktNeu = new Kontakt("test1@gmail.com", "078 546 93 93");
		lastPerson.setKontakt(kontaktNeu);
		// Person mit geändertem Kontakt abspeichern
		pPerson.update(lastPerson);
		logger.info("Person nach Änderung: " + lastPerson.toString());
		logger.info("--------------------------------");

		// Person von der DB holen
		Person personNeuAusDb = pPerson.findAll().get(size -1);

		assertFalse(kontaktAusDB.equals(personNeuAusDb.getKontakt()));
		assertTrue(kontaktNeu.getEmail().equals(personNeuAusDb.getKontakt().getEmail()));
		assertTrue(kontaktNeu.getTelefon().equals(personNeuAusDb.getKontakt().getTelefon()));

		logger.info("Personen nach Update --------------------------------------------------");
		for (Person p : pPerson.findAll()) {
			logger.info(p);
		}
	}

	@Test
	public final void testDelete() throws Exception{

		init();

		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

		int size = pPerson.findAll().size();

		Person lastPerson = pPerson.findAll().get(size -1);

		pPerson.delete(lastPerson);

		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN -1);


	}

	@Test
	public void testFindAll() throws Exception{
		init();

		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);
	}

	@Test
	public void testFindByNachname() throws Exception {

		init();
		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

		int size = pPerson.findAll().size();

		Person lastPerson = pPerson.findAll().get(size - 1);

		String nachname = lastPerson.getName();

		List <Person> personNachNachnameListe = pPerson.findByNachname(nachname);

		EntityManager em = JpaUtil.createEntityManager();

		List <Person> pListe = pPerson.findAll();

		assertTrue(personNachNachnameListe.contains(lastPerson));

	}

	@Test
	public void testFindByVorname() throws Exception {

		init();
		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

		int size = pPerson.findAll().size();

		Person lastPerson = pPerson.findAll().get(size -1);

		String vorname = lastPerson.getVorname();

		List<Person> personNachVornameListe = pPerson.findByVorname(vorname);
		
		Person persFromDb = pPerson.findByVorname(vorname).get(0);
		
		
		assertTrue(personNachVornameListe.get(0).getVorname().equals(vorname));
		assertTrue(personNachVornameListe.contains(lastPerson));
		
		

	}

	@Test
	public void testFindByUsername()throws Exception{

		init();
		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

		int size = pPerson.findAll().size();

		Person lastPerson = pPerson.findAll().get(size -1);

		String lastPersonUsername = lastPerson.getLogin().getUsername();


		Person personAusDbMitUsername = (Person) pPerson.findByUsername(lastPersonUsername);


		assertTrue(personAusDbMitUsername.getLogin().getUsername().equals(lastPerson.getLogin().getUsername()));
		assertTrue(personAusDbMitUsername.getLogin().eqauls(lastPerson.getLogin()));
	}

	@Test
	public final void testFindByNachnameUndVorname() throws Exception {
		init();

		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

		int size = pPerson.findAll().size();

		Person lastPerson = pPerson.findAll().get(size -1);

		String nachname = lastPerson.getName();
		String vorname = lastPerson.getVorname();

		List <Person> personNachNachnameUndVornameListe = pPerson.findByNachnameUndVorname(nachname, vorname);

		for (Person p: personNachNachnameUndVornameListe) {
			System.out.println("Person NachNachname und Vorname -------------");
			System.out.println(p);
		}

		assertTrue(personNachNachnameUndVornameListe.contains(lastPerson));
	}
}
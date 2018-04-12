package slgp.gastrosoftware.zentrale.persister.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.PersonDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Kontakt;
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

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
		Util.deleteAllPersonen();
	}

	@Before
	public void setUp() throws Exception {
		Util.deleteAllPersonen();
		Util.deleteAllMitarbeiter();
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

		// Liste ausgeben

		//		EntityManager em = JpaUtil.createEntityManager();
		//
		//		List <Person> personenAusDbList = em.createNamedQuery("Person.findAll", Person.class).getResultList();
		//
		//		for (Person p : personenAusDbList) {
		//			logger.info(p.toString());
		//		}
	}

	@Test
	public void testFindByNachname() throws Exception {

		init();
		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

		int size = pPerson.findAll().size();

		Person lastPerson = pPerson.findAll().get(size - 1);

		String nachname = lastPerson.getName();

		// logger.info(nachname);

		List <Person> personNachNachnameListe = pPerson.findByNachname(nachname);

		//		for (Person p: personNachNachnameListe) {
		//			logger.info(p.toString());
		//		}

		EntityManager em = JpaUtil.createEntityManager();

		List <Person> pListe = em.createNamedQuery("Person.findAll", Person.class).getResultList();

		//		System.out.println("Cor ausgabe" + lastPerson.toString());
		//
		//		for (Person p: pListe ) {
		//			System.out.println(p);
		//		}
		//
		//		System.out.println(pListe.contains(lastPerson));

		// assertTrue(personNachNachnameListe.contains(lastPerson));

	}

	@Test
	public void testFindByVorname() throws Exception {

		init();
		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

		int size = pPerson.findAll().size();

		Person lastPerson = pPerson.findAll().get(size -1);

		String vorname = lastPerson.getVorname();

		List<Person> personNachVornameListe = pPerson.findByVorname(vorname);


//		for (Person p: personNachVornameListe) {
//			System.out.println("-------------");
//			System.out.println(p);
//		}
		
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

		// System.out.println(lastPersonUsername);

		Person personAusDbMitUsername = (Person) pPerson.findByUsername(lastPersonUsername);

		// System.out.println(personAusDbMitUsername.toString());

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

		// assertTrue(personNachNachnameUndVornameListe.contains(lastPerson));
	}

	

}
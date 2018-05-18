package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.Person;
import ch.hslu.slgp.gastrosoftware.persister.impl.LoginDAOImpl;
import ch.hslu.slgp.gastrosoftware.persister.impl.PersonDAOImpl;
import ch.hslu.slgp.gastrosoftware.persister.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LoginDAOTest {
	
	private static PersonDAO pPerson = new PersonDAOImpl();
	
	private static LoginDAO lLogin = new LoginDAOImpl();
	
	private static Logger logger = LogManager.getLogger(LoginDAOTest.class);
	
	private static List<Person> personen;
	
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
	public void setUp() throws Exception{
		Util.deleteAllMitarbeiter();
		Util.deleteAllPersonen();
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	public void init() throws Exception {
		Util.erstellePersonenListe();
	}

	@Test
	public void pruefeLoginVorhanden() throws Exception {
		init();

		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

		boolean ergebnis = lLogin.pruefeLogin("mjana", "abcde");

		assertTrue(ergebnis == true);

	}

	@Test
	public void pruefeLoginNichtVorhanden() throws Exception{
		init();

		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

		boolean ergebnis = lLogin.pruefeLogin("mjana", "abeecde");

		assertTrue(ergebnis == false);
	}


	@Test
	public void getFunktionPerson() throws Exception{
		init();

		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

		String personFunktion = lLogin.getFunktionPerson("mjana", "abcde");

		assertTrue(personFunktion.equals("Servicepersonal"));
	}

	@Test
	public void getFunktionPersonNichtVorhanden() throws Exception {
		init();

		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

		String personFunktion = lLogin.getFunktionPerson("mjana", "abcde");

		assertTrue(!personFunktion.equals("Kuechenpersonal"));
	}
}

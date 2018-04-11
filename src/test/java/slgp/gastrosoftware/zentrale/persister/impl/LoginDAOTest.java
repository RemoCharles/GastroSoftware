package slgp.gastrosoftware.zentrale.persister.impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.sessions.Login;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.LoginDAO;
import slgp.gastrosoftware.zentrale.persister.api.PersonDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.impl.LoginDAOImpl;
import slgp.gastrosoftware.zentrale.persister.impl.PersonDAOImpl;
import slgp.gastrosoftware.zentrale.persister.util.DbHelper;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

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
		Util.deleteAllPersonen();
	}
	
	@Before
	public void setUp() throws Exception{
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
	
//	@Test
//	public void testfindAll() throws Exception {
//		init();
//		
//		assertTrue(lLogin.findAll().size() == Util.INIT_SIZE_PERSONEN);
//		
//		// Ausgabe aller Logins
//		EntityManager em = JpaUtil.createEntityManager();
//		
//		List <Login> alleLogin = em.createNamedQuery("Login.findAll", Login.class).getResultList();
//		
//		for (Login l: alleLogin) {
//			logger.info("Login welche gefunden wurden: " + l);
//		}
//		
//	}

}

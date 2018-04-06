package slgp.gastrosoftware.zentrale.persister.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;


import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.LoginDAO;
import slgp.gastrosoftware.zentrale.persister.api.PersonDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.impl.LoginDAOImpl;
import slgp.gastrosoftware.zentrale.persister.impl.PersonDAOImpl;
import slgp.gastrosoftware.zentrale.persister.util.DbHelper;

public class LoginDAOTest {
	
	private static PersonDAO pPerson = new PersonDAOImpl();
	
	private static LoginDAO lLogin = new LoginDAOImpl();
	
	private static Logger logger = LogManager.getLogger(LoginDAOTest.class);
	
	private static List<Person> personen;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		personen = Util.erstellePersonenListe();
	}
	
	public void init() throws Exception {
		DbHelper.personenSpeichern(personen);
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
		boolean ergebnis = lLogin.pruefeLogin("mjana", "abeecde");
		
		assertTrue(ergebnis == false);
	}
	
	
	@Test
	public void getFunktionPerson() throws Exception{
		String personFunktion = lLogin.getFunktionPerson("mjana", "abcde");
		
		assertTrue(personFunktion.equals("Servicepersonal"));
	}
	
	@Test
	public void getFunktionPersonNichtVorhanden() throws Exception {
		String personFunktion = lLogin.getFunktionPerson("mjana", "abcde");
		
		assertTrue(!personFunktion.equals("Kuechenpersonal"));
	}

}

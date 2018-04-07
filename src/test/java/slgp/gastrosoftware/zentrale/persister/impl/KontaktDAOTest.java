package slgp.gastrosoftware.zentrale.persister.impl;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.KontaktDAO;

public class KontaktDAOTest {
	
	private static KontaktDAO kKontakt = new KontaktDAOImpl();
	
	private static Logger logger = LogManager.getLogger(KontaktDAOTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		Util.resetDb();
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Util.deleteAllPersonen();
	}
	
	@Before
	public void setUp() throws Exception {
		Util.deleteAllPersonen();
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	public void init() throws Exception {
		Util.erstellePersonenListe();
	}
	
	@Test
	public final void findAll() throws Exception  {
		fail("Not yet implemented");
	}

}

package slgp.gastrosoftware.zentrale.persister.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.PersonDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Kontakt;
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.impl.PersonDAOImpl;
import slgp.gastrosoftware.zentrale.persister.util.DbHelper;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

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

        assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);

        int size = pPerson.findAll().size();

        Person lastPerson = pPerson.findAll().get(size -1);

        logger.info("Person vor Änderung: " + lastPerson.toString());

        Kontakt kontaktAusDB = lastPerson.getKontakt();

        Kontakt kontaktNeu = new Kontakt("test1@gmail.com", "078 546 93 93");

        lastPerson.setKontakt(kontaktNeu);

        // Person mit geändertem Kontakt abspeichern
        pPerson.update(lastPerson);

        logger.info("Person nach Änderung: " + lastPerson.toString());

        // Person von der DB holen
        Person personNeuAusDb = pPerson.findAll().get(size -1);

        assertFalse(kontaktAusDB.equals(personNeuAusDb.getKontakt()));
        assertTrue(kontaktNeu.getEmail().equals(personNeuAusDb.getKontakt().getEmail()));
        assertTrue(kontaktNeu.getTelefon().equals(personNeuAusDb.getKontakt().getTelefon()));

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
        
        EntityManager em = JpaUtil.createEntityManager();

		List <Person> personenAusDbList = em.createNamedQuery("Person.findAll", Person.class).getResultList();

        for (Person p : personenAusDbList) {
        	logger.info(p.toString());
        }
    }

    @Test
    public void testFindByNachname() throws Exception {


//    	init();
//		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);
//
//		int size = pPerson.findAll().size();
//
//		Person lastPerson = pPerson.findAll().get(size - 1);
//
//		String vorname = lastPerson.getVorname();
//
//		List<Person> personNachNachnameListe = pPerson.findByVorname(vorname);
//
//		assertTrue(personNachNachnameListe.contains(lastPerson));
    }

    @Test
    public void testFindByVorname() throws Exception {

    }

}
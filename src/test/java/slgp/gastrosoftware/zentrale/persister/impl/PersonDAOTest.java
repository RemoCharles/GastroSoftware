package slgp.gastrosoftware.zentrale.persister.domain.personen;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.PersonDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.impl.PersonDAOImpl;
import slgp.gastrosoftware.zentrale.persister.util.DbHelper;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

public class PersonDAOTest {
	
	private static PersonDAO pPerson = new PersonDAOImpl();
	
	private static Logger logger = LogManager.getLogger(PersonDAOTest.class);

	private static List<Person> personen;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		personen = Util.erstellePersonenListe();
	}

	
	public void init() throws Exception {
		DbHelper.personenSpeichern(personen);
	}
	
	@Test
	public final void testSave() throws Exception {
		init();
		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);
	}
	
	@Test
	public final void testUpdate() throws Exception {
		assertTrue(pPerson.findAll().size() == Util.INIT_SIZE_PERSONEN);
		
		int size = pPerson.findAll().size();
		
		Person lastPerson = pPerson.findAll().get(size -1);
		
		System.out.println(lastPerson.toString());
	}
	
	@Test
	public void testFindByNachname() throws Exception {
	
	
//		DbHelper.personenSpeichern(personen);
//		PersonDAOImpl test1 = new PersonDAOImpl();
//		List<Person> ausgeben = new ArrayList<>();
//		ausgeben = test1.findByNachname("Meier");
//		
//		for(int i=0;i<ausgeben.size();i++){
//
//            System.out.print(ausgeben.get(i).toString());
//            System.out.println("----------------------------");
//            
//        }
	}
	
	@Test 
	public void testFindByVorname() throws Exception {
		
	}

}

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
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.impl.PersonDAOImpl;
import slgp.gastrosoftware.zentrale.persister.util.DbHelper;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

public class PersonDAOTest {
	
	private static Logger logger = LogManager.getLogger(PersonDAOTest.class);

	private static List<Person> personen;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		personen = Util.erstellePersonenListe();
	}

	@Test
	public void testFindByNachname() throws Exception {
	
		DbHelper.personenSpeichern(personen);
		PersonDAOImpl test1 = new PersonDAOImpl();
		List<Person> ausgeben = new ArrayList<>();
		ausgeben = test1.findByNachname("Meier");
		
		for(int i=0;i<ausgeben.size();i++){

            System.out.print(ausgeben.get(i).toString());
            System.out.println("----------------------------");
            
        }
	}

}

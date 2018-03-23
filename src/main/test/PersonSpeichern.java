import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;

import slgp.gastrosoftware.zentrale.persister.domain.Adresse;
import slgp.gastrosoftware.zentrale.persister.domain.Kontakt;
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

public class PersonSpeichern {

	@Test
	public void testSpeichern() {
		
		System.out.println("funktinier");
		EntityManager em = JpaUtil.createEntityManager();
		System.out.println("Entitymanger erstellt");
		em.getTransaction().begin();
		System.out.println("Start");

		Person pers = new Person("Müller", "Marco", new Adresse("Kusterweg 4", 6004, "Luzern"), new Kontakt("mmarco@gmx.ch", "041 234 56 67"));
		System.out.println("Person erstellt");

		try {
			em.persist(pers);
			System.out.println("Gespeichert");

			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Person konnte nicht gespeichert werden" + e);

			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}

		} finally {
			if(em.isOpen()) {
				em.close();
			}
		}

	}

}

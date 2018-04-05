package slgp.gastrosoftware.zentrale.persister.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import slgp.gastrosoftware.zentrale.persister.api.LoginDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Leiter;
import slgp.gastrosoftware.zentrale.persister.domain.Login;
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.domain.personen.PersonDAOTest;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

public class LoginDAOImpl extends GenericPersisterDAOImpl<Login> implements LoginDAO{

	public LoginDAOImpl() {
		super(Login.class);
	}

	private static Logger logger = LogManager.getLogger(PersonDAOTest.class);

	public boolean pruefeLogin(String username, String passwort) throws Exception {

		EntityManager em = JpaUtil.createEntityManager();

		TypedQuery<Person> query = em.createNamedQuery("Person.findByUsername", Person.class);

		query.setParameter("username", username);

		List<Person> liste = query.getResultList();

		em.close();

		if (liste.size() != 1) {
			System.out.println("Es wurden mehrere Personen gefunden");
			logger.info("Es wurden mehrere Personen gefunden");

		}

		boolean pw = false;

		for (Person p : liste) {
			if (p.getLogin().getPasswort().equals(passwort)) {
				pw = true;
			} else {
				pw = false;
				logger.info("Passwort falsch");
			}
		}

		return pw;


	}

	public String getFunktionPerson (String username, String passwort) throws Exception {

		EntityManager em = JpaUtil.createEntityManager();

		TypedQuery<Person> query = em.createNamedQuery("Person.findByUsername", Person.class);

		query.setParameter("username", username);

		List<Person> liste = query.getResultList();

		em.close();

		if (liste.size() != 1) {
			System.out.println("Es wurden mehrere Personen gefunden");
			logger.info("Es wurden mehrere Personen gefunden");

		}

		String funktionPerson = "";

		for (Person p : liste) {
			if (p.getLogin().getPasswort().equals(passwort)) {
				funktionPerson = p.getFunktion();
			}


			else {
				System.out.println("Passowrt falsch");
				logger.info("Passwort falsch");

			}
		}

		return funktionPerson;


	}

}

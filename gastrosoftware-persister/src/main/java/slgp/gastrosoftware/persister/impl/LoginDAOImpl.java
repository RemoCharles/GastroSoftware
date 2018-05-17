package slgp.gastrosoftware.persister.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.Login;
import slgp.gastrosoftware.model.Mitarbeiter;
import slgp.gastrosoftware.model.Person;
import slgp.gastrosoftware.persister.LoginDAO;
import slgp.gastrosoftware.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoginDAOImpl extends GenericPersisterDAOImpl<Login> implements LoginDAO {

    public LoginDAOImpl() {
        super(Login.class);
    }

    private static Logger logger = LogManager.getLogger(LoginDAOImpl.class);

    public boolean pruefeLogin(String username, String passwort) throws Exception {

        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Person> query = em.createNamedQuery("Person.findByUsername", Person.class);

        query.setParameter("username", username);

        List<Person> liste = query.getResultList();

        em.close();

        if (liste.size() != 1) {
            System.out.println("Es wurden keine oder mehrere Personen gefunden");
            logger.info("Es wurden keine oder mehrere Personen gefunden");

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

    public String getFunktionPerson(String username, String passwort) throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Person> query = em.createNamedQuery("Person.findByUsername", Person.class);

        query.setParameter("username", username);

        List<Person> liste = query.getResultList();

        em.close();

        if (liste.size() > 1) {
            logger.info("Es wurden mehrere Personen gefunden");

        }

        String funktionPerson = "";

        for (Person p : liste) {
            if (p.getLogin().getPasswort().equals(passwort)) {
                funktionPerson = p.getFunktion();
            } else {
                logger.info("Passwort falsch");

            }
        }

        return funktionPerson;


    }

    public String getFunktionMitarbeiter(String username, String passwort) throws Exception {

        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Mitarbeiter> query = em.createNamedQuery("Mitarbeiter.findByUsername", Mitarbeiter.class);

        query.setParameter("username", username);

        List<Mitarbeiter> liste = query.getResultList();

        em.close();

        if (liste.size() > 1) {
            logger.info("Es wurden mehrere Mitarbeiter gefunden");

        }

        String funktionPerson = "";

        for (Person p : liste) {
            if (p.getLogin().getPasswort().equals(passwort)) {
                funktionPerson = p.getFunktion();
            } else {
                logger.info("Passwort falsch");

            }
        }

        return funktionPerson;


    }

    @Override
    public List<Login> findAll() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();

        TypedQuery<Login> query = em.createNamedQuery("Login.findAll", Login.class);

        List<Login> liste = query.getResultList();

        em.close();

        return liste;
    }
}

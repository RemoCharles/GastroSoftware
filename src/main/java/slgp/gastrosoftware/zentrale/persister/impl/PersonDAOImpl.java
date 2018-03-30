package slgp.gastrosoftware.zentrale.persister.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import slgp.gastrosoftware.zentrale.persister.api.PersonDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

public class PersonDAOImpl extends GenericPersisterDAOImpl<Person> implements PersonDAO {
	
	public PersonDAOImpl() {
		super(Person.class);
	}

	private static final Logger logger = LogManager.getLogger(PersonDAOImpl.class);

	public List <Person> findByNachname(String nachname) throws Exception{
		EntityManager em = JpaUtil.createEntityManager();
		
		TypedQuery<Person> query = em.createNamedQuery("Person.findByNachname", Person.class);

        query.setParameter("nachname", nachname);

        List<Person> liste = query.getResultList();
        
        logger.info("Groesse der Liste");
        logger.info(liste.size());

        em.close();

        return liste != null ? liste : new ArrayList<Person>();
	}
	
	
}

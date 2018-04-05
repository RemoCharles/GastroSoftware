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

	private static final Logger logger = LogManager.getLogger(PersonDAOImpl.class);

	public List <Person> findByNachname(String nachname) throws Exception{
		
		EntityManager em = JpaUtil.createEntityManager();
		
		TypedQuery<Person> query = em.createNamedQuery("Person.findByNachname", Person.class);

        query.setParameter("nachname", nachname);

        List<Person> liste = query.getResultList();
        
//        logger.info("Groesse der Liste");
//        logger.info(liste.size());

        em.close();

        return liste != null ? liste : new ArrayList<Person>();
	}
	
	public List <Person> findByVorname (String vorname) throws Exception {
		
		EntityManager em = JpaUtil.createEntityManager();
		
		TypedQuery<Person> query = em.createNamedQuery("Person.findByVorname", Person.class);
		
		query.setParameter("vorname", vorname);
		
		List<Person> liste = query.getResultList();
		
		em.close();
		
		return liste != null ? liste : new ArrayList <Person>();
	
	}
	
	public List <Person> findByNachnameUndVorname(String nachname, String vorname)throws Exception {
		
		EntityManager em = JpaUtil.createEntityManager();
		
		TypedQuery <Person> query = em.createQuery("Person.findByNachnameUndVorname", Person.class);
		
		query.setParameter("nachname", nachname);
		query.setParameter("vorname", vorname);
		
		List<Person> liste = query.getResultList();
		
		em.close();
		
		return liste != null ? liste : new ArrayList <Person>();
	}
	
	public Person findByUsername(String username) throws Exception {
		
		EntityManager em = JpaUtil.createEntityManager();
		
		TypedQuery <Person> query = em.createQuery("Person.findByUsername", Person.class);
		
		query.setParameter("username", username);
		
		List <Person> liste = query.getResultList();
		
		em.close();
		
		if (liste.isEmpty()) {
			return null;
		} else if (liste.size() == 1) {
			return liste.get(0);
		} else {
			String message = "Mehr als eine Person-Entity mit Username: " + username + " gefunden";
			logger.error(message);
			throw new IllegalStateException(message);
		}
	}

	public PersonDAOImpl(Class<Person> type) {
		super(type);
	}

	@Override
	public Person save(Person entity) throws Exception {
		return super.save(entity);
	}

	@Override
	public Person update(Person entity) throws Exception {
		return super.update(entity);
	}

	@Override
	public void delete(Person entity) throws Exception {
		super.delete(entity);
	}

	@Override
	public void deleteById(long id) throws Exception {
		super.deleteById(id);
	}

	@Override
	public Person findById(long id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<Person> findAll() throws Exception {
		EntityManager em = JpaUtil.createEntityManager();

		TypedQuery<Person> query = em.createNamedQuery("Person.findAll", Person.class);

		List<Person> liste = query.getResultList();

		em.close();

		return liste;
	}
}

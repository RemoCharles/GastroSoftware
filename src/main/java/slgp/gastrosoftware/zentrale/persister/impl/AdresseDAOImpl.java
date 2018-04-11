package slgp.gastrosoftware.zentrale.persister.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import slgp.gastrosoftware.zentrale.persister.api.AdresseDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Adresse;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

public class AdresseDAOImpl extends GenericPersisterDAOImpl<Adresse> implements AdresseDAO {
    public AdresseDAOImpl(Class<Adresse> type) {
        super(type);
    }
    
    public AdresseDAOImpl(){
        super(Adresse.class);
    }
    
    @Override
    public List <Adresse> findAll() throws Exception {
    	EntityManager em = JpaUtil.createEntityManager();
    	
    	TypedQuery<Adresse> query = em.createNamedQuery("Adresse.findAll", Adresse.class);
    	
    	List <Adresse> liste = query.getResultList();
    	
    	em.close();
    	
    	return liste;
    }
}

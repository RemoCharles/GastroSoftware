package ch.hslu.slgp.gastrosoftware.persister.impl;

import ch.hslu.slgp.gastrosoftware.model.Adresse;
import ch.hslu.slgp.gastrosoftware.persister.AdresseDAO;
import ch.hslu.slgp.gastrosoftware.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

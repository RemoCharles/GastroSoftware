package slgp.gastrosoftware.zentrale.persister.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JpaUtil {

<<<<<<< HEAD
	private static Logger logger = LogManager.getLogger(JpaUtil.class);

	private static EntityManagerFactory entityManagerFactory = null;

	static {
		try {
			/* EntityManagerFactory erzeugen */
			entityManagerFactory = Persistence.createEntityManagerFactory("JpaDemosPU");
		} catch (Throwable e) {
			logger.error("ERROR: ", e);
			throw new RuntimeException(e);
		}
	}

	public static EntityManager createEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
=======
    private static Logger logger = LogManager.getLogger(JpaUtil.class);

    private static EntityManagerFactory entityManagerFactory = null;

    static {
        try {
            /* EntityManagerFactory erzeugen */
            entityManagerFactory = Persistence.createEntityManagerFactory("JpaDemosPU");
        } catch (Throwable e) {
            logger.error("ERROR: ", e);
            throw new RuntimeException(e);
        }
    }

    public static EntityManager createEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
>>>>>>> origin/master

package ch.hslu.slgp.gastrosoftware.persister.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {

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

	public static EntityManager createEntityManagerForDelition() {
		return Persistence.createEntityManagerFactory("delete-JpaDemosPU").createEntityManager();
	}
}


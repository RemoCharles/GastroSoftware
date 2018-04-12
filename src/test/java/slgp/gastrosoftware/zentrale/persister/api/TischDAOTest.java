package slgp.gastrosoftware.zentrale.persister.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;
import slgp.gastrosoftware.zentrale.persister.domain.Tisch;
import slgp.gastrosoftware.zentrale.persister.impl.BestellungDAOImpl;
//import slgp.gastrosoftware.zentrale.persister.impl.BestellungDAOTest;
import slgp.gastrosoftware.zentrale.persister.impl.TischDAOImpl;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.*;

public class TischDAOTest {
    private static Logger logger = LogManager.getLogger(TischDAOTest.class);

    private static TischDAO pTischDAO = new TischDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Util.resetDb();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Util.deleteAllTisch();
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllTisch();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        Util.createTisch();
        logger.info("Initalisierung fertig!");
    }


    @Test
    public final void testSave() throws Exception {
        init();
        assertTrue(pTischDAO.findAll().size() == Util.INIT_SIZE_TISCH);
        EntityManager em = JpaUtil.createEntityManager();
        em = JpaUtil.createEntityManager();
        List<Tisch> tischList = em.createQuery("SELECT a FROM Tisch a", Tisch.class).getResultList();
        for (Tisch t : tischList) {
            logger.info(t);
        }
        em.close();
    }

}
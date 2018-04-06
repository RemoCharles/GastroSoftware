package slgp.gastrosoftware.zentrale.persister.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.BestellungDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class BestellungDAOTest {

    private static Logger logger = LogManager.getLogger(BestellungDAOTest.class);

    private static BestellungDAO pBestellungDAO = new BestellungDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Util.resetDb();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Util.deleteAllBestellung();
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllBestellung();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        Util.createBestellungListe();
        Util.createEsswarenListe();
        Util.createGetraenkeListe();
        Util.createKonsumartikelListe();
        Util.createMitarbeiter();
        Util.createTagesmenuListe();
        Util.createTisch();
        Util.createTischRechnung();
        logger.info("Initalisierung fertig!");
    }


    @Test
    public final void testSave() throws Exception {
        init();
        assertTrue(pBestellungDAO.findAll().size() == Util.INIT_SIZE_BESTELLUNG_LISTE);
        EntityManager em = JpaUtil.createEntityManager();
        em = JpaUtil.createEntityManager();
        List<Bestellung> bestellungList = em.createQuery("SELECT a FROM Bestellung a ORDER BY a.datum", Bestellung.class).getResultList();
        for (Bestellung best : bestellungList) {
            logger.info(best);
        }
        em.close();
    }



}

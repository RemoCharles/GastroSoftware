package slgp.gastrosoftware.zentrale.persister.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.EsswarenDAO;
import slgp.gastrosoftware.zentrale.persister.api.GetraenkeDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class KonsumartikelDAOTest {
    private static Logger logger = LogManager.getLogger(KonsumartikelDAOTest.class);

    private static EsswarenDAO esswarenDAO = new EsswarenDAOImpl();
    private static GetraenkeDAO getraenkeDAO = new GetraenkeDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Util.resetDb();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Util.deleteAllEsswaren();
        Util.deleteAllGetraenke();
        Util.deleteAllKonsumartikel();
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllEsswaren();
        Util.deleteAllGetraenke();
        Util.deleteAllKonsumartikel();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        Util.createEsswarenListe();
        Util.createKonsumartikelListe();
        Util.createGetraenkeListe();
        logger.info("Initalisierung fertig!");
    }

    @Test
    public void testEsswarenSave() throws Exception {
        init();
        assertTrue(esswarenDAO.findAll().size() == 2);

        EntityManager em = JpaUtil.createEntityManager();
        em = JpaUtil.createEntityManager();
        List<Esswaren> bestellungList = em.createQuery("SELECT a FROM Esswaren a", Esswaren.class).getResultList();
        for (Esswaren e : bestellungList) {
            logger.info(e);
        }
        em.close();
    }

    @Test
    public void testGetraenkeSave() throws Exception {
        init();
        assertTrue(getraenkeDAO.findAll().size() == 2);

        EntityManager em = JpaUtil.createEntityManager();
        em = JpaUtil.createEntityManager();
        List<Getraenke> bestellungList = em.createQuery("SELECT a FROM Getraenke a", Getraenke.class).getResultList();
        for (Getraenke g : bestellungList) {
            logger.info(g);
        }
        em.close();
    }


}
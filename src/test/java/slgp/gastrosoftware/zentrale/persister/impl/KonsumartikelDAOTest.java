package slgp.gastrosoftware.zentrale.persister.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.EsswarenDAO;
import slgp.gastrosoftware.zentrale.persister.api.GetraenkeDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
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
        Util.deleteAllBestellung();
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllEsswaren();
        Util.deleteAllGetraenke();
        Util.deleteAllKonsumartikel();
        Util.deleteAllBestellung();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        Util.createKonsumartikelListe();
        logger.info("Initalisierung fertig!");
    }

    @Test
    public void testEsswarenSave() throws Exception {
        init();
        assertTrue(esswarenDAO.findAll().size() == Util.INIT_SIZE_ESSWAREN);
    }

    @Test
    public void testEsswarenDelete() throws Exception {
        init();
        assertTrue(esswarenDAO.findAll().size() == Util.INIT_SIZE_ESSWAREN);
        int size = esswarenDAO.findAll().size();
        Esswaren lastEssware = esswarenDAO.findAll().get(size - 1);
        esswarenDAO.delete(lastEssware);
        Assert.assertTrue(esswarenDAO.findAll().size() == Util.INIT_SIZE_PERSONEN - 1);
    }

    @Test
    public void testEsswarenUpdate() throws Exception {
        init();
        assertTrue(esswarenDAO.findAll().size() == Util.INIT_SIZE_KONSUMARTIKEL);
        int size = esswarenDAO.findAll().size();
        Esswaren lastEsswaren = esswarenDAO.findAll().get(size - 1);
        esswarenDAO.update(lastEsswaren);
        Esswaren lastEsswarenNachUpdate = esswarenDAO.findAll().get(size - 1);
        assertTrue(lastEsswarenNachUpdate.getPreis() == lastEsswaren.getPreis());
    }

    @Test
    public void testGetraenkeSave() throws Exception {
        init();
        assertTrue(getraenkeDAO.findAll().size() == 2);
    }

    @Test
    public void testGetraenkeDelete() throws Exception {
        init();
        assertTrue(getraenkeDAO.findAll().size() == Util.INIT_SIZE_GETRAENKE);
        int size = getraenkeDAO.findAll().size();
        Getraenke lastGetraenke = getraenkeDAO.findAll().get(size - 1);
        getraenkeDAO.delete(lastGetraenke);
        Assert.assertTrue(getraenkeDAO.findAll().size() == Util.INIT_SIZE_GETRAENKE - 1);

        for (Getraenke g : getraenkeDAO.findAll()) {
            logger.info(g);
        }
    }

    @Test
    public final void testGetraenkeUpdate() throws Exception {
        init();
        assertTrue(getraenkeDAO.findAll().size() == Util.INIT_SIZE_GETRAENKE);
        Getraenke lastGetraenke = getraenkeDAO.findAll().get(getraenkeDAO.findAll().size() - 1);
        Getraenke getraenkeAusDb = lastGetraenke;
        getraenkeAusDb.setPreis(999);
        getraenkeDAO.update(getraenkeAusDb);
        Getraenke getraenkeNeuAusDb = getraenkeDAO.findAll().get(getraenkeDAO.findAll().size() - 1);
        assertTrue(getraenkeAusDb.getPreis() == getraenkeNeuAusDb.getPreis());
        assertTrue(getraenkeAusDb.getBezeichnung().equals(getraenkeNeuAusDb.getBezeichnung()));
    }
}
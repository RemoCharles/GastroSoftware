package slgp.gastrosoftware.persister;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.persister.impl.EsswarenDAOImpl;
import slgp.gastrosoftware.persister.impl.GetraenkeDAOImpl;
import slgp.gastrosoftware.model.Esswaren;
import slgp.gastrosoftware.model.Getraenke;
import slgp.gastrosoftware.persister.util.Util;

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
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllGetraenke();
        Util.deleteAllEsswaren();
    }

    @After
    public void tearDown() throws Exception {

    }

    private void init() throws Exception {
        Util.createEsswarenListe();
        Util.createGetraenkeListe();
        logger.info("Initalisierung fertig!");
    }

    @Test
    public void testEsswarenSave() throws Exception {
        init();
        logger.info(esswarenDAO.findAll().size());
        assertTrue(esswarenDAO.findAll().size() == Util.INIT_SIZE_ESSWAREN);
    }

    @Test
    public void testEsswarenDelete() throws Exception {
        init();
        logger.info(esswarenDAO.findAll().size());
        assertTrue(esswarenDAO.findAll().size() == Util.INIT_SIZE_ESSWAREN);
        int size = esswarenDAO.findAll().size();
        Esswaren lastEssware = esswarenDAO.findAll().get(size - 1);
        esswarenDAO.delete(lastEssware);
        logger.info(esswarenDAO.findAll().size());
        for(Esswaren esswaren : esswarenDAO.findAll()){
            logger.info(esswaren);
        }
        assertTrue(esswarenDAO.findAll().size() == Util.INIT_SIZE_ESSWAREN - 1);
    }

    @Test
    public void testEsswarenUpdate() throws Exception {
        init();

        logger.info(esswarenDAO.findAll().size());
        assertTrue(esswarenDAO.findAll().size() == Util.INIT_SIZE_ESSWAREN);
        int size = esswarenDAO.findAll().size();
        Esswaren lastEsswaren = esswarenDAO.findAll().get(size - 1);

        esswarenDAO.update(lastEsswaren);
        Esswaren lastEsswarenNachUpdate = esswarenDAO.findAll().get(size - 1);
        assertTrue(lastEsswarenNachUpdate.getPreis() == lastEsswaren.getPreis());
    }

    @Test
    public void testGetraenkeSave() throws Exception {
        init();
        logger.info(getraenkeDAO.findAll().size());
        assertTrue(getraenkeDAO.findAll().size() == Util.INIT_SIZE_GETRAENKE);
    }

    @Test
    public void testGetraenkeDelete() throws Exception {
        init();
        logger.info(getraenkeDAO.findAll().size());
        assertTrue(getraenkeDAO.findAll().size() == Util.INIT_SIZE_GETRAENKE);
        int size = getraenkeDAO.findAll().size();
        Getraenke lastGetraenke = getraenkeDAO.findAll().get(size - 1);
        getraenkeDAO.delete(lastGetraenke);
        assertTrue(getraenkeDAO.findAll().size() == Util.INIT_SIZE_GETRAENKE - 1);

    }

    @Test
    public final void testGetraenkeUpdate() throws Exception {
        init();
        logger.info(getraenkeDAO.findAll().size());
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
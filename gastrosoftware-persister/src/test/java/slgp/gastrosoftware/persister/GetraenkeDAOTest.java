package slgp.gastrosoftware.persister;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.model.Getraenke;
import slgp.gastrosoftware.persister.impl.GetraenkeDAOImpl;
import slgp.gastrosoftware.persister.util.Util;

import static junit.framework.TestCase.assertTrue;

public class GetraenkeDAOTest {
    private static Logger logger = LogManager.getLogger(GetraenkeDAOTest.class);

    private static GetraenkeDAO getraenkeDAO = new GetraenkeDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Util.resetDb();
        Util.deleteAllBestellung();
        Util.deleteAllBestellPosition();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Util.deleteAllBestellung();
        Util.deleteAllBestellPosition();
        Util.deleteAllKonsumartikel();
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllGetraenke();
        Util.deleteAllBestellung();
        Util.deleteAllBestellPosition();
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        Util.createGetraenkeListe();
        logger.info("Initalisierung fertig!");
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
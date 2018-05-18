package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.Esswaren;
import ch.hslu.slgp.gastrosoftware.persister.impl.EsswarenDAOImpl;
import ch.hslu.slgp.gastrosoftware.persister.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import static junit.framework.TestCase.assertEquals;

public class EsswarenDAOTest {
    private static Logger logger = LogManager.getLogger(EsswarenDAOTest.class);

    private static EsswarenDAO esswarenDAO = new EsswarenDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Util.resetDb();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Util.deleteAllBestellung();
        Util.deleteAllBestellPosition();
        Util.deleteAllKonsumartikel();
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllKonsumartikel();
     }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        Util.createEsswarenListe();
        logger.info("Initalisierung fertig!");
    }

    @Test
    public void testEsswarenSave() throws Exception {
        init();
        logger.info(esswarenDAO.findAll().size());
        for (Esswaren esswaren : esswarenDAO.findAll()){
            logger.info(esswaren);
        }
        assertEquals(esswarenDAO.findAll().size(), Util.INIT_SIZE_ESSWAREN);


    }

    @Test
    public void testEsswarenDelete() throws Exception {
        init();
        logger.info(esswarenDAO.findAll().size());
        for (Esswaren esswaren : esswarenDAO.findAll()){
            logger.info(esswaren);
        }
        assertEquals(esswarenDAO.findAll().size(), Util.INIT_SIZE_ESSWAREN);
        int size = esswarenDAO.findAll().size();
        Esswaren lastEssware = esswarenDAO.findAll().get(size - 1);
        esswarenDAO.delete(lastEssware);
        logger.info(esswarenDAO.findAll().size());
        for (Esswaren esswaren : esswarenDAO.findAll()) {
            logger.info(esswaren);
        }
        assertEquals(esswarenDAO.findAll().size(), Util.INIT_SIZE_ESSWAREN - 1);
    }

    @Test
    public void testEsswarenUpdate() throws Exception {
        init();

        logger.info(esswarenDAO.findAll().size());
        for (Esswaren esswaren : esswarenDAO.findAll()){
            logger.info(esswaren);
        }
        assertEquals(esswarenDAO.findAll().size(), Util.INIT_SIZE_ESSWAREN);
        int size = esswarenDAO.findAll().size();
        Esswaren lastEsswaren = esswarenDAO.findAll().get(size - 1);

        esswarenDAO.update(lastEsswaren);
        Esswaren lastEsswarenNachUpdate = esswarenDAO.findAll().get(size - 1);
        assertEquals(lastEsswarenNachUpdate.getPreis(), lastEsswaren.getPreis(), 0.0);
    }
}

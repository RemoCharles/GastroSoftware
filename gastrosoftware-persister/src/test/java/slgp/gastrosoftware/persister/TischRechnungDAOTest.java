package slgp.gastrosoftware.persister;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.persister.impl.KonsumartikelDAOImpl;
import slgp.gastrosoftware.persister.impl.RechnungDAOImpl;
import slgp.gastrosoftware.persister.impl.TischRechnungDAOImpl;
import slgp.gastrosoftware.model.TischRechnung;
import slgp.gastrosoftware.persister.util.Util;

import static org.junit.Assert.assertTrue;

public class TischRechnungDAOTest {

    private static RechnungDAO rechnungDAO = new RechnungDAOImpl();
    private static TischRechnungDAO tischRechnungDAO = new TischRechnungDAOImpl();
    private static KonsumartikelDAO konsumartikelDAO = new KonsumartikelDAOImpl();

    private static Logger logger = LogManager.getLogger(PersonDAOTest.class);


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Util.resetDb();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        Util.deleteAllTischRechnung();
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllTischRechnung();
    }

    @After
    public void tearDown() throws Exception{
        Util.deleteAllTischRechnung();
    }

    public void init() throws Exception {
        Util.createTischRechnung();
    }

    @Test
    public void testTischrechnungSave() throws Exception{
        init();
        assertTrue(tischRechnungDAO.findAll().size() == Util.INIT_SIZE_TISCH_RECHNUNG);
        for(TischRechnung tr : tischRechnungDAO.findAll()){
            logger.info(tr);
        }
    }

    @Test
    public void testTischRechnungDelete() throws  Exception{
        init();
        assertTrue(tischRechnungDAO.findAll().size() == Util.INIT_SIZE_TISCH_RECHNUNG);
        int size = tischRechnungDAO.findAll().size();
        TischRechnung lastTischRechnung = tischRechnungDAO.findAll().get(size -1);
        tischRechnungDAO.delete(lastTischRechnung);
        assertTrue(tischRechnungDAO.findAll().size() == Util.INIT_SIZE_TISCH_RECHNUNG -1);
    }
}

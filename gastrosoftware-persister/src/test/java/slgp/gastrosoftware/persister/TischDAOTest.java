package slgp.gastrosoftware.persister;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.persister.impl.TischDAOImpl;
import slgp.gastrosoftware.persister.util.Util;

import static org.junit.Assert.assertTrue;

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
    }

}
package slgp.gastrosoftware.persister;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.model.MAAbrechnung;
import slgp.gastrosoftware.persister.impl.KonsumartikelDAOImpl;
import slgp.gastrosoftware.persister.impl.MAAbrechnungDAOImpl;
import slgp.gastrosoftware.persister.impl.RechnungDAOImpl;
import slgp.gastrosoftware.persister.impl.TischRechnungDAOImpl;
import slgp.gastrosoftware.persister.util.Util;

import static org.junit.Assert.assertTrue;

public class MAAbrechnungDAOTest {

    private static MAAbrechnungDAO maAbrechnungDAO = new MAAbrechnungDAOImpl();
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
        Util.deleteAllBestellung();
        Util.deleteAllBestellPosition();
        Util.deleteAllKonsumartikel();
        Util.deleteAllMAAbrechnung();
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllMAAbrechnung();
    }

    @After
    public void tearDown() throws Exception {
        Util.deleteAllMAAbrechnung();
    }

    public void init() throws Exception {
        Util.createMAAbrechnung();
    }

    @Test
    public void testMAAbrechnungSave() throws Exception {
        init();
       assertTrue(maAbrechnungDAO.findAll().size() == Util.INIT_SIZE_MAAbrechnung);
    }

    @Test
    public void testMAAbrechnungDelete() throws Exception {
        init();
        assertTrue(maAbrechnungDAO.findAll().size() == Util.INIT_SIZE_MAAbrechnung);
        int size = maAbrechnungDAO.findAll().size();
        MAAbrechnung lastMAAbrechnung = maAbrechnungDAO.findAll().get(size - 1);
        maAbrechnungDAO.delete(lastMAAbrechnung);
        assertTrue(maAbrechnungDAO.findAll().size() == Util.INIT_SIZE_MAAbrechnung - 1);
    }
}

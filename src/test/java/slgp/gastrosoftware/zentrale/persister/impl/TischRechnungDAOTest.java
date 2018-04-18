package slgp.gastrosoftware.zentrale.persister.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.KonsumartikelDAO;
import slgp.gastrosoftware.zentrale.persister.api.RechnungDAO;
import slgp.gastrosoftware.zentrale.persister.api.TischRechnungDAO;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.domain.TischRechnung;

import java.util.List;

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

    @Test
    public void testTischRechnungUpdate() throws Exception{
        init();
        assertTrue(tischRechnungDAO.findAll().size() == Util.INIT_SIZE_TISCH_RECHNUNG);
        int size = tischRechnungDAO.findAll().size();
        TischRechnung lastTischRechnung = tischRechnungDAO.findAll().get(size -1);
        List<Konsumartikel> konsumartikelListeDB;
        konsumartikelListeDB = lastTischRechnung.getBestellungList().get(0).getKonsumartikel();
        for (Konsumartikel k : konsumartikelListeDB){
            logger.info(k);
        }
        Konsumartikel neuerKonsumartikel = konsumartikelListeDB.get(0);
        neuerKonsumartikel.setBezeichnung("Test");
        konsumartikelDAO.update(neuerKonsumartikel);
        for (Konsumartikel k : konsumartikelListeDB){
            logger.info(k);
        }
    }
}

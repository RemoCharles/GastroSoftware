package slgp.gastrosoftware.persister;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.model.BestellPosition;
import slgp.gastrosoftware.persister.impl.BestellPositionDAOImpl;
import slgp.gastrosoftware.persister.util.Util;

import static org.junit.Assert.assertTrue;

public class BestellPositionDAOTest {
    private static Logger logger = LogManager.getLogger(BestellungDAOTest.class);

    private static BestellPositionDAO bestellPositionDAO = new BestellPositionDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Util.resetDb();

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        Util.deleteAllBestellPosition();
        Util.deleteAllKonsumartikel();
        Util.deleteAllBestellung();
        Util.deleteAllGetraenke();
        Util.deleteAllEsswaren();
        Util.deleteAllBestellung();
    }

    @After
    public void tearDown() throws Exception {
        Util.deleteAllBestellPosition();
        Util.deleteAllKonsumartikel();
        Util.deleteAllBestellung();
        Util.deleteAllGetraenke();
        Util.deleteAllEsswaren();
        Util.deleteAllBestellung();
    }

    private void init() throws Exception {
        Util.createBestellPosition();
        logger.info("Initalisierung fertig!");
    }

    @Test
    public void testBestellPositionSave() throws Exception {
        init();
        assertTrue(bestellPositionDAO.findAll().size() == 2);
    }

    @Test
    public void testBestellPositionDelete() throws Exception{
        init();
        assertTrue(bestellPositionDAO.findAll().size() == Util.INIT_SIZE_BESTELLPOSITION);
        int size = bestellPositionDAO.findAll().size();
        BestellPosition lastBestellPosition = bestellPositionDAO.findAll().get(size - 1);
        bestellPositionDAO.delete(lastBestellPosition);
        Assert.assertTrue(bestellPositionDAO.findAll().size() == Util.INIT_SIZE_BESTELLPOSITION - 1);

        for (BestellPosition g : bestellPositionDAO.findAll()) {
            logger.info(g);
        }
    }

    @Test
    public void testBestellPositionUpdate() throws Exception{
        init();
        assertTrue(bestellPositionDAO.findAll().size() == Util.INIT_SIZE_BESTELLPOSITION);
        BestellPosition lastBestellPosition = bestellPositionDAO.findAll().get(bestellPositionDAO.findAll().size() - 1);
        BestellPosition bestellPositionAusDb = lastBestellPosition;
        bestellPositionAusDb.setAnzahl(17);
        bestellPositionDAO.update(bestellPositionAusDb);
        BestellPosition bestellPositionNeuAusDb = bestellPositionDAO.findAll().get(bestellPositionDAO.findAll().size() - 1);
        assertTrue(bestellPositionAusDb.getAnzahl() == bestellPositionNeuAusDb.getAnzahl());
    }

}
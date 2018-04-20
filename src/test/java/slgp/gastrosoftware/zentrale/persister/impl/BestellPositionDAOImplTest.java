package slgp.gastrosoftware.zentrale.persister.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.BestellPositionDAO;
import slgp.gastrosoftware.zentrale.persister.api.BestellungDAO;
import slgp.gastrosoftware.zentrale.persister.domain.BestellPosition;
import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;

import static org.junit.Assert.*;

public class BestellPositionDAOImplTest {
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
    }

    @After
    public void tearDown() throws Exception {
        Util.deleteAllBestellPosition();
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
        assertTrue(bestellPositionDAO.findAll().size() == Util.INIT_SIZE_GETRAENKE);
        int size = bestellPositionDAO.findAll().size();
        BestellPosition lastBestellPosition = bestellPositionDAO.findAll().get(size - 1);
        bestellPositionDAO.delete(lastBestellPosition);
        Assert.assertTrue(bestellPositionDAO.findAll().size() == Util.INIT_SIZE_GETRAENKE - 1);

        for (BestellPosition g : bestellPositionDAO.findAll()) {
            logger.info(g);
        }
    }

    @Test
    public void testBestellPositionUpdate() throws Exception{
        init();
        assertTrue(bestellPositionDAO.findAll().size() == Util.INIT_SIZE_GETRAENKE);
        BestellPosition lastBestellPosition = bestellPositionDAO.findAll().get(bestellPositionDAO.findAll().size() - 1);
        BestellPosition bestellPositionAusDb = lastBestellPosition;
        bestellPositionAusDb.setAnzahl(17);
        bestellPositionDAO.update(bestellPositionAusDb);
        BestellPosition bestellPositionNeuAusDb = bestellPositionDAO.findAll().get(bestellPositionDAO.findAll().size() - 1);
        assertTrue(bestellPositionAusDb.getAnzahl() == bestellPositionNeuAusDb.getAnzahl());
    }

}
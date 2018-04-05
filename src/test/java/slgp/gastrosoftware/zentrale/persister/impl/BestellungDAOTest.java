package slgp.gastrosoftware.zentrale.persister.impl;

import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.Util.Util;
import slgp.gastrosoftware.zentrale.persister.api.BestellungDAO;
import slgp.gastrosoftware.zentrale.persister.impl.BestellungDAOImpl;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertTrue;

public class BestellungDAOTest {

    private static BestellungDAO pBestellungDAO = (BestellungDAO) new BestellungDAOImpl();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        Util.resetDb();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    private void init() throws Exception {
        Util.createBestellungListe();
        Util.createEsswarenListe();
        Util.createGetraenkeListe();
        Util.createKonsumartikelListe();
        Util.createMitarbeiter();
        Util.createTagesmenuListe();
        Util.createTisch();
        Util.createTischRechnung();
    }


    @Test
    public final void testFindAll() throws Exception {

        init();
        assertTrue(pBestellungDAO.findAll().size() == Util.INIT_SIZE_BESTELLUNG_LISTE);
    }

}

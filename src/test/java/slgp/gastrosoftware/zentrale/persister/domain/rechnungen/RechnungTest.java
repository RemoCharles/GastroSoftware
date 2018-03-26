package slgp.gastrosoftware.zentrale.persister.domain.rechnungen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;
import slgp.gastrosoftware.zentrale.persister.domain.Tisch;
import slgp.gastrosoftware.zentrale.persister.domain.artikel.Getraenke;
import slgp.gastrosoftware.zentrale.persister.domain.artikel.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.domain.menu.KonsumartikelTest;
import slgp.gastrosoftware.zentrale.persister.domain.personen.Adresse;
import slgp.gastrosoftware.zentrale.persister.domain.personen.Kontakt;
import slgp.gastrosoftware.zentrale.persister.domain.personen.Mitarbeiter;
import slgp.gastrosoftware.zentrale.persister.impl.Util;
import slgp.gastrosoftware.zentrale.persister.util.DbHelper;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class RechnungTest {

    private static Logger logger = LogManager.getLogger(KonsumartikelTest.class);
    private static List<Konsumartikel> konsumartikelList;
    private static List<Getraenke> getraenke;
    private static List<Konsumartikel> konsumliste;
    private static Mitarbeiter ma;
    private static Tisch tisch;
    private static Kontakt kontakt;
    private static Adresse adr;
    private static TischRechnung tischRechnung;
    private static List<Bestellung> bestellungListe;

    //public Bestellung(Mitarbeiter mitarbeiter, Tisch tisch, List<Konsumartikel> konsumartikel, boolean zubereitet, Date datum) {
    //public TischRechnung(Date datum, String nameRestaunt, List<Bestellung> bestellungList)
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        konsumliste = Util.createKonsumartikelListe();
        tisch = Util.createTisch();
        kontakt = Util.createKontakt();
        adr = Util.createAdresse();
        ma = Util.createMitarbeiter(adr, kontakt);
        bestellungListe = Util.createBestellungListe(ma, tisch, konsumliste);

        tischRechnung = Util.createTischRechnung(new Date(), "TestResti", bestellungListe);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        DbHelper.deleteEsswaren();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRechnungspeichern() {
        EntityManager em = JpaUtil.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(kontakt);
            em.persist(adr);
            em.persist(ma);
            em.persist(tisch);
            em.persist(bestellungListe);
            em.persist(konsumliste);

            em.persist(tischRechnung);

            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("TischRechnung konnte nicht erfasst werden!", e);

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
        em = JpaUtil.createEntityManager();
        List<TischRechnung> tischRechnungList = em.createQuery("SELECT a FROM TischRechnung a ", TischRechnung.class).getResultList();
        for (TischRechnung k : tischRechnungList) {
            logger.info(k);
        }

        em.close();
    }


}
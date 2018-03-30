package slgp.gastrosoftware.zentrale.persister.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.domain.menu.KonsumartikelTest;
import slgp.gastrosoftware.zentrale.persister.impl.Util;
import slgp.gastrosoftware.zentrale.persister.util.DbHelper;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class BestellungTest {
    private static Logger logger = LogManager.getLogger(KonsumartikelTest.class);
    private static List<Esswaren> esswaren;
    private static List<Getraenke> getraenke;
    private static List<Konsumartikel> konsumartikel;
    private static List<Konsumartikel> konsumartikelFuerSpeichernTest;
    private static Mitarbeiter ma;
    private static Tisch tisch;
    private static Kontakt kontakt;
    private static Adresse adr;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        esswaren = Util.createEsswarenListe();
        getraenke = Util.createGetraenkeListe();
        konsumartikel = Util.createKonsumartikelListe();

        konsumartikelFuerSpeichernTest = Util.createKonsumartikelListe();
        tisch = Util.createTisch();
        kontakt = Util.createKontakt();
        adr = Util.createAdresse();
        ma = Util.createMitarbeiter(adr, kontakt);
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

    // public Bestellung(Mitarbeiter mitarbeiter, Tisch tisch, List<Konsumartikel> konsumartikel, boolean zubereitet, Date datum)
    @Test
    public void testKonsumartikelListeSpeichern() {
        EntityManager em = JpaUtil.createEntityManager();
        em.getTransaction().begin();
        try {
            for (Konsumartikel k : konsumartikel) {
                em.persist(k);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Bestellung konnte nicht erfasst werden!", e);

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
        em = JpaUtil.createEntityManager();
        List<Konsumartikel> konsumartikelList = em.createQuery("SELECT a FROM Konsumartikel a ORDER BY a.preis", Konsumartikel.class).getResultList();
        for (Konsumartikel k : konsumartikelList) {
            logger.info(k);
        }

        em.close();
    }

    @Test
    public void testBestellungSpeichern() {
        EntityManager em = JpaUtil.createEntityManager();
        em.getTransaction().begin();

        try {

            em.persist(tisch);
            em.persist(adr);
            em.persist(kontakt);
            em.persist(ma);
            for (Konsumartikel k : konsumartikelFuerSpeichernTest) {
                em.persist(k);
            }
            Bestellung best = new Bestellung(ma, tisch, konsumartikelFuerSpeichernTest, false, LocalDate.now());
            em.persist(best);

            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Bestellung konnte nicht erfasst werden!", e);

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
        em = JpaUtil.createEntityManager();
        List<Bestellung> bestellungList = em.createQuery("SELECT a FROM Bestellung a ORDER BY a.datum", Bestellung.class).getResultList();
        for (Bestellung best : bestellungList) {
            logger.info(best);
        }

        em.close();
    }


}
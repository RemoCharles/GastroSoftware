//package slgp.gastrosoftware.zentrale.persister.domain.rechnungen;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.*;
//import slgp.gastrosoftware.zentrale.persister.domain.*;
//import slgp.gastrosoftware.zentrale.persister.domain.menu.KonsumartikelTest;
//import slgp.gastrosoftware.zentrale.persister.Util.Util;
//import slgp.gastrosoftware.zentrale.persister.util.DbHelper;
//import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;
//
//import javax.persistence.EntityManager;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import java.time.LocalDate;
//import java.util.List;
//
//public class RechnungTest {
//
//    private static Logger logger = LogManager.getLogger(KonsumartikelTest.class);
//    private static List<Konsumartikel> konsumartikelList;
//    @OneToMany
//    private static List<Getraenke> getraenke;
//    @OneToMany
//    private static List<Konsumartikel> konsumliste;
//    @OneToOne
//    private static Mitarbeiter ma;
//    @OneToOne
//    private static Tisch tisch;
//    @OneToOne
//    private static Kontakt kontakt;
//    @OneToOne
//    private static Adresse adr;
//    @OneToOne
//    private static TischRechnung tischRechnung;
//    @OneToMany
//    private static List<Bestellung> bestellungListe;
//
//    @BeforeClass
//    public static void setUpBeforeClass() throws Exception {
//
//        konsumliste = Util.createKonsumartikelListe();
//        tisch = Util.createTisch();
//        kontakt = Util.createKontakt();
//        adr = Util.createAdresse();
//        ma = Util.createMitarbeiter(adr, kontakt);
//        bestellungListe = Util.createBestellungListe(ma, tisch, konsumliste);
//
//        tischRechnung = Util.createTischRechnung(LocalDate.now(), "TestResti", bestellungListe);
//    }
//
//    @AfterClass
//    public static void tearDownAfterClass() throws Exception {
//        DbHelper.deleteEsswaren();
//    }
//
//    @Before
//    public void setUp() throws Exception {
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//    }
//
//    @Test
//    public void testRechnungspeichern() {
//        tischRechnung = Util.createTischRechnung(LocalDate.now(), "TestResti", bestellungListe);
//        EntityManager em = JpaUtil.createEntityManager();
//        em.getTransaction().begin();
//        try {
//            em.persist(kontakt);
//            em.persist(adr);
//            em.persist(ma);
//            em.persist(tisch);
//
//            for(Bestellung bestellung: bestellungListe) {
//                em.persist(bestellung);
//            }
//            for(Konsumartikel konsumartikel : konsumliste) {
//                em.persist(konsumartikel);
//            }
//
//            em.persist(tischRechnung);
//
//            em.getTransaction().commit();
//        } catch (Exception e) {
//            logger.error("TischRechnung konnte nicht erfasst werden!", e);
//
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//        } finally {
//            if (em.isOpen()) {
//                em.close();
//            }
//        }
//        em = JpaUtil.createEntityManager();
//        List<TischRechnung> tischRechnungList = em.createQuery("SELECT a FROM TischRechnung a  ORDER BY a.datum", TischRechnung.class).getResultList();
//        for (TischRechnung k : tischRechnungList) {
//            logger.info(k);
//        }
//
//        em.close();
//    }
//
//
//}
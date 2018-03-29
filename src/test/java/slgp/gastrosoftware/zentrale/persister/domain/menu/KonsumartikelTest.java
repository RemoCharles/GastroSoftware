package slgp.gastrosoftware.zentrale.persister.domain.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.domain.artikel.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.artikel.Getraenke;
import slgp.gastrosoftware.zentrale.persister.impl.Util;
import slgp.gastrosoftware.zentrale.persister.util.DbHelper;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class KonsumartikelTest {
    private static Logger logger = LogManager.getLogger(KonsumartikelTest.class);
    private static List<Esswaren> esswaren;
    private static List<Getraenke> getraenke;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        esswaren = Util.createEsswarenListe();
        getraenke = Util.createGetraenkeListe();
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
    public void testEsswarenSpeichern() {
        EntityManager em = JpaUtil.createEntityManager();
        em.getTransaction().begin();

        try {
            for (Esswaren a : esswaren) {
                em.persist(a);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Essware konnte nicht erfasst werden!", e);

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
        em = JpaUtil.createEntityManager();
        List<Esswaren> esswarenList = em.createQuery("SELECT a FROM Esswaren a ORDER BY a.kategorie", Esswaren.class).getResultList();
        assertTrue(esswarenList.size() == esswaren.size());
        for (Esswaren e : esswarenList) {
            logger.info(e);
        }
        em.close();
    }

    @Test
    public void testGetraenkeSpeichern() {
        EntityManager em = JpaUtil.createEntityManager();
        em.getTransaction().begin();

        try {
            for (Getraenke a : getraenke) {
                em.persist(a);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Getraenk konnte nicht erfasst werden!", e);

            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
        em = JpaUtil.createEntityManager();
        List<Getraenke> getraenkeList = em.createQuery("SELECT a FROM Getraenke a ORDER BY a.preis", Getraenke.class).getResultList();
        assertTrue(getraenkeList.size() == getraenke.size());
        for (Getraenke g : getraenkeList) {
            logger.info(g);
        }

        em.close();
    }

//    @Test
//    public void testGetraenkLoeschen() {
//
//        String suchBegriff = "Cola";
//
//        EntityManager em = JpaUtil.createEntityManager();
//        em.getTransaction().begin();
//        for (Getraenke g : getraenke) {
//            em.persist(g);
//
//        }
//        em.getTransaction().commit();
//
//        try {
//            for (int i = 0; i < getraenke.size(); ++i) {
//                if (getraenke.get(i).getBezeichnung().equals(suchBegriff)) {
//                    logger.info("Getraenk wurde gefunden an Stelle: " + getraenke.get(i).getId());
//                    Getraenke getrankVonDb = em.find(Getraenke.class, getraenke.get(i).getId());
//                    em.remove(getrankVonDb);
//                    em.getTransaction().commit();
//                }
//            }
//
//        } catch (Exception e) {
//            logger.error("Getraenk konnte nicht geloescht werden" + e);
//
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//
//        } finally {
//            if (em.isOpen()) {
//                em.close();
//            }
//        }
//
//        em = JpaUtil.createEntityManager();
//        List<Getraenke> getraenkeListe = em.createQuery("SELECT a FROM Getraenke a ORDER BY a.id", Getraenke.class).getResultList();
//        //Assert.assertTrue(getraenkeListe.size() == 2);
//        for (Getraenke g : getraenke) {
//            logger.info(g);
//        }
//        em.close();
//
//    }
}
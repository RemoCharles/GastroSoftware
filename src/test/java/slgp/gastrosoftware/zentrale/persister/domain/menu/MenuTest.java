package slgp.gastrosoftware.zentrale.persister.domain.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.domain.Tagesmenu;
import slgp.gastrosoftware.zentrale.persister.impl.Util;
import slgp.gastrosoftware.zentrale.persister.util.DbHelper;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import javax.persistence.EntityManager;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class MenuTest {
    private static Logger logger = LogManager.getLogger(KonsumartikelTest.class);
    private static List<Esswaren> esswaren;
    private static List<Getraenke> getraenke;
    private static List<Tagesmenu> tagesmenu;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        esswaren = Util.createEsswarenListe();
        getraenke = Util.createGetraenkeListe();
        tagesmenu = Util.createTagesmenuListe();
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
    public void menuErstellenTest() throws Exception {
        EntityManager em = JpaUtil.createEntityManager();
        em.getTransaction().begin();

        try {
            for(Esswaren e : esswaren) {
                em.persist(e);
            }

            Tagesmenu tm = new Tagesmenu("Montag", esswaren);
                em.persist(tm);


            em.getTransaction().commit();
        } catch (Exception e) {
            logger.error("Menu konnte nicht erfasst werden!", e);
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em.isOpen()) {
                em.close();
            }
        }
        em = JpaUtil.createEntityManager();
        List<Tagesmenu> tagesmenuList = em.createQuery("SELECT a FROM Tagesmenu a ORDER BY a.wochenTag", Tagesmenu.class).getResultList();
        assertTrue(tagesmenuList.size() == tagesmenu.size());
        for (Tagesmenu tm : tagesmenuList) {
            logger.info(tm);
        }

        em.close();
    }
}
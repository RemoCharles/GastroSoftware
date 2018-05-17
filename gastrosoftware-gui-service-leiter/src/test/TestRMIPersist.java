import slgp.gastrosoftware.gui.Context;
import slgp.gastrosoftware.model.Konsumartikel;

public class TestRMIPersist {
    private static RMIPersonService personService = Context.getInstance().getPersonService();
    private static RMIBestellService bestellService = Context.getInstance().getBestellService();
    private static RMIRechnungService rechnungService = Context.getInstance().getRechnungService();
    private static RMIKonsumartikelService konsumartikelService = Context.getInstance().getKonsumartikelService();
    private static RMIMenuService menuService = Context.getInstance().getMenuService();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception{
    }

    @Before
    public void setUp() throws Exception{
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSaveKonsumartikel() throws Exception{
        Konsumartikel konsumartikel = new Konsumartikel("Banana", "Demo", 13);
        konsumartikelService.konsumartikelHinzufuegen(konsumartikel);
    }

}

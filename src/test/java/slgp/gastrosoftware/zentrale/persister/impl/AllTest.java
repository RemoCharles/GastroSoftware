package slgp.gastrosoftware.zentrale.persister.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.domain.Kontakt;
import slgp.gastrosoftware.zentrale.persister.domain.MAAbrechnung;
import slgp.gastrosoftware.zentrale.persister.domain.TischRechnung;

@RunWith(Suite.class)
@Suite.SuiteClasses({BestellungDAOTest.class, PersonDAOTest.class, LoginDAOTest.class, KontaktDAOTest.class, KonsumartikelDAOTest.class, AdresseDAOTest.class, TischRechnungDAOTest.class, MAAbrechnungDAOTest.class})

public class AllTest {

}




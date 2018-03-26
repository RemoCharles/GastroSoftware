package PersonTest;

import java.util.ArrayList;
import java.util.List;

import slgp.gastrosoftware.zentrale.persister.domain.Adresse;
import slgp.gastrosoftware.zentrale.persister.domain.Kontakt;
import slgp.gastrosoftware.zentrale.persister.domain.Login;
import slgp.gastrosoftware.zentrale.persister.domain.Person;

public class DbHelper {
	
	public static List <Person> erstellePersonenListe(){
		
		List<Person> liste = new ArrayList<Person>();
		
		Person persA = new Person("Meier", "Marco", new Adresse("Kusterweg 4", 6004, "Luzern"), new Kontakt("mmarco@gmx.ch", "041 234 56 67"));
		Person persB = new Person("Münch", "Jana", new Adresse("Müllweg 8", 6008, "Luzern"), new Kontakt("mjana@gmx.ch", "041 234 56 67"), new Login("mjana", "abcde"));
		Person persC = new Person("Mohn", "Kevin", new Adresse("Hohlweg 8", 6003, "Luzern"), new Kontakt("mkevin@gmx.ch", "078 435 66 88"));
		
		liste.add(persA);
		liste.add(persB);
		liste.add(persC);
		
		return liste;
		
	}
	
	

}

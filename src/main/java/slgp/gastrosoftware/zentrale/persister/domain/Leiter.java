package slgp.gastrosoftware.zentrale.persister.domain;


import javax.persistence.Entity;

@Entity
public class Leiter extends Person {
	
	
	public Leiter(String name, String vorname, Adresse adresse, Kontakt kontakt) {
		super(name, vorname, adresse, kontakt);
	}

	public Leiter() {
	}

	
	@Override
	public String toString() {
		return super.toString();
	}

}

package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Leiter extends Person{
	
	@Id
	@GeneratedValue
	private int id;
	
	
	public Leiter(String name, String vorname, Adresse adresse, Kontakt kontakt) {
		super(name, vorname, adresse, kontakt);
	}
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}

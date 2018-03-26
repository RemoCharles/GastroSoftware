package slgp.gastrosoftware.zentrale.persister.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mitarbeiter extends Person implements Serializable{
	
	
	@Id
	@GeneratedValue
	private int id;
	public Mitarbeiter(String name, String vorname, Adresse adresse, Kontakt kontakt) {
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

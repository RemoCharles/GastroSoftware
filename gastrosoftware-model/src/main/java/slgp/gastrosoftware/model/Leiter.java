package slgp.gastrosoftware.model;


import javax.persistence.Entity;

@Entity
public class Leiter extends Person {


	private static final long serialVersionUID = -77627763998384155L;

	public Leiter(String name, String vorname, String funktion, Adresse adresse, Kontakt kontakt, Login login) {
		super(name, vorname, funktion, adresse, kontakt, login);
	}
	
	public Leiter(String name, String vorname, String funktion, Adresse adresse, Kontakt kontakt) {
		super(name, vorname, funktion, adresse, kontakt);
	}

	public Leiter() {
	}

	
	@Override
	public String toString() {
		return super.toString();
	}

}

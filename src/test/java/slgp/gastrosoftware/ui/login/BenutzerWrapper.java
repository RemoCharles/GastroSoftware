package slgp.gastrosoftware.ui.login;

import slgp.gastrosoftware.zentrale.persister.domain.Person;

public class BenutzerWrapper {
	
	private int nummer;
	private Person person;
	
	public BenutzerWrapper (int nummer ,Person person) {
		this.nummer = nummer;
		this.person = person;
	}
	
	public String getName() {
		return person.getName();
	}
	
	public String getVorname() {
		return person.getVorname();
	}
	
	public String getStrasse() {
		return person.getAdresse().getStrasse();
	}
	
	public int getPlz() {
		return person.getAdresse().getPlz();
	}
	
	public String getOrt() {
		return person.getAdresse().getOrt();
	}
	
	public String getEmail() {
		return person.getKontakt().getEmail();
	}
	
	public String getTelefon() {
		return person.getKontakt().getTelefon();
	}
	
	public String getUsername() {
		return person.getLogin().getUsername();
	}
	
	public String getPasswort() {
		return person.getLogin().getPasswort();
	}
	
	public String getFunktion() {
		return person.getFunktion();
	}
	
	public int getNummer() {
		return nummer;
	}
	
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}

}

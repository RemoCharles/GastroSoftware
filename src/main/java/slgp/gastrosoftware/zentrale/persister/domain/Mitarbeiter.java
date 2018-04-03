package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.Entity;

@Entity
public class Mitarbeiter extends Person {


    public Mitarbeiter(String name, String vorname, String funktion, Adresse adresse, Kontakt kontakt, Login login) {
        super(name, vorname, funktion, adresse, kontakt, login);
    }
    
    public Mitarbeiter(String name, String vorname, String funktion, Adresse adresse, Kontakt kontakt) {
    	super(name, vorname, funktion, adresse, kontakt);
    }

    public Mitarbeiter() {
    }



}
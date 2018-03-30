package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.Entity;

@Entity
public class Mitarbeiter extends Person {


    public Mitarbeiter(String name, String vorname, Adresse adresse, Kontakt kontakt) {
        super(name, vorname, adresse, kontakt);
    }

    public Mitarbeiter() {
    }



}
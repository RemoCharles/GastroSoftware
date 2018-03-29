package slgp.gastrosoftware.zentrale.persister.domain.personen;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mitarbeiter extends Person {


    public Mitarbeiter(String name, String vorname, Adresse adresse, Kontakt kontakt) {
        super(name, vorname, adresse, kontakt);
    }

    public Mitarbeiter() {
    }



}
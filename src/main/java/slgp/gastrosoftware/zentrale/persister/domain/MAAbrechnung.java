package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class MAAbrechnung extends Rechnung {

    public MAAbrechnung() {
    }

    public MAAbrechnung(LocalDate datum, String nameRestaunt, String test){
        super(datum, nameRestaunt);
    }
}

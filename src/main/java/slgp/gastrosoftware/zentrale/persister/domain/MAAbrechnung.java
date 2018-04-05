package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.time.LocalDate;

@Entity
@NamedQueries({
        @NamedQuery(name = "MAAbrechnung.findAll", query = "SELECT e FROM MAAbrechnung e")})
public class MAAbrechnung extends Rechnung {

    public MAAbrechnung() {
    }

    public MAAbrechnung(LocalDate datum, String nameRestaunt, String test){
        super(datum, nameRestaunt);
    }
}

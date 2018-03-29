package slgp.gastrosoftware.zentrale.persister.domain.rechnungen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class MAAbrechnung extends Rechnung {

    public MAAbrechnung() {
    }

    public MAAbrechnung(Date datum, String nameRestaunt, String test){
        super(datum, nameRestaunt);
    }
}

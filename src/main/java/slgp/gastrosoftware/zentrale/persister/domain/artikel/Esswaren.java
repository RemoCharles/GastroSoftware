package slgp.gastrosoftware.zentrale.persister.domain.artikel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Esswaren extends Konsumartikel implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue
    private int id;

    public Esswaren() {
        super();
    }

    public Esswaren(String bezeichnung, String kategorie, double preis) {
        super(bezeichnung, kategorie, preis);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

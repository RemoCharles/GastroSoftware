package slgp.gastrosoftware.zentrale.persister.domain.artikel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Getraenke extends Konsumartikel{

    public Getraenke(){
    }

    public Getraenke(String bezeichnung, String kategorie, double preis) {
        super(bezeichnung, kategorie, preis);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

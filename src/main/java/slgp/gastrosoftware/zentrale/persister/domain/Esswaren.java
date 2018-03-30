package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.*;

@Entity
public class Esswaren extends Konsumartikel{

    public Esswaren() {
    }

    public Esswaren(String bezeichnung, String kategorie, double preis) {
        super(bezeichnung, kategorie, preis);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

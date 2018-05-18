package ch.hslu.slgp.gastrosoftware.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
        @NamedQuery(name = "Esswaren.findAll", query = "SELECT e FROM Esswaren e")})
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

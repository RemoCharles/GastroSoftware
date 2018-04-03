package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Getraenke.findAll", query = "SELECT e FROM Getraenke e")})
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

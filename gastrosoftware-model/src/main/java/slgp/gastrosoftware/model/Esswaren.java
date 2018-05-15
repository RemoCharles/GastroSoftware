package slgp.gastrosoftware.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Esswaren.findAll", query = "SELECT e FROM Esswaren e")})
public class Esswaren extends Konsumartikel{

    private static final long serialVersionUID = 7396730561410576340L;

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

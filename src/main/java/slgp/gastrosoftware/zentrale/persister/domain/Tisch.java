package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
       @NamedQuery(name = "Tisch.findAll", query = "SELECT e FROM Tisch e")})
public class Tisch implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private int anzahlSitzplaetze;

    public Tisch () {

    }

    public Tisch(int anzahlSitzplaetze) {
        this.anzahlSitzplaetze = anzahlSitzplaetze;
    }

    public int getAnzahlSitzplaetze() {
        return anzahlSitzplaetze;
    }

    public void setAnzahlSitzplaetze(int anzahlSitzplaetze) {
        this.anzahlSitzplaetze = anzahlSitzplaetze;
    }

    @Override
    public String toString() {
        return "Tisch{" +
                " AnzahlSitzplaetze=" + anzahlSitzplaetze +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tisch tisch = (Tisch) o;
        return anzahlSitzplaetze == tisch.anzahlSitzplaetze;
    }

    @Override
    public int hashCode() {

        return Objects.hash(anzahlSitzplaetze);
    }
}

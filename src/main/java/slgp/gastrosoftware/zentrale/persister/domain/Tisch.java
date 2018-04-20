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
    private int tischNummer;


    public Tisch () {

    }

    public Tisch(int anzahlSitzplaetze) {
        this.tischNummer = anzahlSitzplaetze;
    }

    public int getTischNummer() {
        return tischNummer;
    }

    public void setTischNummer(int anzahlSitzplaetze) {
        this.tischNummer = anzahlSitzplaetze;
    }

    @Override
    public String toString() {
        return "Tisch{" +
                " Tischnummer=" + tischNummer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tisch tisch = (Tisch) o;
        return tischNummer == tisch.tischNummer;
    }

    @Override
    public int hashCode() {

        return Objects.hash(tischNummer);
    }
}

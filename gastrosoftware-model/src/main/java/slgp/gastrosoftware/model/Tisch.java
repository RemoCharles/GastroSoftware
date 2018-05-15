package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Tisch.findAll", query = "SELECT e FROM Tisch e"),
        @NamedQuery(name = "Tisch.findByTischNummer", query = "SELECT e FROM Tisch e WHERE e.tischNummer=:tischNummer")})

public class Tisch implements Serializable {
    private static final long serialVersionUID = 5974664788311293565L;
    @Id
    @GeneratedValue
    private int id;
    //TODO: @Column(unique = true)
    private int tischNummer;


    public Tisch() {

    }

    public int getId() {
        return id;
    }

    public Tisch(int tischNummer) {
        this.tischNummer = tischNummer;
    }

    public int getTischNummer() {
        return tischNummer;
    }

    public void setTischNummer(int tischNummer) {
        this.tischNummer = tischNummer;
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

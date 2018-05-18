package ch.hslu.slgp.gastrosoftware.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Tisch.findAll", query = "SELECT e FROM Tisch e"),
        @NamedQuery(name = "Tisch.findByTischNummer", query = "SELECT e FROM Tisch e WHERE e.tischNummer=:tischNummer")})

public class Tisch implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private int tischNummer;
    private boolean verfuegbarkeit = true;


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

    public boolean getVerfuegbarkeit() { return verfuegbarkeit; }

    public void setVerfuegbarkeit(boolean verfuegbarkeit) { this.verfuegbarkeit = verfuegbarkeit; }

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

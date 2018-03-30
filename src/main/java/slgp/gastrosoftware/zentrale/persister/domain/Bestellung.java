package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Bestellung implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Mitarbeiter mitarbeiter;
    @ManyToOne
    private Tisch tisch;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Konsumartikel> konsumartikel;
    private boolean zubereitet;
//    @Temporal(TemporalType.DATE)
    private LocalDate datum;

    public Bestellung(Mitarbeiter mitarbeiter, Tisch tisch, List<Konsumartikel> konsumartikel, boolean zubereitet, LocalDate datum) {
        this.mitarbeiter = mitarbeiter;
        this.tisch = tisch;
        this.konsumartikel = konsumartikel;
        this.zubereitet = zubereitet;
        this.datum = datum;
    }

    public Bestellung() {
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public Tisch getTisch() {
        return tisch;
    }

    public void setTisch(Tisch tisch) {
        this.tisch = tisch;
    }

    public List<Konsumartikel> getKonsumartikel() {
        return konsumartikel;
    }

    public void setKonsumartikel(List<Konsumartikel> konsumartikel) {
        this.konsumartikel = konsumartikel;
    }

    public boolean isZubereitet() {
        return zubereitet;
    }

    public void setZubereitet(boolean zubereitet) {
        this.zubereitet = zubereitet;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                ", mitarbeiter=" + mitarbeiter +
                ", tisch=" + tisch +
                ", konsumartikel=" + konsumartikel +
                ", zubereitet=" + zubereitet +
                ", datum=" + datum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bestellung that = (Bestellung) o;
        return zubereitet == that.zubereitet &&
                Objects.equals(mitarbeiter, that.mitarbeiter) &&
                Objects.equals(tisch, that.tisch) &&
                Objects.equals(konsumartikel, that.konsumartikel) &&
                Objects.equals(datum, that.datum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mitarbeiter, tisch, konsumartikel, zubereitet, datum);
    }
}

package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Bestellung.findByDatum", query = "SELECT e FROM Bestellung e WHERE e.datum=:datum"),
        @NamedQuery(name = "Bestellung.findAll", query = "SELECT e FROM Bestellung e"),
        @NamedQuery(name = "Bestellung.findBezahlt", query = "SELECT e from Bestellung e WHERE e.bezahlt=:bezahlt"),
        @NamedQuery(name = "Bestellung.findByTischNummer", query = "SELECT e FROM Bestellung e WHERE e.tisch.tischNummer=:tischNummer")})

public class Bestellung implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    private Mitarbeiter mitarbeiter;
    @ManyToOne
    private Tisch tisch;
    @OneToMany(fetch = FetchType.EAGER)
    private List<BestellPosition> bestellPositionList;
    private boolean zubereitet;
    private LocalDate datum;
    private boolean bezahlt;
    private double summeBestellPositionList;

    public Bestellung(Mitarbeiter mitarbeiter, Tisch tisch, List<BestellPosition> bestellPositionList, boolean zubereitet, boolean bezahlt, LocalDate datum) {
        this.mitarbeiter = mitarbeiter;
        this.tisch = tisch;
        this.bestellPositionList = bestellPositionList;
        this.zubereitet = zubereitet;
        this.datum = datum;
        this.bezahlt = bezahlt;
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

    public List<BestellPosition> getKonsumartikel() {
        return bestellPositionList;
    }

    //Rückgabe für DB
    public int getAnzahlKonsumartikel (){
        int anz = getKonsumartikel().size();
        return anz;
    }

    public double getSummebestellPositionList(){
       for (BestellPosition b : bestellPositionList){
           summeBestellPositionList = b.getBetrag();
       }

       return summeBestellPositionList;
    }

    public double getSummePreisKonsumartikel (){
        List <BestellPosition> tempListKonsumartikel = getKonsumartikel();

        double summeKonsumartikel = 0;

        for (BestellPosition b : tempListKonsumartikel){
            summeKonsumartikel = b.getKonsumartikel().getPreis();
        }
        return summeKonsumartikel;
    }


    public void setKonsumartikel(List<BestellPosition> bestellPositionList) {
        this.bestellPositionList = bestellPositionList;
    }

    public boolean getZubereitet() {
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

    public boolean getBezahlt() {
        return bezahlt;
    }

    public void setBezahlt(boolean bezahlt) {
        this.bezahlt = bezahlt;
    }

    @Override
    public String toString() {
        return "Bestellung{" +
                ", mitarbeiter=" + mitarbeiter +
                ", tisch=" + tisch +
                ", BestellPositionList=" + bestellPositionList +
                ", zubereitet=" + zubereitet +
                ", datum=" + datum +
                ", bezahlt=" + bezahlt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bestellung that = (Bestellung) o;
        return zubereitet == that.zubereitet && bezahlt == that.bezahlt &&
                Objects.equals(mitarbeiter, that.mitarbeiter) &&
                Objects.equals(tisch, that.tisch) &&
                Objects.equals(bestellPositionList, that.bestellPositionList) &&
                Objects.equals(datum, that.datum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mitarbeiter, tisch, bestellPositionList, zubereitet, datum, bezahlt);
    }
}

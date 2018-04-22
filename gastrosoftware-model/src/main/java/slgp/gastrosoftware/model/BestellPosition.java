package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "BestellPosition.findAll", query = "SELECT e FROM BestellPosition e")
})

public class BestellPosition implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @OneToOne
    private Konsumartikel konsumartikel;
    private int anzahl;

    public BestellPosition() {
    }

    public BestellPosition(Konsumartikel konsumartikel, int anzahl) {
        this.konsumartikel = konsumartikel;
        this.anzahl = anzahl;
    }

    public BestellPosition(Konsumartikel konsumartikel) {
        this.konsumartikel = konsumartikel;
        anzahl = 0;
    }

    public String getBezeichnung(){
        return konsumartikel.getBezeichnung();
    }

    public double getPreis(){
        return konsumartikel.getPreis();
    }

    public String getKategorie(){
        return konsumartikel.getKategorie();
    }

    public Konsumartikel getKonsumartikel() {
        return konsumartikel;
    }

    public void setKonsumartikel(Konsumartikel konsumartikel) {
        this.konsumartikel = konsumartikel;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BestellPosition that = (BestellPosition) o;
        return anzahl == that.anzahl &&
                Objects.equals(konsumartikel, that.konsumartikel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(konsumartikel, anzahl);
    }

    @Override
    public String toString() {
        return "BestellPosition{" +
                "Konsumartikel=" + konsumartikel +
                ", anzahl=" + anzahl +
                '}';
    }
}

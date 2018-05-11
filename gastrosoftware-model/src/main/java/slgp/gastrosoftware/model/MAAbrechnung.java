package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "MAAbrechnung.findAll", query = "SELECT e FROM MAAbrechnung e")})
public class MAAbrechnung extends Rechnung {

    //@OneToMany(fetch=FetchType.EAGER)
    //private List<TischRechnung> tischRechnungList;

    @OneToMany(fetch = FetchType.EAGER)
    private List <Bestellung> mitarbeiterBestellungList;
    private double umsatz;
    @ManyToOne (fetch = FetchType.EAGER)
    private Mitarbeiter mitarbeiter;

    public MAAbrechnung() {

    }

    public MAAbrechnung(LocalDate datum, double umsatz, Mitarbeiter mitarbeiter){
        super(datum);
        this.umsatz = umsatz;
        this.mitarbeiter = mitarbeiter;
    }

    public MAAbrechnung (LocalDate datum, List <Bestellung> mitarbeiterBestellungList){
        super(datum);
        this.mitarbeiterBestellungList = mitarbeiterBestellungList;
    }

    public double getUmsatz() {
        return umsatz;
    }

    public void setUmsatz(double umsatz) {
        this.umsatz = umsatz;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public List<Bestellung> getMitarbeiterBestellungList() {
        return mitarbeiterBestellungList;
    }

    public void setMitarbeiterBestellungList(List<Bestellung> mitarbeiterBestellungList) {
        this.mitarbeiterBestellungList = mitarbeiterBestellungList;
    }


    @Override
    public LocalDate getDatum() {
        return super.getDatum();
    }

    @Override
    public void setDatum(LocalDate datum) {
        super.setDatum(datum);
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MAAbrechnung that = (MAAbrechnung) o;
        return Objects.equals(mitarbeiterBestellungList, that.mitarbeiterBestellungList);
    }*/

    @Override
    public boolean equals (Object obj){
        if (this == obj){
            return true;
        }
        if (!(obj instanceof MAAbrechnung)){
            return false;
        }
        MAAbrechnung mab = (MAAbrechnung) obj;
        return this.getDatum().equals(mab.getDatum()) && this.mitarbeiter.equals(mab.mitarbeiter);
    }

    /*@Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), mitarbeiterBestellungList);
    }*/

    @Override
    public int hashCode(){
        return Objects.hash(super.hashCode(), mitarbeiter, umsatz);
    }

    @Override
    public String toString() {
        return "MAAbrechnung{" +
                "tischRechnungList=" + mitarbeiterBestellungList +"}";
    }
}

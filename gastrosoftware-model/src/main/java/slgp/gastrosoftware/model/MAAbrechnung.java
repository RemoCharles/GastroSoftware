package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "MAAbrechnung.findAll", query = "SELECT e FROM MAAbrechnung e")})
public class MAAbrechnung extends Rechnung {
    private static final long serialVersionUID = -5039934739503932388L;

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

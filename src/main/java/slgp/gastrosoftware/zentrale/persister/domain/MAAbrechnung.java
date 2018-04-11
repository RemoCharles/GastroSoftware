package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "MAAbrechnung.findAll", query = "SELECT e FROM MAAbrechnung e")})
public class MAAbrechnung extends Rechnung {

    @OneToMany
    private List<TischRechnung> tischRechnungList;
    @OneToOne
    private Mitarbeiter mitarbeiter;

    public MAAbrechnung() {
    }

    public MAAbrechnung(LocalDate datum, String nameRestaunt, List<TischRechnung> tischRechnungList, Mitarbeiter mitarbeiter){
        super(datum, nameRestaunt);
        this.tischRechnungList = tischRechnungList;
        this.mitarbeiter = mitarbeiter;
    }

    public List<TischRechnung> getTischRechnungList() {
        return tischRechnungList;
    }

    public void setTischRechnungList(List<TischRechnung> tischRechnungList) {
        this.tischRechnungList = tischRechnungList;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public MAAbrechnung(LocalDate datum, String nameRestaunt) {
        super(datum, nameRestaunt);
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
    public String getNameRestaurant() {
        return super.getNameRestaurant();
    }

    @Override
    public void setNameRestaurant(String nameRestaunt) {
        super.setNameRestaurant(nameRestaunt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MAAbrechnung that = (MAAbrechnung) o;
        return Objects.equals(tischRechnungList, that.tischRechnungList) &&
                Objects.equals(mitarbeiter, that.mitarbeiter);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), tischRechnungList, mitarbeiter);
    }

    @Override
    public String toString() {
        return "MAAbrechnung{" +
                "tischRechnungList=" + tischRechnungList +
                ", mitarbeiter=" + mitarbeiter +
                '}';
    }
}

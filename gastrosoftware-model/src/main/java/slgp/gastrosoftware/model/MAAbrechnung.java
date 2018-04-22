package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "MAAbrechnung.findAll", query = "SELECT e FROM MAAbrechnung e")})
public class MAAbrechnung extends Rechnung {

    @OneToMany(fetch=FetchType.EAGER)
    private List<TischRechnung> tischRechnungList;

    public MAAbrechnung() {
    }

    public MAAbrechnung(LocalDate datum, String nameRestaunt, List<TischRechnung> tischRechnungList) {
        super(datum, nameRestaunt);
        this.tischRechnungList = tischRechnungList;
    }

    public List<TischRechnung> getTischRechnungList() {
        return tischRechnungList;
    }

    public void setTischRechnungList(List<TischRechnung> tischRechnungList) {
        this.tischRechnungList = tischRechnungList;
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
        return Objects.equals(tischRechnungList, that.tischRechnungList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), tischRechnungList);
    }

    @Override
    public String toString() {
        return "MAAbrechnung{" +
                "tischRechnungList=" + tischRechnungList +"}";
    }
}

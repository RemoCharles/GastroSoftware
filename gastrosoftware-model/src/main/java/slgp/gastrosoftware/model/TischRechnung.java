package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "TischRechnung.findAll", query = "SELECT e FROM TischRechnung e")})
public class TischRechnung extends Rechnung {

    @OneToMany(fetch = FetchType.EAGER)
    private List<Bestellung> bestellungList;


    public TischRechnung() {
    }

    public TischRechnung(LocalDate datum, String nameRestaunt, List<Bestellung> bestellungList) {

        super(datum, nameRestaunt);
        this.bestellungList = bestellungList;
    }

    public int getId() {
        return super.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TischRechnung that = (TischRechnung) o;
        return Objects.equals(bestellungList, that.bestellungList);
    }

    public List<Bestellung> getBestellungList() {
        return bestellungList;
    }

    public void setBestellungList(List<Bestellung> bestellungList) {
        this.bestellungList = bestellungList;
    }


    @Override
    public int hashCode() {

        return Objects.hash(bestellungList);
    }

    @Override
    public String toString() {
        return " TischRechnung{" + super.toString() +
                "bestellungList=" + bestellungList +
                '}';
    }


}

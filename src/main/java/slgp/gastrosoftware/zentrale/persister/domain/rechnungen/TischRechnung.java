package slgp.gastrosoftware.zentrale.persister.domain.rechnungen;

import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class TischRechnung {
    @Id
    @GeneratedValue
    private int id;
    @OneToMany
    private List<Bestellung> bestellungList;

    public TischRechnung() {
    }

    public TischRechnung(Date datum, String nameRestaunt, List<Bestellung> bestellungList) {
        this.bestellungList = bestellungList;
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
        return super.toString();
    }
}

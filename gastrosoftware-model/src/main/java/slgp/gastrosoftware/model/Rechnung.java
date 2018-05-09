package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Rechnung.findByDatum", query = "SELECT e FROM Rechnung e WHERE e.datum=:datum"),
        @NamedQuery(name = "Rechnung.findAll", query = "SELECT e FROM Rechnung e")})

public class Rechnung implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate datum;

    public Rechnung() {

    }

    public Rechnung(LocalDate datum) {
        this.datum = datum;
    }


    public Rechnung(LocalDate datum, String nameRestaunt) {
        this.datum = datum;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Rechnung{" +
                "datum=" + datum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rechnung rechnung = (Rechnung) o;
        return Objects.equals(datum, rechnung.datum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(datum);
    }
}

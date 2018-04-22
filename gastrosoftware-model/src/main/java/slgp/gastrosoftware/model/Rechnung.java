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
    private String nameRestaurant;

    public Rechnung() {

    }

    public Rechnung(LocalDate datum, String nameRestaunt) {
        this.datum = datum;
        this.nameRestaurant = nameRestaunt;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaunt) {
        this.nameRestaurant = nameRestaunt;
    }

    @Override
    public String toString() {
        return "Rechnung{" +
                "datum=" + datum +
                ", nameRestaunt='" + nameRestaurant + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rechnung rechnung = (Rechnung) o;
        return Objects.equals(datum, rechnung.datum) &&
                Objects.equals(nameRestaurant, rechnung.nameRestaurant);
    }

    @Override
    public int hashCode() {

        return Objects.hash(datum, nameRestaurant);
    }
}

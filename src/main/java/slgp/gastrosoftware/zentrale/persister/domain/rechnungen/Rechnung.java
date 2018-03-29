package slgp.gastrosoftware.zentrale.persister.domain.rechnungen;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Inheritance(strategy= InheritanceType.JOINED)
public class Rechnung implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private Date datum;
    private String nameRestaunt;

    public Rechnung(){

    }
    public Rechnung(Date datum, String nameRestaunt) {
        this.datum = datum;
        this.nameRestaunt = nameRestaunt;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getNameRestaunt() {
        return nameRestaunt;
    }

    public void setNameRestaunt(String nameRestaunt) {
        this.nameRestaunt = nameRestaunt;
    }

    @Override
    public String toString() {
        return "Rechnung{" +
                "datum=" + datum +
                ", nameRestaunt='" + nameRestaunt + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rechnung rechnung = (Rechnung) o;
        return Objects.equals(datum, rechnung.datum) &&
                Objects.equals(nameRestaunt, rechnung.nameRestaunt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(datum, nameRestaunt);
    }
}

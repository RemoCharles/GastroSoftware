package slgp.gastrosoftware.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Mitarbeiter.findByNachname", query = "SELECT e FROM Mitarbeiter e WHERE e.name=:nachname"),
        @NamedQuery(name = "Mitarbeiter.findByVorname", query = "SELECT e FROM Mitarbeiter e WHERE e.vorname=:vorname"),
        @NamedQuery(name = "Mitarbeiter.findByNachnameUndVorname", query = "SELECT e FROM Mitarbeiter e WHERE e.name=:nachname AND e.vorname=:vorname"),
        @NamedQuery(name = "Mitarbeiter.findByUsername", query = "SELECT e FROM Mitarbeiter e WHERE e.login.username=:username"),
        @NamedQuery(name = "Mitarbeiter.findAll", query = "SELECT e FROM Mitarbeiter e")})
public class Mitarbeiter extends Person {


    public Mitarbeiter(String name, String vorname, String funktion, Adresse adresse, Kontakt kontakt, Login login) {
        super(name, vorname, funktion, adresse, kontakt, login);
    }
    
    public Mitarbeiter(String name, String vorname, String funktion, Adresse adresse, Kontakt kontakt) {
    	super(name, vorname, funktion, adresse, kontakt);
    }

    public Mitarbeiter() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAdresse());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person pers = (Person) obj;
        return this.getName().equals(pers.getName()) && this.getAdresse().equals(pers.getAdresse());
    }

    @Override
    public String toString() {
        return "Mitarbeiter [name=" + getName() + ", vorname=" + getVorname() + ", funktion=" + getFunktion() + ", adresse=" + getAdresse() + ", kontakt=" + getKontakt() + ", login=" + getLogin() + "]";
    }



}
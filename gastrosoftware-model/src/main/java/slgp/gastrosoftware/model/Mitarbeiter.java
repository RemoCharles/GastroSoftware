package slgp.gastrosoftware.model;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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



}
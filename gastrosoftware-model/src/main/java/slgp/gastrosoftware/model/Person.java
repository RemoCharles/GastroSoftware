package slgp.gastrosoftware.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Person.findByNachname", query = "SELECT e FROM Person e WHERE e.name=:nachname"),
        @NamedQuery(name = "Person.findByVorname", query = "SELECT e FROM Person e WHERE e.vorname=:vorname"),
        @NamedQuery(name = "Person.findByNachnameUndVorname", query = "SELECT e FROM Person e WHERE e.name=:nachname AND e.vorname=:vorname"),
        @NamedQuery(name = "Person.findByUsername", query = "SELECT e FROM Person e WHERE e.login.username=:username"),
        @NamedQuery(name = "Person.findAll", query = "SELECT e FROM Person e")})

public class Person implements Serializable {

    private static final long serialVersionUID = 8384793184683358592L;
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String vorname;
    private String funktion;
    @OneToOne(cascade = CascadeType.ALL)
    private Adresse adresse;
    @OneToOne(cascade = CascadeType.ALL)
    private Kontakt kontakt;
    @OneToOne(cascade = CascadeType.ALL)
    private Login login;

    public Person(String name, String vorname, String funktion, Adresse adresse, Kontakt kontakt, Login login) {
        this.name = name;
        this.vorname = vorname;
        this.funktion = funktion;
        this.adresse = adresse;
        this.kontakt = kontakt;
        this.login = login;
    }


    public Person(String name, String vorname, String funktion, Adresse adresse, Kontakt kontakt) {
        this.name = name;
        this.vorname = vorname;
        this.funktion = funktion;
        this.adresse = adresse;
        this.kontakt = kontakt;
    }

    public String getFunktion() {
        return funktion;
    }

    public void setFunktion(String funktion) {
        this.funktion = funktion;
    }

    public Person() {
    }

    public Login getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }


    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Kontakt getKontakt() {
        return kontakt;
    }

    public void setKontakt(Kontakt kontakt) {
        this.kontakt = kontakt;
    }

    public void setLogin(Login login) {this.login = login;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
    
    // Aus BenutzerWrapper
    public String getStrasse() {
    	return adresse.getStrasse();
    }
    
    public String getOrt() {
    	return adresse.getOrt();
    }
    
    public String getEmail() {
    	return kontakt.getEmail();
    }
    
    public String getTelefon() {
    	return kontakt.getTelefon();
    }
    
    public String getUsername() {
    	if (login == null) {
    		return null;
    	}
    	return login.getUsername();
    }
    
    public String getPasswort() {
    	if (login == null) {
    		return null;
    	}
    	return login.getPasswort();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, adresse);
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
        return this.name.equals(pers.name) && this.adresse.equals(pers.adresse);
    }


    @Override
    public String toString() {
        return "Person [name=" + name + ", vorname=" + vorname + ", funktion=" + funktion + ", adresse=" + adresse + ", kontakt=" + kontakt + ", login=" + login + "]";
    }

}

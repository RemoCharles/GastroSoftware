package slgp.gastrosoftware.zentrale.persister.domain;

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
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
	@NamedQuery(name = "Person.findByNachname", query = "SELECT e FROM Person e WHERE e.name=:nachname"),
	@NamedQuery(name = "Person.findByVorname", query = "SELECT e FROM Person e WHERE e.vorname=:vorname"),
	@NamedQuery(name = "Person.findByNachnameUndVorname", query = "SELECT e FROM Person e WHERE e.name=:nachname AND e.vorname=:vorname"),
	@NamedQuery(name = "Person.findByUsername", query = "SELECT e FROM Person e WHERE e.login.username=:username")})
	
public class Person implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String vorname;
    private String funktion;
    @OneToOne(cascade=CascadeType.ALL)
    private Adresse adresse;
    @OneToOne(cascade=CascadeType.ALL)
    private Kontakt kontakt;
    @OneToOne(cascade=CascadeType.ALL)
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

    @Override
    public int hashCode() {
        return Objects.hash(name, adresse);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if(!(obj instanceof Person)) {
            return false;
        }

        Person pers = (Person)obj;

        return this.name == pers.name && this.adresse == pers.adresse;
    }


    @Override
    public String toString() {
        return "Person [name=" + name + ", vorname=" + vorname + ", adresse=" + adresse + ", kontakt=" + kontakt + ", login=" + login + "]";
    }

}

package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(name = "Kontakt.findAll", query = "SELECT e FROM Kontakt e")})
public class Kontakt implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String email;
    private String telefon;

    public Kontakt() {

    }

    public Kontakt(String email, String telefon) {
        this.email = email;
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "KontaktDAO [email=" + email + ", telefon=" + telefon + "]";
    }
}

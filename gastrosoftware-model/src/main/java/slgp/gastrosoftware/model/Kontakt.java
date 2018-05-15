package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Kontakt.findAll", query = "SELECT e FROM Kontakt e")})
public class Kontakt implements Serializable {

    private static final long serialVersionUID = -4160923981885672983L;
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
    public int hashCode() {
    	return Objects.hash(email, telefon);
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (this == obj) {
    		return true;
    	}
    	
    	if (obj == null) {
    		return false;
    	}
    	
    	if (!(obj instanceof Kontakt)) {
    		return false;
    	}
    	
    	Kontakt kont = (Kontakt) obj;
    	
    	return this.email.equals(kont.email) && this.telefon.equals(kont.telefon);
    }

    @Override
    public String toString() {
        return "KontaktDAO [email=" + email + ", telefon=" + telefon + "]";
    }
}

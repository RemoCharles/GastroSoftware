package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
	@NamedQuery(name = "Adresse.findAll", query = "SELECT e FROM Adresse e")})
public class Adresse implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String strasse;
    private int plz;
    private String ort;

    public Adresse() {
    }

    public Adresse(String strasse, int plz, String ort) {
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(strasse, plz);
    }
    
    @Override
    public boolean equals (Object obj) {
    	if (this == obj) {
    		return true;
    	}
    	
    	if (obj == null) {
    		return false;
    	}
    	
    	if (!(obj instanceof Adresse)) {
    		return false;
    	}
    	
    	Adresse adr = (Adresse) obj;
    	
    	return this.strasse.equals(adr.strasse) && this.plz == adr.plz;
    }

    @Override
    public String toString() {
        return "Adresse [" + (strasse != null ? "strasse=" + strasse + ", " : "") + "plz=" + plz + ", "
                + (ort != null ? "ort=" + ort : "") + "]";
    }


}
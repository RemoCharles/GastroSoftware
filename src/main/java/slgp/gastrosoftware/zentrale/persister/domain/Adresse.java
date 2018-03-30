package slgp.gastrosoftware.zentrale.persister.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
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
    public String toString() {
        return "Adresse [" + (strasse != null ? "strasse=" + strasse + ", " : "") + "plz=" + plz + ", "
                + (ort != null ? "ort=" + ort : "") + "]";
    }


}
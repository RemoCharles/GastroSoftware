package slgp.gastrosoftware.zentrale.persister.domain.artikel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Konsumartikel implements Serializable {
	@Id
    @GeneratedValue
    private int id;
    private double preis;
    private String bezeichnung;
    private String kategorie;

    public Konsumartikel() {

    }

    public Konsumartikel(String bezeichnung, String kategorie, double preis) {
        this.bezeichnung = bezeichnung;
        this.kategorie = kategorie;
        this.preis = preis;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getKategorie() {
        return kategorie;
    }

    public void setKategorie(String kategorie) {
        this.kategorie = kategorie;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Konsumartikel {" +

                "Bezeichnung='" + bezeichnung + '\'' +
                ", Kategorie='" + kategorie + '\'' +
                ", Preis=" + preis +
                '}';
    }
}

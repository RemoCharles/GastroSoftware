package slgp.gastrosoftware.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = "Konsumartikel.findByBezeichnung", query = "SELECT e FROM Konsumartikel e WHERE e.bezeichnung=:bezeichnung"),
        @NamedQuery(name = "Konsumartikel.findByKategorie", query = "SELECT e FROM Konsumartikel e WHERE e.kategorie=:kategorie"),
        @NamedQuery(name = "Konsumartikel.findAll", query = "SELECT e FROM Konsumartikel e")})

public class Konsumartikel implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private double preis;
    private String bezeichnung;
    private String kategorie;
    private boolean verfuegbar = true;

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

    public boolean getVerfuegbar(){return verfuegbar;}

    public void setVerfuegbar(boolean verfuegbar) { this.verfuegbar = verfuegbar;}

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Konsumartikel {" +

                "Bezeichnung='" + bezeichnung + '\'' +
                ", Kategorie='" + kategorie + '\'' +
                ", Preis=" + preis +
                ", Verfügbarkeit=" + verfuegbar +
                '}';
    }
}

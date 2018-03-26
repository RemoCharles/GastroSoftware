package slgp.gastrosoftware.zentrale.persister.domain.menu;

import slgp.gastrosoftware.zentrale.persister.domain.artikel.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.artikel.Getraenke;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Menukarte implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String bezeichnung;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Esswaren> listeEsswaren;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Getraenke> listeGetraenke;

    public Menukarte() {

    }

    public Menukarte(List<Getraenke> listeGetraenke, List<Esswaren> listeEsswaren, String bezeichnung) {
        this.listeGetraenke = listeGetraenke;
        this.listeEsswaren = listeEsswaren;
        this.bezeichnung = bezeichnung;

    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public List<Esswaren> getListeEsswaren() {
        return listeEsswaren;
    }

    public void setListeEsswaren(List<Esswaren> listeEsswaren) {
        this.listeEsswaren = listeEsswaren;
    }

    public List<Getraenke> getListeGetraenke() {
        return listeGetraenke;
    }

    public void setListeGetraenke(List<Getraenke> listeGetraenke) {
        this.listeGetraenke = listeGetraenke;
    }
}

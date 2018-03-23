package slgp.gastrosoftware.zentrale.persister.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Tagesmenu implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String wochenTag;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Esswaren> listeEsswaren;

    public Tagesmenu(){

    }

    public Tagesmenu(String wochenTag, List<Esswaren> listeEsswaren) {
        this.wochenTag = wochenTag;
        this.listeEsswaren = listeEsswaren;
    }

    public List<Esswaren> getListeKonsumartikel() {
        return listeEsswaren;
    }

    public void setListeKonsumartikel(List<Konsumartikel> listeKonsumartikel) {
        this.listeEsswaren = listeEsswaren;
    }

    public String getWochenTag() {
        return wochenTag;
    }

    public void setWochenTag(String wochenTag) {
        this.wochenTag = wochenTag;
    }

    @Override
    public String toString() {
        return "Tagesmenu{" +
                "wochenTag='" + wochenTag + '\'' +
                ", konsumartikel=" + listeEsswaren +
                '}';
    }
}

package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.domain.Tagesmenu;

import javax.swing.text.html.HTML;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<Esswaren> createEsswarenListe() {

        List<Esswaren> liste = new ArrayList<Esswaren>();

        liste.add(new Esswaren("Pizza", "Hauptspeise", 500));
        liste.add(new Esswaren("Steak", "Hauptspeise", 20));
        liste.add(new Esswaren("Salat", "Vorspeise", 30));

        return liste;
    }

    public static List<Getraenke> createGetraenkeListe() {

        List<Getraenke> liste = new ArrayList<Getraenke>();

        liste.add(new Getraenke("Bier", "Bier", 10));
        liste.add(new Getraenke("Porto", "Wein", 50));
        liste.add(new Getraenke("Cola", "Softgetraenke", 5));

        return liste;
    }

    public static List<Tagesmenu> createTagesmenuListe() {

        List<Tagesmenu> liste = new ArrayList<Tagesmenu>();

        liste.add(new Tagesmenu("Montag", createEsswarenListe()));


        return liste;
    }

}


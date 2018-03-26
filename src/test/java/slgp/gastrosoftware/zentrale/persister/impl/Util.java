package slgp.gastrosoftware.zentrale.persister.impl;

import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;
import slgp.gastrosoftware.zentrale.persister.domain.Tisch;
import slgp.gastrosoftware.zentrale.persister.domain.artikel.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.artikel.Getraenke;
import slgp.gastrosoftware.zentrale.persister.domain.artikel.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.domain.menu.Tagesmenu;
import slgp.gastrosoftware.zentrale.persister.domain.personen.Adresse;
import slgp.gastrosoftware.zentrale.persister.domain.personen.Kontakt;
import slgp.gastrosoftware.zentrale.persister.domain.personen.Mitarbeiter;
import slgp.gastrosoftware.zentrale.persister.domain.rechnungen.TischRechnung;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    public static List<Konsumartikel> createKonsumartikelListe() {

        List<Konsumartikel> liste = new ArrayList<Konsumartikel>();

        liste.add(new Esswaren("Pizza", "Hauptspeise", 500));
        liste.add(new Getraenke("Cola", "Softgetraenke", 5));

        return liste;
    }

    public static List<Tagesmenu> createTagesmenuListe() {
        List<Tagesmenu> liste = new ArrayList<Tagesmenu>();
        liste.add(new Tagesmenu("Montag", createEsswarenListe()));
        return liste;
    }

    public static Mitarbeiter createMitarbeiter(Adresse adr, Kontakt kontakt) {
        Mitarbeiter ma = new Mitarbeiter("Hans", "Müller", adr, kontakt);
        return ma;
    }
    public static Adresse createAdresse(){
        Adresse adr = new Adresse("Luzernerstrasse", 54, "Luzern");
        return  adr;
    }

    public static Kontakt createKontakt(){
        Kontakt kontakt = new Kontakt("Test@test.ch", "079223123123");
        return kontakt;
    }

    public static Tisch createTisch() {
        Tisch tisch = new Tisch(6);
        return tisch;
    }

    public static Date createDate() {
        Date date = new Date();
        return date;
    }

    //public Bestellung(Mitarbeiter mitarbeiter, Tisch tisch, List<Konsumartikel> konsumartikel, boolean zubereitet, Date datum) {
    public static List<Bestellung> createBestellungListe(Mitarbeiter ma, Tisch tisch, List<Konsumartikel> konsumliste){
        List<Bestellung> bestList = new ArrayList<Bestellung>();
        Bestellung best = new Bestellung(ma, tisch, konsumliste, false, new Date());
        bestList.add(best);
        return bestList;
    }
    public static TischRechnung createTischRechnung(Date date, String restaurant, List<Bestellung> bestellungList) {
        TischRechnung tr = new TischRechnung(date, restaurant, bestellungList);
        return tr;
    }
}


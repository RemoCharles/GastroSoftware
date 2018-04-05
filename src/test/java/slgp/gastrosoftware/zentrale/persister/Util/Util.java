package slgp.gastrosoftware.zentrale.persister.Util;

import slgp.gastrosoftware.zentrale.persister.api.*;
import slgp.gastrosoftware.zentrale.persister.domain.*;
import slgp.gastrosoftware.zentrale.persister.impl.*;
import slgp.gastrosoftware.zentrale.persister.util.JpaUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Util {

    public static final int INIT_SIZE_PERSONEN = 3;
    public static final int INIT_SIZE_ESSWAREN = 3;
    public static final int INIT_SIZE_GETRAENKE = 3;
    public static final int INIT_SIZE_KONSUMARTIKEL = 2;
    public static final int INIT_SIZE_TAGESMENU = 1;
    public static final int INIT_SIZE_MITARBEITER = 3;
    public static final int INIT_SIZE_LIEFERANT = 3;
    public static final int INIT_SIZE_TISCH = 3;
    public static final int INIT_SIZE_BESTELLUNG_LISTE = 1;
    public static final int INIT_SIZE_TISCH_RECHNUNG = 1;


    public static List<Person> erstellePersonenListe() throws Exception {

        List<Person> list = new ArrayList<Person>();

        list.add(new Person("Meier", "Marco", "Barpersonal", new Adresse("Kusterweg 4", 6004, "Luzern"), new Kontakt("mmarco@gmx.ch", "041 234 56 67")));
        list.add(new Person("Mueller", "Jana", "Servicepersonal", new Adresse("Muellweg 8", 6008, "Luzern"), new Kontakt("mjana@gmx.ch", "041 234 56 67"), new Login("mjana", "abcde")));
        list.add(new Person("Mohn", "Kevin", "Kuechenpersonal", new Adresse("Hohlweg 8", 6003, "Luzern"), new Kontakt("mkevin@gmx.ch", "078 435 66 88")));
        return list;
    }

    public static void deleteAllPersonen() throws Exception {
        PersonDAO pPerson = (PersonDAO) new PersonDAOImpl();
        for (Person p : pPerson.findAll()){
            pPerson.delete(p);
        }
    }


    public static List<Esswaren> createEsswarenListe() throws Exception {

        List<Esswaren> list = new ArrayList<Esswaren>();

        list.add(new Esswaren("Pizza", "Hauptspeise", 500));
        list.add(new Esswaren("Steak", "Hauptspeise", 20));
        list.add(new Esswaren("Salat", "Vorspeise", 30));

        return list;
    }

    public static void deleteAllEsswaren() throws Exception{
        EsswarenDAO pEsswaren = (EsswarenDAO) new EsswarenDAOImpl();
        for (Esswaren e : pEsswaren.findAll()){
            pEsswaren.delete(e);
        }
    }

    public static List<Getraenke> createGetraenkeListe() throws Exception {

        List<Getraenke> list = new ArrayList<Getraenke>();

        list.add(new Getraenke("Bier", "Bier", 10));
        list.add(new Getraenke("Porto", "Wein", 50));
        list.add(new Getraenke("Cola", "Softgetraenke", 5));

        return list;
    }

    public static void deleteAllGetraenke() throws Exception{
        GetraenkeDAO pGetraenke = new GetraenkeDAOImpl();
        for (Getraenke g : pGetraenke.findAll()){
            pGetraenke.delete(g);
        }
    }

    public static List<Konsumartikel> createKonsumartikelListe() throws Exception {

        List<Konsumartikel> list = new ArrayList<Konsumartikel>();

        list.add(new Esswaren("Pizza", "Hauptspeise", 500));
        list.add(new Getraenke("Cola", "Softgetraenke", 5));

        return list;
    }

    public static void deleteAllKonsumartikel() throws Exception{
        KonsumartikelDAO pKonsumartikel = new KonsumartikelDAOImpl();
        for (Konsumartikel k : pKonsumartikel.findAll()){
            pKonsumartikel.delete(k);
        }
    }

    public static List<Tagesmenu> createTagesmenuListe() throws Exception {
        List<Tagesmenu> list = new ArrayList<Tagesmenu>();
        list.add(new Tagesmenu("Montag", createEsswarenListe()));
        return list;
    }

    public static void deleteAllTagesmenu() throws Exception {
        TagesmenuDAO pTagesmenu = new TagesmenuDAOImpl();
        for (Tagesmenu t : pTagesmenu.findAll()){
            pTagesmenu.delete(t);
        }
    }

    public static List<Mitarbeiter> createMitarbeiter() throws Exception {
        List<Mitarbeiter> list = new ArrayList<>();
        list.add(new Mitarbeiter("Meierhans", "Franz", "Kuechenpersonal", new Adresse("Luzernerstrasse 4", 6023, "Basel"), new Kontakt("test@gsdmx.ch", "041 233 34 22")));
        list.add(new Mitarbeiter("Burg", "Susann", "Servicepersonal", new Adresse("Mondweg 2", 6014, "Zuerich"), new Kontakt("hslu@hslu.ch", "079 234 56 32")));
        list.add(new Mitarbeiter("Vogt", "Magdalena", "Barpersonal", new Adresse("Baselstrasse 24", 6024, "Luzern"), new Kontakt("remog@glsmx.ch", "078 123 22 67")));

        return list;
    }

    public static void deleteAllMitarbeiter() throws Exception {
    }

    public static List<Tisch> createTisch() throws Exception {
        ArrayList<Tisch> list = new ArrayList<Tisch>();
        list.add(new Tisch(6));
        list.add(new Tisch(4));
        list.add(new Tisch(10));
        return list;
    }

    public static void deleteAllTisch() throws Exception {
    }

    public static List<Bestellung> createBestellungListe() throws Exception {
        List<Bestellung> list = new ArrayList<Bestellung>();
        List<Konsumartikel> konsumList = new ArrayList<Konsumartikel>();
        konsumList.add(new Esswaren("Pizza", "Hauptspeise", 500));
        konsumList.add(new Getraenke("Cola", "Softgetraenke", 5));

        Mitarbeiter ma = new Mitarbeiter("Meierhans", "Franz", "Kuechenpersonal", new Adresse("Luzernerstrasse 4", 6023, "Basel"), new Kontakt("test@gsdmx.ch", "041 233 34 22"));
        list.add(new Bestellung(ma, new Tisch(6), konsumList, false, LocalDate.now()));
        return list;
    }

    public static void deleteAllBestellung() throws Exception {
        BestellungDAO pBestellungListe = new BestellungDAOImpl();
        for (Bestellung b : pBestellungListe.findAll()) {
            pBestellungListe.delete(b);
        }
    }

    public static List<TischRechnung> createTischRechnung() throws Exception {
        //LocalDate date, String restaurant, List<Bestellung> bestellungList
        List<TischRechnung> list = new ArrayList<>();

        List<Bestellung> bestellungList = new ArrayList<Bestellung>();
        List<Konsumartikel> konsumList = new ArrayList<Konsumartikel>();
        konsumList.add(new Esswaren("Pizza", "Hauptspeise", 500));
        konsumList.add(new Getraenke("Cola", "Softgetraenke", 5));

        Mitarbeiter ma = new Mitarbeiter("Meierhans", "Franz", "Barpersonal", new Adresse("Luzernerstrasse 4", 6023, "Basel"), new Kontakt("test@gsdmx.ch", "041 233 34 22"));
        bestellungList.add(new Bestellung(ma, new Tisch(6), konsumList, false, LocalDate.now()));


        list.add(new TischRechnung(LocalDate.now(), "Chochichaeschtli", bestellungList));
        return list;
    }

    public static void deleteAllTischRechnung() throws Exception {
    }

    public static void resetDb() throws Exception {
        /* Schema wird angelegt, die vorhandenen Daten werden dabei gelöscht. */
        JpaUtil.createEntityManagerForDelition().close();
    }
}


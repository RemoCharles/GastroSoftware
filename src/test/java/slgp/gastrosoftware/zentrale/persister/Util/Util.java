package slgp.gastrosoftware.zentrale.persister.Util;

import slgp.gastrosoftware.zentrale.persister.domain.Bestellung;
import slgp.gastrosoftware.zentrale.persister.domain.Tisch;
import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Getraenke;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;
import slgp.gastrosoftware.zentrale.persister.domain.Tagesmenu;
import slgp.gastrosoftware.zentrale.persister.domain.Adresse;
import slgp.gastrosoftware.zentrale.persister.domain.Kontakt;
import slgp.gastrosoftware.zentrale.persister.domain.Login;
import slgp.gastrosoftware.zentrale.persister.domain.Mitarbeiter;
import slgp.gastrosoftware.zentrale.persister.domain.Person;
import slgp.gastrosoftware.zentrale.persister.domain.TischRechnung;

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



    public static List<Person> erstellePersonenListe() {

        List<Person> list = new ArrayList<Person>();

        list.add(new Person("Meier", "Marco", "Barpersonal", new Adresse("Kusterweg 4", 6004, "Luzern"), new Kontakt("mmarco@gmx.ch", "041 234 56 67")));
        list.add(new Person("Mueller", "Jana", "Servicepersonal", new Adresse("Muellweg 8", 6008, "Luzern"), new Kontakt("mjana@gmx.ch", "041 234 56 67"), new Login("mjana", "abcde")));
        list.add(new Person("Mohn", "Kevin", "Kuechenpersonal", new Adresse("Hohlweg 8", 6003, "Luzern"), new Kontakt("mkevin@gmx.ch", "078 435 66 88")));
        return list;
    }
    

    public static List<Esswaren> createEsswarenListe() {

        List<Esswaren> list = new ArrayList<Esswaren>();

        list.add(new Esswaren("Pizza", "Hauptspeise", 500));
        list.add(new Esswaren("Steak", "Hauptspeise", 20));
        list.add(new Esswaren("Salat", "Vorspeise", 30));

        return list;
    }

    public static List<Getraenke> createGetraenkeListe() {

        List<Getraenke> list = new ArrayList<Getraenke>();

        list.add(new Getraenke("Bier", "Bier", 10));
        list.add(new Getraenke("Porto", "Wein", 50));
        list.add(new Getraenke("Cola", "Softgetraenke", 5));

        return list;
    }

    public static List<Konsumartikel> createKonsumartikelListe() {

        List<Konsumartikel> list = new ArrayList<Konsumartikel>();

        list.add(new Esswaren("Pizza", "Hauptspeise", 500));
        list.add(new Getraenke("Cola", "Softgetraenke", 5));

        return list;
    }

    public static List<Tagesmenu> createTagesmenuListe() {
        List<Tagesmenu> list = new ArrayList<Tagesmenu>();
        list.add(new Tagesmenu("Montag", createEsswarenListe()));
        return list;
    }

    public static List<Mitarbeiter> createMitarbeiter() {
        List<Mitarbeiter> list = new ArrayList<>();
        list.add(new Mitarbeiter("Meierhans", "Franz", "Kuechenpersonal", new Adresse("Luzernerstrasse 4", 6023, "Basel"), new Kontakt("test@gsdmx.ch", "041 233 34 22")));
        list.add(new Mitarbeiter("Burg", "Susann", "Servicepersonal", new Adresse("Mondweg 2", 6014, "Zuerich"), new Kontakt("hslu@hslu.ch", "079 234 56 32")));
        list.add(new Mitarbeiter("Vogt", "Magdalena", "Barpersonal",  new Adresse("Baselstrasse 24", 6024, "Luzern"), new Kontakt("remog@glsmx.ch", "078 123 22 67")));

        return list;
    }

    public static List<Tisch> createTisch() {
        ArrayList<Tisch> list = new ArrayList<Tisch>();
        list.add(new Tisch(6));
        list.add(new Tisch(4));
        list.add(new Tisch(10));
        return list;
    }

    public static List<Bestellung> createBestellungListe() {
        List<Bestellung> list = new ArrayList<Bestellung>();
        List<Konsumartikel> konsumList = new ArrayList<Konsumartikel>();
        konsumList.add(new Esswaren("Pizza", "Hauptspeise", 500));
        konsumList.add(new Getraenke("Cola", "Softgetraenke", 5));

        Mitarbeiter ma = new Mitarbeiter("Meierhans", "Franz", "Kuechenpersonal", new Adresse("Luzernerstrasse 4", 6023, "Basel"), new Kontakt("test@gsdmx.ch", "041 233 34 22"));
        list.add(new Bestellung(ma, new Tisch(6),konsumList, false, LocalDate.now()));
        return list;
    }

    public static List<TischRechnung> createTischRechnung() {
        //LocalDate date, String restaurant, List<Bestellung> bestellungList
        List<TischRechnung> list = new ArrayList<>();

        List<Bestellung> bestellungList = new ArrayList<Bestellung>();
        List<Konsumartikel> konsumList = new ArrayList<Konsumartikel>();
        konsumList.add(new Esswaren("Pizza", "Hauptspeise", 500));
        konsumList.add(new Getraenke("Cola", "Softgetraenke", 5));

        Mitarbeiter ma = new Mitarbeiter("Meierhans", "Franz", "Barpersonal", new Adresse("Luzernerstrasse 4", 6023, "Basel"), new Kontakt("test@gsdmx.ch", "041 233 34 22"));
        bestellungList.add(new Bestellung(ma, new Tisch(6),konsumList, false, LocalDate.now()));



        list.add(new TischRechnung(LocalDate.now(), "Chochichaeschtli", bestellungList));
        return list;
    }
}


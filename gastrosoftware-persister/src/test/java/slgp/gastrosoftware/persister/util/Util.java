package slgp.gastrosoftware.persister.util;

import slgp.gastrosoftware.model.*;
import slgp.gastrosoftware.persister.*;
import slgp.gastrosoftware.persister.impl.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Util {

    public static final int INIT_SIZE_PERSONEN = 3;
    public static final int INIT_SIZE_ESSWAREN = 3;
    public static final int INIT_SIZE_GETRAENKE = 3;
    public static final int INIT_SIZE_BESTELLPOSITION = 2;
    public static final int INIT_SIZE_KONSUMARTIKEL = 5;
    public static final int INIT_SIZE_TAGESMENU = 1;
    public static final int INIT_SIZE_MITARBEITER = 3;
    public static final int INIT_SIZE_LIEFERANT = 3;
    public static final int INIT_SIZE_TISCH = 3;
    public static final int INIT_SIZE_BESTELLUNG_LISTE = 1;
    public static final int INIT_SIZE_TISCH_RECHNUNG = 1;
    public static final int INIT_SIZE_MAAbrechnung = 1;


    public static List<Person> erstellePersonenListe() throws Exception {
        PersonDAO pPerson = new PersonDAOImpl();
        List<Person> list = new ArrayList<Person>();

        list.add(new Person("Meier", "Marco", "Barpersonal", new Adresse("Kusterweg 4", 6004, "Luzern"), new Kontakt("mmarco@gmx.ch", "041 234 56 67"), new Login("mmarco", "12345")));
        list.add(new Person("Mueller", "Jana", "Servicepersonal", new Adresse("Muellweg 8", 6008, "Luzern"), new Kontakt("mjana@gmx.ch", "041 234 56 67"), new Login("mjana", "abcde")));
        list.add(new Person("Mohn", "Kevin", "Leiter", new Adresse("Hohlweg 8", 6003, "Luzern"), new Kontakt("mkevin@gmx.ch", "078 435 66 88"), new Login("mkevin", "xyz")));
        for (Person p : list) {
            pPerson.save(p);
        }
        return list;
    }

    public static void deleteAllPersonen() throws Exception {
        PersonDAO pPerson = (PersonDAO) new PersonDAOImpl();
        for (Person p : pPerson.findAll()) {
            pPerson.delete(p);
        }
    }


    public static List<Esswaren> createEsswarenListe() throws Exception {
        EsswarenDAO pEsswaren = new EsswarenDAOImpl();

        List<Esswaren> list = new ArrayList<Esswaren>();

        list.add(new Esswaren("Pasta", "Hauptspeise", 500));
        list.add(new Esswaren("Steak", "Hauptspeise", 20));
        list.add(new Esswaren("Salat", "Vorspeise", 30));

        for (Esswaren e : list) {
            pEsswaren.save(e);
        }

        return list;
    }

    public static void deleteAllEsswaren() throws Exception {
        EsswarenDAO pEsswaren = (EsswarenDAO) new EsswarenDAOImpl();
        for (Esswaren e : pEsswaren.findAll()) {
            pEsswaren.delete(e);
        }
    }

    public static List<Getraenke> createGetraenkeListe() throws Exception {
        GetraenkeDAO pGetraenke = new GetraenkeDAOImpl();
        List<Getraenke> list = new ArrayList<Getraenke>();

        list.add(new Getraenke("Bier", "Bier", 10));
        list.add(new Getraenke("Porto", "Wein", 50));
        list.add(new Getraenke("Cola", "Softgetraenke", 5));

        for (Getraenke g : list) {
            pGetraenke.save(g);
        }
        return list;
    }

    public static void deleteAllGetraenke() throws Exception {
        GetraenkeDAO pGetraenke = new GetraenkeDAOImpl();
        for (Getraenke g : pGetraenke.findAll()) {
            pGetraenke.delete(g);
        }
    }

    public static List<Konsumartikel> createKonsumartikelListe() throws Exception {
        KonsumartikelDAO pKonsumartikel = new KonsumartikelDAOImpl();
        List<Konsumartikel> list = new ArrayList<Konsumartikel>();

        list.add(new Esswaren("Pizza", "Hauptspeise", 13));
        list.add(new Getraenke("Cola", "Softgetränke", 5));
        list.add(new Esswaren("Käsekuchen", "Hauptspeise", 12));
        list.add(new Getraenke("Wasser", "Softgetränke", 2));
        list.add(new Getraenke("Eichhof", "Bier", 17));
        list.add(new Esswaren("Salat", "Vorspeise", 1));

        for (Konsumartikel k : list) {
            pKonsumartikel.save(k);
        }

        return list;
    }

    public static void deleteAllKonsumartikel() throws Exception {
        KonsumartikelDAO pKonsumartikel = new KonsumartikelDAOImpl();
        for (Konsumartikel k : pKonsumartikel.findAll()) {
            pKonsumartikel.delete(k);
        }
    }

    public static List<Tagesmenu> createTagesmenuListe() throws Exception {
        TagesmenuDAO pTagesmenu = new TagesmenuDAOImpl();
        List<Tagesmenu> list = new ArrayList<Tagesmenu>();
        list.add(new Tagesmenu("Montag", createEsswarenListe()));
        for (Tagesmenu t : list) {
            pTagesmenu.save(t);
        }
        return list;
    }

    public static void deleteAllTagesmenu() throws Exception {
        TagesmenuDAO pTagesmenu = new TagesmenuDAOImpl();
        for (Tagesmenu t : pTagesmenu.findAll()) {
            pTagesmenu.delete(t);
        }
    }

    public static List<Mitarbeiter> createMitarbeiter() throws Exception {
        MitarbeiterDAO pMitarbeiter = new MitarbeiterDAOImpl();
        List<Mitarbeiter> list = new ArrayList<>();
        list.add(new Mitarbeiter("Meierhans", "Franz", "Kuechenpersonal", new Adresse("Luzernerstrasse 4", 6023, "Basel"), new Kontakt("test@gsdmx.ch", "041 233 34 22")));
        list.add(new Mitarbeiter("Burg", "Susann", "Servicepersonal", new Adresse("Mondweg 2", 6014, "Zuerich"), new Kontakt("hslu@hslu.ch", "079 234 56 32")));
        list.add(new Mitarbeiter("Vogt", "Magdalena", "Barpersonal", new Adresse("Baselstrasse 24", 6024, "Luzern"), new Kontakt("remog@glsmx.ch", "078 123 22 67")));

        for (Mitarbeiter m : list) {
            pMitarbeiter.save(m);
        }
        return list;
    }

    public static void deleteAllMitarbeiter() throws Exception {
        PersonDAO pPerson = new PersonDAOImpl();
        for (Person p : pPerson.findAll()) {
            pPerson.delete(p);
        }
    }

    public static List<Tisch> createTisch() throws Exception {
        TischDAO pTisch = new TischDAOImpl();
        ArrayList<Tisch> list = new ArrayList<Tisch>();
        list.add(new Tisch(6));
        list.add(new Tisch(4));
        list.add(new Tisch(10));

        for (Tisch t : list) {
            pTisch.save(t);
        }
        return list;
    }

    public static void deleteAllTisch() throws Exception {
        TischDAO pTisch = new TischDAOImpl();
        for (Tisch t : pTisch.findAll()) {
            pTisch.delete(t);
        }
    }

    public static List<Bestellung> createBestellungListe() throws Exception {
        MitarbeiterDAO pMitarbeiter = new MitarbeiterDAOImpl();
        BestellungDAO pBestellung = new BestellungDAOImpl();
        KonsumartikelDAO pKonsumartikel = new KonsumartikelDAOImpl();
        TischDAO pTisch = new TischDAOImpl();

        List<BestellPosition> bestellPositionList = createBestellPosition();
        List<Bestellung> list = new ArrayList<Bestellung>();
        List<Konsumartikel> konsumList = new ArrayList<Konsumartikel>();
        konsumList.add(new Esswaren("Pizza", "Hauptspeise", 500));
        konsumList.add(new Getraenke("Eichhof", "Bier", 5));


        Tisch tisch = new Tisch(6);
        Mitarbeiter ma = new Mitarbeiter("Meierhans", "Franz", "Kuechenpersonal", new Adresse("Luzernerstrasse 4", 6023, "Basel"), new Kontakt("test@gsdmx.ch", "041 233 34 22"));
        list.add(new Bestellung(ma, tisch, bestellPositionList, false, false, LocalDate.now()));

        pMitarbeiter.save(ma);
        pTisch.save(tisch);
        for (Konsumartikel k : konsumList) {
            pKonsumartikel.save(k);
        }
        for (Bestellung b : list) {
            pBestellung.save(b);
        }
        return list;
    }

    public static void deleteAllBestellung() throws Exception {
        BestellungDAO pBestellungListe = new BestellungDAOImpl();
        for (Bestellung b : pBestellungListe.findAll()) {
            pBestellungListe.delete(b);
        }
    }

    public static List<BestellPosition> createBestellPosition() throws Exception {
        BestellPositionDAO bestellPositionDAO = new BestellPositionDAOImpl();
        List<BestellPosition> bestellPositionList = new ArrayList<>();
        Konsumartikel k1 = Util.createKonsumartikelListe().get(0);
        Konsumartikel k2 = Util.createKonsumartikelListe().get(1);

        BestellPosition bP1 = new BestellPosition(k1, 12);
        BestellPosition bP2 = new BestellPosition(k2, 1);

        bestellPositionList.add(bP1);
        bestellPositionList.add(bP2);

        for (BestellPosition bestellPosition : bestellPositionList) {
            bestellPositionDAO.save(bestellPosition);
        }

        return bestellPositionList;
    }

    public static void deleteAllBestellPosition() throws Exception {
        BestellPositionDAO bestellPositionDAO = new BestellPositionDAOImpl();
        for (BestellPosition bestellPosition : bestellPositionDAO.findAll()) {
            bestellPositionDAO.delete(bestellPosition);
        }
    }

    public static List<TischRechnung> createTischRechnung() throws Exception {
        TischRechnungDAO pTischRechnung = new TischRechnungDAOImpl();
        BestellungDAO pBestellung = new BestellungDAOImpl();
        MitarbeiterDAO pMitarbeiter = new MitarbeiterDAOImpl();
        TischDAO tischDAO = new TischDAOImpl();
        KonsumartikelDAO konsumartikelDAO = new KonsumartikelDAOImpl();
        List<TischRechnung> list = new ArrayList<>();
        List<BestellPosition> bestellPositionList = Util.createBestellPosition();
        Tisch tisch = new Tisch(6);
        List<Bestellung> bestellungList = new ArrayList<Bestellung>();
        List<Konsumartikel> konsumList = new ArrayList<Konsumartikel>();
        konsumList.add(new Esswaren("Pasta", "Hauptspeise", 500));
        konsumList.add(new Getraenke("Cola", "Softgetraenke", 5));
        Mitarbeiter ma = new Mitarbeiter("Meierhans", "Franz", "Barpersonal", new Adresse("Luzernerstrasse 4", 6023, "Basel"), new Kontakt("test@gsdmx.ch", "041 233 34 22"));
        bestellungList.add(new Bestellung(ma, tisch, bestellPositionList, false, false, LocalDate.now()));
        list.add(new TischRechnung(LocalDate.now(), "Chochichaeschtli", bestellungList));
        pMitarbeiter.save(ma);
        tischDAO.save(tisch);
        for (Konsumartikel k : konsumList) {
            konsumartikelDAO.save(k);
        }

        for (Bestellung b : bestellungList) {
            pBestellung.save(b);
        }
        for (TischRechnung tr : list) {
            pTischRechnung.save(tr);
        }
        return list;
    }

    public static void deleteAllTischRechnung() throws Exception {
        TischRechnungDAO pTischRechnung = new TischRechnungDAOImpl();
        for (TischRechnung t : pTischRechnung.findAll()) {
            pTischRechnung.delete(t);
        }
    }

    public static List<MAAbrechnung> createMAAbrechnung() throws Exception {
        List<MAAbrechnung> maAbrechnungList = new ArrayList<>();

        MAAbrechnungDAO maAbrechnungDAO = new MAAbrechnungDAOImpl();

        List<TischRechnung> tischRechnungList = createTischRechnung();

        MAAbrechnung maAbrechnung = new MAAbrechnung(LocalDate.now(), "Test", tischRechnungList);
        maAbrechnungList.add(maAbrechnung);

        for (MAAbrechnung maAbrech : maAbrechnungList) {
            maAbrechnungDAO.save(maAbrech);
        }

        return maAbrechnungList;
    }

    public static void deleteAllMAAbrechnung() throws Exception {
        MAAbrechnungDAO maAbrechnungDAO = new MAAbrechnungDAOImpl();
        for (MAAbrechnung maAbrechnung : maAbrechnungDAO.findAll()) {
            maAbrechnungDAO.delete(maAbrechnung);
        }
    }

    public static void deleteAllKontakt() throws Exception {
        KontaktDAO kontaktDAO = new KontaktDAOImpl();
        for (Kontakt kontakt : kontaktDAO.findAll()) {
            kontaktDAO.delete(kontakt);
        }
    }

    public static void deleteAllAdresse() throws Exception {
        AdresseDAO adresseDAO = new AdresseDAOImpl();
        for (Adresse adresse : adresseDAO.findAll()) {
            adresseDAO.delete(adresse);
        }
    }

    public static void deleteAllLogin() throws Exception {
        LoginDAO loginDAO = new LoginDAOImpl();
        for (Login login : loginDAO.findAll()) {
            loginDAO.delete(login);
        }
    }

    public static void deleteAllRechnung() throws Exception {
        RechnungDAO rechnungDAO = new RechnungDAOImpl();
        for (Rechnung rechnung : rechnungDAO.findAll()) {
            rechnungDAO.delete(rechnung);
        }
    }

    public static List<BestellPosition> createBestellPositionAlleKonsumartikel() throws Exception {
        KonsumartikelDAO konsumartikelDAO = new KonsumartikelDAOImpl();
        BestellPositionDAO bestellPositionDAO = new BestellPositionDAOImpl();
        List<BestellPosition> bestellPositionList = new ArrayList<>();
        for (Konsumartikel konsumartikel : konsumartikelDAO.findAll()) {
            BestellPosition bestellPosition = new BestellPosition(konsumartikel, 0);
            bestellPositionList.add(bestellPosition);
        }

        for (BestellPosition bestellPosition : bestellPositionList) {
            bestellPositionDAO.save(bestellPosition);
        }
        return bestellPositionList;
    }


    public static void resetDb() throws Exception {
        /* Schema wird angelegt, die vorhandenen Daten werden dabei gelöscht. */
        JpaUtil.createEntityManagerForDelition().close();
    }
}


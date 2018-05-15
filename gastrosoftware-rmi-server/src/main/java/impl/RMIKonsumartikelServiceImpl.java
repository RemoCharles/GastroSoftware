package impl;

import slgp.KonsumartikelManager;
import slgp.gastrosoftware.KonsumartikelService;
import slgp.gastrosoftware.RMIKonsumartikelService;
import slgp.gastrosoftware.model.Esswaren;
import slgp.gastrosoftware.model.Getraenke;
import slgp.gastrosoftware.model.Konsumartikel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIKonsumartikelServiceImpl extends UnicastRemoteObject implements RMIKonsumartikelService {

    private KonsumartikelService konsumartikelService;

    public RMIKonsumartikelServiceImpl() throws RemoteException {

    }

    public KonsumartikelService getKonsumartikelService() {
        if (konsumartikelService == null) {
            konsumartikelService = new KonsumartikelManager();
        }
        return konsumartikelService;
    }

    @Override
    public Konsumartikel konsumartikelHinzufuegen(Konsumartikel konsumartikel) throws Exception {
        return getKonsumartikelService().konsumartikelHinzufuegen(konsumartikel);
    }

    @Override
    public Konsumartikel konsumartikelAktualisieren(Konsumartikel konsumartikel) throws Exception {
        return getKonsumartikelService().konsumartikelAktualisieren(konsumartikel);
    }

    @Override
    public void konsumartikelLoeschen(Konsumartikel konsumartikel) throws Exception {
        getKonsumartikelService().konsumartikelLoeschen(konsumartikel);
    }

    @Override
    public Konsumartikel findKonsumartikelByBezeichnung(String bezeichnung) throws Exception {
        return getKonsumartikelService().findKonsumartikelByBezeichnung(bezeichnung);
    }

    @Override
    public List<Konsumartikel> findKonsumartikelByKategorie(String kategorie) throws Exception {
        return getKonsumartikelService().findKonsumartikelByKategorie(kategorie);
    }

    @Override
    public List<Konsumartikel> findKonsumartikelAll() throws Exception {
        return getKonsumartikelService().findKonsumartikelAll();
    }

    @Override
    public Getraenke getraenkeHinzufuegen(Getraenke getraenke) throws Exception {
        return getKonsumartikelService().getraenkeHinzufuegen(getraenke);
    }

    @Override
    public Getraenke getraenkeAktualisieren(Getraenke getraenke) throws Exception {
        return getKonsumartikelService().getraenkeAktualisieren(getraenke);
    }

    @Override
    public void getraenkeLoeschen(Getraenke getraenke) throws Exception {
        getKonsumartikelService().getraenkeLoeschen(getraenke);
    }

    @Override
    public List<Getraenke> findGetraenkeAll() throws Exception {
        return getKonsumartikelService().findGetraenkeAll();
    }

    @Override
    public Esswaren esswarenHinzufuegen(Esswaren esswaren) throws Exception {
        return getKonsumartikelService().esswarenHinzufuegen(esswaren);
    }

    @Override
    public Esswaren esswarenAktualisieren(Esswaren esswaren) throws Exception {
        return getKonsumartikelService().esswarenAktualisieren(esswaren);
    }

    @Override
    public void esswareLoeschen(Esswaren esswaren) throws Exception {
        getKonsumartikelService().esswareLoeschen(esswaren);
    }

    @Override
    public List<Esswaren> findEsswarenAll() throws Exception {
        return getKonsumartikelService().findEsswarenAll();
    }
}
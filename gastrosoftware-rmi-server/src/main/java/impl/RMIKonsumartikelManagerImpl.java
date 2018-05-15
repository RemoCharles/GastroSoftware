package impl;

import slgp.KonsumartikelManager;
import slgp.gastrosoftware.KonsumartikelService;
import slgp.gastrosoftware.model.Esswaren;
import slgp.gastrosoftware.model.Getraenke;
import slgp.gastrosoftware.model.Konsumartikel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIKonsumartikelManagerImpl extends UnicastRemoteObject implements KonsumartikelService {

    private KonsumartikelService konsumartikelService;

    public RMIKonsumartikelManagerImpl() throws RemoteException {

    }

    public KonsumartikelService getKonsumartikelService() {
        if (konsumartikelService == null) {
            konsumartikelService = new KonsumartikelManager();
        }
        return konsumartikelService;
    }

    @Override
    public Konsumartikel konsumartikelHinzufuegen(Konsumartikel konsumartikel) throws Exception {
        return konsumartikelService.konsumartikelHinzufuegen(konsumartikel);
    }

    @Override
    public Konsumartikel konsumartikelAktualisieren(Konsumartikel konsumartikel) throws Exception {
        return konsumartikelService.konsumartikelAktualisieren(konsumartikel);
    }

    @Override
    public void konsumartikelLoeschen(Konsumartikel konsumartikel) throws Exception {
        konsumartikelService.konsumartikelLoeschen(konsumartikel);
    }

    @Override
    public Konsumartikel findKonsumartikelByBezeichnung(String bezeichnung) throws Exception {
        return konsumartikelService.findKonsumartikelByBezeichnung(bezeichnung);
    }

    @Override
    public List<Konsumartikel> findKonsumartikelByKategorie(String kategorie) throws Exception {
        return konsumartikelService.findKonsumartikelByKategorie(kategorie);
    }

    @Override
    public List<Konsumartikel> findKonsumartikelAll() throws Exception {
        return konsumartikelService.findKonsumartikelAll();
    }

    @Override
    public Getraenke getraenkeHinzufuegen(Getraenke getraenke) throws Exception {
        return konsumartikelService.getraenkeHinzufuegen(getraenke);
    }

    @Override
    public Getraenke getraenkeAktualisieren(Getraenke getraenke) throws Exception {
        return konsumartikelService.getraenkeAktualisieren(getraenke);
    }

    @Override
    public void getraenkeLoeschen(Getraenke getraenke) throws Exception {
        konsumartikelService.getraenkeLoeschen(getraenke);
    }

    @Override
    public List<Getraenke> findGetraenkeAll() throws Exception {
        return konsumartikelService.findGetraenkeAll();
    }

    @Override
    public Esswaren esswarenHinzufuegen(Esswaren esswaren) throws Exception {
        return konsumartikelService.esswarenHinzufuegen(esswaren);
    }

    @Override
    public Esswaren esswarenAktualisieren(Esswaren esswaren) throws Exception {
        return konsumartikelService.esswarenAktualisieren(esswaren);
    }

    @Override
    public void esswareLoeschen(Esswaren esswaren) throws Exception {
        konsumartikelService.esswareLoeschen(esswaren);
    }

    @Override
    public List<Esswaren> findEsswarenAll() throws Exception {
        return konsumartikelService.findEsswarenAll();
    }
}
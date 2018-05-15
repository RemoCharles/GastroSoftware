package impl;

import slgp.BestellManager;
import slgp.gastrosoftware.BestellService;
import slgp.gastrosoftware.RMIBestellService;
import slgp.gastrosoftware.model.BestellPosition;
import slgp.gastrosoftware.model.Bestellung;
import slgp.gastrosoftware.model.Tisch;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class RMIBestellManagerImpl extends UnicastRemoteObject implements RMIBestellService {

    private BestellService bestellService;

    public RMIBestellManagerImpl() throws RemoteException {

    }

    public BestellService getBestellService() {
        if (bestellService == null) {
            bestellService = new BestellManager();
        }
        return bestellService;
    }

    @Override
    public Bestellung bestellungHinzufuegen(Bestellung bestellung) throws Exception {
        return bestellService.bestellungHinzufuegen(bestellung);
    }

    @Override
    public Bestellung bestellungAktualisieren(Bestellung bestellung) throws Exception {
        return bestellService.bestellungAktualisieren(bestellung);
    }

    @Override
    public void bestellungLoeschen(Bestellung bestellung) throws Exception {
        bestellService.bestellungLoeschen(bestellung);
    }

    @Override
    public List<Bestellung> findBestellungAll() throws Exception {
        return bestellService.findBestellungAll();
    }

    @Override
    public List<Bestellung> findBestellungByDatum(LocalDate datum) throws Exception {
        return bestellService.findBestellungByDatum(datum);
    }

    @Override
    public List<Bestellung> findBestellungAllBezahlt(boolean bezahlt) throws Exception {
        return bestellService.findBestellungAllBezahlt(bezahlt);
    }

    @Override
    public List<Bestellung> findBestellungByTischNummer(Integer tischNummer) throws Exception {
        return bestellService.findBestellungByTischNummer(tischNummer);
    }

    @Override
    public Tisch tischHinzufuegen(Tisch tisch) throws Exception {
        return bestellService.tischHinzufuegen(tisch);
    }

    @Override
    public Tisch tischAktualisieren(Tisch tisch) throws Exception {
        return bestellService.tischAktualisieren(tisch);
    }

    @Override
    public void tischLoeschen(Tisch tisch) throws Exception {
        bestellService.tischLoeschen(tisch);
    }

    @Override
    public List<Tisch> findTischAll() throws Exception {
        return bestellService.findTischAll();
    }

    @Override
    public Tisch findTischByTischNummer(int tischNummer) throws Exception {
        return bestellService.findTischByTischNummer(tischNummer);
    }

    @Override
    public BestellPosition bestellPositionHinzufuegen(BestellPosition bestellPosition) throws Exception {
        return bestellService.bestellPositionHinzufuegen(bestellPosition);
    }

    @Override
    public BestellPosition bestellPositionAktualisieren(BestellPosition bestellPosition) throws Exception {
        return bestellService.bestellPositionAktualisieren(bestellPosition);
    }

    @Override
    public void bestellPositionLoeschen(BestellPosition bestellPosition) throws Exception {
        bestellService.bestellPositionLoeschen(bestellPosition);
    }

    @Override
    public List<BestellPosition> findBestellPositionAll() throws Exception {
        return bestellService.findBestellPositionAll();
    }
}
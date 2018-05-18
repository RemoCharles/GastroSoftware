package ch.hslu.slgp.gastrosoftware.rmi.server.impl;

import ch.hslu.slgp.gastrosoftware.api.BestellService;
import ch.hslu.slgp.gastrosoftware.business.BestellManager;
import ch.hslu.slgp.gastrosoftware.model.*;
import ch.hslu.slgp.gastrosoftware.rmi.api.RMIBestellService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class RMIBestellServiceImpl extends UnicastRemoteObject implements RMIBestellService {

    private BestellService bestellService;

    public RMIBestellServiceImpl() throws RemoteException {
    }

    public BestellService getBestellService() {
        if (bestellService == null) {
            bestellService = new BestellManager();
        }
        return bestellService;
    }

    @Override
    public Bestellung bestellungHinzufuegen(Bestellung bestellung) throws Exception {
        return getBestellService().bestellungHinzufuegen(bestellung);
    }

    @Override
    public Bestellung bestellungAktualisieren(Bestellung bestellung) throws Exception {
        return getBestellService().bestellungAktualisieren(bestellung);
    }

    @Override
    public void bestellungLoeschen(Bestellung bestellung) throws Exception {
        getBestellService().bestellungLoeschen(bestellung);
    }

    @Override
    public List<Bestellung> findBestellungAll() throws Exception {
        return getBestellService().findBestellungAll();
    }

    @Override
    public List<Bestellung> findBestellungByDatum(LocalDate datum) throws Exception {
        return getBestellService().findBestellungByDatum(datum);
    }

    @Override
    public List<Bestellung> findBestellungAllBezahlt(boolean bezahlt) throws Exception {
        return getBestellService().findBestellungAllBezahlt(bezahlt);
    }

    @Override
    public List<Bestellung> findBestellungByTischNummer(Integer tischNummer) throws Exception {
        return getBestellService().findBestellungByTischNummer(tischNummer);
    }

    @Override
    public Tisch tischHinzufuegen(Tisch tisch) throws Exception {
        return getBestellService().tischHinzufuegen(tisch);
    }

    @Override
    public Tisch tischAktualisieren(Tisch tisch) throws Exception {
        return getBestellService().tischAktualisieren(tisch);
    }

    @Override
    public void tischLoeschen(Tisch tisch) throws Exception {
        getBestellService().tischLoeschen(tisch);
    }

    @Override
    public List<Tisch> findTischAll() throws Exception {
        return getBestellService().findTischAll();
    }

    @Override
    public Tisch findTischByTischNummer(int tischNummer) throws Exception {
        return getBestellService().findTischByTischNummer(tischNummer);
    }

    @Override
    public BestellPosition bestellPositionHinzufuegen(BestellPosition bestellPosition) throws Exception {
        return getBestellService().bestellPositionHinzufuegen(bestellPosition);
    }

    @Override
    public BestellPosition bestellPositionAktualisieren(BestellPosition bestellPosition) throws Exception {
        return getBestellService().bestellPositionAktualisieren(bestellPosition);
    }

    @Override
    public void bestellPositionLoeschen(BestellPosition bestellPosition) throws Exception {
        getBestellService().bestellPositionLoeschen(bestellPosition);
    }

    @Override
    public List<BestellPosition> findBestellPositionAll() throws Exception {
        return getBestellService().findBestellPositionAll();
    }
}
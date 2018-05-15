package impl;

import slgp.RechnungManager;
import slgp.gastrosoftware.RechnungService;
import slgp.gastrosoftware.model.MAAbrechnung;
import slgp.gastrosoftware.model.TischRechnung;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIRechnungManagerImpl extends UnicastRemoteObject implements RechnungService {

    private RechnungService rechnungService;

    public RMIRechnungManagerImpl() throws RemoteException {

    }

    public RechnungService getRechnungService() {
        if (rechnungService == null) {
            rechnungService = new RechnungManager();
        }
        return rechnungService;
    }

    @Override
    public TischRechnung tischRechnungHinzufuegen(TischRechnung tischRechnung) throws Exception {
        return rechnungService.tischRechnungHinzufuegen(tischRechnung);
    }

    @Override
    public TischRechnung tischRechnungAktualisieren(TischRechnung tischRechnung) throws Exception {
        return rechnungService.tischRechnungAktualisieren(tischRechnung);
    }

    @Override
    public void tischRechnungLoeschen(TischRechnung tischRechnung) throws Exception {
        rechnungService.tischRechnungLoeschen(tischRechnung);
    }

    @Override
    public List<TischRechnung> findTischRechnungAll() throws Exception {
        return rechnungService.findTischRechnungAll();
    }

    @Override
    public MAAbrechnung maAbrechnungHinzufuegen(MAAbrechnung maAbrechnung) throws Exception {
        return rechnungService.maAbrechnungHinzufuegen(maAbrechnung);
    }

    @Override
    public MAAbrechnung maAbrechnungAktualisieren(MAAbrechnung maAbrechnung) throws Exception {
        return rechnungService.maAbrechnungAktualisieren(maAbrechnung);
    }

    @Override
    public void maAbrechnungLoeschen(MAAbrechnung maAbrechnung) throws Exception {
        rechnungService.maAbrechnungLoeschen(maAbrechnung);
    }

    @Override
    public List<MAAbrechnung> findMAAbrechnungAll() throws Exception {
        return rechnungService.findMAAbrechnungAll();
    }
}

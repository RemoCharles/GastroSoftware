package slgp.gastrosoftware.impl;

import slgp.RechnungManager;
import slgp.gastrosoftware.RMIRechnungService;
import slgp.gastrosoftware.RechnungService;
import slgp.gastrosoftware.model.MAAbrechnung;
import slgp.gastrosoftware.model.TischRechnung;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIRechnungServiceImpl extends UnicastRemoteObject implements RMIRechnungService {

    private RechnungService rechnungService;

    public RMIRechnungServiceImpl() throws RemoteException {

    }

    public RechnungService getRechnungService() {
        if (rechnungService == null) {
            rechnungService = new RechnungManager();
        }
        return rechnungService;
    }

    @Override
    public TischRechnung tischRechnungHinzufuegen(TischRechnung tischRechnung) throws Exception {
        return getRechnungService().tischRechnungHinzufuegen(tischRechnung);
    }

    @Override
    public TischRechnung tischRechnungAktualisieren(TischRechnung tischRechnung) throws Exception {
        return getRechnungService().tischRechnungAktualisieren(tischRechnung);
    }

    @Override
    public void tischRechnungLoeschen(TischRechnung tischRechnung) throws Exception {
        getRechnungService().tischRechnungLoeschen(tischRechnung);
    }

    @Override
    public List<TischRechnung> findTischRechnungAll() throws Exception {
        return getRechnungService().findTischRechnungAll();
    }

    @Override
    public MAAbrechnung maAbrechnungHinzufuegen(MAAbrechnung maAbrechnung) throws Exception {
        return getRechnungService().maAbrechnungHinzufuegen(maAbrechnung);
    }

    @Override
    public MAAbrechnung maAbrechnungAktualisieren(MAAbrechnung maAbrechnung) throws Exception {
        return getRechnungService().maAbrechnungAktualisieren(maAbrechnung);
    }

    @Override
    public void maAbrechnungLoeschen(MAAbrechnung maAbrechnung) throws Exception {
        getRechnungService().maAbrechnungLoeschen(maAbrechnung);
    }

    @Override
    public List<MAAbrechnung> findMAAbrechnungAll() throws Exception {
        return getRechnungService().findMAAbrechnungAll();
    }

}

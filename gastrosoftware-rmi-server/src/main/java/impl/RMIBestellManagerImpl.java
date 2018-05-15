package impl;

import slgp.gastrosoftware.RMIBestellService;
import slgp.gastrosoftware.model.Bestellung;

import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIBestellManagerImpl extends UnicastRemoteObject implements RMIBestellService {
    public RMIBestellManagerImpl() throws Exception{

    }

    @Override
    public List<Bestellung> findALl() throws Exception {
        return null;
    }
}

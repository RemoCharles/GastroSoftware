package ch.hslu.slgp.gastrosoftware.rmi.api;

import ch.hslu.slgp.gastrosoftware.api.KonsumartikelService;

import java.rmi.Remote;

public interface RMIKonsumartikelService extends KonsumartikelService, Remote {
    public static final String RO_NAME = "RO_KONSUMARTIKEL_SERVICE";

}
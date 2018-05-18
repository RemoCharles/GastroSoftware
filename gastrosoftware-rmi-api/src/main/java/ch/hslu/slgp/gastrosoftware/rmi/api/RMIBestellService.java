package ch.hslu.slgp.gastrosoftware.rmi.api;

import ch.hslu.slgp.gastrosoftware.api.BestellService;

import java.rmi.Remote;

public interface RMIBestellService extends BestellService, Remote {
    public static final String RO_NAME = "RO_BESTELL_SERVICE";

}

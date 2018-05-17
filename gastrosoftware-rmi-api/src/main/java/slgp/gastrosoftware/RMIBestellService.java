package slgp.gastrosoftware;

import java.rmi.Remote;

public interface RMIBestellService extends BestellService, Remote {
    public static final String RO_NAME = "RO_BESTELL_SERVICE";

}

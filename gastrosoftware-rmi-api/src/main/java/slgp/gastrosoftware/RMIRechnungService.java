package slgp.gastrosoftware;

import java.rmi.Remote;

public interface RMIRechnungService extends RechnungService, Remote {
    public static final String RO_NAME = "RO_RECHNUNG_SERVICE";

}
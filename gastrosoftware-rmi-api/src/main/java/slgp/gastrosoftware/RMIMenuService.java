package slgp.gastrosoftware;

import java.rmi.Remote;

public interface RMIMenuService extends MenuService, Remote {
    public static final String RO_NAME = "RO_MENU_SERVICE";

}
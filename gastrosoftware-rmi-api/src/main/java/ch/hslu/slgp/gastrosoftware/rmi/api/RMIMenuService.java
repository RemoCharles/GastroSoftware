package ch.hslu.slgp.gastrosoftware.rmi.api;

import ch.hslu.slgp.gastrosoftware.api.MenuService;

import java.rmi.Remote;

public interface RMIMenuService extends MenuService, Remote {
    public static final String RO_NAME = "RO_MENU_SERVICE";

}
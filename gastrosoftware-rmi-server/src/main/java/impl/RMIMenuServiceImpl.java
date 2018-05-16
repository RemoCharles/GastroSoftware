package impl;

import slgp.gastrosoftware.MenuManager;
import slgp.gastrosoftware.MenuService;
import slgp.gastrosoftware.RMIMenuService;
import slgp.gastrosoftware.model.Menukarte;
import slgp.gastrosoftware.model.Tagesmenu;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIMenuServiceImpl extends UnicastRemoteObject implements RMIMenuService {

    private MenuService menuService;

    public RMIMenuServiceImpl() throws RemoteException {

    }

    public MenuService getMenuService() {
        if (menuService == null) {
            menuService = new MenuManager();
        }
        return menuService;
    }

    @Override
    public Menukarte menukarteHinzufuegen(Menukarte menukarte) throws Exception {
        return getMenuService().menukarteHinzufuegen(menukarte);
    }

    @Override
    public Menukarte menukarteAktualisieren(Menukarte menukarte) throws Exception {
        return getMenuService().menukarteAktualisieren(menukarte);
    }

    @Override
    public void menukarteLoeschen(Menukarte menukarte) throws Exception {
        getMenuService().menukarteLoeschen(menukarte);
    }

    @Override
    public List<Menukarte> findMenukarteAll() throws Exception {
        return getMenuService().findMenukarteAll();
    }

    @Override
    public Tagesmenu tagesmenuHinzufuegen(Tagesmenu tagesmenu) throws Exception {
        return getMenuService().tagesmenuHinzufuegen(tagesmenu);
    }

    @Override
    public Tagesmenu tagesmenuAktualisieren(Tagesmenu tagesmenu) throws Exception {
        return getMenuService().tagesmenuAktualisieren(tagesmenu);
    }

    @Override
    public void tagesmenuLoeschen(Tagesmenu tagesmenu) throws Exception {
        getMenuService().tagesmenuLoeschen(tagesmenu);
    }

    @Override
    public List<Tagesmenu> findyTagesmenuByWochenTag(String wochenTag) throws Exception {
        return getMenuService().findyTagesmenuByWochenTag(wochenTag);
    }

    @Override
    public List<Tagesmenu> findTagesmenuAll() throws Exception {
        return getMenuService().findTagesmenuAll();
    }
}

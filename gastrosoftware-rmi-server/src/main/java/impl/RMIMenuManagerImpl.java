package impl;

import slgp.gastrosoftware.MenuManager;
import slgp.gastrosoftware.MenuService;
import slgp.gastrosoftware.model.Menukarte;
import slgp.gastrosoftware.model.Tagesmenu;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIMenuManagerImpl extends UnicastRemoteObject implements MenuService {

    private MenuService menuService;

    public RMIMenuManagerImpl() throws RemoteException {

    }

    public MenuService getMenuService() {
        if (menuService == null) {
            menuService = new MenuManager();
        }
        return menuService;
    }

    @Override
    public Menukarte menukarteHinzufuegen(Menukarte menukarte) throws Exception {
        return menuService.menukarteHinzufuegen(menukarte);
    }

    @Override
    public Menukarte menukarteAktualisieren(Menukarte menukarte) throws Exception {
        return menuService.menukarteAktualisieren(menukarte);
    }

    @Override
    public void menukarteLoeschen(Menukarte menukarte) throws Exception {
        menuService.menukarteLoeschen(menukarte);
    }

    @Override
    public List<Menukarte> findMenukarteAll() throws Exception {
        return menuService.findMenukarteAll();
    }

    @Override
    public Tagesmenu tagesmenuHinzufuegen(Tagesmenu tagesmenu) throws Exception {
        return menuService.tagesmenuHinzufuegen(tagesmenu);
    }

    @Override
    public Tagesmenu tagesmenuAktualisieren(Tagesmenu tagesmenu) throws Exception {
        return menuService.tagesmenuAktualisieren(tagesmenu);
    }

    @Override
    public void tagesmenuLoeschen(Tagesmenu tagesmenu) throws Exception {
        menuService.tagesmenuLoeschen(tagesmenu);
    }

    @Override
    public List<Tagesmenu> findyTagesmenuByWochenTag(String wochenTag) throws Exception {
        return menuService.findyTagesmenuByWochenTag(wochenTag);
    }

    @Override
    public List<Tagesmenu> findTagesmenuAll() throws Exception {
        return menuService.findTagesmenuAll();
    }
}

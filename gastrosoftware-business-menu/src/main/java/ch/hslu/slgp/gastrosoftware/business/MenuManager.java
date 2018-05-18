package ch.hslu.slgp.gastrosoftware.business;

import ch.hslu.slgp.gastrosoftware.api.MenuService;
import ch.hslu.slgp.gastrosoftware.model.*;
import ch.hslu.slgp.gastrosoftware.persister.*;
import ch.hslu.slgp.gastrosoftware.persister.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

public class MenuManager implements MenuService {

    private static Logger logger = LogManager.getLogger(MenuManager.class);

    private static MenuManager INSTANCE = new MenuManager();

    public static MenuManager getInstance() {
        return INSTANCE;
    }

    private TagesmenuDAO tagesmenuDAO;

    public TagesmenuDAO getTagesmenuDAO() {
        if (tagesmenuDAO == null) {
            tagesmenuDAO = new TagesmenuDAOImpl();
        }
        return tagesmenuDAO;
    }

    private MenukarteDAO menukarteDAO;

    public MenukarteDAO getMenukarteDAO() {
        if (menukarteDAO == null) {
            menukarteDAO = new MenukarteDAOImpl();
        }
        return menukarteDAO;
    }

    @Override
    public Menukarte menukarteHinzufuegen(Menukarte menukarte) throws Exception {
        try {
            return getMenukarteDAO().save(menukarte);
        } catch (Exception e) {
            String msg = "Menukarte konnten nicht gespeichert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Menukarte menukarteAktualisieren(Menukarte menukarte) throws Exception {
        try {
            return getMenukarteDAO().update(menukarte);
        } catch (Exception e) {
            String msg = "Menukarte konnten nicht aktualisiert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }    }

    @Override
    public void menukarteLoeschen(Menukarte menukarte) throws Exception {
        try {
            getMenukarteDAO().delete(menukarte);
        } catch (Exception e) {
            String msg = "Menukarte konnten nicht gelöscht werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Menukarte> findMenukarteAll() throws Exception {
        try {
            return getMenukarteDAO().findAll();
        } catch (Exception e) {
            String msg = "Es konnte keine Menukarte gefunden werden.";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Tagesmenu tagesmenuHinzufuegen(Tagesmenu tagesmenu) throws Exception {
        try {
            return getTagesmenuDAO().save(tagesmenu);
        } catch (Exception e) {
            String msg = "Tagesmenu konnten nicht gespeichert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Tagesmenu tagesmenuAktualisieren(Tagesmenu tagesmenu) throws Exception {
        try {
            return getTagesmenuDAO().update(tagesmenu);
        } catch (Exception e) {
            String msg = "Tagesmenu konnten nicht aktualisiert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public void tagesmenuLoeschen(Tagesmenu tagesmenu) throws Exception {
        try {
            getTagesmenuDAO().delete(tagesmenu);
        } catch (Exception e) {
            String msg = "Tagesmenu konnten nicht gelöscht werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Tagesmenu> findyTagesmenuByWochenTag(String wochenTag) throws Exception {
        try {
            return getTagesmenuDAO().findyByWochenTag(wochenTag);
        } catch (Exception e) {
            String msg = "Es konnte keine Menukarte für den Wochentag " + wochenTag + "gefunden werden.";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Tagesmenu> findTagesmenuAll() throws Exception {
        try {
            return getTagesmenuDAO().findAll();
        } catch (Exception e) {
            String msg = "Es konnte kein Tagesmenu gefunden werden.";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }
}

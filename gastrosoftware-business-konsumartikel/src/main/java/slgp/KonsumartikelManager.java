package slgp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.KonsumartikelService;
import slgp.gastrosoftware.model.Esswaren;
import slgp.gastrosoftware.model.Getraenke;
import slgp.gastrosoftware.model.Konsumartikel;
import slgp.gastrosoftware.persister.EsswarenDAO;
import slgp.gastrosoftware.persister.GetraenkeDAO;
import slgp.gastrosoftware.persister.KonsumartikelDAO;
import slgp.gastrosoftware.persister.impl.EsswarenDAOImpl;
import slgp.gastrosoftware.persister.impl.GetraenkeDAOImpl;
import slgp.gastrosoftware.persister.impl.KonsumartikelDAOImpl;

import java.util.List;

public class KonsumartikelManager implements KonsumartikelService {

    private static Logger logger = LogManager.getLogger(KonsumartikelManager.class);

    private static KonsumartikelManager INSTANCE = new KonsumartikelManager();

    public static KonsumartikelManager getInstance() {
        return INSTANCE;
    }

    private KonsumartikelDAO konsumartikelDAO;

    public KonsumartikelDAO getKonsumartikelDAO() {
        if (konsumartikelDAO == null) {
            konsumartikelDAO = new KonsumartikelDAOImpl();
        }
        return konsumartikelDAO;
    }

    private EsswarenDAO esswarenDAO;

    public EsswarenDAO getEsswarenDAO() {
        if (esswarenDAO == null) {
            esswarenDAO = new EsswarenDAOImpl();
        }
        return esswarenDAO;
    }

    private GetraenkeDAO getraenkeDAO;

    public GetraenkeDAO getGetraenkeDAO() {
        if (getraenkeDAO == null) {
            getraenkeDAO = new GetraenkeDAOImpl();
        }
        return getraenkeDAO;
    }

    @Override
    public Konsumartikel konsumartikelHinzufuegen(Konsumartikel konsumartikel) throws Exception {
        try {
            return getKonsumartikelDAO().save(konsumartikel);
        } catch (Exception e) {
            String msg = "Konsumartikel konnten nicht gespeichert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Konsumartikel konsumartikelAktualisieren(Konsumartikel konsumartikel) throws Exception {
        try {
            return getKonsumartikelDAO().update(konsumartikel);
        } catch (Exception e) {
            String msg = "Konsumartikel konnten nicht aktualisiert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public void konsumartikelLoeschen(Konsumartikel konsumartikel) throws Exception {
        try {
            getKonsumartikelDAO().delete(konsumartikel);
        } catch (Exception e) {
            String msg = "Konsumartikel konnten nicht gelöscht werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Konsumartikel findKonsumartikelByBezeichnung(String bezeichnung) throws Exception {
        try {
            return getKonsumartikelDAO().findByBezeichnung(bezeichnung);
        } catch (Exception e) {
            String msg = "Es konnte kein Konsumartikel mit der Bezeichnung " + bezeichnung + " gefunden werden.";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Konsumartikel> findKonsumartikelByKategorie(String kategorie) throws Exception {
        try {
            return getKonsumartikelDAO().findByKategorie(kategorie);
        } catch (Exception e) {
            String msg = "Es konnte kein Konsumartikel mit der kategorie " + kategorie + " gefunden werden.";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Konsumartikel> findKonsumartikelAll() throws Exception {
        try {
            return getKonsumartikelDAO().findAll();
        } catch (Exception e) {
            String msg = "Es konnte kein Konsumartikel gefunden werden.";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Getraenke getraenkeHinzufuegen(Getraenke getraenke) throws Exception {
        try {
            return getGetraenkeDAO().save(getraenke);
        } catch (Exception e) {
            String msg = "Getränk konnten nicht gespeichert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Getraenke getraenkeAktualisieren(Getraenke getraenke) throws Exception {
        try {
            return getGetraenkeDAO().update(getraenke);
        } catch (Exception e) {
            String msg = "Getränk konnten nicht aktualisiert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public void getraenkeLoeschen(Getraenke getraenke) throws Exception {
        try {
            getGetraenkeDAO().delete(getraenke);
        } catch (Exception e) {
            String msg = "Getränk konnten nicht gelöscht werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Getraenke> findGetraenkeAll() throws Exception {
        try {
            return getGetraenkeDAO().findAll();
        } catch (Exception e) {
            String msg = "Es konnte kein Getränk gefunden werden.";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Esswaren esswarenHinzufuegen(Esswaren esswaren) throws Exception {
        try {
            return getEsswarenDAO().save(esswaren);
        } catch (Exception e) {
            String msg = "Essware konnten nicht gespeichert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Esswaren esswarenAktualisieren(Esswaren esswaren) throws Exception {
        try {
            return getEsswarenDAO().update(esswaren);
        } catch (Exception e) {
            String msg = "Essware konnten nicht aktualisiert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public void esswareLoeschen(Esswaren esswaren) throws Exception {
        try {
            getEsswarenDAO().delete(esswaren);
        } catch (Exception e) {
            String msg = "Essware konnten nicht gelöscht werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Esswaren> findEsswarenAll() throws Exception {
        try {
            return getEsswarenDAO().findAll();
        } catch (Exception e) {
            String msg = "Es konnte keine Esswaren gefunden werden.";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }
}

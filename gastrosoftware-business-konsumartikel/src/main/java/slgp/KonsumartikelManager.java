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

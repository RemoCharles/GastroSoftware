package slgp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.RechnungService;
import slgp.gastrosoftware.model.MAAbrechnung;
import slgp.gastrosoftware.model.TischRechnung;
import slgp.gastrosoftware.persister.MAAbrechnungDAO;
import slgp.gastrosoftware.persister.TischRechnungDAO;
import slgp.gastrosoftware.persister.impl.MAAbrechnungDAOImpl;
import slgp.gastrosoftware.persister.impl.TischRechnungDAOImpl;

import java.util.List;

public class RechnungManager implements RechnungService {

    private static Logger logger = LogManager.getLogger(RechnungManager.class);


    private TischRechnungDAO tischRechnungDAO;

    public TischRechnungDAO getTischRechnungDAO() {
        if (tischRechnungDAO == null) {
            tischRechnungDAO = new TischRechnungDAOImpl();
        }
        return tischRechnungDAO;
    }

    private MAAbrechnungDAO maAbrechnungDAO;

    public MAAbrechnungDAO getMaAbrechnungDAO() {
        if (maAbrechnungDAO == null) {
            maAbrechnungDAO = new MAAbrechnungDAOImpl();
        }
        return maAbrechnungDAO;
    }

    @Override
    public TischRechnung tischRechnungHinzufuegen(TischRechnung tischRechnung) throws Exception {
        try {
            return getTischRechnungDAO().save(tischRechnung);
        } catch (Exception e) {
            String msg = "Tischrechnung konnten nicht gespeichert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public TischRechnung tischRechnungAktualisieren(TischRechnung tischRechnung) throws Exception {
        try {
            return getTischRechnungDAO().update(tischRechnung);
        } catch (Exception e) {
            String msg = "Tischrechnung konnten nicht aktualisiert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public void tischRechnungLoeschen(TischRechnung tischRechnung) throws Exception {
        try {
            getTischRechnungDAO().delete(tischRechnung);
        } catch (Exception e) {
            String msg = "Tischrechnung konnten nicht gelöscht werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<TischRechnung> findTischRechnungAll() throws Exception {
        try {
            return getTischRechnungDAO().findAll();
        } catch (Exception e) {
            String msg = "Es konnte keine Tischrechnung gefunden werden.";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public MAAbrechnung maAbrechnungHinzufuegen(MAAbrechnung maAbrechnung) throws Exception {
        try {
            return getMaAbrechnungDAO().save(maAbrechnung);
        } catch (Exception e) {
            String msg = "Mitarbeiter Abrechnung konnten nicht gespeichert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public MAAbrechnung maAbrechnungAktualisieren(MAAbrechnung maAbrechnung) throws Exception {
        try {
            return getMaAbrechnungDAO().update(maAbrechnung);
        } catch (Exception e) {
            String msg = "Mitarbeiter Abrechnung konnten nicht aktualisiert werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public void maAbrechnungLoeschen(MAAbrechnung maAbrechnung) throws Exception {
        try {
            getMaAbrechnungDAO().delete(maAbrechnung);
        } catch (Exception e) {
            String msg = "Mitarbeiter Abrechnung konnten nicht gelöschts werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<MAAbrechnung> findMAAbrechnungAll() throws Exception {
        try {
            return getMaAbrechnungDAO().findAll();
        } catch (Exception e) {
            String msg = "Es konnte keine Mitarbeiter Abrechnung gefunden werden.";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }
}

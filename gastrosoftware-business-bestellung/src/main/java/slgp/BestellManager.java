package slgp;

import slgp.gastrosoftware.BestellService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slgp.gastrosoftware.model.BestellPosition;
import slgp.gastrosoftware.model.Bestellung;
import slgp.gastrosoftware.model.Tisch;
import slgp.gastrosoftware.persister.BestellPositionDAO;
import slgp.gastrosoftware.persister.BestellungDAO;
import slgp.gastrosoftware.persister.TischDAO;
import slgp.gastrosoftware.persister.impl.BestellPositionDAOImpl;
import slgp.gastrosoftware.persister.impl.BestellungDAOImpl;
import slgp.gastrosoftware.persister.impl.TischDAOImpl;

import java.time.LocalDate;
import java.util.List;

public class BestellManager implements BestellService {

    private static Logger logger = LogManager.getLogger(BestellManager.class);

    private BestellungDAO bestellunDAO;

    public BestellungDAO getBestellunDAO() {

        if (bestellunDAO == null) {
            bestellunDAO = new BestellungDAOImpl();
        }
        return bestellunDAO;
    }

    private TischDAO tischDAO;

    public TischDAO getTischDAO() {
        if (tischDAO == null) {
            tischDAO = new TischDAOImpl();
        }
        return tischDAO;
    }

    private BestellPositionDAO bestellPositionDAO;

    public BestellPositionDAO getBestellPositionDAO(){
        if (bestellPositionDAO == null){
            bestellPositionDAO = new BestellPositionDAOImpl();
        }
        return  bestellPositionDAO;
    }

    @Override
    public List<Bestellung> findBestellungALl() throws Exception {
        try {
            return getBestellunDAO().findAll();
        } catch (Exception e) {
            String msg = "Bestellungen konnten nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Bestellung> findBestellungByDatum(LocalDate datum) throws Exception {
        try {
            return getBestellunDAO().findByDatum(datum);
        } catch (Exception e) {
            String msg = "Bestellungen mit Datum " + datum + " konnten nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Bestellung> findBestellungAllBezahlt(boolean bezahlt) throws Exception {
        try {
            return getBestellunDAO().findAllBezahlt(bezahlt);
        } catch (Exception e) {
            String msg = "Bestellungen mit Bezahlstatus " + bezahlt + " konnten nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Bestellung> findBestellungByTischNummer(Integer tischNummer) throws Exception {
        try {
            return getBestellunDAO().findByTischNummer(tischNummer);
        } catch (Exception e) {
            String msg = "Bestellungen mit Tischnummer " + tischNummer + " konnten nicht gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<Tisch> findTischAll() throws Exception {
        try {
            return getTischDAO().findAll();
        } catch (Exception e) {
            String msg = "Es konnten keine Tische gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public Tisch findTischByTischNummer(int tischNummer) throws Exception {
        try {
            return getTischDAO().findByTischNummer(tischNummer);
        } catch (Exception e) {
            String msg = "Es konnte kein Tisch mit der Nummer " + tischNummer + "gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }

    @Override
    public List<BestellPosition> findBestellPositionAll() throws Exception {
        try {
            return getBestellPositionDAO().findAll();
        } catch (Exception e) {
            String msg = "Es konnten keine Bestellpositionen gefunden werden";
            logger.error(msg, e);
            throw new Exception(msg);
        }
    }
}

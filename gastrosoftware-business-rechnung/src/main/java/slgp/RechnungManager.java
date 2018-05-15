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
        if (tischRechnungDAO == null){
            tischRechnungDAO = new TischRechnungDAOImpl();
        }
        return tischRechnungDAO;
    }

    private MAAbrechnungDAO maAbrechnungDAO;

    public MAAbrechnungDAO getMaAbrechnungDAO() {
        if (maAbrechnungDAO == null){
            maAbrechnungDAO = new MAAbrechnungDAOImpl();
        }
        return maAbrechnungDAO;
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

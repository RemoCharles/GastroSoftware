package api;

import slgp.gastrosoftware.model.MAAbrechnung;
import slgp.gastrosoftware.model.Rechnung;
import slgp.gastrosoftware.model.TischRechnung;

public interface PrinterService {

    /**
     * Erstellt die Rechnung als PDF.
     *
     * @param rechnung
     */
    void printTischRechnungAlsPdf(TischRechnung rechnung) throws Exception;

    /**
     * Erstellt die Mitarbeiterabrechnung als PDF
     *
     * @param maAbrechnung
     */
    void printMAAbrechnungAlsPdf(MAAbrechnung maAbrechnung) throws Exception;
}

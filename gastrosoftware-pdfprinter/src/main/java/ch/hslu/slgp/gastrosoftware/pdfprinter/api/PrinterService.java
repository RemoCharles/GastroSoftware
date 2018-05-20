package ch.hslu.slgp.gastrosoftware.pdfprinter.api;

import ch.hslu.slgp.gastrosoftware.model.MAAbrechnung;
import ch.hslu.slgp.gastrosoftware.model.Mitarbeiter;
import ch.hslu.slgp.gastrosoftware.model.TischRechnung;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PrinterService extends Remote {

    /**
     * Erstellt die Rechnung als PDF.
     *
     * @param rechnung
     */
    void printTischRechnungAlsPdf(TischRechnung rechnung) throws RemoteException, Exception;

    /**
     * Erstellt die Mitarbeiterabrechnung als PDF
     *
     * @param maAbrechnungList
     */
    void printMAAbrechnungAlsPdf(List<MAAbrechnung> maAbrechnungList, Mitarbeiter mitarbeiter) throws Exception;
}

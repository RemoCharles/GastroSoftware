package ch.hslu.slgp.gastrosoftware.pdfprinter.api;

import ch.hslu.slgp.gastrosoftware.model.TischRechnung;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrinterService extends Remote {

    /**
     * Erstellt die Rechnung als PDF.
     *
     * @param rechnung
     */
    void printTischRechnungAlsPdf(TischRechnung rechnung) throws RemoteException, Exception;

//    /**
//     * Erstellt die Mitarbeiterabrechnung als PDF
//     *
//     * @param maAbrechnung
//     */
//    void printMAAbrechnungAlsPdf(MAAbrechnung maAbrechnung) throws Exception;
}

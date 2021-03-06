package ch.hslu.slgp.gastrosoftware.pdfprinter;

import ch.hslu.slgp.gastrosoftware.model.*;
import ch.hslu.slgp.gastrosoftware.pdfprinter.api.PrinterService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class PDFPrinter extends UnicastRemoteObject implements PrinterService {

    private static Logger logger = LogManager.getLogger(PDFPrinter.class);


    public PDFPrinter() throws RemoteException {
    }


    /**
     * Erstellt die Rechnung als PDF.
     *
     * @param tischRechnung
     */
    @Override
    public void printTischRechnungAlsPdf(TischRechnung tischRechnung) throws Exception {

        SimpleDateFormat sdfName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
        FileOutputStream fos = null;

        try {
            int cnt = 1;

            Properties props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("printer.properties"));
            String outputDir = ausgabeVerzeichnis(props);

            String defaultFileName = props.getProperty("rechnung_default_file_name");

            Document pdfDocument = new Document();
            fos = new FileOutputStream(
                    outputDir + File.separator + sdfName.format(new Date()) + "-" + defaultFileName + ".pdf");
            PdfWriter.getInstance(pdfDocument, fos);
            pdfDocument.open();

            /* Fonts */
            Font anschriftFont = FontFactory.getFont("Courier", 8, BaseColor.BLACK);
            Font titelFont = FontFactory.getFont("Courier", 14, BaseColor.BLACK);
            titelFont.setStyle(Font.BOLD);
            Font zwischenzeileFont = FontFactory.getFont("Courier", 6, BaseColor.BLACK);
            Font tableFont = FontFactory.getFont("Courier", 10, BaseColor.BLACK);
            Font descriptionFont = FontFactory.getFont("Courier", 8, BaseColor.BLACK);

            /* Anschrift zusammenstellen */
            StringBuilder sb = new StringBuilder();

            sb.append(props.getProperty("anschrift_zeile_1")).append("\n");
            sb.append(props.getProperty("anschrift_zeile_2")).append("\n");
            sb.append(props.getProperty("anschrift_zeile_3")).append("\n");
            sb.append(props.getProperty("anschrift_zeile_4")).append("\n\n\n\n\n");

            Paragraph paragraph = new Paragraph(sb.toString(), anschriftFont);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            pdfDocument.add(paragraph);

            /* Tabelle */
            PdfPTable tbl = new PdfPTable(5);

            /* Spaltenbreite definieren */
            float[] columnWidths = new float[]{8f, 52f, 15f, 10f, 15f};
            tbl.setWidths(columnWidths);

            PdfPCell cell = new PdfPCell();

            cell = new PdfPCell(new Phrase("Tischrechnung", titelFont));
            cell.setColspan(5);
            cell.setBorderWidth(0);
            tbl.addCell(cell);

            /* Zwischenzeile */
            cell = new PdfPCell(new Phrase(" ", zwischenzeileFont));
            cell.setColspan(5);
            cell.setBorderWidth(0);
            tbl.addCell(cell);

            /* Überschriften */
            cell = new PdfPCell(new Phrase("Nr.", tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            tbl.addCell(cell);

            cell = new PdfPCell(new Phrase("Beschreibung", tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            tbl.addCell(cell);

            cell = new PdfPCell(new Phrase("Preis [CHF]", tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tbl.addCell(cell);

            cell = new PdfPCell(new Phrase("Anzahl", tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tbl.addCell(cell);

            cell = new PdfPCell(new Phrase("Betrag [CHF]", tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tbl.addCell(cell);

            List<Bestellung> bestellungList = tischRechnung.getBestellungList();
            for (Bestellung bestellung : bestellungList) {
                for (BestellPosition pos : bestellung.getKonsumartikel()) {

                    cell = new PdfPCell(new Phrase("" + cnt++, tableFont));
                    cell.setBorderColor(BaseColor.BLACK);
                    tbl.addCell(cell);

                    cell = new PdfPCell(new Phrase(pos.getBezeichnung(), descriptionFont));
                    cell.setBorderColor(BaseColor.BLACK);
                    tbl.addCell(cell);

                    cell = new PdfPCell(new Phrase("" + pos.getPreis(), tableFont));
                    cell.setBorderColor(BaseColor.BLACK);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tbl.addCell(cell);

                    cell = new PdfPCell(new Phrase("" + pos.getAnzahl(), tableFont));
                    cell.setBorderColor(BaseColor.BLACK);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tbl.addCell(cell);

                    cell = new PdfPCell(new Phrase("" + pos.getBetrag(), tableFont));
                    cell.setBorderColor(BaseColor.BLACK);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tbl.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("Totalbetrag", tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            cell.setColspan(4);
            tbl.addCell(cell);

            cell = new PdfPCell(new Phrase("" + tischRechnung.getSummeBestellungen(), tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tbl.addCell(cell);

            pdfDocument.add(tbl);

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy ' um ' HH:mm");
            String zeitpunkt = sdf.format(new Date());

            Font datumFont = FontFactory.getFont("Courier", 8, BaseColor.BLACK);

            paragraph = new Paragraph("\n\nDatum / Zeit: " + zeitpunkt, datumFont);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            pdfDocument.add(paragraph);
            pdfDocument.close();

        } catch (Exception e) {
            logger.error("Fehler bei der Generierung der Rechnung als PDF: ", e);
            throw new RuntimeException(e);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    @Override
    public void printMAAbrechnungAlsPdf(List<MAAbrechnung> maAbrechnungList, Mitarbeiter mitarbeiter) throws Exception {
        SimpleDateFormat sdfName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
        FileOutputStream fos = null;

        try {
            int cnt = 1;

            Properties props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("printer.properties"));
            String outputDir = ausgabeVerzeichnis(props);

            String defaultFileName = props.getProperty("maabrechnung_default_file_name");

            Document pdfDocument = new Document();
            fos = new FileOutputStream(
                    outputDir + File.separator + sdfName.format(new Date()) + "-" + defaultFileName + ".pdf");
            PdfWriter.getInstance(pdfDocument, fos);
            pdfDocument.open();

            /* Fonts */
            Font anschriftFont = FontFactory.getFont("Courier", 8, BaseColor.BLACK);
            Font titelFont = FontFactory.getFont("Courier", 14, BaseColor.BLACK);
            titelFont.setStyle(Font.BOLD);
            Font zwischenzeileFont = FontFactory.getFont("Courier", 6, BaseColor.BLACK);
            Font tableFont = FontFactory.getFont("Courier", 10, BaseColor.BLACK);
            Font descriptionFont = FontFactory.getFont("Courier", 8, BaseColor.BLACK);

            /* Anschrift zusammenstellen */
            StringBuilder sb = new StringBuilder();

            sb.append(mitarbeiter.getName()).append("\n");
            sb.append(mitarbeiter.getVorname()).append("\n");
            sb.append(mitarbeiter.getEmail()).append("\n");
            sb.append(mitarbeiter.getTelefon()).append("\n\n\n\n\n");

            Paragraph paragraph = new Paragraph(sb.toString(), anschriftFont);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            pdfDocument.add(paragraph);

            /* Tabelle */
            PdfPTable tbl = new PdfPTable(3);



            /* Spaltenbreite definieren */
            float[] columnWidths = new float[]{8f, 52f, 15f};
            tbl.setWidths(columnWidths);

            PdfPCell cell = new PdfPCell();

            cell = new PdfPCell(new Phrase("Mitarbeiterabrechnung", titelFont));
            cell.setColspan(5);
            cell.setBorderWidth(0);
            tbl.addCell(cell);

            /* Zwischenzeile */
            cell = new PdfPCell(new Phrase(" ", zwischenzeileFont));
            cell.setColspan(5);
            cell.setBorderWidth(0);
            tbl.addCell(cell);

            /* Überschriften */
            cell = new PdfPCell(new Phrase("Nr.", tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            tbl.addCell(cell);

            cell = new PdfPCell(new Phrase("Datum", tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            tbl.addCell(cell);

            cell = new PdfPCell(new Phrase("Umsatz [CHF]", tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tbl.addCell(cell);


            for (MAAbrechnung maAbrechnung : maAbrechnungList) {

                cell = new PdfPCell(new Phrase("" + cnt++, tableFont));
                cell.setBorderColor(BaseColor.BLACK);
                tbl.addCell(cell);

                cell = new PdfPCell(new Phrase("" + maAbrechnung.getDatum(), tableFont));
                cell.setBorderColor(BaseColor.BLACK);
                tbl.addCell(cell);

                cell = new PdfPCell(new Phrase("" + maAbrechnung.getUmsatz(), tableFont));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                tbl.addCell(cell);

            }

            pdfDocument.add(tbl);

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy ' um ' HH:mm");
            String zeitpunkt = sdf.format(new Date());

            Font datumFont = FontFactory.getFont("Courier", 8, BaseColor.BLACK);

            paragraph = new Paragraph("\n\nDatum / Zeit: " + zeitpunkt, datumFont);
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            pdfDocument.add(paragraph);
            pdfDocument.close();

        } catch (Exception e) {
            logger.error("Fehler bei der Generierung der Rechnung als PDF: ", e);
            throw new RuntimeException(e);
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }


    /**
     * Helper-Methode
     */
    private String ausgabeVerzeichnis(Properties props) {

        String outputDir = props.getProperty("output_dir");

        File f = new File(outputDir);

        if (!f.exists() && !f.isDirectory()) {
            logger.warn("WARNUNG: Das Verzeichnis \'" + outputDir + "\' existiert nicht! "
                    + "Bitte passen Sie den Wert für das Ausgabeverzechnis in der \'printer.properties\'-Datei (Property \'output_dir\') "
                    + "des Subprojekts \'moebelhaus-pdfprinter\' an. "
                    + "Inzwischen wird das Home-Verzeichnis des Benutzers als Ausgabeverzeichnis verwendet.");

            outputDir = System.getProperty("user.home");
        }

        return outputDir;
    }
}

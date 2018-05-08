import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import api.PrinterService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import slgp.gastrosoftware.model.*;

public class PDFPrinter implements PrinterService {

    private static Logger logger = LogManager.getLogger(PDFPrinter.class);

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

            /* Rechnung-Nr. */
            cell = new PdfPCell(new Phrase("RECHNUNG NR. " + tischRechnung.getId(), titelFont));
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

                    cell = new PdfPCell(new Phrase("" + pos.getPreis(), tableFont));
                    cell.setBorderColor(BaseColor.BLACK);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    tbl.addCell(cell);
                }
            }

            cell = new PdfPCell(new Phrase("Totalbetrag", tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            cell.setColspan(4);
            tbl.addCell(cell);

            //cell = new PdfPCell(new Phrase("" + tischRechnung.getBetrag(), tableFont));
            cell.setBorderColor(BaseColor.BLACK);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tbl.addCell(cell);

            pdfDocument.add(tbl);

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy ' um ' HH:mm:ss");
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

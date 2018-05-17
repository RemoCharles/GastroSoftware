package slgp.gastrosoftware;

import slgp.gastrosoftware.model.Menukarte;
import slgp.gastrosoftware.model.Tagesmenu;

import java.util.List;

public interface MenuService {
    /**
     * Speichert neue Menukarte
     *
     * @param menukarte
     * @return
     * @throws Exception
     */
    Menukarte menukarteHinzufuegen(Menukarte menukarte) throws Exception;
    /**
     * Aktualisiert Menukarte
     *
     * @param menukarte
     * @return
     * @throws Exception
     */
    Menukarte menukarteAktualisieren(Menukarte menukarte) throws Exception;
    /**
     * löscht Menukarte
     *
     * @param menukarte
     * @return
     * @throws Exception
     */
    void menukarteLoeschen(Menukarte menukarte) throws Exception;

    /**
     * Liefert die Menukarte mit allen Elementen als Liste zurück.
     *
     * @return
     * @throws Exception
     */
    List<Menukarte> findMenukarteAll() throws Exception;

    /**
     * Speichert neues Tagesmenu
     *
     * @param tagesmenu
     * @return
     * @throws Exception
     */
    Tagesmenu tagesmenuHinzufuegen(Tagesmenu tagesmenu) throws Exception;
    /**
     * Aktualisiert Tagesmenu
     *
     * @param tagesmenu
     * @return
     * @throws Exception
     */
    Tagesmenu tagesmenuAktualisieren(Tagesmenu tagesmenu) throws Exception;
    /**
     * löscht Tagesmenu
     *
     * @param tagesmenu
     * @return
     * @throws Exception
     */
    void tagesmenuLoeschen(Tagesmenu tagesmenu) throws Exception;

    /**
     * Liefert das Tagesmenu zu einem gew�nschten Wochentag zur�ck.
     *
     * @param wochenTag
     * @return
     * @throws Exception
     */
    List<Tagesmenu> findyTagesmenuByWochenTag(String wochenTag) throws Exception;

    /**
     * Liefert alle Tagesmenu zurueck.
     *
     * @param
     * @return
     * @throws Exception
     */
    List<Tagesmenu> findTagesmenuAll() throws Exception;
}

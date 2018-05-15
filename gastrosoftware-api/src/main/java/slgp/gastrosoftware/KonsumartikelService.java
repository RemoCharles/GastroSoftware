package slgp.gastrosoftware;

import slgp.gastrosoftware.model.Getraenke;
import slgp.gastrosoftware.model.Konsumartikel;
import slgp.gastrosoftware.model.Esswaren;

import java.util.List;

public interface KonsumartikelService {
    /**
     * Speichert neuen Konsumartikel
     *
     * @param konsumartikel
     * @return
     * @throws Exception
     */
    Konsumartikel konsumartikelHinzufuegen(Konsumartikel konsumartikel) throws Exception;
    /**
     * Aktualisiert Konsumartikel
     *
     * @param konsumartikel
     * @return
     * @throws Exception
     */
    Konsumartikel konsumartikelAktualisieren(Konsumartikel konsumartikel) throws Exception;
    /**
     * löscht Konsumartikel
     *
     * @param konsumartikel
     * @return
     * @throws Exception
     */
    void konsumartikelLoeschen(Konsumartikel konsumartikel) throws Exception;

    /**
     * Liefert den Konsumartikel zurück, dessen Bezeichnung übergeben wurde.
     *
     * @param bezeichnung
     * @return
     * @throws Exception
     */
    Konsumartikel findKonsumartikelByBezeichnung(String bezeichnung) throws Exception;

    /**
     * Liefert alle Konsumartikel zurück der angegebenen Kategorie
     *
     * @param kategorie
     * @return
     * @throws Exception
     */
    List<Konsumartikel> findKonsumartikelByKategorie(String kategorie) throws Exception;

    /**
     * Liefert alle Konsumartikel zurück
     *
     * @return
     * @throws Exception
     */
    List<Konsumartikel> findKonsumartikelAll() throws Exception;

    /**
     * Speichert neues Getränk
     *
     * @param getraenke
     * @return
     * @throws Exception
     */
    Getraenke getraenkeHinzufuegen(Getraenke getraenke) throws Exception;
    /**
     * Aktualisiert Getränk
     *
     * @param getraenke
     * @return
     * @throws Exception
     */
    Getraenke getraenkeAktualisieren(Getraenke getraenke) throws Exception;
    /**
     * löscht Getränk
     *
     * @param getraenke
     * @return
     * @throws Exception
     */
    void getraenkeLoeschen(Getraenke getraenke) throws Exception;

    /**
     * Liefert alle Getränke zurück
     *
     * @return
     * @throws Exception
     */
    List<Getraenke> findGetraenkeAll() throws Exception;

    /**
     * Speichert neue Essware
     *
     * @param esswaren
     * @return
     * @throws Exception
     */
    Esswaren esswarenHinzufuegen(Esswaren esswaren) throws Exception;
    /**
     * Aktualisiert Essware
     *
     * @param esswaren
     * @return
     * @throws Exception
     */
    Esswaren esswarenAktualisieren(Esswaren esswaren) throws Exception;
    /**
     * löscht Essware
     *
     * @param esswaren
     * @return
     * @throws Exception
     */
    void esswareLoeschen(Esswaren esswaren) throws Exception;

    /**
     * Liefert alle Esswaren zurück
     *
     * @return
     * @throws Exception
     */
    List<Esswaren> findEsswarenAll() throws Exception;
}

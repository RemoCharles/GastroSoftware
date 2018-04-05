package slgp.gastrosoftware.zentrale.persister.api;

import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;

import java.util.List;

public interface EsswarenDAO {
    /**
     * Liefert alle Esswaren zurück
     *
     * @return
     * @throws Exception
     */
    List<Esswaren> showAll() throws Exception;
}

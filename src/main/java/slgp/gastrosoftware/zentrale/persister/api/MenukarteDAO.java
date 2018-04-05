package slgp.gastrosoftware.zentrale.persister.api;

import java.util.List;

import slgp.gastrosoftware.zentrale.persister.domain.Menukarte;

public interface MenukarteDAO extends GenericPersisterDAO<Menukarte>{

	/**
	 * Liefert die Menukarte mit allen Elementen als Liste zurück.
	 *
	 * @return
	 * @throws Exception
	 */
	List<Menukarte> showAll() throws Exception;
}

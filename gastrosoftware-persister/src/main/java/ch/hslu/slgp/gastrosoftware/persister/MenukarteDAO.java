package ch.hslu.slgp.gastrosoftware.persister;

import ch.hslu.slgp.gastrosoftware.model.Menukarte;

import java.util.List;

public interface MenukarteDAO extends GenericPersisterDAO<Menukarte>{

	/**
	 * Liefert die Menukarte mit allen Elementen als Liste zurück.
	 *
	 * @return
	 * @throws Exception
	 */
	List<Menukarte> showAll() throws Exception;
}

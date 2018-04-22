package slgp.gastrosoftware.persister;

import java.util.List;

import slgp.gastrosoftware.model.Menukarte;

public interface MenukarteDAO extends GenericPersisterDAO<Menukarte>{

	/**
	 * Liefert die Menukarte mit allen Elementen als Liste zurück.
	 *
	 * @return
	 * @throws Exception
	 */
	List<Menukarte> showAll() throws Exception;
}

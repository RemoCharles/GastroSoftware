package slgp.gastrosoftware.zentrale.persister.util;


import slgp.gastrosoftware.zentrale.persister.domain.Esswaren;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


public class DbHelper {

    public static void deleteEsswaren() {

        EntityManager em = null;
        TypedQuery<Esswaren> tQuery = null;
        List<Esswaren> liste;

        // TODO - vervollstaendigen ...

    }

}

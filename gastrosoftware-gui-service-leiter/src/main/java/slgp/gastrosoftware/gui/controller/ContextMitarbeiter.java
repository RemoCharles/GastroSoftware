package slgp.gastrosoftware.gui.controller;

import slgp.gastrosoftware.model.Mitarbeiter;

public class ContextMitarbeiter {
    private static final ContextMitarbeiter INSTANCE = new ContextMitarbeiter();

    private Mitarbeiter mitarbeiter;

    private ContextMitarbeiter() {}

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public static ContextMitarbeiter getInstance() {
        return INSTANCE;
    }



}



import slgp.gastrosoftware.model.Mitarbeiter;

public class Context {
    private static final Context INSTANCE = new Context();

    private Mitarbeiter mitarbeiter;

    private Context() {}

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public static Context getInstance() {
        return INSTANCE;
    }
}

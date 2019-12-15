package projektKompetencyjny;

public class setGlobalNauczyciel {

    static Nauczyciel nauczyciel;

    public static Nauczyciel getNauczyciel() {
        return nauczyciel;
    }

    public static void setNauczyciel(Nauczyciel nauczyciel) {
        setGlobalNauczyciel.nauczyciel = nauczyciel;
    }

}

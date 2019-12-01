package projektKompetencyjny;

public class setGlobalUczen {

    static Uczen uczen;

    public static Uczen getUczen() {
        return uczen;
    }

    public static void setUczen(Uczen uczen) {
        setGlobalUczen.uczen = uczen;
    }

}

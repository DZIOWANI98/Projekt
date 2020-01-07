package projektKompetencyjny;

import javafx.fxml.FXMLLoader;

import java.util.ArrayList;
import java.util.List;

public class setGlobalRodzic {

    static Rodzic rodzic;

    public static Rodzic getRodzic() {
        return rodzic;
    }

    public static void setRodzic(Rodzic rodzic) {
        setGlobalRodzic.rodzic = rodzic;
        List<Uczen> uczniowie = rodzic.getUczen();
        setGlobalUczen.setUczen(uczniowie.get(0));
    }

}

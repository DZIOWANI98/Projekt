package projektKompetencyjny.data;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import projektKompetencyjny.Uczen;

import java.net.URL;
import java.util.ResourceBundle;

public class dataController implements Initializable {
    static Uczen uczen;

    @FXML
    private Label imie;

    @FXML
    private Label nazwisko;

    @FXML
    private Label miejsceZamieszkania;

    @FXML
    private Label dataUrodzenia;

    @FXML
    private Label miejsceUrodzenia;

    @FXML
    private Label email;

    @FXML
    private Label klasa;

    @FXML
    private Label czyDojezdza;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imie.setText(uczen.getName());
        nazwisko.setText(uczen.getNazwisko());
        miejsceZamieszkania.setText(uczen.getMiejsceZamieszkania());
        dataUrodzenia.setText(uczen.getDataUrodzenia());
        miejsceUrodzenia.setText(uczen.getMiejsceUrodzenia());
        email.setText(uczen.getEmail());
        klasa.setText(Integer.toString(uczen.getIdKlasy()));

        if(uczen.isCzyDojezdza()) {
            czyDojezdza.setText("tak");
        } else {
            czyDojezdza.setText("nie");
        }

    }

    public static void setUczen(Uczen uczen) {
        dataController.uczen = uczen;
    }
}

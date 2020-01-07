package projektKompetencyjny.rodzicFXML;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import projektKompetencyjny.Rodzic;
import projektKompetencyjny.Uczen;

import java.net.URL;
import java.util.ResourceBundle;

import static projektKompetencyjny.setGlobalUczen.getUczen;
import static projektKompetencyjny.setGlobalRodzic.getRodzic;

public class UiDataRodzic implements Initializable {
    Uczen uczen = getUczen();
    Rodzic rodzic = getRodzic();

    @FXML
    private Label tytulowyLabel;

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
        tytulowyLabel.setText("Witaj " + rodzic.getImie() + " " + rodzic.getNazwisko() + " !");
        imie.setText(uczen.getName());
        nazwisko.setText(uczen.getNazwisko());
        miejsceZamieszkania.setText(uczen.getMiejsceZamieszkania());
        dataUrodzenia.setText(uczen.getDataUrodzenia());
        miejsceUrodzenia.setText(uczen.getMiejsceUrodzenia());
        email.setText(uczen.getEmail());
        klasa.setText(uczen.getIdKlasy().getNazwa_klasy());

        if (uczen.isCzyDojezdza()) {
            czyDojezdza.setText("tak");
        } else {
            czyDojezdza.setText("nie");
        }

    }
}

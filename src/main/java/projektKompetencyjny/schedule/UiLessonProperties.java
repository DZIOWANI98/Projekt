package projektKompetencyjny.schedule;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import projektKompetencyjny.Lekcja;
import projektKompetencyjny.Uczen;

import java.net.URL;
import java.util.ResourceBundle;

import static projektKompetencyjny.setGlobalUczen.getUczen;

public class UiLessonProperties implements Initializable {

  @FXML GridPane properties_window;
  @FXML Label przedmiot;
  @FXML Label nauczyciel;
  @FXML Label sala;
  @FXML Label godzina;

  Uczen uczen = getUczen();
  Lekcja lekcja;

  public void generateUserInteface() {
    this.przedmiot.setText(lekcja.getId_przedmiotu().getNazwa_przedmiotu());
    this.nauczyciel.setText(lekcja.getId_nauczyciela().getImie() + " " + lekcja.getId_nauczyciela().getNazwisko());
    this.sala.setText(lekcja.getNr_sali() + "");
    this.godzina.setText(lekcja.getId_godziny().getGodzina());

  }

  public void setLekcja(Lekcja lekcja) {
    this.lekcja = lekcja;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.properties_window.setOnMouseClicked(event -> {
      Stage stage = (Stage) properties_window.getScene().getWindow();
      stage.close();
    });
  }
}

package projektKompetencyjny.schedule;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import projektKompetencyjny.Lekcja;
import projektKompetencyjny.Main;
import projektKompetencyjny.Uczen;
import projektKompetencyjny.clientRootController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static projektKompetencyjny.setGlobalUczen.getUczen;

public class scheduleController implements Initializable {

  @FXML private GridPane schedule;
  @FXML private Button add_button;

  @FXML private TextField lesson_data;
  @FXML private Button add_lesson_button;

  Uczen uczen = getUczen();

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    schedule.paddingProperty().set(new Insets(5));

    Configuration con = new Configuration().configure();
    SessionFactory sessionFactory = con.buildSessionFactory();

    Session session = sessionFactory.openSession();
    Transaction tx = null;

    tx = session.beginTransaction();
    List lekcje = session.createQuery("FROM Lekcja").list();
    for (Object o : lekcje) {
      Lekcja lekcja = (Lekcja) o;
      int col = 0, row = 0;
      switch (lekcja.getId_godziny().getGodzina()) {
        case "8":
          col = 1;
          break;
        case "9":
          col = 2;
          break;
        case "10":
          col = 3;
          break;
        case "11":
          col = 4;
          break;
        case "12":
          col = 5;
          break;
        case "13":
          col = 6;
          break;
        default:
          break;
      }
      switch (lekcja.getId_godziny().getId_dnia().getDzien()) {
        case "Poniedzialek":
          row = 1;
          break;
        case "Wtorek":
          row = 2;
          break;
        case "Sroda":
          row = 3;
          break;
        case "Czwartek":
          row = 4;
          break;
        case "Piatek":
          row = 5;
          break;
        default:
          break;
      }
      if (col != 0 && row != 0 && lekcja.getId_klasy().getId_klasy() == uczen.getIdKlasy().getId_klasy()) {
        schedule.add(new Label(lekcja.getId_przedmiotu().getNazwa_przedmiotu() + "\n" + lekcja.getId_nauczyciela().getNazwisko() + "\n" + lekcja.getNr_sali()), col, row);
      }
    }
    tx.commit();

    schedule.getChildren().forEach(cell -> {
      cell.setOnMouseClicked(event -> {
        try {
          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(Main.class.getClassLoader().getResource("ui_lesson_properties.fxml"));
          GridPane root = loader.load();
          UiLessonProperties controller = loader.getController();
          for (Object o : lekcje) {
            Lekcja lekcja = (Lekcja) o;
            if (lekcja.getId_godziny().getId_dnia().getId_dnia() == GridPane.getRowIndex(cell) && lekcja.getId_godziny().getId_godziny() == GridPane.getColumnIndex(cell)) {
              controller.setLekcja(lekcja);
              controller.generateUserInteface();
              Stage stage = new Stage();
              stage.initModality(Modality.APPLICATION_MODAL);
              stage.initStyle(StageStyle.UNDECORATED);
              stage.setScene(new Scene(root));
              stage.show();
              break;
            }
          }
        }
        catch (Exception e) {
          e.printStackTrace();
        }
      });
    });
  }
}

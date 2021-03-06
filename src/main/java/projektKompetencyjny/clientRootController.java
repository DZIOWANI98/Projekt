package projektKompetencyjny;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class clientRootController {

  @FXML
  private TabPane root;
  private String userStatus;

  public void generateUserInterface() {
    try {
      switch (userStatus.toLowerCase()) {
        case "uczen":
          root.getTabs().add(0, new Tab());
          root.getTabs().get(0).setText("Dane");
          root.getTabs().get(0).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_data.fxml")));

          root.getTabs().add(1, new Tab());
          root.getTabs().get(1).setText("Rozkład zajęć");
          root.getTabs().get(1).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_schedule.fxml")));

          root.getTabs().add(2, new Tab());
          root.getTabs().get(2).setText("Oceny");
          root.getTabs().get(2).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_mark.fxml")));

          root.getTabs().add(3, new Tab());
          root.getTabs().get(3).setText("Terminarz");
          root.getTabs().get(3).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_event_uczen.fxml")));

          root.getTabs().add(4, new Tab());
          root.getTabs().get(4).setText("Wyloguj");
          root.getTabs().get(4).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_wyloguj.fxml")));
          break;
        case "nauczyciel":
          root.getTabs().add(0, new Tab());
          root.getTabs().get(0).setText("Wydarzenia");
          root.getTabs().get(0).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_events_admin.fxml")));

          root.getTabs().add(1, new Tab());
          root.getTabs().get(1).setText("Uwagi");
          root.getTabs().get(1).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_uwagi_admin.fxml")));

          root.getTabs().add(2, new Tab());
          root.getTabs().get(2).setText("Oceny");
          root.getTabs().get(2).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_marks_admin.fxml")));

          root.getTabs().add(3, new Tab());
          root.getTabs().get(3).setText("Komunikator");
            root.getTabs().get(3).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_chat_admin.fxml")));

          root.getTabs().add(4, new Tab());
          root.getTabs().get(4).setText("Wyloguj");
          root.getTabs().get(4).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_wyloguj.fxml")));
          break;
        case "rodzic":
          root.getTabs().add(0, new Tab());
          root.getTabs().get(0).setText("Dane");
          root.getTabs().get(0).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_data_rodzic.fxml")));

          root.getTabs().add(1, new Tab());
          root.getTabs().get(1).setText("Rozkład zajęć");
          root.getTabs().get(1).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_schedule_rodzic.fxml")));

          root.getTabs().add(2, new Tab());
          root.getTabs().get(2).setText("Oceny");
          root.getTabs().get(2).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_mark_rodzic.fxml")));

          root.getTabs().add(3, new Tab());
          root.getTabs().get(3).setText("Terminarz");
          root.getTabs().get(3).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_event_uczen_rodzic.fxml")));

          root.getTabs().add(4, new Tab());
          root.getTabs().get(4).setText("Uwagi");
          root.getTabs().get(4).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_rodzic_uwagi.fxml")));

          root.getTabs().add(5, new Tab());
          root.getTabs().get(5).setText("Komunikator");
          root.getTabs().get(5).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_chat_rodzic.fxml")));

          root.getTabs().add(6, new Tab());
          root.getTabs().get(6).setText("Wyloguj");
          root.getTabs().get(6).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_wyloguj.fxml")));
        case "admin":
          root.getTabs().add(0, new Tab());
          root.getTabs().get(0).setText("Zarzadanie nauczycielami");
          root.getTabs().get(0).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_addNauczyciel.fxml")));

          root.getTabs().add(1, new Tab());
          root.getTabs().get(1).setText("Zarzadzanie uczniami");
          root.getTabs().get(1).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_addUczen.fxml")));

          root.getTabs().add(2, new Tab());
          root.getTabs().get(2).setText("Zarzadzanie rodzicami");
          root.getTabs().get(2).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_addRodzic.fxml")));

          root.getTabs().add(3, new Tab());
          root.getTabs().get(3).setText("Wyloguj");
          root.getTabs().get(3).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_wyloguj.fxml")));
          break;
        default:
          throw new IllegalArgumentException("User status error!");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void setUserStatus(String userStatus) {
    this.userStatus = userStatus;
  }

  public String getUserStatus() {
    return userStatus;
  }
}

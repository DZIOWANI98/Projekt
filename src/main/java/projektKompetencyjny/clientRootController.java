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
          //root.getTabs().get(0).setStyle("-fx-background-color: #DEDEDE; -fx-border-color: black;");
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

          root.getTabs().add(2, new Tab());
          root.getTabs().get(2).setText("Komunikator");
          root.getTabs().get(2).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_chat.fxml")));
          break;
        case "rodzic":
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
          root.getTabs().get(3).setText("Termiarz");
          root.getTabs().get(3).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_event_uczen.fxml")));

          root.getTabs().add(4, new Tab());
          root.getTabs().get(4).setText("Uwagi i wydarzenia");
          root.getTabs().get(4).setContent(FXMLLoader.load(getClass().getClassLoader().getResource("ui_rodzic_uwagi.fxml")));
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

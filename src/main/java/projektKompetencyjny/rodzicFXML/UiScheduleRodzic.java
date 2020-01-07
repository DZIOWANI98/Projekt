package projektKompetencyjny.rodzicFXML;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import projektKompetencyjny.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class UiScheduleRodzic implements Initializable {
    @FXML
    private GridPane schedule;
    @FXML
    private Button add_button;

    @FXML
    private TextField lesson_data;
    @FXML
    private Button add_lesson_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//    schedule.getChildren().forEach(cell -> {
//      cell.setOnMouseClicked(event -> {
//          System.out.println(GridPane.getColumnIndex(cell) + " " + GridPane.getRowIndex(cell));
//          Label text = new Label("text");
//          schedule.add(text, GridPane.getColumnIndex(cell), GridPane.getRowIndex(cell));
//      });
//    });

        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 6; j++) {
                Pane pane = new Pane();
                schedule.add(pane, i, j);
            }
        }


        schedule.getChildren().forEach(cell -> {
            cell.setOnMouseClicked(event -> {
                Integer col = GridPane.getColumnIndex(cell);
                Integer row = GridPane.getRowIndex(cell);

                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Main.class.getResource("/addNewLesson.fxml"));
                    AnchorPane root = loader.load();

                    Stage stage = new Stage();
                    stage.setTitle("Add lesson");
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (col != null && row != null) {
                    System.out.println(col + " " + row);
                    Label lesson = new Label("Nazwa przedmiotu\nProwadzacy\nNumer sali");
                    schedule.add(lesson, col, row);
                }
            });
        });


    }
}

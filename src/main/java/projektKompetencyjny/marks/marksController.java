package projektKompetencyjny.marks;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import projektKompetencyjny.Oceny;
import projektKompetencyjny.Uczen;
import projektKompetencyjny.doTabeliUcznia;

import java.net.URL;
import java.util.ResourceBundle;

import static projektKompetencyjny.setGlobalUczen.getUczen;

public class marksController implements Initializable {
    Uczen uczen = getUczen();

    @FXML
    private Label UserName;

    @FXML
    private TableView<doTabeliUcznia> tabela;

    @FXML
    private TableColumn<doTabeliUcznia, String> przedmiotyColumn;

    @FXML
    private TableColumn<doTabeliUcznia, String> klasowkiColumn;

    @FXML
    private TableColumn<doTabeliUcznia, String> pracedomoweColumn;

    @FXML
    private TableColumn<doTabeliUcznia, String> kartkowkiColumn;

    @FXML
    private TableColumn<doTabeliUcznia, String> odpowiedziColumn;
    private ObservableList<doTabeliUcznia> studentsModels = FXCollections.observableArrayList(
            new doTabeliUcznia("Matematyka", "5,4,3", "4,2", "", "")
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UserName.setText("Witaj " + uczen.getName() + " " + uczen.getNazwisko() + " o to Twoje oceny:");

        przedmiotyColumn.setCellValueFactory(new PropertyValueFactory<>("Przedmiot"));
        klasowkiColumn.setCellValueFactory(new PropertyValueFactory<>("Klasowka"));
        pracedomoweColumn.setCellValueFactory(new PropertyValueFactory<>("PraceDomowe"));
        kartkowkiColumn.setCellValueFactory(new PropertyValueFactory<>("Kartkowka"));
        odpowiedziColumn.setCellValueFactory(new PropertyValueFactory<>("Odpowiedz"));
        //add your data to the table here.
        tabela.setItems(studentsModels);
    }




}

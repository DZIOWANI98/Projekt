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
import projektKompetencyjny.doTabeliUcznia;

import java.net.URL;
import java.util.ResourceBundle;

public class marksController implements Initializable {


    @FXML
    private Label UserName;

    @FXML
    private TableView<Oceny> tabela;

    @FXML
    private TableColumn<Oceny, String> przedmiotyColumn;

    @FXML
    private TableColumn<Oceny, String> klasowkiColumn;

    @FXML
    private TableColumn<Oceny, String> pracedomoweColumn;

    @FXML
    private TableColumn<Oceny, String> kartkowkiColumn;

    @FXML
    private TableColumn<Oceny, String> odpowiedziColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        przedmiotyColumn.setCellValueFactory(new PropertyValueFactory<>("StudentId"));
        klasowkiColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        pracedomoweColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        kartkowkiColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        odpowiedziColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        //add your data to the table here.
        //tabela.setItems(studentsModels);
    }

    private ObservableList<doTabeliUcznia> studentsModels = FXCollections.observableArrayList(

            );




}

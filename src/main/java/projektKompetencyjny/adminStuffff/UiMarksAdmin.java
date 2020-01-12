package projektKompetencyjny.adminStuffff;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import projektKompetencyjny.Klasa;
import projektKompetencyjny.Nauczyciel;
import projektKompetencyjny.Uczen;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static projektKompetencyjny.setGlobalNauczyciel.getNauczyciel;


//id tabeli: tabela
//id kolumn: uczenColumn, klasowkiColumn, pracedomoweColumn, kartkowkiColumn, odpowiedziColumn
//id: selectClass, selectUczen, selectTypOceny, ocena, data, addButton
//przedmiot pobiera sie z tego czego uczy zalogowana osoba


public class UiMarksAdmin implements Initializable {
    Nauczyciel nauczyciel = getNauczyciel();

    private List<Klasa> klasy = new ArrayList<>();

    private List<Uczen> uczniowie = new ArrayList<>();

    @FXML
    private JFXComboBox<String> selectClass;

    @FXML
    private JFXTextField dataLabel;

    @FXML
    private JFXButton addButton;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXComboBox<String> selectUczen;

    @FXML
    private JFXComboBox<String> selectTypOceny;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXTextField ocenaLabel;

    @FXML
    private TableView<doTabeliMarksAdmin> tabela;

    @FXML
    private TableColumn<doTabeliMarksAdmin, String> uczenColumn;

    @FXML
    private TableColumn<doTabeliMarksAdmin, String> klasowkiColumn;

    @FXML
    private TableColumn<doTabeliMarksAdmin, String> pracedomoweColumn;

    @FXML
    private TableColumn<doTabeliMarksAdmin, String> kartkowkiColumn;

    @FXML
    private TableColumn<doTabeliMarksAdmin, String> odpowiedziColumn;

    @FXML
    private TableColumn<doTabeliMarksAdmin, Double> sredniaColumn;

    private ObservableList<String> selectClassObservableList = FXCollections.observableArrayList();
    private ObservableList<String> listOfStudents = FXCollections.observableArrayList();
    private ObservableList<String> rodzajOceny = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uczenColumn.setCellValueFactory(new PropertyValueFactory<>("Uczen"));
        klasowkiColumn.setCellValueFactory(new PropertyValueFactory<>("Klasowka"));
        pracedomoweColumn.setCellValueFactory(new PropertyValueFactory<>("PraceDomowe"));
        kartkowkiColumn.setCellValueFactory(new PropertyValueFactory<>("Kartkowka"));
        odpowiedziColumn.setCellValueFactory(new PropertyValueFactory<>("Odpowiedz"));
        sredniaColumn.setCellValueFactory(new PropertyValueFactory<>("Srednia"));

        rodzajOceny.addAll("Praca domowa", "Kartkówka", "Odpowiedź", "Praca klasowa");
        selectTypOceny.setItems(rodzajOceny);

        klasy = nauczyciel.getKlasy();
        for (Klasa classa : klasy) {
            selectClassObservableList.add(classa.getNazwa_klasy());
        }
        selectClass.setItems(selectClassObservableList);


    }


    @FXML
    void addToDatabase(ActionEvent event) {

    }

    @FXML
    void updateDataClassSelected(ActionEvent event) {
        selectUczen.getItems().clear();
        listOfStudents.clear();
        uczniowie.clear();
        String klasa = selectClass.getSelectionModel().getSelectedItem();
        Klasa klasaSelected = null;
        for (Klasa klass : klasy) {
            if (klass.getNazwa_klasy().equals(klasa)) {
                klasaSelected = klass;
            }
        }
        if (klasaSelected == null) {
            errorLabel.setText("Uzupelnij pola");
        } else {
            errorLabel.setText("");
            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction txn = session.beginTransaction();
            Query query = session.createQuery("FROM Uczen U WHERE U.idKlasy = :idklasy");
            query.setParameter("idklasy", klasaSelected);
            List result = query.list();
            for (Object res : result) {
                uczniowie.add((Uczen) res);
            }
            //Commit the transaction
            txn.commit();
        }
        for (Uczen uczen : uczniowie) {
            listOfStudents.add(uczen.getName() + " " + uczen.getNazwisko());
        }
        selectUczen.setItems(listOfStudents);
    }


}

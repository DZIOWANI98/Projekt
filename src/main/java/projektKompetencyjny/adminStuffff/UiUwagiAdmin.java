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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import projektKompetencyjny.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static projektKompetencyjny.setGlobalNauczyciel.getNauczyciel;

public class UiUwagiAdmin implements Initializable {
    Nauczyciel nauczyciel = getNauczyciel();
    //id: selectUczen, uwagaTextArea, dataTextArea, addButton
    //tabela: tabelkaUwagiAdmin
    //kolumny: uwagiKol, dataKol
    //deleteMenuItem i deleteRecordOnAction
    //uwaga musi sie zapisywac z nazwą nauczyciela który ją dodaje
    //uwagi wyswietlane to tylko te od zalogowanego nauczyciela

    //to będzie rozwiązanie, które będzie miało buga przy 2 osobach, które maja tak samo na imię i na nazwisko :(
    //nie mam pomysłu jak inaczej i żeby łatwo było ;p

    private List<Klasa> klasy = new ArrayList<>();
    private List<Uczen> uczniowie = new ArrayList<>();
    private List<Uwaga> uwagi = new ArrayList<>();

    @FXML
    private JFXComboBox<String> selectUczen;

    @FXML
    private JFXTextField eventTextArea;

    @FXML
    private JFXTextField dataTextArea;

    @FXML
    private JFXButton addButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TableView<doTabeliUwagi> tabelkaUwagiAdmin;

    @FXML
    private TableColumn<doTabeliUwagi, String> uwagiKol;

    @FXML
    private TableColumn<doTabeliUwagi, String> dataKol;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private JFXDatePicker dataPicker;

    private ObservableList<String> listOfStudents = FXCollections.observableArrayList();
    private ObservableList<doTabeliUwagi> uwagiObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uwagiKol.setCellValueFactory(new PropertyValueFactory<>("Uwaga"));
        dataKol.setCellValueFactory(new PropertyValueFactory<>("Data"));
        this.deleteMenuItem.disableProperty().bind(this.tabelkaUwagiAdmin.getSelectionModel().selectedItemProperty().isNull());
        klasy = nauczyciel.getKlasy();

        for (Klasa klasa : klasy) {
            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction txn = session.beginTransaction();
            Query query = session.createQuery("FROM Uczen U WHERE U.idKlasy = :idklasy");
            query.setParameter("idklasy", klasa);
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


        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List uwags = session.createQuery("FROM Uwaga").list();
        for (Iterator iterator1 = uwags.iterator(); iterator1.hasNext(); ) {
            Uwaga uwaga = (Uwaga) iterator1.next();
            uwagi.add(uwaga);
        }

    }

    public void addToDatabase(ActionEvent actionEvent) {
        String eventText = eventTextArea.getText();
        LocalDate dataText = null;


        if (dataPicker.validate()) {
            dataText = LocalDate.parse(dataPicker.getValue().toString());
        }
        Uczen pickedStudent = null;
        String pickedStudentName = selectUczen.getSelectionModel().getSelectedItem();
        for (Uczen uczen : uczniowie) {
            String student = uczen.getName() + " " + uczen.getNazwisko();
            if (student.equals(pickedStudentName)) {
                pickedStudent = uczen;
                break;
            }
        }

        if (pickedStudent == null || dataPicker == null || eventText.isEmpty()) {
            errorLabel.setText("Uzupełnij pola");
        } else {
            errorLabel.setText("");
            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            tx = session.beginTransaction();
            //Add new Uwaga object
            Uwaga uwga = new Uwaga();
            uwga.setUwaga(eventText);
            uwga.setData(dataText);
            uwga.setUczen(pickedStudent);
            uwga.setNauczyciel(nauczyciel);
            //Save the Uwaga in database
            session.save(uwga);
            //Commit the transaction
            tx.commit();
            updateUwaga();
            refresh();

        }
    }

    private void updateUwaga() {
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        uwagi.clear();
        List uwags = session.createQuery("FROM Uwaga").list();
        for (Iterator iterator1 = uwags.iterator(); iterator1.hasNext(); ) {
            Uwaga uwaga = (Uwaga) iterator1.next();
            uwagi.add(uwaga);
        }
    }

    private void refresh() {
        tabelkaUwagiAdmin.getItems().clear();
        String uczen = selectUczen.getSelectionModel().getSelectedItem();
        LocalDate localDate = LocalDate.now();
        for (Uwaga uwaga : uwagi) {
            String uczenUw = uwaga.getUczen().getName() + " " + uwaga.getUczen().getNazwisko();
            if (uwaga.getNauczyciel().getId_nauczyciela() == nauczyciel.getId_nauczyciela()
                    && uczenUw.equals(uczen)) {
                //System.out.println(eventt.getEvent() + " " + eventt.getData());
                LocalDate data = uwaga.getData();
                if (data != null) {
                    //System.out.println("jestem here");
                    uwagiObservableList.add(new doTabeliUwagi(uwaga.getUwaga(), uwaga.getData().toString()));
                } else {
                    uwagiObservableList.add(new doTabeliUwagi(uwaga.getUwaga(), "Brak daty"));
                }
            }
        }
        tabelkaUwagiAdmin.setItems(uwagiObservableList);
    }

    public void deleteRecordOnAction(ActionEvent actionEvent) {
        doTabeliUwagi selectedForDelete = tabelkaUwagiAdmin.getSelectionModel().getSelectedItem();
        if (selectedForDelete == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting event");
        alert.setContentText("Czy jestes pewien, ze chcesz usunac uwage " + selectedForDelete.getUwaga() + " data: " +
                selectedForDelete.getData() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction txn = session.beginTransaction();
            Query query = session.createQuery("delete Uwaga where uwaga = :uwaga");
            query.setParameter("uwaga", selectedForDelete.getUwaga());
            query.executeUpdate();
            //Commit the transaction
            txn.commit();
            updateUwaga();
            refresh();
        } else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Usuwanie uwagi");
            alert1.setContentText("Usuwanie uwagi anulowane");
            alert1.showAndWait();
        }


    }

    @FXML
    public void updateData(ActionEvent actionEvent) {
        tabelkaUwagiAdmin.getItems().clear();
        String uczen = selectUczen.getSelectionModel().getSelectedItem();
        LocalDate localDate = LocalDate.now();
        for (Uwaga uwaga : uwagi) {
            String uczenUw = uwaga.getUczen().getName() + " " + uwaga.getUczen().getNazwisko();
            if (uwaga.getNauczyciel().getId_nauczyciela() == nauczyciel.getId_nauczyciela()
                    && uczenUw.equals(uczen)) {
                //System.out.println(eventt.getEvent() + " " + eventt.getData());
                LocalDate data = uwaga.getData();
                if (data != null) {
                    //System.out.println("jestem here");
                    uwagiObservableList.add(new doTabeliUwagi(uwaga.getUwaga(), uwaga.getData().toString()));
                } else {
                    uwagiObservableList.add(new doTabeliUwagi(uwaga.getUwaga(), "Brak daty"));
                }
            }
        }
        tabelkaUwagiAdmin.setItems(uwagiObservableList);

    }
}

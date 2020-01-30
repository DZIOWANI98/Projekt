package projektKompetencyjny.admin;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import projektKompetencyjny.Nauczyciel;
import projektKompetencyjny.Przedmiot;
import projektKompetencyjny.Rodzic;
import projektKompetencyjny.login.loginController;
import projektKompetencyjny.setGlobalNauczyciel;

import java.net.URL;
import java.util.*;

public class UiAddNauczyciel implements Initializable {

    private ArrayList<Nauczyciel> nauczyciele = new ArrayList<>();

    private ArrayList<Przedmiot> przedmioty = new ArrayList<>();

    @FXML
    private GridPane layoutDodawanie1;

    @FXML
    private JFXButton addButton;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXComboBox<String> selectPrzedmiot;

    @FXML
    private JFXPasswordField hasloLabel;

    @FXML
    private JFXTextField imieLabel;

    @FXML
    private JFXTextField emailLabel;

    @FXML
    private JFXTextField nazwiskoLabel;

    @FXML
    private TableView<doTabeliUiAdminNauczyciel> table;

    @FXML
    private TableColumn<doTabeliUiAdminNauczyciel, String> imieColumn;

    @FXML
    private TableColumn<doTabeliUiAdminNauczyciel, String> nazwiskoColumn;

    @FXML
    private TableColumn<doTabeliUiAdminNauczyciel, String> idColumn;

    @FXML
    private TableColumn<doTabeliUiAdminNauczyciel, String> emailColumn;

    @FXML
    private TableColumn<doTabeliUiAdminNauczyciel, String> przedmiotColumn;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem editMenuItem;

    private ObservableList<String> selectPrzedmiotObservableList = FXCollections.observableArrayList();
    private ObservableList<doTabeliUiAdminNauczyciel> nauczycielObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imieColumn.setCellValueFactory(new PropertyValueFactory<>("Imie"));
        nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        przedmiotColumn.setCellValueFactory(new PropertyValueFactory<>("Przedmiot"));

        nauczyciele.addAll(loginController.getNauczyciels());

        for (Nauczyciel nauczyciel : nauczyciele) {
            nauczycielObservableList.add(new doTabeliUiAdminNauczyciel(nauczyciel.getImie(), nauczyciel.getNazwisko(), nauczyciel.getId_nauczyciela(),
                    nauczyciel.getEmail(), nauczyciel.getPrzedmiot().getNazwa_przedmiotu()));
        }
        table.setItems(nauczycielObservableList);

        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        List subcjects = session.createQuery("FROM Przedmiot").list();
        for (Iterator iterator1 = subcjects.iterator(); iterator1.hasNext(); ) {
            Przedmiot subject = (Przedmiot) iterator1.next();
            przedmioty.add(subject);
            selectPrzedmiotObservableList.add(subject.getNazwa_przedmiotu());
        }
        tx.commit();
        selectPrzedmiot.setItems(selectPrzedmiotObservableList);


    }

    @FXML
    public void addToDatabase(ActionEvent actionEvent) {
        String imie = imieLabel.getText();
        String nazwisko = nazwiskoLabel.getText();
        String email = emailLabel.getText();
        String haslo = hasloLabel.getText();
        String przedmiot = selectPrzedmiot.getSelectionModel().getSelectedItem();
        Przedmiot przedmiot1 = null;

        for (Przedmiot przedmiotixy : przedmioty) {
            if (przedmiot.equals(przedmiotixy.getNazwa_przedmiotu())) {
                przedmiot1 = przedmiotixy;
            }
        }
        if (imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || haslo.isEmpty() || przedmiot.isEmpty() || przedmiot1 == null) {
            errorLabel.setText("Uzupelnij pola!");
        } else {
            errorLabel.setText("");
            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            tx = session.beginTransaction();
            //Add new Event object
            Nauczyciel nauczyciel = new Nauczyciel();
            nauczyciel.setEmail(email);
            nauczyciel.setHaslo(haslo);
            nauczyciel.setNazwisko(nazwisko);
            nauczyciel.setImie(imie);
            nauczyciel.setPrzedmiot(przedmiot1);
            session.merge(nauczyciel);
            //Commit the transaction
            updateTeacher();
            refreshTable();
        }

    }

    private void refreshTable() {
        nauczycielObservableList.clear();
        table.getItems().clear();
        for (Nauczyciel nauczyciel : nauczyciele) {
            nauczycielObservableList.add(new doTabeliUiAdminNauczyciel(nauczyciel.getImie(), nauczyciel.getNazwisko(), nauczyciel.getId_nauczyciela(),
                    nauczyciel.getEmail(), nauczyciel.getPrzedmiot().getNazwa_przedmiotu()));
        }
        table.setItems(nauczycielObservableList);
    }

    private void updateTeacher() {
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        nauczyciele.clear();
        tx = session.beginTransaction();
        List teachers = session.createQuery("FROM Nauczyciel").list();
        for (Iterator iterator1 = teachers.iterator(); iterator1.hasNext(); ) {
            Nauczyciel teacher = (Nauczyciel) iterator1.next();
            nauczyciele.add(teacher);
        }
        tx.commit();
    }

    @FXML
    public void deleteRecordOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void editRecordOnAction(ActionEvent actionEvent) {
    }
}
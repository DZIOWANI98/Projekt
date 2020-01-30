package projektKompetencyjny.admin;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import projektKompetencyjny.Event;
import projektKompetencyjny.Klasa;
import projektKompetencyjny.Uczen;
import projektKompetencyjny.event_uczen.doTabeliEvent;
import projektKompetencyjny.login.loginController;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static projektKompetencyjny.setGlobalNauczyciel.getNauczyciel;

public class UiAddUczen implements Initializable {

    private ArrayList<Uczen> uczniowie = new ArrayList<>();

    private ArrayList<Klasa> klasyyy = new ArrayList<>();

    @FXML
    private GridPane layoutDodawanie1;

    @FXML
    private JFXButton addButton;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXComboBox<String> selectKlasa;

    @FXML
    private JFXPasswordField hasloLabel;

    @FXML
    private JFXTextField imieLabel;

    @FXML
    private JFXTextField emailLabel;

    @FXML
    private JFXTextField nazwiskoLabel;

    @FXML
    private JFXDatePicker selectData;

    @FXML
    private JFXComboBox<String> selectDojezdza;

    @FXML
    private JFXTextField mZamieszkaniaLabel;

    @FXML
    private JFXTextField mUrodzeniaLabel;

    @FXML
    private TableView<doTabeliUiAdminUczen> table;

    @FXML
    private TableColumn<doTabeliUiAdminUczen, String> imieColumn;

    @FXML
    private TableColumn<doTabeliUiAdminUczen, String> nazwiskoColumn;

    @FXML
    private TableColumn<doTabeliUiAdminUczen, String> idColumn;

    @FXML
    private TableColumn<doTabeliUiAdminUczen, String> emailColumn;

    @FXML
    private TableColumn<doTabeliUiAdminUczen, String> dataUrodzeniaColumn;

    @FXML
    private TableColumn<doTabeliUiAdminUczen, String> klasaColumn;

    @FXML
    private TableColumn<doTabeliUiAdminUczen, String> mZamieszkaniaColumn;

    @FXML
    private TableColumn<doTabeliUiAdminUczen, String> mUrodzeniaColumn;

    @FXML
    private TableColumn<doTabeliUiAdminUczen, String> dojezdzaColumn;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem editMenuItem;

    private ObservableList<String> selectClassObservableList = FXCollections.observableArrayList();
    private ObservableList<String> dojezdzaObservableList = FXCollections.observableArrayList();
    private ObservableList<doTabeliUiAdminUczen> studentsObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imieColumn.setCellValueFactory(new PropertyValueFactory<>("Imie"));
        nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        dataUrodzeniaColumn.setCellValueFactory(new PropertyValueFactory<>("DataUrodzenia"));
        klasaColumn.setCellValueFactory(new PropertyValueFactory<>("Klasa"));
        mZamieszkaniaColumn.setCellValueFactory(new PropertyValueFactory<>("MiejsceZamieszkania"));
        mUrodzeniaColumn.setCellValueFactory(new PropertyValueFactory<>("MiejsceUrodzenia"));
        dojezdzaColumn.setCellValueFactory(new PropertyValueFactory<>("Dojezdza"));

        dojezdzaObservableList.addAll("Tak", "Nie");
        selectDojezdza.setItems(dojezdzaObservableList);

        uczniowie.addAll(loginController.getUczeniowie());

        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List klasy = session.createQuery("FROM Klasa").list();
        for (Iterator iterator1 = klasy.iterator(); iterator1.hasNext(); ) {
            Klasa klassa = (Klasa) iterator1.next();
            klasyyy.add(klassa);
        }

        for (Klasa klasa : klasyyy) {
            selectClassObservableList.add(klasa.getNazwa_klasy());
        }
        selectKlasa.setItems(selectClassObservableList);

        for (Uczen student : uczniowie) {
            String dojezdzzza = "Nie";
            if (student.isCzyDojezdza()) {
                dojezdzzza = "Tak";
            }
            studentsObservableList.add(new doTabeliUiAdminUczen(student.getName(), student.getNazwisko(), student.getId_Ucznia(), student.getEmail(),
                    student.getDataUrodzenia().toString(), student.getIdKlasy().getNazwa_klasy(), student.getMiejsceZamieszkania(), student.getMiejsceUrodzenia(),
                    dojezdzzza));
        }
        table.setItems(studentsObservableList);


    }

    public void addToDatabase(ActionEvent actionEvent) {
        LocalDate dataText = null;
        Klasa klassa = null;

        if (selectData.validate() && selectData.getValue() != null) {
            dataText = LocalDate.parse(selectData.getValue().toString());
        }

        for (Klasa klass : klasyyy) {
            if (klass.getNazwa_klasy().equals(selectKlasa.getSelectionModel().getSelectedItem())) {
                klassa = klass;
            }
        }

        boolean czyDojezdza = false;
        if (selectDojezdza.getSelectionModel().getSelectedItem().toLowerCase().equals("tak")) {
            czyDojezdza = true;
        }

        String imie = imieLabel.getText();
        String nazwisko = nazwiskoLabel.getText();
        String email = emailLabel.getText();
        String haslo = hasloLabel.getText();
        String mZamieszkania = mZamieszkaniaLabel.getText();
        String mUrodzenia = mUrodzeniaLabel.getText();

        if (klassa == null || dataText == null || imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || haslo.isEmpty() ||
                mUrodzenia.isEmpty() || mZamieszkania.isEmpty()) {
            errorLabel.setText("Ustaw wszystkie pola");
        } else {
            errorLabel.setText("");
            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            tx = session.beginTransaction();
            //Add new Event object
            Uczen uczen = new Uczen();
            uczen.setIdKlasy(klassa);
            uczen.setName(imie);
            uczen.setDataUrodzenia(dataText);
            uczen.setNazwisko(nazwisko);
            uczen.setEmail(email);
            uczen.setHaslo(haslo);
            uczen.setMiejsceUrodzenia(mUrodzenia);
            uczen.setMiejsceZamieszkania(mZamieszkania);
            //Save the event in database
            session.save(uczen);
            //Commit the transaction
            tx.commit();
            updateEvent();
            refreshTable();
        }
    }

    private void refreshTable() {
        studentsObservableList.clear();
        table.getItems().clear();

        for (Uczen student : uczniowie) {
            String dojezdzzza = "Nie";
            if (student.isCzyDojezdza()) {
                dojezdzzza = "Tak";
            }
            studentsObservableList.add(new doTabeliUiAdminUczen(student.getName(), student.getNazwisko(), student.getId_Ucznia(), student.getEmail(),
                    student.getDataUrodzenia().toString(), student.getIdKlasy().getNazwa_klasy(), student.getMiejsceZamieszkania(), student.getMiejsceUrodzenia(),
                    dojezdzzza));
        }
        table.setItems(studentsObservableList);


    }

    private void updateEvent() {
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        uczniowie.clear();
        List employees = session.createQuery("FROM Uczen").list();
        for (Iterator iterator1 = employees.iterator(); iterator1.hasNext(); ) {
            Uczen employee = (Uczen) iterator1.next();
            uczniowie.add(employee);
        }
        tx.commit();
    }


    public void deleteRecordOnAction(ActionEvent actionEvent) {
    }

    public void editRecordOnAction(ActionEvent actionEvent) {
    }
}
package projektKompetencyjny.adminStuffff;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import projektKompetencyjny.*;
import projektKompetencyjny.event_uczen.doTabeliEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static projektKompetencyjny.setGlobalNauczyciel.getNauczyciel;


//id tabeli: tabela
//id kolumn: uczenColumn, klasowkiColumn, pracedomoweColumn, kartkowkiColumn, odpowiedziColumn
//id: selectClass, selectUczen, selectTypOceny, ocena, data, addButton
//przedmiot pobiera sie z tego czego uczy zalogowana osoba


public class UiMarksAdmin implements Initializable {
    Nauczyciel nauczyciel = getNauczyciel();

    private List<Klasa> klasy = new ArrayList<>();

    private List<Uczen> uczniowie = new ArrayList<>();

    private static final int WAGAPRACYKLASOWEJ = 3;
    private static final int WAGAODPOWIEDZI = 1;
    private static final int WAGAPRACYDOMOWEJ = 1;
    private static final int WAGAKARTKOWKI = 2;
    private List<Oceny> oceny = new ArrayList<>();
    private Klasa klasaSelected = null;
    private Uczen pickedStudent = null;
    private Przedmiot przedmiotSelected = null;
    private double srednia;
    private int SUMAWAG = 0;

    private Uczen pickedUczenBeforeDelete;

    private boolean layout1 = true;

    private doTabeliMarksAdminLayout2 selectedForEdit;

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
    private TableView<doTabeliMarksAdminLayout1> tabela;

    @FXML
    private TableColumn<doTabeliMarksAdminLayout1, String> uczenColumn;

    @FXML
    private TableColumn<doTabeliMarksAdminLayout1, String> klasowkiColumn;

    @FXML
    private TableColumn<doTabeliMarksAdminLayout1, String> pracedomoweColumn;

    @FXML
    private TableColumn<doTabeliMarksAdminLayout1, String> kartkowkiColumn;

    @FXML
    private TableColumn<doTabeliMarksAdminLayout1, String> odpowiedziColumn;

    @FXML
    private TableColumn<doTabeliMarksAdminLayout1, Double> sredniaColumn;

    @FXML
    private JFXButton changeButton;

    @FXML
    private JFXComboBox<String> selectClass2;

    @FXML
    private Label errorLabel1;

    @FXML
    private JFXComboBox<String> selectUczen2;

    @FXML
    private JFXComboBox<String> selectPrzedmiot2;

    @FXML
    private GridPane layoutDodawanie1;

    @FXML
    private VBox layoutDodawanie2;

    @FXML
    private GridPane layoutEdycja1;

    @FXML
    private VBox layoutEdycja2;

    @FXML
    private TableView<doTabeliMarksAdminLayout2> tabela2;

    @FXML
    private TableColumn<doTabeliMarksAdminLayout2, String> dataColumn2;

    @FXML
    private TableColumn<doTabeliMarksAdminLayout2, Integer> ocenaColumn2;

    @FXML
    private TableColumn<doTabeliMarksAdminLayout2, String> typOcenyColumn2;

    @FXML
    private TableColumn<doTabeliMarksAdminLayout2, Integer> idColumnEditLayout;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem editMenuItem;


    private ObservableList<String> selectClassObservableList = FXCollections.observableArrayList();
    private ObservableList<String> listOfStudentsLayout1 = FXCollections.observableArrayList();
    private ObservableList<String> listOfStudentsLayout2 = FXCollections.observableArrayList();
    private ObservableList<String> rodzajOceny = FXCollections.observableArrayList();
    private ObservableList<doTabeliMarksAdminLayout1> studentMarksLayout1 = FXCollections.observableArrayList();
    private ObservableList<doTabeliMarksAdminLayout2> studentMarksLayout2 = FXCollections.observableArrayList();
    private ObservableList<String> przedmiot = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uczenColumn.setCellValueFactory(new PropertyValueFactory<>("Uczen"));
        klasowkiColumn.setCellValueFactory(new PropertyValueFactory<>("Klasowka"));
        pracedomoweColumn.setCellValueFactory(new PropertyValueFactory<>("PraceDomowe"));
        kartkowkiColumn.setCellValueFactory(new PropertyValueFactory<>("Kartkowka"));
        odpowiedziColumn.setCellValueFactory(new PropertyValueFactory<>("Odpowiedz"));
        sredniaColumn.setCellValueFactory(new PropertyValueFactory<>("Srednia"));

        dataColumn2.setCellValueFactory(new PropertyValueFactory<>("Data"));
        ocenaColumn2.setCellValueFactory(new PropertyValueFactory<>("Ocena"));
        typOcenyColumn2.setCellValueFactory(new PropertyValueFactory<>("TypOceny"));
        idColumnEditLayout.setCellValueFactory(new PropertyValueFactory<>("Id"));

        this.deleteMenuItem.disableProperty().bind(this.tabela2.getSelectionModel().selectedItemProperty().isNull());
        this.editMenuItem.disableProperty().bind(this.tabela2.getSelectionModel().selectedItemProperty().isNull());


        rodzajOceny.addAll("Praca domowa", "Kartkówka", "Odpowiedź", "Praca klasowa");
        selectTypOceny.setItems(rodzajOceny);

        klasy = nauczyciel.getKlasy();
        for (Klasa classa : klasy) {
            selectClassObservableList.add(classa.getNazwa_klasy());
        }
        selectClass.setItems(selectClassObservableList);
        selectClass2.setItems(selectClassObservableList);

        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        Query query = session.createQuery("FROM Przedmiot where id_przedmiotu = :nauczyciel_id_przedmiotu");
        query.setParameter("nauczyciel_id_przedmiotu", nauczyciel.getId_przedmiotu());
        List przedmioty = query.list();
        for (Iterator iterator1 = przedmioty.iterator(); iterator1.hasNext(); ) {
            Przedmiot przedmiot = (Przedmiot) iterator1.next();
            przedmiotSelected = przedmiot;
        }
        //Commit the transaction
        przedmiot.add(przedmiotSelected.getNazwa_przedmiotu());
        selectPrzedmiot2.setItems(przedmiot);
        txn.commit();


    }


    @FXML
    void addToDatabase(ActionEvent event) {
        LocalDate dataText = null;
        LocalDate dataPo = LocalDate.parse("2020-02-01");

        if (datePicker.validate() && datePicker.getValue() != null) {
            dataText = LocalDate.parse(datePicker.getValue().toString());
        }

        int nr_semestru = 0;
        if (dataText != null && dataText.isAfter(dataPo)) {
            nr_semestru = 2;
        } else {
            nr_semestru = 1;
        }

        String typOceny = selectTypOceny.getSelectionModel().getSelectedItem();
        String ocena = ocenaLabel.getText();

        if (klasaSelected == null || pickedStudent == null || typOceny.isEmpty() || ocena.isEmpty() || dataText == null) {
            errorLabel.setText("Uzupełnij pola.");
        } else if (przedmiotSelected == null) {
            errorLabel.setText("Uzupełnij pola.");
        } else {
            errorLabel.setText("");
            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            tx = session.beginTransaction();
            //Add new Event object
            Oceny ocenaToSave = new Oceny();
            ocenaToSave.setUczen(pickedStudent);
            ocenaToSave.setId_przedmiotu(przedmiotSelected);
            ocenaToSave.setOcena(Integer.parseInt(ocena));
            ocenaToSave.setData(dataText);
            ocenaToSave.setRodzaj_oceny(typOceny);
            ocenaToSave.setNr_semestru(nr_semestru);
            //Save the event in database
            session.save(ocenaToSave);
            //Commit the transaction
            tx.commit();
            refreshLayout1();
        }


    }

    private void refreshLayout1() {
        uczniowie.clear();
        studentMarksLayout1.clear();
        tabela.getItems().clear();
        String klasa = selectClass.getSelectionModel().getSelectedItem();
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
            double suma = 0;
            srednia = 0;
            SUMAWAG = 0;
            StringBuffer oceny_z_kartkowki = new StringBuffer();
            StringBuffer oceny_z_odpowiedzi = new StringBuffer();
            StringBuffer oceny_z_pracyKlasowej = new StringBuffer();
            StringBuffer oceny_z_pracDomowych = new StringBuffer();
            oceny = uczen.getOceny();
            for (Oceny ocena : oceny) {
                if (ocena.getId_przedmiotu().getId_przedmiotu() == nauczyciel.getId_przedmiotu() && ocena.getUczen().getId_Ucznia() == uczen.getId_Ucznia()) {
                    switch (ocena.getRodzaj_oceny()) {
                        case "Kartkówka":
                            suma += (ocena.getOcena() * WAGAKARTKOWKI);
                            SUMAWAG += WAGAKARTKOWKI;
                            if (oceny_z_kartkowki.length() == 0) {
                                oceny_z_kartkowki.append(ocena.getOcena());
                            } else {
                                oceny_z_kartkowki.append(", ");
                                oceny_z_kartkowki.append(ocena.getOcena());
                            }
                            break;
                        case "Praca domowa":
                            suma += (ocena.getOcena() * WAGAPRACYDOMOWEJ);
                            SUMAWAG += WAGAPRACYDOMOWEJ;
                            if (oceny_z_pracDomowych.length() == 0) {
                                oceny_z_pracDomowych.append(ocena.getOcena());
                            } else {
                                oceny_z_pracDomowych.append(", ");
                                oceny_z_pracDomowych.append(ocena.getOcena());
                            }
                            break;
                        case "Odpowiedź":
                            suma += (ocena.getOcena() * WAGAODPOWIEDZI);
                            SUMAWAG += WAGAODPOWIEDZI;
                            if (oceny_z_odpowiedzi.length() == 0) {
                                oceny_z_odpowiedzi.append(ocena.getOcena());
                            } else {
                                oceny_z_odpowiedzi.append(", ");
                                oceny_z_odpowiedzi.append(ocena.getOcena());
                            }
                            break;
                        case "Praca klasowa":
                            suma += (ocena.getOcena() * WAGAPRACYKLASOWEJ);
                            SUMAWAG += WAGAPRACYKLASOWEJ;
                            if (oceny_z_pracyKlasowej.length() == 0) {
                                oceny_z_pracyKlasowej.append(ocena.getOcena());
                            } else {
                                oceny_z_pracyKlasowej.append(", ");
                                oceny_z_pracyKlasowej.append(ocena.getOcena());
                            }
                            break;
                    }
                }
            }
            srednia = (double) suma / SUMAWAG;
            srednia *= 100;
            srednia = Math.round(srednia);
            srednia /= 100;
            studentMarksLayout1.add(new doTabeliMarksAdminLayout1(uczen.getName() + " " + uczen.getNazwisko(),
                    oceny_z_pracyKlasowej.toString(), oceny_z_pracDomowych.toString(), oceny_z_kartkowki.toString(),
                    oceny_z_odpowiedzi.toString(), srednia));
            //add your data to the table here.
        }
        tabela.setItems(studentMarksLayout1);
    }

    @FXML
    void updateDataClassSelected(ActionEvent event) {
        listOfStudentsLayout1.clear();
        uczniowie.clear();
        studentMarksLayout1.clear();
        tabela.getItems().clear();
        String klasa = selectClass.getSelectionModel().getSelectedItem();
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
            listOfStudentsLayout1.add(uczen.getName() + " " + uczen.getNazwisko());
        }
        selectUczen.setItems(listOfStudentsLayout1);

        for (Uczen uczen : uczniowie) {
            double suma = 0;
            srednia = 0;
            SUMAWAG = 0;
            StringBuffer oceny_z_kartkowki = new StringBuffer();
            StringBuffer oceny_z_odpowiedzi = new StringBuffer();
            StringBuffer oceny_z_pracyKlasowej = new StringBuffer();
            StringBuffer oceny_z_pracDomowych = new StringBuffer();
            oceny = uczen.getOceny();
            for (Oceny ocena : oceny) {
                if (ocena.getId_przedmiotu().getId_przedmiotu() == nauczyciel.getId_przedmiotu() && ocena.getUczen().getId_Ucznia() == uczen.getId_Ucznia()) {
                    switch (ocena.getRodzaj_oceny()) {
                        case "Kartkówka":
                            suma += (ocena.getOcena() * WAGAKARTKOWKI);
                            SUMAWAG += WAGAKARTKOWKI;
                            if (oceny_z_kartkowki.length() == 0) {
                                oceny_z_kartkowki.append(ocena.getOcena());
                            } else {
                                oceny_z_kartkowki.append(", ");
                                oceny_z_kartkowki.append(ocena.getOcena());
                            }
                            break;
                        case "Praca domowa":
                            suma += (ocena.getOcena() * WAGAPRACYDOMOWEJ);
                            SUMAWAG += WAGAPRACYDOMOWEJ;
                            if (oceny_z_pracDomowych.length() == 0) {
                                oceny_z_pracDomowych.append(ocena.getOcena());
                            } else {
                                oceny_z_pracDomowych.append(", ");
                                oceny_z_pracDomowych.append(ocena.getOcena());
                            }
                            break;
                        case "Odpowiedź":
                            suma += (ocena.getOcena() * WAGAODPOWIEDZI);
                            SUMAWAG += WAGAODPOWIEDZI;
                            if (oceny_z_odpowiedzi.length() == 0) {
                                oceny_z_odpowiedzi.append(ocena.getOcena());
                            } else {
                                oceny_z_odpowiedzi.append(", ");
                                oceny_z_odpowiedzi.append(ocena.getOcena());
                            }
                            break;
                        case "Praca klasowa":
                            suma += (ocena.getOcena() * WAGAPRACYKLASOWEJ);
                            SUMAWAG += WAGAPRACYKLASOWEJ;
                            if (oceny_z_pracyKlasowej.length() == 0) {
                                oceny_z_pracyKlasowej.append(ocena.getOcena());
                            } else {
                                oceny_z_pracyKlasowej.append(", ");
                                oceny_z_pracyKlasowej.append(ocena.getOcena());
                            }
                            break;
                    }
                }
            }
            srednia = (double) suma / SUMAWAG;
            srednia *= 100;
            srednia = Math.round(srednia);
            srednia /= 100;
            studentMarksLayout1.add(new doTabeliMarksAdminLayout1(uczen.getName() + " " + uczen.getNazwisko(),
                    oceny_z_pracyKlasowej.toString(), oceny_z_pracDomowych.toString(), oceny_z_kartkowki.toString(),
                    oceny_z_odpowiedzi.toString(), srednia));
            //add your data to the table here.
        }
        tabela.setItems(studentMarksLayout1);
    }

    @FXML
    void updateDataStudentSelected(ActionEvent event) {
        if (klasaSelected == null) {
            errorLabel.setText("Wybierz klasę !");
            return;
        }
        String pickedStudentName = selectUczen.getSelectionModel().getSelectedItem();
        for (Uczen uczen : uczniowie) {
            String student = uczen.getName() + " " + uczen.getNazwisko();
            if (student.equals(pickedStudentName)) {
                pickedStudent = uczen;
                break;
            }
        }

    }

    @FXML
    public void changeLayout(ActionEvent actionEvent) {
        if (layout1) {
            layoutDodawanie1.setVisible(false);
            layoutDodawanie2.setVisible(false);
            layoutEdycja1.setVisible(true);
            layoutEdycja2.setVisible(true);
            layout1 = false;
        } else {
            layoutDodawanie1.setVisible(true);
            layoutDodawanie2.setVisible(true);
            layoutEdycja1.setVisible(false);
            layoutEdycja2.setVisible(false);
            layout1 = true;
        }
    }

    @FXML
    public void updateDataClassSelected2(ActionEvent actionEvent) {
        listOfStudentsLayout2.clear();
        uczniowie.clear();
        studentMarksLayout2.clear();
        tabela2.getItems().clear();
        String klasa = selectClass2.getSelectionModel().getSelectedItem();
        for (Klasa klass : klasy) {
            if (klass.getNazwa_klasy().equals(klasa)) {
                klasaSelected = klass;
            }
        }
        if (klasaSelected == null) {
            errorLabel1.setText("Uzupelnij pola");
        } else {
            errorLabel1.setText("");
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
            listOfStudentsLayout2.add(uczen.getName() + " " + uczen.getNazwisko());
        }
        selectUczen2.setItems(listOfStudentsLayout2);

    }

    @FXML
    public void updateDataStudentSelected2(ActionEvent actionEvent) {
        tabela2.getItems().clear();
        if (klasaSelected == null) {
            errorLabel1.setText("Wybierz klasę !");
            return;
        }
        String pickedStudentName = selectUczen2.getSelectionModel().getSelectedItem();
        for (Uczen uczen : uczniowie) {
            String student = uczen.getName() + " " + uczen.getNazwisko();
            if (student.equals(pickedStudentName)) {
                pickedStudent = uczen;
                break;
            }
        }
        List<Oceny> ocenyPickedStudent = pickedStudent.getOceny();

        for (Oceny ocena : ocenyPickedStudent) {
            if (ocena.getId_przedmiotu().getId_przedmiotu() == przedmiotSelected.getId_przedmiotu()) {
                studentMarksLayout2.add(new doTabeliMarksAdminLayout2(ocena.getData().toString(), ocena.getOcena(), ocena.getRodzaj_oceny(),
                        ocena.getId_oceny()));
            }
        }
    }

    @FXML
    public void updateDataPrzedmiotSelected2(ActionEvent actionEvent) {
        tabela2.setItems(studentMarksLayout2);
    }

    @FXML
    void deleteRecordOnAction(ActionEvent event) {
        doTabeliMarksAdminLayout2 selectedForDelete = tabela2.getSelectionModel().getSelectedItem();
        if (selectedForDelete == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Usuwanie oceny");
        alert.setContentText("Czy jesteś pewien, że chcesz usunąć ocenę " + selectedForDelete.getOcena() + " z " + selectedForDelete.getTypOceny() + " z dnia " +
                selectedForDelete.getData() + "?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction txn = session.beginTransaction();
            Query query = session.createQuery("delete Oceny where id_oceny = :id_oceny");
            query.setParameter("id_oceny", selectedForDelete.getId());
            query.executeUpdate();
            //Commit the transaction
            txn.commit();
            pickedUczenBeforeDelete = pickedStudent;
            updateOceny();
            refresh();
        } else {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Usuwanie oceny");
            alert1.setContentText("Usuwanie oceny anulowane.");
            alert1.showAndWait();
        }
    }

    void refresh() {
        tabela2.getItems().clear();
        studentMarksLayout2.clear();
        if (klasaSelected == null) {
            errorLabel1.setText("Wybierz klasę !");
            return;
        }
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        Uczen pickedHereUczen = (Uczen) session.load(Uczen.class, pickedUczenBeforeDelete.getId_Ucznia());
        txn.commit();
        List<Oceny> ocenyPickedStudent = pickedHereUczen.getOceny();

        for (Oceny ocena : ocenyPickedStudent) {
            if (ocena.getId_przedmiotu().getId_przedmiotu() == przedmiotSelected.getId_przedmiotu()) {
                studentMarksLayout2.add(new doTabeliMarksAdminLayout2(ocena.getData().toString(), ocena.getOcena(), ocena.getRodzaj_oceny(),
                        ocena.getId_oceny()));
            }
        }
        tabela2.setItems(studentMarksLayout2);
    }

    private void updateOceny() {
        listOfStudentsLayout2.clear();
        uczniowie.clear();
        studentMarksLayout2.clear();
        tabela2.getItems().clear();
        String klasa = selectClass2.getSelectionModel().getSelectedItem();
        for (Klasa klass : klasy) {
            if (klass.getNazwa_klasy().equals(klasa)) {
                klasaSelected = klass;
            }
        }
        if (klasaSelected == null) {
            errorLabel1.setText("Uzupelnij pola");
        } else {
            errorLabel1.setText("");
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
            listOfStudentsLayout2.add(uczen.getName() + " " + uczen.getNazwisko());
        }
        selectUczen2.setItems(listOfStudentsLayout2);
    }

    @FXML
    void editRecordOnAction(ActionEvent event) {
        Singleton.getInstance().setSelectedForEditMarksAdmin(tabela2.getSelectionModel().getSelectedItem());
        try {
            Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("ui_marks_admin_edit.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Edycja");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/graduate.png")));
            stage.setScene(new Scene(root1));
            pickedUczenBeforeDelete = pickedStudent;
            stage.showAndWait();
            updateOceny();
            refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}

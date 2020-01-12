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
import projektKompetencyjny.*;

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
    private ObservableList<doTabeliMarksAdmin> studentMarks = FXCollections.observableArrayList();

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
        txn.commit();


    }


    @FXML
    void addToDatabase(ActionEvent event) {
        LocalDate dataText = null;

        if (datePicker.validate()) {
            dataText = LocalDate.parse(datePicker.getValue().toString());
        }
        LocalDate dataPo = LocalDate.parse("2020-02-01");

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
            errorLabel.setText("fatal error");
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
            refresh();
        }


    }

    private void refresh() {
        listOfStudents.clear();
        uczniowie.clear();
        studentMarks.clear();
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
            listOfStudents.add(uczen.getName() + " " + uczen.getNazwisko());
        }
        selectUczen.setItems(listOfStudents);

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
            studentMarks.add(new doTabeliMarksAdmin(uczen.getName() + " " + uczen.getNazwisko(),
                    oceny_z_pracyKlasowej.toString(), oceny_z_pracDomowych.toString(), oceny_z_kartkowki.toString(),
                    oceny_z_odpowiedzi.toString(), srednia));
            //add your data to the table here.
        }
        tabela.setItems(studentMarks);
    }

    @FXML
    void updateDataClassSelected(ActionEvent event) {
        listOfStudents.clear();
        uczniowie.clear();
        studentMarks.clear();
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
            listOfStudents.add(uczen.getName() + " " + uczen.getNazwisko());
        }
        selectUczen.setItems(listOfStudents);

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
            studentMarks.add(new doTabeliMarksAdmin(uczen.getName() + " " + uczen.getNazwisko(),
                    oceny_z_pracyKlasowej.toString(), oceny_z_pracDomowych.toString(), oceny_z_kartkowki.toString(),
                    oceny_z_odpowiedzi.toString(), srednia));
            //add your data to the table here.
        }
        tabela.setItems(studentMarks);

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


}

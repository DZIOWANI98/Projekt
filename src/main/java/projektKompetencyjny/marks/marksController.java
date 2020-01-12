package projektKompetencyjny.marks;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import projektKompetencyjny.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static projektKompetencyjny.setGlobalUczen.getUczen;

public class marksController implements Initializable {
    Uczen uczen = getUczen();

    private List<Przedmiot> przedmiotty = new ArrayList<>();

    private static final int WAGAPRACYKLASOWEJ = 3;
    private static final int WAGAODPOWIEDZI = 1;
    private static final int WAGAPRACYDOMOWEJ = 1;
    private static final int WAGAKARTKOWKI = 2;
    private double srednia;
    private int SUMAWAG = 0;
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

    @FXML
    private TableColumn<doTabeliUcznia, Double> sredniaColumn;

    private ObservableList<doTabeliUcznia> studentsModels = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        przedmiotyColumn.setCellValueFactory(new PropertyValueFactory<>("Przedmiot"));
        klasowkiColumn.setCellValueFactory(new PropertyValueFactory<>("Klasowka"));
        pracedomoweColumn.setCellValueFactory(new PropertyValueFactory<>("PraceDomowe"));
        kartkowkiColumn.setCellValueFactory(new PropertyValueFactory<>("Kartkowka"));
        odpowiedziColumn.setCellValueFactory(new PropertyValueFactory<>("Odpowiedz"));
        sredniaColumn.setCellValueFactory(new PropertyValueFactory<>("Srednia"));


        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;


        tx = session.beginTransaction();
        List przedmioty = session.createQuery("FROM Przedmiot").list();
        for (Iterator iterator1 = przedmioty.iterator(); iterator1.hasNext(); ) {
            Przedmiot przedmiot = (Przedmiot) iterator1.next();
            przedmiotty.add(przedmiot);
        }
        tx.commit();

        List<Oceny> oceny = uczen.getOceny();

        for (Przedmiot sub : przedmiotty) {
            double suma = 0;
            srednia = 0;
            SUMAWAG = 0;
            StringBuffer oceny_z_kartkowki = new StringBuffer();
            StringBuffer oceny_z_odpowiedzi = new StringBuffer();
            StringBuffer oceny_z_pracyKlasowej = new StringBuffer();
            StringBuffer oceny_z_pracDomowych = new StringBuffer();
            for (Oceny ocena : oceny) {
                if (ocena.getId_przedmiotu().getId_przedmiotu() == sub.getId_przedmiotu() && ocena.getUczen().getId_Ucznia() == uczen.getId_Ucznia()) {
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
            studentsModels.add(new doTabeliUcznia(sub.getNazwa_przedmiotu(), oceny_z_pracyKlasowej.toString(), oceny_z_pracDomowych.toString(), oceny_z_kartkowki.toString(),
                    oceny_z_odpowiedzi.toString(), srednia));
            //add your data to the table here.
        }
        tabela.setItems(studentsModels);
    }

    @FXML
    void semestr1Clicked(MouseEvent event) {
        tabela.getItems().clear();
        List<Oceny> oceny = uczen.getOceny();

        for (Przedmiot sub : przedmiotty) {
            double suma = 0;
            srednia = 0;
            SUMAWAG = 0;
            StringBuffer oceny_z_kartkowki = new StringBuffer();
            StringBuffer oceny_z_odpowiedzi = new StringBuffer();
            StringBuffer oceny_z_pracyKlasowej = new StringBuffer();
            StringBuffer oceny_z_pracDomowych = new StringBuffer();
            for (Oceny ocena : oceny) {
                if (ocena.getId_przedmiotu().getId_przedmiotu() == sub.getId_przedmiotu() && ocena.getUczen().getId_Ucznia() == uczen.getId_Ucznia()) {
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
            studentsModels.add(new doTabeliUcznia(sub.getNazwa_przedmiotu(), oceny_z_pracyKlasowej.toString(), oceny_z_pracDomowych.toString(), oceny_z_kartkowki.toString(),
                    oceny_z_odpowiedzi.toString(), srednia));
            //add your data to the table here.
        }
        tabela.setItems(studentsModels);

    }

    @FXML
    void semestr2Clicked(MouseEvent event) {
        tabela.getItems().clear();
        List<Oceny> oceny = uczen.getOceny();

        for (Przedmiot sub : przedmiotty) {
            double suma = 0;
            srednia = 0;
            SUMAWAG = 0;
            StringBuffer oceny_z_kartkowki = new StringBuffer();
            StringBuffer oceny_z_odpowiedzi = new StringBuffer();
            StringBuffer oceny_z_pracyKlasowej = new StringBuffer();
            StringBuffer oceny_z_pracDomowych = new StringBuffer();
            for (Oceny ocena : oceny) {
                if (ocena.getId_przedmiotu().getId_przedmiotu() == sub.getId_przedmiotu() && ocena.getUczen().getId_Ucznia() == uczen.getId_Ucznia()) {
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
            studentsModels.add(new doTabeliUcznia(sub.getNazwa_przedmiotu(), oceny_z_pracyKlasowej.toString(), oceny_z_pracDomowych.toString(), oceny_z_kartkowki.toString(),
                    oceny_z_odpowiedzi.toString(), srednia));
            //add your data to the table here.
        }
        tabela.setItems(studentsModels);
    }

}

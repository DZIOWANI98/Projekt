package projektKompetencyjny.rodzicFXML;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import projektKompetencyjny.Oceny;
import projektKompetencyjny.Przedmiot;
import projektKompetencyjny.Uczen;
import projektKompetencyjny.doTabeliUcznia;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static projektKompetencyjny.setGlobalUczen.getUczen;

public class UiMarkRodzic implements Initializable {
    Uczen uczen = getUczen();

    private List<Przedmiot> przedmiotty = new ArrayList<>();

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
    private ObservableList<doTabeliUcznia> studentsModels = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        przedmiotyColumn.setCellValueFactory(new PropertyValueFactory<>("Przedmiot"));
        klasowkiColumn.setCellValueFactory(new PropertyValueFactory<>("Klasowka"));
        pracedomoweColumn.setCellValueFactory(new PropertyValueFactory<>("PraceDomowe"));
        kartkowkiColumn.setCellValueFactory(new PropertyValueFactory<>("Kartkowka"));
        odpowiedziColumn.setCellValueFactory(new PropertyValueFactory<>("Odpowiedz"));


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
            StringBuffer oceny_z_kartkowki = new StringBuffer();
            StringBuffer oceny_z_odpowiedzi = new StringBuffer();
            StringBuffer oceny_z_pracyKlasowej = new StringBuffer();
            StringBuffer oceny_z_pracDomowych = new StringBuffer();
            for (Oceny ocena : oceny) {
                if (ocena.getId_przedmiotu().getId_przedmiotu() == sub.getId_przedmiotu() && ocena.getUczen().getId_Ucznia() == uczen.getId_Ucznia()) {
                    switch (ocena.getRodzaj_oceny()) {
                        case "Kartkówka":
                            oceny_z_kartkowki.append(ocena.getOcena());
                            break;
                        case "Praca domowa":
                            oceny_z_pracDomowych.append(ocena.getOcena());
                            break;
                        case "Odpowiedź":
                            oceny_z_odpowiedzi.append(ocena.getOcena());
                            break;
                        case "Praca klasowa":
                            oceny_z_pracyKlasowej.append(ocena.getOcena());
                    }
                }
            }
            studentsModels.add(new doTabeliUcznia(sub.getNazwa_przedmiotu(), oceny_z_pracyKlasowej.toString(), oceny_z_pracDomowych.toString(), oceny_z_kartkowki.toString(), oceny_z_odpowiedzi.toString()));
            //add your data to the table here.
        }
        tabela.setItems(studentsModels);
    }

    @FXML
    void semestr1Clicked(MouseEvent event) {
        tabela.getItems().clear();
        List<Oceny> oceny = uczen.getOceny();

        for (Przedmiot sub : przedmiotty) {
            StringBuffer oceny_z_kartkowki = new StringBuffer();
            StringBuffer oceny_z_odpowiedzi = new StringBuffer();
            StringBuffer oceny_z_pracyKlasowej = new StringBuffer();
            StringBuffer oceny_z_pracDomowych = new StringBuffer();
            for (Oceny ocena : oceny) {
                if (ocena.getId_przedmiotu().getId_przedmiotu() == sub.getId_przedmiotu() && ocena.getUczen().getId_Ucznia() == uczen.getId_Ucznia() && ocena.getNr_semestru() == 1) {
                    switch (ocena.getRodzaj_oceny()) {
                        case "Kartkówka":
                            oceny_z_kartkowki.append(ocena.getOcena());
                            break;
                        case "Praca domowa":
                            oceny_z_pracDomowych.append(ocena.getOcena());
                            break;
                        case "Odpowiedź":
                            oceny_z_odpowiedzi.append(ocena.getOcena());
                            break;
                        case "Praca klasowa":
                            oceny_z_pracyKlasowej.append(ocena.getOcena());
                    }
                }
            }
            studentsModels.add(new doTabeliUcznia(sub.getNazwa_przedmiotu(), oceny_z_pracyKlasowej.toString(), oceny_z_pracDomowych.toString(), oceny_z_kartkowki.toString(), oceny_z_odpowiedzi.toString()));
            //add your data to the table here.
        }
        tabela.setItems(studentsModels);

    }

    @FXML
    void semestr2Clicked(MouseEvent event) {
        tabela.getItems().clear();
        List<Oceny> oceny = uczen.getOceny();

        for (Przedmiot sub : przedmiotty) {
            StringBuffer oceny_z_kartkowki = new StringBuffer();
            StringBuffer oceny_z_odpowiedzi = new StringBuffer();
            StringBuffer oceny_z_pracyKlasowej = new StringBuffer();
            StringBuffer oceny_z_pracDomowych = new StringBuffer();
            for (Oceny ocena : oceny) {
                if (ocena.getId_przedmiotu().getId_przedmiotu() == sub.getId_przedmiotu() && ocena.getUczen().getId_Ucznia() == uczen.getId_Ucznia() && ocena.getNr_semestru() == 2) {
                    switch (ocena.getRodzaj_oceny()) {
                        case "Kartkówka":
                            oceny_z_kartkowki.append(ocena.getOcena());
                            break;
                        case "Praca domowa":
                            oceny_z_pracDomowych.append(ocena.getOcena());
                            break;
                        case "Odpowiedź":
                            oceny_z_odpowiedzi.append(ocena.getOcena());
                            break;
                        case "Praca klasowa":
                            oceny_z_pracyKlasowej.append(ocena.getOcena());
                    }
                }
            }
            studentsModels.add(new doTabeliUcznia(sub.getNazwa_przedmiotu(), oceny_z_pracyKlasowej.toString(), oceny_z_pracDomowych.toString(), oceny_z_kartkowki.toString(), oceny_z_odpowiedzi.toString()));
            //add your data to the table here.
        }
        tabela.setItems(studentsModels);
    }
}

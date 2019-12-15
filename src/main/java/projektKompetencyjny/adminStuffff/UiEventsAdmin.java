package projektKompetencyjny.adminStuffff;

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
import projektKompetencyjny.Event;
import projektKompetencyjny.Klasa;
import projektKompetencyjny.Nauczyciel;
import projektKompetencyjny.event_uczen.doTabeliEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static projektKompetencyjny.setGlobalNauczyciel.getNauczyciel;

public class UiEventsAdmin implements Initializable {
    Nauczyciel nauczyciel = getNauczyciel();

    private List<Klasa> klasy = new ArrayList<>();

    private List<Event> events = new ArrayList<>();

    @FXML
    private TableView<doTabeliEvent> tabelkaEventAdmin;

    @FXML
    private TableColumn<doTabeliEvent, String> futureEvent;

    @FXML
    private TableColumn<doTabeliEvent, String> data;

    @FXML
    private ComboBox<String> selectClass;

    @FXML
    private TextField eventTextArea;

    @FXML
    private TextField dataTextArea;

    @FXML
    private Button addButton;

    @FXML
    private Label errorLabel;

    @FXML
    private MenuItem deleteMenuItem;


    private ObservableList<String> selectClassObservableList = FXCollections.observableArrayList();
    private ObservableList<doTabeliEvent> eventyObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        futureEvent.setCellValueFactory(new PropertyValueFactory<>("Event"));
        data.setCellValueFactory(new PropertyValueFactory<>("Data"));

        this.deleteMenuItem.disableProperty().bind(this.tabelkaEventAdmin.getSelectionModel().selectedItemProperty().isNull());
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        klasy = nauczyciel.getKlasy();

        for (Klasa classa : klasy) {
            selectClassObservableList.add(classa.getNazwa_klasy());
        }
        selectClass.setItems(selectClassObservableList);

        List eventy = session.createQuery("FROM Event").list();
        for (Iterator iterator1 = eventy.iterator(); iterator1.hasNext(); ) {
            Event event = (Event) iterator1.next();
            events.add(event);
        }

    }


    @FXML
    void updateData(ActionEvent event) {
        tabelkaEventAdmin.getItems().clear();
        String klasa = selectClass.getSelectionModel().getSelectedItem();

        LocalDate localDate = LocalDate.now();
        for (Event eventt : events) {
            if (eventt.getId_klasy().getNazwa_klasy().equals(klasa)) {
                //System.out.println(eventt.getEvent() + " " + eventt.getData());
                LocalDate data = eventt.getData();
                if (data.isAfter(localDate)) {
                    //System.out.println("jestem here");
                    eventyObservableList.add(new doTabeliEvent(eventt.getEvent(), eventt.getData().toString()));
                }
            }
        }
        tabelkaEventAdmin.setItems(eventyObservableList);
    }

    @FXML
    void addToDatabase(ActionEvent event) {

        String eventText = eventTextArea.getText();
        LocalDate dataText = LocalDate.parse(dataTextArea.getText());
        Klasa klassa = null;
        for (Klasa klass : klasy) {
            if (klass.getNazwa_klasy().equals(selectClass.getSelectionModel().getSelectedItem())) {
                klassa = klass;
            }
        }
        if (klassa == null || eventText.isEmpty()) {
            errorLabel.setText("Blad, puste pola");
        }
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        //Add new Employee object
        Event eve = new Event();
        eve.setEvent(eventText);
        eve.setData(dataText);
        eve.setId_klasy(klassa);
        //Save the employee in database
        session.save(eve);
        //Commit the transaction
        tx.commit();
        updateEvent();
        refresh();
    }

    private void updateEvent() {
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        events.clear();
        List eventy = session.createQuery("FROM Event").list();
        for (Iterator iterator1 = eventy.iterator(); iterator1.hasNext(); ) {
            Event event = (Event) iterator1.next();
            events.add(event);
        }
    }


    void refresh() {
        tabelkaEventAdmin.getItems().clear();
        String klasa = selectClass.getSelectionModel().getSelectedItem();

        LocalDate localDate = LocalDate.now();
        for (Event eventt : events) {
            if (eventt.getId_klasy().getNazwa_klasy().equals(klasa)) {
                //System.out.println(eventt.getEvent() + " " + eventt.getData());
                LocalDate data = eventt.getData();
                if (data.isAfter(localDate)) {
                    //System.out.println("jestem here");
                    eventyObservableList.add(new doTabeliEvent(eventt.getEvent(), eventt.getData().toString()));
                }
            }
        }
        tabelkaEventAdmin.setItems(eventyObservableList);
    }

    @FXML
    void deleteRecordOnAction(ActionEvent event) {
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        int id_event = 0;
        for (Event eventt : events) {
            System.out.println(eventt.getData().toString() + " " + eventt.getEvent());
            System.out.println(tabelkaEventAdmin.getSelectionModel().getSelectedItem().getData() + " " + tabelkaEventAdmin.getSelectionModel().getSelectedItem().getEvent());
            if (eventt.getEvent().equals(tabelkaEventAdmin.getSelectionModel().getSelectedItem().getEvent())
                    && eventt.getData().equals(tabelkaEventAdmin.getSelectionModel().getSelectedItem().getData())) {
                id_event = eventt.getId_event();
                System.out.println(id_event);
            }
        }
        if (id_event == 0)
            return;
        System.out.println(id_event);
        tx = session.beginTransaction();

        Event event1 = (Event) session.get(Event.class, id_event);
        //Add new Employee object
        //Save the employee in database
        session.delete(event1);
        //Commit the transaction
        tx.commit();
        updateEvent();
        refresh();
    }


}

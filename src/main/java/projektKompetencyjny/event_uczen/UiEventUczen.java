package projektKompetencyjny.event_uczen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import projektKompetencyjny.Event;
import projektKompetencyjny.Przedmiot;
import projektKompetencyjny.Uczen;
import projektKompetencyjny.doTabeliUcznia;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static projektKompetencyjny.setGlobalUczen.getUczen;

public class UiEventUczen implements Initializable {
    Uczen uczen = getUczen();

    private List<Event> events = new ArrayList<>();

    @FXML
    private TableView<doTabeliEvent> tabelaMain;

    @FXML
    private TableColumn<doTabeliEvent, String> futureEvent;

    @FXML
    private TableColumn<doTabeliEvent, String> data;

    private ObservableList<doTabeliEvent> eventyObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        futureEvent.setCellValueFactory(new PropertyValueFactory<>("Event"));
        data.setCellValueFactory(new PropertyValueFactory<>("Data"));

        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        tx = session.beginTransaction();
        List eventy = session.createQuery("FROM Event").list();
        for (Iterator iterator1 = eventy.iterator(); iterator1.hasNext(); ) {
            Event event = (Event) iterator1.next();
            events.add(event);
        }
        tx.commit();

        int klasa = uczen.getIdKlasy().getId_klasy();

        for (Event event : events) {
            if (event.getId_klasy().getId_klasy() == klasa) {
                eventyObservableList.add(new doTabeliEvent(event.getEvent(), event.getData()));
            }
        }
        tabelaMain.setItems(eventyObservableList);

    }
}

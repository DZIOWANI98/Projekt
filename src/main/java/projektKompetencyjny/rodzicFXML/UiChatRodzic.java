package projektKompetencyjny.rodzicFXML;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
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
import projektKompetencyjny.adminStuffff.doTabeliChatAdmin;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static projektKompetencyjny.setGlobalRodzic.getRodzic;
import static projektKompetencyjny.setGlobalUczen.getUczen;

public class UiChatRodzic implements Initializable {
    Uczen uczen = getUczen();

    Rodzic rodzic = getRodzic();

    //id rozwijanej listy listaOsob
    //id kolumn to data, godzina, autor, wiadomosc
    //id przycisku wyslij
    //id pola tekstowego tekstWiadomosci

    private List<Przedmiot> przedmioty = new ArrayList<>();

    private Przedmiot przedmiotSelected = null;

    private Nauczyciel pickedNauczyciel = null;

    private List<Nauczyciel> nauczyciele = new ArrayList<>();

    private List<Wiadomosci> wiadomosci = new ArrayList<>();

    @FXML
    private JFXComboBox<String> listaNauczycieli;

    @FXML
    private JFXComboBox<String> listaPrzedmiotow;

    @FXML
    private Label errorLabel;

    @FXML
    private TableView<doTabeliChatRodzic> tabelaMain;

    @FXML
    private TableColumn<doTabeliChatRodzic, String> data;

    @FXML
    private TableColumn<doTabeliChatRodzic, String> godzina;

    @FXML
    private TableColumn<doTabeliChatRodzic, String> autor;

    @FXML
    private TableColumn<doTabeliChatRodzic, String> wiadomosc;

    @FXML
    private TableColumn<doTabeliChatRodzic, Integer> id;

    @FXML
    private JFXTextField tekstWiadomosci;

    @FXML
    private JFXButton wyslij;

    private ObservableList<String> listaNauczycieliObservableList = FXCollections.observableArrayList();
    private ObservableList<String> listaPrzedmiotowObservableList = FXCollections.observableArrayList();
    private ObservableList<doTabeliChatRodzic> wiadomosciObservableList = FXCollections.observableArrayList();

    private ScheduledExecutorService service = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        godzina.setCellValueFactory(new PropertyValueFactory<>("Godzina"));
        autor.setCellValueFactory(new PropertyValueFactory<>("Autor"));
        wiadomosc.setCellValueFactory(new PropertyValueFactory<>("Wiadomosc"));
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));

        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List przedmiots = session.createQuery("FROM Przedmiot").list();
        for (Iterator iterator1 = przedmiots.iterator(); iterator1.hasNext(); ) {
            Przedmiot przedmiot = (Przedmiot) iterator1.next();
            przedmioty.add(przedmiot);
        }
        for (Przedmiot przedmiot : przedmioty) {
            listaPrzedmiotowObservableList.add(przedmiot.getNazwa_przedmiotu());
            nauczyciele.addAll(przedmiot.getNauczyciele());
        }
        listaPrzedmiotow.setItems(listaPrzedmiotowObservableList);

    }

    @FXML
    void listaPrzedmiotowSelected(ActionEvent event) {
        listaNauczycieliObservableList.clear();
        String przedmiot = listaPrzedmiotow.getSelectionModel().getSelectedItem();
        for (Przedmiot przedmiott : przedmioty) {
            if (przedmiott.getNazwa_przedmiotu().equals(przedmiot)) {
                przedmiotSelected = przedmiott;
            }
        }
        if (przedmiotSelected == null) {
            errorLabel.setText("fatal error");
        }

        for (Nauczyciel nauczyciel : nauczyciele) {
            List<Klasa> klasy = nauczyciel.getKlasy();
            System.out.println(nauczyciel.toString());
            for (Klasa klas : klasy) {
                if (klas.getId_klasy() == uczen.getIdKlasy().getId_klasy()) {
                    listaNauczycieliObservableList.add(nauczyciel.getImie() + " " + nauczyciel.getNazwisko());
                }
            }
        }
        listaNauczycieli.setItems(listaNauczycieliObservableList);
    }


    @FXML
    void nauczycielSelected(ActionEvent event) {
        if (service != null && !service.isShutdown()) {
            service.shutdown();
        }
        if (przedmiotSelected == null) {
            errorLabel.setText("Wybierz przedmiot !");
        } else {
            errorLabel.setText("");
        }
        String nauczyciel = listaNauczycieli.getSelectionModel().getSelectedItem();
        for (Nauczyciel nauczyciel1 : nauczyciele) {
            String nauczycielImie = nauczyciel1.getImie() + " " + nauczyciel1.getNazwisko();
            if (nauczycielImie.equals(nauczyciel)) {
                pickedNauczyciel = nauczyciel1;
            }
        }
        if (pickedNauczyciel == null) {
            errorLabel.setText("Fatal error");
        } else {
            errorLabel.setText("");
        }

        Runnable runnableTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
                getDataFromDB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(runnableTask, 0, 5, TimeUnit.SECONDS);


    }

    private void getDataFromDB() {
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        wiadomosci.clear();
        wiadomosciObservableList.clear();
        tabelaMain.getItems().clear();
        List msg = session.createQuery("FROM Wiadomosci").list();
        for (Iterator iterator1 = msg.iterator(); iterator1.hasNext(); ) {
            Wiadomosci msgg = (Wiadomosci) iterator1.next();
            wiadomosci.add(msgg);
        }
        Collections.sort(wiadomosci, new Comparator<Wiadomosci>() {
            public int compare(Wiadomosci left, Wiadomosci right) {
                return left.getId_wiadomosci() - right.getId_wiadomosci();
            }
        });
        for (Wiadomosci wiad : wiadomosci) {
            if (wiad.getRodzic().getId() == rodzic.getId() && wiad.getNauczyciel().getId_nauczyciela() == pickedNauczyciel.getId_nauczyciela()) {
                wiadomosciObservableList.add(new doTabeliChatRodzic(wiad.getData().toString(), wiad.getCzas().toString(),
                        wiad.getAutor(), wiad.getWiadomosc(), wiad.getId_wiadomosci()));
            }
        }
        for (doTabeliChatRodzic wiad : wiadomosciObservableList) {
            System.out.println(wiad);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tabelaMain.setItems(wiadomosciObservableList);
                tabelaMain.refresh();
            }
        });

    }

    @FXML
    public void wyslijDoBazy(ActionEvent actionEvent) {
        String wiadomoscText = tekstWiadomosci.getText();
        LocalDate dataWiadomosci = LocalDate.now();
        String autorWiadomosci = rodzic.getImie() + " " + rodzic.getNazwisko();
        LocalTime czasWiadomosci = LocalTime.now();

        if (pickedNauczyciel == null) {
            errorLabel.setText("Brak wybranych pól!");
        } else if (wiadomoscText.isEmpty()) {
            errorLabel.setText("Musisz cos wpisać w wiadomości.");
        } else {
            errorLabel.setText("");
            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            tx = session.beginTransaction();
            Wiadomosci wiadomosc = new Wiadomosci();
            wiadomosc.setWiadomosc(wiadomoscText);
            wiadomosc.setData(dataWiadomosci);
            wiadomosc.setAutor(autorWiadomosci);
            wiadomosc.setCzas(Time.valueOf(czasWiadomosci));
            wiadomosc.setNauczyciel(pickedNauczyciel);
            wiadomosc.setRodzic(rodzic);
            session.save(wiadomosc);
            tx.commit();
            tekstWiadomosci.clear();
        }

    }




}

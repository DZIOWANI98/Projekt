package projektKompetencyjny.adminStuffff;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static projektKompetencyjny.setGlobalNauczyciel.getNauczyciel;

public class UiChatAdmin implements Initializable {
    private Nauczyciel nauczyciel = getNauczyciel();

    private List<Klasa> klasy = new ArrayList<>();

    private List<Uczen> uczniowie = new ArrayList<>();

    private List<Wiadomosci> wiadomosciSelectedRodzica = new ArrayList<>();

    private List<Wiadomosci> wiadomosciAdmina = new ArrayList<>();

    private Klasa klasaSelected = null;
    private Uczen pickedStudent = null;
    private Rodzic pickedRodzic = null;

    private List<Wiadomosci> wiadomosci = new ArrayList<>();

    @FXML
    private JFXComboBox<String> listaOsob;

    @FXML
    private JFXComboBox<String> listaKlas;

    @FXML
    private Label labelJakiRodzic;

    @FXML
    private Label errorLabel;

    @FXML
    private TableView<doTabeliChatAdmin> tabelaMain;

    @FXML
    private TableColumn<doTabeliChatAdmin, String> data;

    @FXML
    private TableColumn<doTabeliChatAdmin, String> godzina;

    @FXML
    private TableColumn<doTabeliChatAdmin, String> autor;

    @FXML
    private TableColumn<doTabeliChatAdmin, String> wiadomosc;

    @FXML
    private TableColumn<doTabeliChatAdmin, Integer> id;

    @FXML
    private JFXTextField tekstWiadomosci;

    @FXML
    private JFXButton wyslij;

    private ObservableList<String> listaKlasObservableList = FXCollections.observableArrayList();
    private ObservableList<String> listaOsobObservableList = FXCollections.observableArrayList();
    private ObservableList<doTabeliChatAdmin> wiadomosciObservableList = FXCollections.observableArrayList();

    private ScheduledExecutorService service = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        godzina.setCellValueFactory(new PropertyValueFactory<>("Godzina"));
        autor.setCellValueFactory(new PropertyValueFactory<>("Autor"));
        wiadomosc.setCellValueFactory(new PropertyValueFactory<>("Wiadomosc"));
        id.setCellValueFactory(new PropertyValueFactory<>("Id"));

        klasy = nauczyciel.getKlasy();

        for (Klasa classa : klasy) {
            listaKlasObservableList.add(classa.getNazwa_klasy());
        }
        listaKlas.setItems(listaKlasObservableList);


    }

    @FXML
    void classPicked(ActionEvent event) {
        listaOsobObservableList.clear();
        uczniowie.clear();
        tabelaMain.getItems().clear();
        String klasa = listaKlas.getSelectionModel().getSelectedItem();
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
            listaOsobObservableList.add(uczen.getName() + " " + uczen.getNazwisko());
        }
        listaOsob.setItems(listaOsobObservableList);


    }

    @FXML
    void studentPicked(ActionEvent event) {
        if (service != null && !service.isShutdown()) {
            service.shutdown();
        }
        if (klasaSelected == null) {
            errorLabel.setText("Wybierz klasę !");
            return;
        }
        String pickedStudentName = listaOsob.getSelectionModel().getSelectedItem();
        for (Uczen uczen : uczniowie) {
            String student = uczen.getName() + " " + uczen.getNazwisko();
            if (student.equals(pickedStudentName)) {
                pickedStudent = uczen;
                break;
            }
        }
        pickedRodzic = pickedStudent.getRodzice().get(0);
        String imieNazwiskoRodzica = pickedRodzic.getImie() + " " + pickedRodzic.getNazwisko();
        labelJakiRodzic.setText(imieNazwiskoRodzica);


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
            if (wiad.getRodzic().getId() == pickedRodzic.getId() && wiad.getNauczyciel().getId_nauczyciela() == nauczyciel.getId_nauczyciela()) {
                wiadomosciObservableList.add(new doTabeliChatAdmin(wiad.getData().toString(), wiad.getCzas().toString(),
                        wiad.getAutor(), wiad.getWiadomosc(), wiad.getId_wiadomosci()));
            }
        }
        for (doTabeliChatAdmin wiad : wiadomosciObservableList) {
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

/*
    protected class LoadWiadomosciTask extends Task<List<Wiadomosci>>
    {

        @Override
        protected List<Wiadomosci>call() throws Exception {

            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            List msg = session.createQuery("FROM Wiadomosci").list();
            for (Iterator iterator1 = msg.iterator(); iterator1.hasNext(); ) {
                Wiadomosci msgg = (Wiadomosci) iterator1.next();
                wiadomosci.add(msgg);
            }
            for (Wiadomosci wiad: wiadomosci) {
                if(wiad.getRodzic().getId() == pickedRodzic.getId() && wiad.getNauczyciel().getId_nauczyciela() == nauczyciel.getId_nauczyciela())
                {
                    wiadomosciObservableList.add(new doTabeliChatAdmin(wiad.getData().toString(), wiad.getCzas().toString(),
                            wiad.getAutor(), wiad.getWiadomosc(), wiad.getId_wiadomosci()));
                }
            }
            return msg;
        }

        @Override
        protected void succeeded() {
            tabelaMain.setItems(wiadomosciObservableList);
        }

    }
*/

    @FXML
    void wyslijDoBazy(ActionEvent event) {
        String wiadomoscText = tekstWiadomosci.getText();
        LocalDate dataWiadomosci = LocalDate.now();
        String autorWiadomosci = nauczyciel.getImie() + " " + nauczyciel.getNazwisko();
        LocalTime czasWiadomosci = LocalTime.now();

        if (pickedRodzic == null) {
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
            wiadomosc.setNauczyciel(nauczyciel);
            wiadomosc.setRodzic(pickedRodzic);
            session.save(wiadomosc);
            tx.commit();
            tekstWiadomosci.clear();
        }
    }


}

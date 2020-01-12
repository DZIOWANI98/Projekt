package projektKompetencyjny.rodzicFXML;

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
import org.hibernate.query.Query;
import projektKompetencyjny.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import static projektKompetencyjny.setGlobalUczen.getUczen;

public class UiRodzicUwagi implements Initializable {
    Uczen uczen = getUczen();
    private List<Uwaga> uwagi = new ArrayList<>();

    //id kolumn to data, nauczyciel, uwaga
    @FXML
    private TableView<doTabeliUwagiRodzic> tabelaMain;

    @FXML
    private TableColumn<doTabeliUwagiRodzic, String> data;

    @FXML
    private TableColumn<doTabeliUwagiRodzic, String> nauczyciel;

    @FXML
    private TableColumn<doTabeliUwagiRodzic, String> uwaga;

    private ObservableList<doTabeliUwagiRodzic> uwagiObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        data.setCellValueFactory(new PropertyValueFactory<>("Data"));
        nauczyciel.setCellValueFactory(new PropertyValueFactory<>("Nauczyciel"));
        uwaga.setCellValueFactory(new PropertyValueFactory<>("Uwaga"));

        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        tx = session.beginTransaction();
        Query query = session.createQuery("FROM Uwaga U Where U.uczen = :uczen");
        query.setParameter("uczen", uczen);
        List uwagiList = query.list();
        for (Iterator iterator1 = uwagiList.iterator(); iterator1.hasNext(); ) {
            Uwaga uwaga = (Uwaga) iterator1.next();
            uwagi.add(uwaga);
        }
        tx.commit();


        for (Uwaga uwaga : uwagi) {
            uwagiObservableList.add(new doTabeliUwagiRodzic(uwaga.getData().toString(), uwaga.getUwaga(), uwaga.getNauczyciel().getImie() + " " +
                    uwaga.getNauczyciel().getNazwisko()));
        }
        tabelaMain.setItems(uwagiObservableList);
    }


}

package projektKompetencyjny.adminStuffff;

import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import projektKompetencyjny.Event;
import projektKompetencyjny.Uwaga;
import projektKompetencyjny.doTabeliUwagi;
import projektKompetencyjny.event_uczen.doTabeliEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UiEventAdminEdit implements Initializable {

    @FXML
    private TextField eventTextEdit;

    @FXML
    private JFXDatePicker dateEventEdit;

    @FXML
    private Button zatwierdzButton;

    @FXML
    private Button anulujButton;

    private doTabeliEvent selectedForDelete;

    @FXML
    void anulujButtonClicked(ActionEvent event) {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void zatwierdzButtonClicked(ActionEvent event) {
        LocalDate dataText = null;
        if (dateEventEdit.validate()) {
            dataText = LocalDate.parse(dateEventEdit.getValue().toString());
        }
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        Event eventForEdit = (Event) session.load(Event.class, selectedForDelete.getId());
        eventForEdit.setEvent(eventTextEdit.getText());
        eventForEdit.setData(dataText);
        txn.commit();
        Stage stage = (Stage) zatwierdzButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedForDelete = Singleton.getInstance().getSelectedForEditEvent();
        eventTextEdit.setText(selectedForDelete.getEvent());
        dateEventEdit.setValue(LocalDate.parse(selectedForDelete.getData()));
    }
}

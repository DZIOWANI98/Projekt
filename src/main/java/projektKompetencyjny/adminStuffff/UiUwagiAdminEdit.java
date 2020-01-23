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
import projektKompetencyjny.Uwaga;
import projektKompetencyjny.doTabeliUwagi;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UiUwagiAdminEdit implements Initializable {

    @FXML
    private TextField uwagaTextEdit;

    @FXML
    private JFXDatePicker dataPickerEdit;

    @FXML
    private Button zatwierdzButton;

    @FXML
    private Button anulujEditButton;

    private doTabeliUwagi selectedForDelete;

    @FXML
    void anulujClicked(ActionEvent event) {
        Stage stage = (Stage) anulujEditButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void zatwierdzClicked(ActionEvent event) {
        LocalDate dataText = null;
        if (dataPickerEdit.validate()) {
            dataText = LocalDate.parse(dataPickerEdit.getValue().toString());
        }
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        Uwaga uwaga = (Uwaga) session.load(Uwaga.class, selectedForDelete.getId());
        uwaga.setUwaga(uwagaTextEdit.getText());
        uwaga.setData(dataText);
        session.update(uwaga);
        txn.commit();
        Stage stage = (Stage) zatwierdzButton.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedForDelete = Singleton.getInstance().getSelectedForEditUwagi();
        uwagaTextEdit.setText(selectedForDelete.getUwaga());
        dataPickerEdit.setValue(LocalDate.parse(selectedForDelete.getData()));
    }
}

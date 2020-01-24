package projektKompetencyjny.adminStuffff;

import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import projektKompetencyjny.Oceny;
import projektKompetencyjny.Uwaga;
import projektKompetencyjny.doTabeliUwagi;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UiMarksAdminEdit implements Initializable {

    @FXML
    private ComboBox<String> typOcenyListEdit;

    @FXML
    private TextField ocenaEdit;

    @FXML
    private JFXDatePicker datePickerEdit;

    @FXML
    private Button zatwierdzButton;

    @FXML
    private Button cancelButton;

    private doTabeliMarksAdminLayout2 selectedForDelete;

    private ObservableList<String> rodzajOceny = FXCollections.observableArrayList();

    @FXML
    void cancelButtonClicked(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void zatwierdzButtonClicked(ActionEvent event) {
        LocalDate dataText = null;
        if (datePickerEdit.validate()) {
            dataText = LocalDate.parse(datePickerEdit.getValue().toString());
        }
        LocalDate dataPo = LocalDate.parse("2020-02-01");
        int nr_semestru = 0;
        if (dataText != null && dataText.isAfter(dataPo)) {
            nr_semestru = 2;
        } else {
            nr_semestru = 1;
        }
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction txn = session.beginTransaction();
        Oceny ocena = (Oceny) session.load(Oceny.class, selectedForDelete.getId());
        ocena.setData(dataText);
        ocena.setOcena(Integer.parseInt(ocenaEdit.getText()));
        ocena.setRodzaj_oceny(typOcenyListEdit.getSelectionModel().getSelectedItem());
        session.update(ocena);
        txn.commit();
        Stage stage = (Stage) zatwierdzButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rodzajOceny.addAll("Praca domowa", "Kartkówka", "Odpowiedź", "Praca klasowa");
        typOcenyListEdit.setItems(rodzajOceny);
        selectedForDelete = Singleton.getInstance().getSelectedForEditMarksAdmin();
        ocenaEdit.setText(selectedForDelete.getOcena() + "");
        datePickerEdit.setValue(LocalDate.parse(selectedForDelete.getData()));
        typOcenyListEdit.setValue(selectedForDelete.getTypOceny());
    }
}

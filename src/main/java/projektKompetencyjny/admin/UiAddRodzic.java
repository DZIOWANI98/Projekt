package projektKompetencyjny.admin;

import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import projektKompetencyjny.Event;
import projektKompetencyjny.Klasa;
import projektKompetencyjny.Nauczyciel;
import projektKompetencyjny.adminStuffff.Singleton;
import projektKompetencyjny.event_uczen.doTabeliEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static projektKompetencyjny.setGlobalNauczyciel.getNauczyciel;

public class UiAddRodzic implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addToDatabase(ActionEvent actionEvent) {
    }

    public void deleteRecordOnAction(ActionEvent actionEvent) {
    }

    public void editRecordOnAction(ActionEvent actionEvent) {
    }
}
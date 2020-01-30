package projektKompetencyjny.admin;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import projektKompetencyjny.*;
import projektKompetencyjny.login.loginController;

import java.net.URL;
import java.util.*;

public class UiAddRodzic implements Initializable {

    private ArrayList<Uczen> uczniowie = new ArrayList<>();

    private ArrayList<Rodzic> rodzice = new ArrayList<>();

    @FXML
    private GridPane layoutDodawanie1;

    @FXML
    private JFXButton addButton;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXComboBox<String> selectUczen;

    @FXML
    private JFXPasswordField hasloLabel;

    @FXML
    private JFXTextField imieLabel;

    @FXML
    private JFXTextField emailLabel;

    @FXML
    private JFXTextField nazwiskoLabel;

    @FXML
    private TableView<doTabeliUiAdminRodzic> table;

    @FXML
    private TableColumn<doTabeliUiAdminRodzic, String> imieColumn;

    @FXML
    private TableColumn<doTabeliUiAdminRodzic, String> nazwiskoColumn;

    @FXML
    private TableColumn<doTabeliUiAdminRodzic, String> idColumn;

    @FXML
    private TableColumn<doTabeliUiAdminRodzic, String> emailColumn;

    @FXML
    private TableColumn<doTabeliUiAdminRodzic, String> uczenColumn;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem editMenuItem;

    private ObservableList<String> selectUczenObservableList = FXCollections.observableArrayList();
    private ObservableList<doTabeliUiAdminRodzic> rodzicObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imieColumn.setCellValueFactory(new PropertyValueFactory<>("Imie"));
        nazwiskoColumn.setCellValueFactory(new PropertyValueFactory<>("Nazwisko"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        uczenColumn.setCellValueFactory(new PropertyValueFactory<>("Uczen"));

        rodzice.addAll(loginController.getRodzics());
        uczniowie.addAll(loginController.getUczeniowie());

        for (Rodzic rodzic : rodzice) {
            try {
                rodzicObservableList.add(new doTabeliUiAdminRodzic(rodzic.getImie(), rodzic.getNazwisko(), rodzic.getId(), rodzic.getEmail(),
                        rodzic.getUczen().get(0).getName() + " " + rodzic.getUczen().get(0).getNazwisko()));
            } catch (IndexOutOfBoundsException exepction) {
                rodzicObservableList.add(new doTabeliUiAdminRodzic(rodzic.getImie(), rodzic.getNazwisko(), rodzic.getId(), rodzic.getEmail(),
                        "Brak informacji"));
            }
        }
        table.setItems(rodzicObservableList);

        for (Uczen uczen : uczniowie) {
            String imieNazw = uczen.getName() + " " + uczen.getNazwisko();
            selectUczenObservableList.add(imieNazw);
        }
        selectUczen.setItems(selectUczenObservableList);


    }

    public void addToDatabase(ActionEvent actionEvent) {
        String imie = imieLabel.getText();
        String nazwisko = nazwiskoLabel.getText();
        String email = emailLabel.getText();
        String haslo = hasloLabel.getText();
        ArrayList<Uczen> pickedUczen = new ArrayList<>();
        String selectedUczen = selectUczen.getSelectionModel().getSelectedItem();
        for (Uczen uczen : uczniowie) {
            if (selectedUczen.equals(uczen.getName() + " " + uczen.getNazwisko())) {
                pickedUczen.add(uczen);
            }
        }

        if (imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || haslo.isEmpty()) {
            errorLabel.setText("Uzupelnij pola!");
        } else {
            errorLabel.setText("");
            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = null;
            tx = session.beginTransaction();
            //Add new Event object
            Rodzic rodzic = new Rodzic();
            rodzic.setImie(imie);
            rodzic.setNazwisko(nazwisko);
            rodzic.setHaslo(haslo);
            rodzic.setEmail(email);
            rodzic.setUczen(pickedUczen);
            session.save(rodzic);
            //Commit the transaction
            tx.commit();
            updateRodzic();
            refreshTable();
        }

    }

    private void refreshTable() {
        rodzicObservableList.clear();
        table.getItems().clear();

        for (Rodzic rodzic : rodzice) {
            try {
                rodzicObservableList.add(new doTabeliUiAdminRodzic(rodzic.getImie(), rodzic.getNazwisko(), rodzic.getId(), rodzic.getEmail(),
                        rodzic.getUczen().get(0).getName() + " " + rodzic.getUczen().get(0).getNazwisko()));
            } catch (IndexOutOfBoundsException exepction) {
                rodzicObservableList.add(new doTabeliUiAdminRodzic(rodzic.getImie(), rodzic.getNazwisko(), rodzic.getId(), rodzic.getEmail(),
                        "Brak informacji"));
            }
        }
        table.setItems(rodzicObservableList);
    }

    private void updateRodzic() {
        Configuration con = new Configuration().configure();
        SessionFactory sessionFactory = con.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        rodzice.clear();
        tx = session.beginTransaction();
        List parents = session.createQuery("FROM Rodzic").list();
        for (Iterator iterator1 = parents.iterator(); iterator1.hasNext(); ) {
            Rodzic parent = (Rodzic) iterator1.next();
            rodzice.add(parent);
        }
        tx.commit();
    }

    public void deleteRecordOnAction(ActionEvent actionEvent) {
    }

    public void editRecordOnAction(ActionEvent actionEvent) {
    }
}
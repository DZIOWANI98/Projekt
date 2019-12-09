package projektKompetencyjny.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import projektKompetencyjny.*;
import projektKompetencyjny.data.dataController;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;


public class loginController implements Initializable {

    @FXML private TextField username_in;
    @FXML private PasswordField password_in;
    @FXML private Button login_button;
    @FXML private Label error_message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        login_button.setOnMouseClicked(event -> {

            String username = username_in.getText();
            String password = password_in.getText();


            ArrayList<Uczen> uczniowe = new ArrayList<>();
            ArrayList<Nauczyciel> nauczyciele = new ArrayList<>();
            ArrayList<Rodzic> rodzice = new ArrayList<>();


            Configuration con = new Configuration().configure();
            SessionFactory sessionFactory = con.buildSessionFactory();
            //Session session = sessionFactory.openSession();
            //Transaction transaction = session.beginTransaction();

            Session session = sessionFactory.openSession();
            Transaction tx = null;


            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Main.class.getClassLoader().getResource("ui_clientRoot.fxml"));
                TabPane root = loader.load();
                clientRootController controller = loader.getController();
                controller.setUserStatus("brak");


                tx = session.beginTransaction();
                List employees = session.createQuery("FROM Uczen").list();
                for (Iterator iterator1 = employees.iterator(); iterator1.hasNext();){
                    Uczen employee = (Uczen) iterator1.next();
                    uczniowe.add(employee);
                    if(employee.getEmail().equals(username) && employee.getHaslo().equals(password)){
                        controller.setUserStatus("uczen");
                        setGlobalUczen.setUczen(employee);
                    }
                }
                tx.commit();

                tx = session.beginTransaction();
                List teachers = session.createQuery("FROM Nauczyciel").list();
                for (Iterator iterator1 = teachers.iterator(); iterator1.hasNext();){
                    Nauczyciel teacher = (Nauczyciel) iterator1.next();
                    nauczyciele.add(teacher);
                    if(teacher.getEmail().equals(username) && teacher.getHaslo().equals(password)){
                        controller.setUserStatus("nauczyciel");
                    }
                }
                tx.commit();


                tx = session.beginTransaction();
                List parents = session.createQuery("FROM Rodzic").list();
                for (Iterator iterator1 = parents.iterator(); iterator1.hasNext();){
                    Rodzic parent = (Rodzic) iterator1.next();
                    rodzice.add(parent);
                    if(parent.getEmail().equals(username) && parent.getHaslo().equals(password)){
                        controller.setUserStatus("rodzic");
                    }
                }
                tx.commit();


                if(controller.getUserStatus().matches("uczen|rodzic|nauczyciel")) {
                    controller.generateUserInterface();
                    Stage stage = new Stage();
                    stage.setTitle("Main app");
                    if (controller.getUserStatus().equals("uczen")) {
                        stage.setTitle("Uczen app");
                    } else if (controller.getUserStatus().equals("rodzic")) {
                        stage.setTitle("Rodzic app");
                    } else if (controller.getUserStatus().equals("nauczyciel")) {
                        stage.setTitle("Nauczyciel app");
                    }
                    stage.setScene(new Scene(root));
                    stage.show();

                    Stage window = (Stage) login_button.getScene().getWindow();
                    window.close();
                } else {
                    error_message.setText("Niepoprawne dane.");
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
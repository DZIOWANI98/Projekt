package projektKompetencyjny.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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

    @FXML
    private TextField username_in;
    @FXML
    private PasswordField password_in;
    @FXML
    private Button login_button;
    @FXML
    private Label error_message;

    static ArrayList<Uczen> uczniow = new ArrayList<>();

    static ArrayList<Rodzic> rodzics = new ArrayList<>();

    static ArrayList<Nauczyciel> nauczyciels = new ArrayList<>();

    public static ArrayList<Uczen> getUczeniowie() {
        return uczniow;
    }

    public static ArrayList<Rodzic> getRodzics() {
        return rodzics;
    }

    public static ArrayList<Nauczyciel> getNauczyciels() {
        return nauczyciels;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        login_button.setOnMouseClicked(event -> {

            String username = username_in.getText();
            String password = password_in.getText();


            ArrayList<Uczen> uczniowe = new ArrayList<>();
            ArrayList<Nauczyciel> nauczyciele = new ArrayList<>();
            ArrayList<Rodzic> rodzice = new ArrayList<>();
            ArrayList<Admin> admin = new ArrayList<>();


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
                    uczniow.add(employee);
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
                    nauczyciels.add(teacher);
                    if(teacher.getEmail().equals(username) && teacher.getHaslo().equals(password)){
                        controller.setUserStatus("nauczyciel");
                        setGlobalNauczyciel.setNauczyciel(teacher);
                    }
                }
                tx.commit();


                tx = session.beginTransaction();
                List parents = session.createQuery("FROM Rodzic").list();
                for (Iterator iterator1 = parents.iterator(); iterator1.hasNext();){
                    Rodzic parent = (Rodzic) iterator1.next();
                    rodzice.add(parent);
                    rodzics.add(parent);
                    if (parent.getEmail().equals(username) && parent.getHaslo().equals(password)) {
                        controller.setUserStatus("rodzic");
                        setGlobalRodzic.setRodzic(parent);
                    }
                }
                tx.commit();


                tx = session.beginTransaction();
                List admini = session.createQuery("FROM Admin").list();
                for (Iterator iterator1 = admini.iterator(); iterator1.hasNext(); ) {
                    Admin admin1 = (Admin) iterator1.next();
                    admin.add(admin1);
                    if (admin1.getEmail().equals(username) && admin1.getHaslo().equals(password)) {
                        controller.setUserStatus("admin");
                        setGlobalAdmin.setAdmin(admin1);
                    }
                }
                tx.commit();


                if (controller.getUserStatus().matches("uczen|rodzic|nauczyciel|admin")) {
                    controller.generateUserInterface();
                    Stage stage = new Stage();
                    if (controller.getUserStatus().equals("uczen")) {
                        stage.setTitle("Panel ucznia");
                    } else if (controller.getUserStatus().equals("rodzic")) {
                        stage.setTitle("Panel rodzica");
                    } else if (controller.getUserStatus().equals("nauczyciel")) {
                        stage.setTitle("Panel nauczyciela");
                    } else if (controller.getUserStatus().equals("admin")) {
                        stage.setTitle("Panel admina");
                    }
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/graduate.png")));
                    stage.setScene(new Scene(root));
                    stage.setOnCloseRequest((WindowEvent eventtClosing) ->
                    {
                        System.out.println("CLOSING");
                        System.exit(0);
                    });
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
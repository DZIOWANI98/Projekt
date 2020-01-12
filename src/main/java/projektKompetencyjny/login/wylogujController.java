package projektKompetencyjny.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import projektKompetencyjny.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;


public class wylogujController implements Initializable {

    @FXML private Button login_button;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        login_button.setOnMouseClicked(event -> {

            Parent root = null;

            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("ui_login.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root, 480, 400));
            stage.setTitle("Dziennik elektroniczny");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/graduate.png")));
            stage.show();

            Stage window = (Stage) login_button.getScene().getWindow();
            window.close();
        });
    }
}
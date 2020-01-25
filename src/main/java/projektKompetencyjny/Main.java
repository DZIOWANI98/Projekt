package projektKompetencyjny;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.beans.EventHandler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ui_login.fxml"));
        primaryStage.setScene(new Scene(root, 480, 400));
        primaryStage.setTitle("Dziennik elektroniczny");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/graduate.png")));
        primaryStage.setOnCloseRequest(event ->
        {
            System.out.println("CLOSING");
            System.exit(0);
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

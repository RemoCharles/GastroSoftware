package slgp.gastrosoftware.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ServiceLeiterApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserLogin.fxml"));
        primaryStage.setTitle("Gastro Software");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.getIcons().add(new Image("restaurant.png"));
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        launch(args);


    }
}

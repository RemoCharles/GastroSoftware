package slgp.gastrosoftware;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BarApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/BarInterface.fxml"));
		primaryStage.setTitle("Gastro Software");
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, 900, 600));
		primaryStage.getIcons().add(new Image("restaurant.png"));
		primaryStage.show();
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}
}

package slgp.gastrosoftware;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class BarApplication extends javafx.application.Application {
	private static Logger logger = (Logger) LogManager.getLogger(BarApplication.class);

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/BarInterface.fxml"));
		primaryStage.setTitle("Gastro Software");
		primaryStage.setResizable(false);
		primaryStage.setScene(new Scene(root, 900, 600));
		primaryStage.show();
	}

	public static void main(String[] args) throws Exception {
		launch(args);
	}
}

package slgp.gastrosoftware.ui.login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import slgp.gastrosoftware.zentrale.persister.domain.Konsumartikel;

public class BarInterfaceControllerTest implements Initializable{

	@FXML
	private Button btBereit;
	
	@FXML
	private TableView<Konsumartikel> tblOffeneBest;
	
	@FXML
	private TableColumn<Konsumartikel, String> colKonsumart;
	
	@FXML
	private TableColumn<Konsumartikel, String> colTisch;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	private void artBereit(ActionEvent event) throws Exception{
		
	}
	
	
	
	@FXML
	private void logout(ActionEvent event) throws Exception {
		System.exit(0);
	}
}

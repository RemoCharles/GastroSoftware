package slgp.gastrosoftware.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import slgp.gastrosoftware.Context;

import java.net.URL;
import java.util.ResourceBundle;

public class LandingController implements Initializable {

    @FXML
    private Label lblError;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void connectRMI(ActionEvent event) throws Exception {

        boolean success = false;
        String message = null;

        try {
            Context.getInstance().getPersonService();
            Context.getInstance().getBestellService();
            Context.getInstance().getKonsumartikelService();
            Context.getInstance().getRechnungService();
            Context.getInstance().getMenuService();
            success = true;
        } catch (Exception ex) {
            message = ex.getMessage();
            success = false;
            lblError.setText(message);
        }

        if (success) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/KuecheInterface.fxml"));
            Parent kueche_interface_parent = loader.load();

            Scene kueche_interface_scene = new Scene(kueche_interface_parent);
            Stage tisch_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            tisch_stage.setScene(kueche_interface_scene);
            tisch_stage.show();
        }
    }
}

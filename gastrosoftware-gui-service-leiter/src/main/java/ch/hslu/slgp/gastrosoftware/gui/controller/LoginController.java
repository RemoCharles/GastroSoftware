package ch.hslu.slgp.gastrosoftware.gui.controller;

import ch.hslu.slgp.gastrosoftware.model.Mitarbeiter;
import ch.hslu.slgp.gastrosoftware.rmi.api.RMIPersonService;
import ch.hslu.slgp.gastrosoftware.rmi.context.Context;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    Button genLogi;

    @FXML
    private TextField genBenu;

    @FXML
    private TextField genPass;

    @FXML
    private Label genPwEr;

    private static RMIPersonService personService = Context.getInstance().getPersonService();
    private static Logger logger = (Logger) LogManager.getLogger(LoginController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genLogi.defaultButtonProperty().bind(genLogi.focusedProperty());
    }

    @FXML
    private void loginaction(ActionEvent event) throws Exception {

        if (personService.pruefeLogin(genBenu.getText(), genPass.getText())) {

            if ("Kuechenpersonal".equals(personService.getFunktionPerson(genBenu.getText(), genPass.getText()))) {
                Parent kueche_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/KuecheInterface.fxml"));
                Scene kueche_interface_scene = new Scene(kueche_interface_parent);
                Stage kueche_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                kueche_stage.setScene(kueche_interface_scene);
                kueche_stage.show();

            } else if ("Servicepersonal".equals(personService.getFunktionPerson(genBenu.getText(), genPass.getText()))) {
                Parent ma_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/MaInterface.fxml"));
                Scene ma_interface_scene = new Scene(ma_interface_parent);
                Stage ma_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                Mitarbeiter mitarbeiter = personService.findMitarbeiterByUsername(genBenu.getText());

                ContextMitarbeiter.getInstance().setMitarbeiter(mitarbeiter);

                ma_stage.setScene(ma_interface_scene);
                ma_stage.show();

            } else if ("Barpersonal".equals(personService.getFunktionPerson(genBenu.getText(), genPass.getText()))) {
                Parent bar_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/BarInterface.fxml"));
                Scene bar_interface_scene = new Scene(bar_interface_parent);
                Stage bar_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                bar_stage.setScene(bar_interface_scene);
                bar_stage.show();

            } else if ("Leiter".equals(personService.getFunktionPerson(genBenu.getText(), genPass.getText()))) {
                Parent leiter_interface_parent = FXMLLoader.load(getClass().getResource("/fxml/LeiterInterface.fxml"));
                Scene leiter_interface_scene = new Scene(leiter_interface_parent);
                Stage leiter_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                leiter_stage.setScene(leiter_interface_scene);
                leiter_stage.show();

            } else {
                genPwEr.setText("Bitte wenden Sie sich an den Administrator...");
            }
        } else {
            genPwEr.setText("Benutzername oder Passwort falsch..");
        }
    }


    @FXML
    private void exitaction(ActionEvent event) throws IOException {

        System.exit(0);
    }

}

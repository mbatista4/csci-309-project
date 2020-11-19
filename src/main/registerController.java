package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.utils.*;
import main.utils.Password;
import java.io.IOException;
import java.net.URL;

public class registerController extends Controller{

    @FXML private TextField passcodeTextField;
    @FXML private TextField userNameTextField;
    @FXML private PasswordField passwordField;


    public void register(ActionEvent event) {

        String PASSCODE = "99203";
        if(PASSCODE.compareTo(passcodeTextField.getText()) == 0) {
            String usernameText = userNameTextField.getText();

            if(!usernameText.isEmpty()){
                if(!FileReader.checkUsername(usernameText)) {
                    String passwordText = passwordField.getText();
                    if(!passwordText.isEmpty()){
                        boolean didAdd = FileReader.addUser(usernameText, Password.encryptPassword(passwordText));
                        if(didAdd) {
                            createAlertWindow("A new Manager was added to Database", Alert.AlertType.INFORMATION);
                            goToEdit(event);
                        } else {
                            createAlertWindow("Was not able to Add Manager", Alert.AlertType.ERROR);
                        }
                    } else {
                        createAlertWindow("password is Empty!", Alert.AlertType.WARNING);
                    }
                } else {
                    createAlertWindow("username already exist", Alert.AlertType.WARNING);
                }
            } else {
                createAlertWindow("username is Empty!", Alert.AlertType.WARNING);
            }
        } else {
            createAlertWindow("Passcode was Incorrect", Alert.AlertType.WARNING);
            passcodeTextField.clear();
        }
    }

    public void goToLogin(ActionEvent event) {
        try {
            URL loginURL = getClass().getResource("LoginScreen.fxml");
            Parent loginParent = FXMLLoader.load(loginURL);
            SwitchScene.switchScene(event,loginParent);

        } catch (IOException e) {
            System.out.println("Error loading resource");
        }
    }

    private void goToEdit(ActionEvent event) {

        try{
            URL editURL = getClass().getResource("editScene.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(editURL);
            Parent editParent = loader.load();
            Scene editScene = new Scene(editParent);
            EditController controller = loader.getController();

            controller.initJetGreenTable();
            controller.initBravoTable();
            controller.initDividedTable();

            SwitchScene.switchScene(event,editScene);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Resource!");
        }
    }

}

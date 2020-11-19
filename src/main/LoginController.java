package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.utils.FileReader;
import main.utils.Password;
import main.utils.SwitchScene;

import java.io.IOException;
import java.net.URL;


public class LoginController extends Controller{

    @FXML private TextField userNameTextField;
    @FXML private PasswordField passwordField;

    public void login(ActionEvent event) {

        String usernameText = userNameTextField.getText().trim();
        String passwordText = passwordField.getText().trim();

        if(!usernameText.isEmpty()){
            String password = FileReader.getUserPassword(usernameText);

            if( !password.isEmpty()){

                boolean isCorrect =  Password.comparePasswords(passwordText,password);

                System.out.println(isCorrect);

                if(isCorrect) {
                    goToEdit(event);
                } else {
                    createAlertWindow("Password is incorrect.", Alert.AlertType.WARNING);
                    passwordField.clear();
                }

            } else {
                createAlertWindow("username was not found", Alert.AlertType.WARNING);
            }
        } else {
            createAlertWindow("Please Enter A username.", Alert.AlertType.WARNING);
        }
    }

    public void goToRegister(ActionEvent event){

        try{
            URL registerURL = getClass().getResource("registerScene.fxml");
            Parent registerParent = FXMLLoader.load(registerURL);
            SwitchScene.switchScene(event,registerParent);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Resource!");
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

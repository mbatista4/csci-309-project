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

/*
 * TODO write description
 */
public class LoginController extends Controller{

    @FXML private TextField userNameTextField;
    @FXML private PasswordField passwordField;

    /*
     * Method used to check information entered by user
     * And switches windows to the Edit window
     */
    public void login(ActionEvent event) {

        String usernameText = userNameTextField.getText().trim();
        String passwordText = passwordField.getText().trim();

        try {
        if(!usernameText.isEmpty()){
            boolean isAdmin = checkAdmin(usernameText,passwordText);

            if(isAdmin){
                goToEdit(event);
            } else {
                String password = FileReader.getUserPassword(usernameText);

                if( !password.isEmpty()){

                    boolean isCorrect =  Password.comparePasswords(passwordText,password);

                    if(isCorrect) {
                        goToEdit(event);
                    } else {
                        createAlertWindow("Password is incorrect.", Alert.AlertType.WARNING);
                        passwordField.clear();
                    }
                } else {
                    createAlertWindow("username was not found", Alert.AlertType.WARNING);
                }
            }

        } else {
            createAlertWindow("Please Enter A username.", Alert.AlertType.WARNING);
        }
        } catch (Exception e) {
            createAlertWindow("An Error Occurred", Alert.AlertType.ERROR);
            System.exit(1);
        }

        createAlertWindow("you can put:\n admin \nfor the username and password", Alert.AlertType.INFORMATION);

    }


    /*
     * method used to check if the user is the main Admin
     */
    private boolean checkAdmin(String username, String password) {
        return (username.compareToIgnoreCase("admin") == 0 && password.compareToIgnoreCase("admin") == 0);
    }



    /*
     * Method used to Initialized the Edit window
     */
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
            createAlertWindow("Error loading resource", Alert.AlertType.ERROR);
            System.exit(1);
        }
    }
}

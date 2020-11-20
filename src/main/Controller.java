package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import main.utils.SwitchScene;

/*
 * Base Controller class used in all other controllers to provide basic support
 */
public class Controller {

    /*
     * TODO write description
     */
    public void switchToMain(ActionEvent event){

        try {
            Parent loginScreenParent = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            SwitchScene.switchScene(event,loginScreenParent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * TODO write description
     */
    public void createAlertWindow(String message, Alert.AlertType type) {
        Alert alertWindow = new Alert(type);
        alertWindow.setContentText(message);
        alertWindow.setTitle("FLY");
        alertWindow.showAndWait();
    }
}

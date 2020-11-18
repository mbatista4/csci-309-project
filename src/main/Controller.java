package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import main.utils.SwitchScene;

public class Controller {

    public void switchToMain(ActionEvent event){

        try {
            Parent loginScreenParent = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
            SwitchScene.switchScene(event,loginScreenParent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createAlertWindow(String message, Alert.AlertType type) {
        Alert alertWindow = new Alert(type);
        alertWindow.setContentText(message);
        alertWindow.showAndWait();
    }
}

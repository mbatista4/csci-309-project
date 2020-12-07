package main.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import main.AirLineController;
import main.Controller;

import java.net.URL;

/*
 * this class provides support for switching between windows
 */
public class SwitchScene {

    /*
     * Method Allows the ability to switch to any Scene
     */
     public static void switchScene(ActionEvent event, Parent parent){
            Scene newScreenScene = new Scene(parent);
            switchScene(event,newScreenScene);
     }

    /*
     * Method Allows the ability to switch to any Scene
     */
    public static void switchScene(ActionEvent event, Scene scene){
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /*
     * Method allows the ability to go to the flights Scene/Window
     */
    public static void changeScreenToFlights(String fileName,ActionEvent event, String airlineName, URL sceneLocation)  {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(sceneLocation);
        try {
            Parent airlineScreenParent = loader.load();
            Scene airlineScene = new Scene(airlineScreenParent);

            AirLineController controller = loader.getController();
            controller.initFile(fileName, airlineName);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(airlineScene);
            window.show();
        } catch (Exception e) {
            Controller.createAlertWindow("Error occurred while loading a resource", Alert.AlertType.ERROR);
            System.exit(1);
        }
    }

}

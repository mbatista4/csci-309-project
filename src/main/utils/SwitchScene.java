package main.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.AirLineController;

import java.net.URL;

/*
 * this class provides support for switching between windows
 */
public class SwitchScene {

    /*
     * TODO write method description
     */
     public static void switchScene(ActionEvent event, Parent parent){
            Scene newScreenScene = new Scene(parent);
            switchScene(event,newScreenScene);
     }

    /*
     * TODO write method description
     */
    public static void switchScene(ActionEvent event, Scene scene){
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    /*
     * TODO write method description
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
           // e.printStackTrace();
            System.out.println("Error here");
        }
    }

}

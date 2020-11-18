package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import main.utils.SwitchScene;

import java.net.URL;

public class MainScreenController {

    public void loadJetGreen(ActionEvent event) {
            loadFLights("jetGreen.txt",event, "JetGreen");
    }

    public void loadUSAirlines(ActionEvent event) {
            loadFLights("usAirlines.txt",event,"US Airlines");
    }

    public void loadBravoAirlines(ActionEvent event) {
            loadFLights("bravoAirlines.txt",event,"Bravo Airlines");
    }

    private void loadFLights(String fileName,ActionEvent event, String flightName) {
        try {
            URL sceneLocation = getClass().getResource("AirlineScene.fxml");

            SwitchScene.changeScreenToFlights(fileName,event, flightName,sceneLocation);
        }catch (Exception e) {
            System.out.println("Here");
          e.printStackTrace();
        }
    }

    public void switchToLogin(ActionEvent event) {

        try {
            URL sceneLocation = getClass().getResource("LoginScreen.fxml");
            Parent loginScreenParent = FXMLLoader.load(sceneLocation);
            SwitchScene.switchScene(event, loginScreenParent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

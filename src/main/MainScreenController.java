package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.utils.SwitchScene;

import java.net.URL;

public class MainScreenController {

    private boolean isLoading = false;


    public void loadJetGreen(ActionEvent event) {
            loadFLights("jetGreen.txt",event, "JetGreen");
    }

    public void loadUSAirlines(ActionEvent event) {
            loadFLights("usAirlines.txt",event,"US Airlines");
    }

    public void loadBravoAirlines(ActionEvent event) {
            loadFLights("bravoAirlines.txt",event,"Bravo Airlines");
    }

    private void loadFLights(String file,ActionEvent event, String flightName) {
        try {
            URL sceneLocation = getClass().getResource("../scenes/AirlineScene.fxml");
            System.out.println(sceneLocation);
            SwitchScene.changeScreenToFlights(file,event, flightName,sceneLocation);
        }catch (Exception e) {
            System.out.println("Here");
          e.printStackTrace();
        }
    }

    public void switchToLogin(ActionEvent event) {

        try {
            URL sceneLocation = getClass().getResource("../scenes/LoginScreen.fxml");
            Parent loginScreenParent = FXMLLoader.load(sceneLocation);
            SwitchScene.switchScene(event, loginScreenParent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

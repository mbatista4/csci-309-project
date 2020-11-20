package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import main.utils.SwitchScene;

import java.net.URL;

/*
 * TODO write description
 */
public class MainScreenController {

    /*
     * TODO write description
     */
    public void loadJetGreen(ActionEvent event) {
            loadFLights("jetGreen.txt",event, "JetGreen");
    }

    /*
     * TODO write description
     */
    public void loadUSAirlines(ActionEvent event) {
            loadFLights("usAirlines.txt",event,"US Airlines");
    }

    /*
     * TODO write description
     */
    public void loadBravoAirlines(ActionEvent event) {
            loadFLights("bravoAirlines.txt",event,"Bravo Airlines");
    }

    /*
     * TODO write description
     */
    private void loadFLights(String fileName,ActionEvent event, String flightName) {
        try {
            URL sceneLocation = getClass().getResource("AirlineScene.fxml");

            SwitchScene.changeScreenToFlights(fileName,event, flightName,sceneLocation);
        }catch (Exception e) {
            System.out.println("Here");
          e.printStackTrace();
        }
    }

    /*
     * TODO write description
     */
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

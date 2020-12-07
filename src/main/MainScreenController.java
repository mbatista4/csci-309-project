package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import main.utils.*;

import java.net.URL;

/*
 * This class provides all of the functionality needed
 * for the Main Screen window
 */
public class MainScreenController extends Controller{

    /*
     * Method used to load JetGreen lights
     */
    public void loadJetGreen(ActionEvent event) { loadFLights(FileReader.JET_GREEN,event, "JetGreen"); }

    /*
     * Method used to load Divided flights
     */
    public void loadDividedAirlines(ActionEvent event) { loadFLights(FileReader.DIVIDED_AIR,event,"Divided Airlines"); }

    /*
     * Method used to load Bravo flights
     */
    public void loadBravoAirlines(ActionEvent event) { loadFLights(FileReader.BRAVO_AIR,event,"Bravo Airlines"); }

    /*
     * Method used to load Airline Scene
     */
    private void loadFLights(String fileName,ActionEvent event, String flightName) {
        try {
            URL sceneLocation = getClass().getResource("AirlineScene.fxml");

            SwitchScene.changeScreenToFlights(fileName,event, flightName,sceneLocation);
        }catch (Exception e) {

          e.printStackTrace();
        }
    }

    /*
     * Method used to load Login window
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
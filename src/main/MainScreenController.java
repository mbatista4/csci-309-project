package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import main.utils.*;

import java.net.URL;

/*
 * TODO write description
 */
public class MainScreenController extends Controller{

    /*
     * TODO write description
     */
    public void loadJetGreen(ActionEvent event) { loadFLights(FileReader.JET_GREEN,event, "JetGreen"); }

    /*
     * TODO write description
     */
    public void loadDividedAirlines(ActionEvent event) { loadFLights(FileReader.DIVIDED_AIR,event,"Divided Airlines"); }

    /*
     * TODO write description
     */
    public void loadBravoAirlines(ActionEvent event) { loadFLights(FileReader.BRAVO_AIR,event,"Bravo Airlines"); }

    /*
     * TODO write description
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

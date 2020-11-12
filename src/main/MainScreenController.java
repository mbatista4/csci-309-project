package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenController {

    private boolean isLoading = false;


    public void loadJetGreen(ActionEvent event) {
            loadFLights("jetGreen.txt",event);
    }

    public void loadUSAirlines(ActionEvent event) {
            loadFLights("usAirlines.txt",event);
    }

    public void loadBravoAirlines(ActionEvent event) {
            loadFLights("bravoAirlines.txt",event);
    }

    private void loadFLights(String file,ActionEvent event) {
        try {
            changeScreenToFlights(file,event);
        }catch (Exception e) {
          e.printStackTrace();
        }
    }

    public void switchToLogin(ActionEvent event){

        try {
            Parent loginScreenParent = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));

            SwitchScene.switchScene(event,loginScreenParent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeScreenToFlights(String fileName,ActionEvent event) throws IOException {

       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource("AirLineScene.fxml"));
       Parent airlineScreenParent = loader.load();

        Scene airlineScene = new Scene(airlineScreenParent);

        AirLineController controller = loader.getController();
        controller.initFile(fileName);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(airlineScene);
        window.show();

    }
}

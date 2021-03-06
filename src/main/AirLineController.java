package main;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.utils.*;

import java.io.IOException;
import java.net.URL;

/*
 * This class Handles all of the functionality of the Airline Window
 */
public class AirLineController extends Controller{

    private String flightTxtFile;
    private ObservableList<Flight> flightList;

    @FXML private Label titleLabel;
    @FXML TableView<Flight> tableView;
    @FXML private TableColumn<Flight,String> flightNameColumn;
    @FXML private TableColumn<Flight,String> destinationColumn;
    @FXML private TableColumn<Flight,String> statusColumn;
    @FXML private TableColumn<Flight,Integer> seatsAvailableColumn;
    @FXML private TableColumn<Flight,Double> priceColumn;

    /*
     * This method accepts a file to initialize the path for the data
     * @param the file with all of the flights
     */
    public void initFile(String fileName, String flightName){
        flightTxtFile = fileName;
        flightNameColumn.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("flightDestination"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("flightStatus"));
        seatsAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("seatsAvailable"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerSeat"));

        // Load data
        tableView.setItems(getFlights());


        // Setting title of the window
        titleLabel.setText(flightName);
    }

    /*
     * Returns a List of flights
     */
    private ObservableList<Flight> getFlights() {
        try {
            flightList = FileReader.getAllFlights(flightTxtFile);
        } catch (Exception e) {
            createAlertWindow("Error Getting Flights", Alert.AlertType.ERROR);
            System.exit(1);
        }
        return  flightList;
    }

    /*
     * Method get information and switches to the Buy Window
     */
    public void buyFlight(ActionEvent event) {

        Flight selectedFlight = tableView.getSelectionModel().getSelectedItem();

        if(selectedFlight != null){
            if(selectedFlight.getFlightStatus().compareToIgnoreCase("FULL") == 0 || selectedFlight.getSeatsAvailable() <= 0){
                createAlertWindow("Flight is full, Select another flight", Alert.AlertType.WARNING);
                return;
            }

            URL checkoutLocation = getClass().getResource("CheckoutScene.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(checkoutLocation);

            try {
                Parent checkoutParent = loader.load();
                CheckoutController controller = loader.getController();
                controller.initData(selectedFlight,flightTxtFile,titleLabel.getText(),flightList);
                SwitchScene.switchScene(event,checkoutParent);

            } catch (IOException e) {
                createAlertWindow("An error occurred while reading files", Alert.AlertType.ERROR);
                System.exit(1);
            }
        } else {
            // Alert User to select a flight
            createAlertWindow("No Flight was selected", Alert.AlertType.WARNING);
        }
    }
}
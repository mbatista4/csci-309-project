package main.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.*;
import main.utils.*;

public class AirLineController extends Controller{

    @FXML private Label titleLabel;
    @FXML TableView<Flight> tableView;
    @FXML private TableColumn<Flight,String> flightNameColumn;
    @FXML private TableColumn<Flight,String> destinationColumn;
    @FXML private TableColumn<Flight,String> statusColumn;
    @FXML private TableColumn<Flight,Integer> seatsAvailableColumn;
    @FXML private TableColumn<Flight,Double> priceColumn;

    private String flightTxtFile;
    private Flight SelectedFlight;

    /*
     * This method accepts a file to initialize the path for the data
     * @param the file with all of the flights
     */
    public void initFile(String fileName, String flightName){
        flightTxtFile = fileName;
        flightNameColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightName"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightDestination"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightStatus"));
        seatsAvailableColumn.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("seatsAvailable"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flight,Double>("pricePerSeat"));

        // Load data
        tableView.setItems(getFlights());

        // Setting title of the window
        titleLabel.setText(flightName);
    }

    private ObservableList<Flight> getFlights() {
        ObservableList<Flight> flights = null;
        try {
            flights = FileReader.getAllFlights(flightTxtFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  flights;
    }

    public void buyFlight(ActionEvent event) {

        Flight selectedFLight = tableView.getSelectionModel().getSelectedItem();

        if(selectedFLight != null){
            System.out.println(selectedFLight);
            // send info to other controller

        } else {

            // Alert User to select a flight
            System.out.println("No Flight was selected");
        }
    }
}
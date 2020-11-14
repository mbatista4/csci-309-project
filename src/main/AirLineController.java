package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AirLineController{

    @FXML TableView<Flight> tableView;
    @FXML private TableColumn<Flight,String> flightNameColumn;
    @FXML private TableColumn<Flight,String> destinationColumn;
    @FXML private TableColumn<Flight,String> statusColumn;
    @FXML private TableColumn<Flight,Integer> seatsAvailableColumn;
    @FXML private TableColumn<Flight,Double> priceColumn;

    public String flightTxtFile;


    /*
     * This method accepts a file to initialize the path for the data
     * @param the file with all of the flights
     */
    public void initFile(String fileName){
        flightTxtFile = fileName;
        System.out.println(fileName);
        flightNameColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightName"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightDestination"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Flight,String>("flightStatus"));
        seatsAvailableColumn.setCellValueFactory(new PropertyValueFactory<Flight,Integer>("seatsAvailable"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Flight,Double>("pricePerSeat"));

        // Load data
        tableView.setItems(getFlights());
    }


    private ObservableList<Flight> getFlights() {
        ObservableList<Flight> flights = null;
        try {
            flights = FileReader.getAllFlights(flightTxtFile); //FXCollections.observableArrayList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  flights;
    }




}

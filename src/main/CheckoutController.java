package main;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.utils.FileReader;
import main.utils.SwitchScene;

import java.io.IOException;
import java.net.URL;

public class CheckoutController extends Controller {

    private Flight selectedFlight;
    private String prevTxt;
    private String prevTitle;
    private ObservableList<Flight> flightList;


    @FXML private Label flightNameLabel;
    @FXML private Label destinationLabel;
    @FXML private Label priceLabel;
    @FXML private Label availableSeatsLabel;
    @FXML private TextField seatsTextField;
    @FXML private TextField totalTextField;



    /*
     *
     */
    public void initData(Flight flight, String prevTxt, String prevTitle, ObservableList<Flight> flightList) {

        this.selectedFlight = flight;
        flightNameLabel.textProperty().setValue(this.selectedFlight.getFlightName());
        destinationLabel.textProperty().setValue(this.selectedFlight.getFlightDestination());
        priceLabel.textProperty().setValue(Double.toString(this.selectedFlight.getPricePerSeat()));
        availableSeatsLabel.textProperty().setValue(Integer.toString(this.selectedFlight.getSeatsAvailable()));
        seatsTextField.textProperty().setValue("1");
        totalTextField.textProperty().setValue("$" + this.selectedFlight.getPricePerSeat());
        this.prevTitle = prevTitle;
        this.prevTxt = prevTxt;
        this.flightList = flightList;

        seatsTextField.textProperty().addListener((observableValue, s, t1) -> {

            try {
                if(!t1.isEmpty()){
                    int seatNumbers = Integer.parseInt(t1);

                    if(seatNumbers >= selectedFlight.getSeatsAvailable()){
                        seatNumbers = selectedFlight.getSeatsAvailable();
                        seatsTextField.textProperty().setValue(Integer.toString(seatNumbers));
                    }

                    if(seatNumbers >= 0) {
                        int totalPrice = seatNumbers * selectedFlight.getPricePerSeat();
                        totalTextField.textProperty().setValue("$" + (double)totalPrice);
                    } else {
                        seatsTextField.textProperty().setValue("0");
                    }
                }

            } catch (NumberFormatException e){
                createAlertWindow("Please enter a valid number", Alert.AlertType.ERROR);
                seatsTextField.textProperty().setValue("1");
            }

        });

    }

    public void goBack(ActionEvent event) {
        URL backUrl = getClass().getResource("AirlineScene.fxml");
        SwitchScene.changeScreenToFlights(prevTxt,event,prevTitle, backUrl);
    }

    public void purchaseFlight(ActionEvent event) {

        System.out.println(flightList.remove(selectedFlight));

        int updatedSeatCount = selectedFlight.getSeatsAvailable() - Integer.parseInt(seatsTextField.getText());

        if(updatedSeatCount == 0) {
            selectedFlight.setFlightStatus("FULL");
        }

        selectedFlight.setSeatsAvailable(updatedSeatCount);
        flightList.add(selectedFlight);
        System.out.println(selectedFlight);
        switchToMain(event);
        try {
            FileReader.updateSeatTotal(prevTxt,flightList);
        } catch (IOException e) {
            createAlertWindow("Error checking out", Alert.AlertType.ERROR);
            flightList.remove(selectedFlight);

            updatedSeatCount = selectedFlight.getSeatsAvailable() + Integer.parseInt(seatsTextField.getText());

            if(updatedSeatCount > 0) {
                selectedFlight.setFlightStatus("AVAILABLE");
            }

            selectedFlight.setPricePerSeat(updatedSeatCount);
            flightList.add(selectedFlight);
        }

    }

}

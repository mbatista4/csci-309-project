package main;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.utils.FileReader;
import main.utils.SwitchScene;

import java.net.URL;

/*
 * This class provides all of the functionality needed
 * for the Checkout view
 */
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
     * Helper Method Initializes all of the data necessary to purchase a flight
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

    /*
     * returns to the previous window
     */
    public void goBack(ActionEvent event) {
        URL backUrl = getClass().getResource("AirlineScene.fxml");
        SwitchScene.changeScreenToFlights(prevTxt,event,prevTitle, backUrl);
    }

    /*
     * Method updates the data from the selected flight.
     */
    public void purchaseFlight(ActionEvent event) {

        if(flightList.remove(selectedFlight)){
            int updatedSeatCount = selectedFlight.getSeatsAvailable() - Integer.parseInt(seatsTextField.getText());

            if(updatedSeatCount == 0) {
                selectedFlight.setFlightStatus("FULL");
            }

            selectedFlight.setSeatsAvailable(updatedSeatCount);
            flightList.add(selectedFlight);

            try {
                FileReader.updateSeatTotal(prevTxt,flightList);

                String data = buildReceipt();

                FileReader.createReceipt(data);
                createAlertWindow( data, Alert.AlertType.INFORMATION);
                switchToMain(event);
            } catch (Exception e) {
                e.printStackTrace();
              createAlertWindow("Error occurred while buying flight", Alert.AlertType.ERROR);
              System.exit(1);
            }
        } else {
            createAlertWindow("Error buying flight", Alert.AlertType.ERROR);
        }
    }

    /*
     * Method build the data required for the user's receipt
     * @param out is fully build output
     */
    private String buildReceipt() {

        return "Flight Name: " + selectedFlight.getFlightName() + "\n" +
                "Flight destination: " + selectedFlight.getFlightDestination() + "\n" +
                "Seats purchased: " + seatsTextField.getText() + "\n" +
                "Total cost: " + totalTextField.getText() + "\n" +
                "Thank you for using F.E.M Airport!";
    }

}

package main;

import javafx.beans.property.SimpleStringProperty;

public class Flight {

    private SimpleStringProperty flightName;
    private SimpleStringProperty flightDestination;
    private SimpleStringProperty flightStatus;
    private int seatsAvailable;
    private double pricePerSeat;


    public Flight(String flightName, String flightDestination, String flightStatus, int seatsAvailable,double pricePerSeat){
        this.flightDestination = new SimpleStringProperty(flightDestination);
        this.flightName = new SimpleStringProperty(flightName);
        this.flightStatus = new SimpleStringProperty(flightStatus);
        this.seatsAvailable = seatsAvailable;
        this.pricePerSeat = pricePerSeat;
    }

    public String getFlightName() {
        return flightName.get();
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public String getFlightDestination() {
        return flightDestination.get();
    }

    public String getFlightStatus() {
        return flightStatus.get();
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public void setFlightDestination(SimpleStringProperty flightDestination) {
        this.flightDestination = flightDestination;
    }

    public void setFlightName(SimpleStringProperty flightName) {
        this.flightName = flightName;
    }

    public void setFlightStatus(SimpleStringProperty flightStatus) {
        this.flightStatus = flightStatus;
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public void setPricePerSeat(double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }
}

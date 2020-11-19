package main;

import javafx.beans.property.SimpleStringProperty;

public class Flight {

    private final SimpleStringProperty flightName;
    private final SimpleStringProperty flightDestination;
    private SimpleStringProperty flightStatus;
    private int seatsAvailable;
    private int pricePerSeat;


    public Flight(String flightName, String flightDestination, String flightStatus, int seatsAvailable,int pricePerSeat){
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

    public int getPricePerSeat() {
        return pricePerSeat;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = new SimpleStringProperty(flightStatus);
    }

    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public void setPricePerSeat(int pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    @Override
    public String toString() {


        return "Flight Name: " + this.getFlightName() + "\n" +
                "Flight Destination: " + this.getFlightDestination() + "\n" +
                "Seats available" + this.getSeatsAvailable() + "\n" +
                "Flight Status: " + this.getFlightStatus() + "\n" +
                "Price per Seat: " + this.getPricePerSeat() + "\n";
    }

}

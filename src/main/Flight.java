package main;

import javafx.beans.property.SimpleStringProperty;

public class Flight {

    private SimpleStringProperty flightName;
    private SimpleStringProperty flightDestination;
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

    public void setPricePerSeat(int pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append("Flight Name: " + this.getFlightName() + "\n");
        out.append("Flight Destination: " + this.getFlightDestination() + "\n");
        out.append("Seats available" + this.getSeatsAvailable() + "\n");
        out.append("Flight Status: "+this.getFlightStatus() + "\n");
        out.append("Price per Seat: " + this.getPricePerSeat() + "\n");


        return out.toString();
    }

}

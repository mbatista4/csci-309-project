package main;

import javafx.beans.property.SimpleStringProperty;

/*
 * TODO write description
 */
public class Flight {

    private final SimpleStringProperty flightName;
    private final SimpleStringProperty flightDestination;
    private SimpleStringProperty flightStatus;
    private int seatsAvailable;
    private int pricePerSeat;


    /*
     * TODO write description
     */
    public Flight(String flightName, String flightDestination, String flightStatus, int seatsAvailable, int pricePerSeat) {
        this.flightDestination = new SimpleStringProperty(flightDestination);
        this.flightName = new SimpleStringProperty(flightName);
        this.flightStatus = new SimpleStringProperty(flightStatus);
        this.seatsAvailable = seatsAvailable;
        this.pricePerSeat = pricePerSeat;
    }

    /*
     * TODO write description
     */
    public String getFlightName() {
        return flightName.get();
    }

    /*
     * TODO write description
     */
    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    /*
     * TODO write description
     */
    public String getFlightDestination() {
        return flightDestination.get();
    }

    /*
     * TODO write description
     */
    public String getFlightStatus() {
        return flightStatus.get();
    }

    /*
     * TODO write description
     */
    public int getPricePerSeat() {
        return pricePerSeat;
    }

    /*
     * TODO write description
     */
    public void setFlightStatus(String flightStatus) {
        this.flightStatus = new SimpleStringProperty(flightStatus);
    }

    /*
     * TODO write description
     */
    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    /*
     * TODO write description
     */
    public void setPricePerSeat(int pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    /*
     * TODO write description
     */
    @Override
    public String toString() {
        return "Flight Name: " + this.getFlightName() + "\n" +
                "Flight Destination: " + this.getFlightDestination() + "\n" +
                "Seats available" + this.getSeatsAvailable() + "\n" +
                "Flight Status: " + this.getFlightStatus() + "\n" +
                "Price per Seat: " + this.getPricePerSeat() + "\n";
    }
}
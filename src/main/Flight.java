package main;

import javafx.beans.property.SimpleStringProperty;

/*
 * This class provides all of the information needed
 * for flights
 */
public class Flight {

    private final String flightName;
    private final String flightDestination;
    private String flightStatus;
    private int seatsAvailable;
    private final int pricePerSeat;

    public Flight(String flightName, String flightDestination, String flightStatus, int seatsAvailable, int pricePerSeat) {
        this.flightDestination =  (flightDestination);
        this.flightName =  (flightName);
        this.flightStatus =  (flightStatus);
        this.seatsAvailable = seatsAvailable;
        this.pricePerSeat = pricePerSeat;
    }

    /*
     * Method returns the name of the flight
     */
    public String getFlightName() {
        return flightName;
    }

    /*
     * Method returns the number of available seats
     */
    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    /*
     * This method returns the Name of the flight destination
     */
    public String getFlightDestination() {
        return flightDestination;
    }

    /*
     * This method returns the status of the flight
     */
    public String getFlightStatus() {
        return flightStatus;
    }

    /*
     * This method returns the price of each seat
     */
    public int getPricePerSeat() {
        return pricePerSeat;
    }

    /*
     * This method modifies the flight status
     */
    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    /*
     * This method modifies the number of seats available
     */
    public void setSeatsAvailable(int seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    /*
     * This method returns a string representation of the flight information
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
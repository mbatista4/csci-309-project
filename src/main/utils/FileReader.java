package main.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import main.Controller;
import main.Flight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * This class handles all of the IO file reading and writing
 */
public class FileReader {

    //Names of the Files used in the program
    private static final String userInfo = "users.dat";
    public static final String JET_GREEN = "jetGreen.dat";
    public static final String DIVIDED_AIR = "dAirlines.dat";
    public static final String BRAVO_AIR = "bAirlines.dat";

    /*
     * Returns a List of all the flight from a given fileName
     */
    public static ObservableList<Flight> getAllFlights(String fileName) throws IOException {
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        File textFile = ensureFile(fileName);
        try{
            Scanner reader = new Scanner(textFile);

            // if there is no file, dummy data is loaded
            if(!reader.hasNext()){

                flights.add(new Flight("FL 221","New York","AVAILABLE",100,200) );
                flights.add(new Flight("AZ 141","Los Angeles","AVAILABLE",100,321) );
                flights.add(new Flight("LS 315","Nevada","AVAILABLE",100,235) );
                flights.add(new Flight("FL 078","Seattle","AVAILABLE",100,123) );
                flights.add(new Flight("EZ 420","Providence","AVAILABLE",100,113) );


            } else {

                while(reader.hasNextLine()){
                    //
                    String flightName = reader.nextLine();
                    String destination = reader.nextLine();
                    String flightStatus = reader.nextLine();
                    String seatsAvailable = reader.nextLine();
                    String pricePerSeat = reader.nextLine();

                    flights.add(new Flight(flightName,destination,flightStatus,Integer.parseInt(seatsAvailable),Integer.parseInt(pricePerSeat)));
                }
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return flights;
    }

    /*
     * method Returns a UserPassword from given user
     */
    public static String getUserPassword(String username) {
        String password = "";

        try {
            //Ensuring that the file exist to prevent error
            File userFile = ensureFile(userInfo);

            Scanner scanner = new Scanner(userFile);
            while(scanner.hasNext()){
                String tempUsername = scanner.nextLine();
                if(tempUsername.compareToIgnoreCase(username) == 0){
                    password = scanner.nextLine();
                    break;
                } else {
                    scanner.nextLine();
                }
            }
        } catch ( IOException e) {
            Controller.createAlertWindow("Error occurred while loading a resource", Alert.AlertType.ERROR);
            System.exit(1);
        }
        return password;
    }

    /*
     * Method checks if username exist in the data
     */
    public static boolean checkUsername(String username) {
        boolean isFound = false;
        try {
            File userFile = ensureFile(userInfo);

            Scanner fileScan = new Scanner(userFile);
            while(fileScan.hasNext()){
                String fileUser = fileScan.nextLine();
                if(fileUser.compareTo(username) == 0){
                    isFound = true;
                    break;
                }
                if(!fileScan.hasNext()){
                    break;
                }
                fileScan.nextLine();
            }

        } catch (Exception e) {
            Controller.createAlertWindow("Error occurred while reading a resource", Alert.AlertType.ERROR);
            System.exit(1);
        }

        return !isFound;
    }

    /*
     * Method add userName to data
     */
    public static boolean addUser (String username, String password) throws IOException {

        boolean didAdd = false;


         if(checkUsername(username)){
             String encryptPassword = Password.encryptPassword(password);

             File userFile = ensureFile(userInfo);
             FileWriter writer = new FileWriter(userFile.getPath(),true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer);

             bufferedWriter.write(username + "\n");
             bufferedWriter.write(encryptPassword + "\n");

             bufferedWriter.close();
            didAdd = true;
         }

        return didAdd;
    }

    /*
     * Method ensures that The file with the given name exists to prevent errors
     */
    private static File ensureFile(String fileName) throws IOException {
        File myFile = new File(fileName);

        myFile.createNewFile();

        return myFile;
    }

    /*
     * adds a new flight to the textFile provided
     * @param return success or fail
     */
    public static void addFlight(String flightTxt, Flight newFlight) {
        try{
            File flightFile = ensureFile(flightTxt);
            FileWriter writer = new FileWriter(flightFile.getPath(),true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(newFlight.getFlightName() + "\n");
            bufferedWriter.write(newFlight.getFlightDestination() + "\n");
            bufferedWriter.write(newFlight.getFlightStatus() + "\n");
            bufferedWriter.write(newFlight.getSeatsAvailable() + "\n");
            bufferedWriter.write(newFlight.getPricePerSeat() + "\n");
            bufferedWriter.close();
        } catch (Exception e) {
            Controller.createAlertWindow("Error occurred while adding flight", Alert.AlertType.ERROR);
            System.exit(1);
        }
    }

    /*
     * Updates totals seat count from a given file
     */
    public static void updateSeatTotal(String fileName, ObservableList<Flight> list) throws IOException {

        File airlineFile = ensureFile(fileName);
        FileWriter writer = new FileWriter(airlineFile.getPath(),false);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        for(Flight flight : list) {
            bufferedWriter.write(flight.getFlightName() + "\n");
            bufferedWriter.write(flight.getFlightDestination() + "\n");
            bufferedWriter.write(flight.getFlightStatus() + "\n");
            bufferedWriter.write(flight.getSeatsAvailable() + "\n");
            bufferedWriter.write(flight.getPricePerSeat() + "\n");
        }
        bufferedWriter.close();

    }

    /*
     * Generates a receipt with the given information
     */
    public static void createReceipt(String data) throws IOException {

        File receipt = ensureFile("receipt.txt");
        FileWriter writer = new FileWriter(receipt.getPath(),false);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        bufferedWriter.write(data + "\n");
        bufferedWriter.close();
    }

}

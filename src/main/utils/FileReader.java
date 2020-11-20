package main.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    /*
     * TODO write method description
     */
    public static ObservableList<Flight> getAllFlights(String fileName){
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        File textFile = ensureFile(fileName);
        try{
            Scanner reader = new Scanner(textFile);
            while(reader.hasNextLine()){

                //
                String flightName = reader.nextLine();
                String destination = reader.nextLine();
                String flightStatus = reader.nextLine();
                String seatsAvailable = reader.nextLine();
                String pricePerSeat = reader.nextLine();

                flights.add(new Flight(flightName,destination,flightStatus,Integer.parseInt(seatsAvailable),Integer.parseInt(pricePerSeat)));
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        return flights;
    }

    /*
     * TODO write method description
     */
    public static String getUserPassword(String username){
        String password = "";

        try {
            //Ensuring that the file exist to prevent error
            File userFile = ensureFile("users.txt");

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
            System.out.println("Error reading files");
        }
        return password;
    }

    /*
     * TODO write method description
     */
    public static boolean checkUsername(String username) {
        boolean isFound = false;
        try {
            File userFile = ensureFile("users.txt");

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
            System.out.println("Error Reading File");
        }

        return isFound;
    }

    /*
     * TODO write method description
     */
    public static boolean addUser (String username, String password) {

        boolean didAdd = false;

        String encryptPassword = Password.encryptPassword(password);

        try {
            File userFile = ensureFile("users.txt");
            FileWriter writer = new FileWriter(userFile.getPath(),true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(username + "\n");
            bufferedWriter.write(encryptPassword + "\n");

            bufferedWriter.close();
            didAdd = true;
        } catch (IOException e) {
            System.out.println("Error Reading file");
        }

        return didAdd;
    }

    /*
     * TODO write method description
     */
    private static File ensureFile(String fileName) {
        File myFile = new File(fileName);
        try {
            boolean didCreate = myFile.createNewFile();
            if(didCreate) {
                System.out.println("New File Was Created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            e.printStackTrace();
            System.out.println("An error occurred");
        }
    }

    /*
     * TODO write method description
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
     * TODO write method description
     */
    public static void createReceipt(String data) throws IOException {

        File receipt = ensureFile("receipt.txt");
        FileWriter writer = new FileWriter(receipt.getPath(),false);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        bufferedWriter.write(data + "\n");
        bufferedWriter.close();
        System.out.println(data);
    }

}

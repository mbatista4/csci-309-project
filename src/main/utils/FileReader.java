package main.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Flight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {


    public static ObservableList<Flight> getAllFlights(String fileName) throws IOException{
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
        } catch (Exception e){
            e.printStackTrace();
        }

        return flights;
    }


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


    private static File ensureFile(String fileName) throws IOException {
        File myFile = new File(fileName);
        boolean didCreate = myFile.createNewFile();

        if(didCreate) {
            System.out.println("New File Was Created");
        }
        return myFile;
    }

    /*
     * adds a new flight to the textFile provided
     * @param return success or fail
     */
    public static boolean addFlight(String flightTxt, Flight newFlight) {
        boolean didAdd = false;
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
            didAdd = true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("An error occurred");
        }
        return didAdd;
    }


}

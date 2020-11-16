package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {

    private static File ensureFile(String fileName) throws IOException {
        System.out.println(fileName);
        File myFile = new File(fileName);

        if(!myFile.isFile()){
            myFile.createNewFile();
        }

        return myFile;
    }

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

                flights.add(new Flight(flightName,destination,flightStatus,Integer.parseInt(seatsAvailable),Double.parseDouble(pricePerSeat)));
            }
            reader.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        return flights;
    }

    private boolean updateFlight(){
        boolean didUpdate = false;


        return didUpdate;
    }

    /*
     * adds a new flight to the textFile provided
     * @param return success or fail
     */
    private boolean addFlight(File flightTxt, Flight newFlight) {
        boolean didAdd = false;
        try{
            FileWriter writer = new FileWriter(flightTxt.getPath(),true);
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

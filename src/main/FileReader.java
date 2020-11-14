package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {

    private File textFile;


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

                //public Flight(String flightName, String flightDestination, int seatsAvailable, String flightStatus,double pricePerSeat){
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

}

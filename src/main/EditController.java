package main;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import main.utils.FileReader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EditController extends Controller implements Initializable {

    @FXML private Slider priceSlider;
    @FXML private VBox newFlightVBox;
    @FXML private Slider seatSlider;
    @FXML private TextField priceTextField;
    @FXML private TextField seatTextField;
    @FXML private TextField flightNameTextField;
    @FXML private  TextField destinationTextField;

    //JetGreen tableview
    @FXML private TableView jetGreenTableView;
    @FXML private TableColumn<Flight,String> jetGreenFlightNameColumn;
    @FXML private TableColumn<Flight,String> jetGreenDestinationColumn;
    @FXML private TableColumn<Flight,String> jetGreenStatusColumn;
    @FXML private TableColumn<Flight,Integer> jetGreenSeatsAvailableColumn;
    @FXML private TableColumn<Flight,Double> jetGreenPriceColumn;

    //Divided tableview
    @FXML private TableView dividedTableView;
    @FXML private TableColumn<Flight,String> dividedFlightNameColumn;
    @FXML private TableColumn<Flight,String> dividedDestinationColumn;
    @FXML private TableColumn<Flight,String> dividedStatusColumn;
    @FXML private TableColumn<Flight,Integer> dividedSeatsAvailableColumn;
    @FXML private TableColumn<Flight,Double> dividedPriceColumn;

    //Bravo tableview
    @FXML private TableView bravoTableView;
    @FXML private TableColumn<Flight,String> bravoFlightNameColumn;
    @FXML private TableColumn<Flight,String> bravoDestinationColumn;
    @FXML private TableColumn<Flight,String> bravoStatusColumn;
    @FXML private TableColumn<Flight,Integer> bravoSeatsAvailableColumn;
    @FXML private TableColumn<Flight,Double> bravoPriceColumn;

    private boolean isVisible = false;

    public void initJetGreenTable() {
        jetGreenFlightNameColumn.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        jetGreenDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("flightDestination"));
        jetGreenStatusColumn.setCellValueFactory(new PropertyValueFactory<>("flightStatus"));
        jetGreenSeatsAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("seatsAvailable"));
        jetGreenPriceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerSeat"));

        jetGreenTableView.setItems(getFlightList("jetGreen.txt"));
    }

    public void initDividedTable() {
        dividedFlightNameColumn.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        dividedDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("flightDestination"));
        dividedStatusColumn.setCellValueFactory(new PropertyValueFactory<>("flightStatus"));
        dividedSeatsAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("seatsAvailable"));
        dividedPriceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerSeat"));


        dividedTableView.setItems(getFlightList("usAirlines.txt"));
    }

    public void initBravoTable() {
        bravoFlightNameColumn.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        bravoDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("flightDestination"));
        bravoStatusColumn.setCellValueFactory(new PropertyValueFactory<>("flightStatus"));
        bravoSeatsAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("seatsAvailable"));
        bravoPriceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerSeat"));

        bravoTableView.setItems(getFlightList("bravoAirlines.txt"));

    }

    private ObservableList<Flight> getFlightList(String fileName) {

        ObservableList<Flight> list = null;

        try {
            list = FileReader.getAllFlights(fileName);
        } catch (IOException e) {
            createAlertWindow("Error getting flights", Alert.AlertType.ERROR);
        }

        return list;
    }


    public void addDivided(ActionEvent event){
        Flight newFlight = addFlight(event,"usAirlines.txt");

        if(newFlight!= null ){
            dividedTableView.getItems().add(newFlight);
        }

    }

    public void addJet(ActionEvent event){
        Flight newFlight = addFlight(event,"jetGreen.txt");
        if(newFlight!= null ){
            jetGreenTableView.getItems().add(newFlight);
        }
    }

    public void addBravo(ActionEvent event) {
        Flight newFlight = addFlight(event,"bravoAirlines.txt");
        if(newFlight!= null ){
            bravoTableView.getItems().add(newFlight);
        }
    }


    public Flight addFlight(ActionEvent event, String flightTxt) {

        String flightName = flightNameTextField.getText();
        String flightDestination = destinationTextField.getText();
        int price = Integer.parseInt(priceTextField.getText());
        int seats = Integer.parseInt(seatTextField.getText());
        Flight newFlight = null;

        if(flightName.isEmpty() || flightDestination.isEmpty() || price <= 0 || seats <= 0) {
            createAlertWindow("Missing Fields!!", Alert.AlertType.WARNING);
        } else {

            newFlight = new Flight(flightName,flightDestination,"Available",seats,price);
            FileReader.addFlight(flightTxt,newFlight);
            createAlertWindow("Flight Added!!", Alert.AlertType.INFORMATION);
        }
        return newFlight;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        priceSlider.valueProperty().addListener((observableValue, number, t1) -> {
            String newVal = Integer.toString(t1.intValue());
            priceTextField.textProperty().setValue(newVal);
        });

        priceTextField.textProperty().addListener((observableValue, s, t1) -> {
            try {

                int price = Integer.parseInt(t1);

                if(price <= 0)
                    priceTextField.textProperty().setValue("1");

                priceSlider.valueProperty().setValue(Integer.parseInt(t1));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        });

        seatSlider.valueProperty().addListener((observableValue, number, t1) -> {
            String newVal = Integer.toString(t1.intValue());
            seatTextField.textProperty().setValue(newVal);
        });

        seatTextField.textProperty().addListener((observableValue, s, t1) -> {
            try {

                int seats = Integer.parseInt(t1);

                if(seats <= 0)
                    seatTextField.textProperty().setValue("1");

                seatSlider.valueProperty().setValue(seats);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
            }
        });
    }

    public void showAdd() {
        isVisible = !isVisible;
        newFlightVBox.setVisible(isVisible);
    }


}

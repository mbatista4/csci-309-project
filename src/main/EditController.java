package main;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import main.utils.*;

import java.net.URL;
import java.util.ResourceBundle;

/*
 * This class provides all of the functionality needed
 * for the Edit Window
 */

public class EditController extends Controller implements Initializable {

    // new Flight information
    @FXML private Slider priceSlider;
    @FXML private VBox newFlightVBox;
    @FXML private Slider seatSlider;
    @FXML private TextField priceTextField;
    @FXML private TextField seatTextField;
    @FXML private ComboBox<String> flightNamesComboBox;
    @FXML private ComboBox<String> destinationComboBox;

    // add manager information
    @FXML private VBox addManagerVBox;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField passwordTextField1;


    //JetGreen tableview
    @FXML private TableView<Flight> jetGreenTableView;
    @FXML private TableColumn<Flight,String> jetGreenFlightNameColumn;
    @FXML private TableColumn<Flight,String> jetGreenDestinationColumn;
    @FXML private TableColumn<Flight,String> jetGreenStatusColumn;
    @FXML private TableColumn<Flight,Integer> jetGreenSeatsAvailableColumn;
    @FXML private TableColumn<Flight,Double> jetGreenPriceColumn;

    //Divided tableview
    @FXML private TableView<Flight> dividedTableView;
    @FXML private TableColumn<Flight,String> dividedFlightNameColumn;
    @FXML private TableColumn<Flight,String> dividedDestinationColumn;
    @FXML private TableColumn<Flight,String> dividedStatusColumn;
    @FXML private TableColumn<Flight,Integer> dividedSeatsAvailableColumn;
    @FXML private TableColumn<Flight,Double> dividedPriceColumn;

    //Bravo tableview
    @FXML private TableView<Flight> bravoTableView;
    @FXML private TableColumn<Flight,String> bravoFlightNameColumn;
    @FXML private TableColumn<Flight,String> bravoDestinationColumn;
    @FXML private TableColumn<Flight,String> bravoStatusColumn;
    @FXML private TableColumn<Flight,Integer> bravoSeatsAvailableColumn;
    @FXML private TableColumn<Flight,Double> bravoPriceColumn;

    private boolean isVisible = false;

    /*
     * Method initializes the Data for a Table
     */
    public void initJetGreenTable() {
        jetGreenFlightNameColumn.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        jetGreenDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("flightDestination"));
        jetGreenStatusColumn.setCellValueFactory(new PropertyValueFactory<>("flightStatus"));
        jetGreenSeatsAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("seatsAvailable"));
        jetGreenPriceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerSeat"));

        jetGreenTableView.setItems(getFlightList("jetGreen.dat"));
    }

    /*
     * Method initializes the Data for a Table
     */
    public void initDividedTable() {
        dividedFlightNameColumn.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        dividedDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("flightDestination"));
        dividedStatusColumn.setCellValueFactory(new PropertyValueFactory<>("flightStatus"));
        dividedSeatsAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("seatsAvailable"));
        dividedPriceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerSeat"));


        dividedTableView.setItems(getFlightList("dAirlines.dat"));
    }

    /*
     * Method initializes the Data for a Table
     */
    public void initBravoTable() {
        bravoFlightNameColumn.setCellValueFactory(new PropertyValueFactory<>("flightName"));
        bravoDestinationColumn.setCellValueFactory(new PropertyValueFactory<>("flightDestination"));
        bravoStatusColumn.setCellValueFactory(new PropertyValueFactory<>("flightStatus"));
        bravoSeatsAvailableColumn.setCellValueFactory(new PropertyValueFactory<>("seatsAvailable"));
        bravoPriceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePerSeat"));
        bravoTableView.setItems(getFlightList("bAirlines.dat"));
    }

    /*
     * Method gets a list of flights from the given file name
     */
    private ObservableList<Flight> getFlightList(String fileName) {

        ObservableList<Flight> flights = null;

        try {
            flights = FileReader.getAllFlights(fileName);
        }  catch (Exception e) {
            createAlertWindow("An Error Occurred", Alert.AlertType.ERROR);
            System.exit(1);
        }
        return flights;
    }

    /*
     * method adds a new Flight to the Airline
     */
    public void addDivided(){
        Flight newFlight = addFlight(FileReader.DIVIDED_AIR);

        if(newFlight!= null ){
            dividedTableView.getItems().add(newFlight);
        }
    }

    /*
     * method adds a new Flight to the Airline
     */
    public void addJet(){
        Flight newFlight = addFlight(FileReader.JET_GREEN);
        if(newFlight!= null ){
            jetGreenTableView.getItems().add(newFlight);
        }
    }

    /*
     * method adds a new Flight to the Airline
     */
    public void addBravo() {
        Flight newFlight = addFlight(FileReader.BRAVO_AIR);
        if(newFlight!= null ){
            bravoTableView.getItems().add(newFlight);
        }
    }

    /*
     * method adds a new Flight to the Airline
     */
    public Flight addFlight(String flightTxt) {

        String flightName = flightNamesComboBox.getValue();
        String flightDestination = destinationComboBox.getValue();
        int price = Integer.parseInt(priceTextField.getText());
        int seats = Integer.parseInt(seatTextField.getText());
        Flight newFlight = null;

        if(flightName == null || flightDestination == null || price <= 0 || seats <= 0) {
            createAlertWindow("Missing Fields!!", Alert.AlertType.WARNING);
        } else {
            newFlight = new Flight(flightName,flightDestination,"Available",seats,price);
            FileReader.addFlight(flightTxt,newFlight);
            createAlertWindow("Flight Added!!", Alert.AlertType.INFORMATION);
        }
        return newFlight;
    }

    /*
     * This method initializes the inputs for the add Manager and Add flights section
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        priceSlider.valueProperty().addListener((observableValue, number, t1) -> {
            String newVal = Integer.toString(t1.intValue());
            priceTextField.textProperty().setValue(newVal);
        });

        priceTextField.textProperty().addListener((observableValue, s, t1) -> {
            try {

                int price = Integer.parseInt(t1);

                if(price <= 0){
                    priceTextField.textProperty().setValue("1");
                } else if(price > 300) {
                    priceTextField.textProperty().setValue("300");
                }
                else {
                    priceSlider.valueProperty().setValue(price);
                }
            } catch (NumberFormatException e) {
                createAlertWindow("Please enter a valid number", Alert.AlertType.WARNING);
            }
        });

        seatSlider.valueProperty().addListener((observableValue, number, t1) -> {
            String newVal = Integer.toString(t1.intValue());
            seatTextField.textProperty().setValue(newVal);
        });

        seatTextField.textProperty().addListener((observableValue, s, t1) -> {
            try {

                int seats = Integer.parseInt(t1);

                if(seats <= 0){
                    seatTextField.textProperty().setValue("1");
                } else if(seats > 200) {
                    seatTextField.textProperty().setValue("200");
                }else
                 {
                    seatSlider.valueProperty().setValue(seats);
                }

            } catch (NumberFormatException e) {
                createAlertWindow("Please enter a valid number", Alert.AlertType.WARNING);
            }
        });

        //Adding Flight names
        flightNamesComboBox.getItems().add("FL 221");
        flightNamesComboBox.getItems().add("AZ 141");
        flightNamesComboBox.getItems().add("LS 315");
        flightNamesComboBox.getItems().add("FL 078");
        flightNamesComboBox.getItems().add("PZ 441");
        flightNamesComboBox.getItems().add("EZ 420");

        //Adding
        destinationComboBox.getItems().add("Boston");
        destinationComboBox.getItems().add("Providence");
        destinationComboBox.getItems().add("New York");
        destinationComboBox.getItems().add("Los Angeles");
        destinationComboBox.getItems().add("Nevada");
        destinationComboBox.getItems().add("Seattle");
    }

    /*
     * This method toggles the Add flight section
     */
    public void showAddFlight() {
        if(addManagerVBox.isVisible()){
            newFlightVBox.setVisible(isVisible);
            addManagerVBox.setVisible(!isVisible);
        } else {
            isVisible = !isVisible;
            newFlightVBox.setVisible(isVisible);
        }
    }

    /*
     * This method toggles the Add Manager section
     */
    public void showAddManager() {
        if(newFlightVBox.isVisible()){
            addManagerVBox.setVisible(isVisible);
            newFlightVBox.setVisible(!isVisible);
        } else {
            isVisible = !isVisible;
            addManagerVBox.setVisible(isVisible);
        }
    }

    /*
     * This method Adds a new Manager to the data
     */
    public void addManager() {

        String username = usernameTextField.getText().trim();
        String pass1 = passwordTextField.getText().trim();
        String pass2 = passwordTextField1.getText().trim();

        if(username.isEmpty() || pass1.isEmpty() || pass2.isEmpty()){
           createAlertWindow("Missing fields!", Alert.AlertType.WARNING);
        } else if (pass1.compareTo(pass2) != 0) {
            createAlertWindow("Passwords do not Match!!", Alert.AlertType.WARNING);
        } else {

            if (FileReader.checkUsername(username)){
                boolean didAdd = false;
                try {
                    didAdd = FileReader.addUser(username,pass1);
                } catch (Exception e) {
                    createAlertWindow("An Error Occurred", Alert.AlertType.ERROR);
                    System.exit(1);
                }
                if(didAdd) {
                    createAlertWindow("Manager added to Database", Alert.AlertType.WARNING);
                    usernameTextField.clear();
                    passwordTextField.clear();
                    passwordTextField1.clear();
                }
            } else {
                createAlertWindow("Username Already exist", Alert.AlertType.WARNING);
                usernameTextField.clear();
            }
        }
    }
}
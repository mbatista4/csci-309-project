package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SwitchScene {

    static public void switchScene(ActionEvent event, Parent parent){

            Scene newScreenScene = new Scene(parent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(newScreenScene);
            window.show();
    }

    static public void switchScene(ActionEvent event, Parent parent, String fileName){

        Scene newScreenScene = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(newScreenScene);
        window.show();
    }

}

package keya.internationaltradefairltd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageViewController
{
    @javafx.fxml.FXML
    private ComboBox<String> userTypeComboBox;

    @javafx.fxml.FXML
    public void initialize() {
        userTypeComboBox.getItems().setAll("EventManager","Customer","Vendor","Admin","Quality Controller","Customer Support Agent","Advertiser","Investor");
    }

    @FXML
    public void nextBTOnAction(ActionEvent actionEvent) throws IOException {
        String selectedUserType = userTypeComboBox.getValue();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(selectedUserType==null || selectedUserType.isEmpty()) {

            alert.setTitle("Selection Error");
            alert.setHeaderText("You didn't select eny user");
            alert.setContentText("Please select a user type before proceeding.");
            alert.showAndWait();
            return;

        }



        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Log In!"+selectedUserType);
        stage.setScene(scene);
        stage.show();
    }

}
package keya.internationaltradefairltd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelperClass.DataManager;

import java.io.IOException;

public class HomePageViewController {
    @FXML
    private ComboBox<String> userTypeComboBox;

    @FXML
    public void initialize() {
        userTypeComboBox.getItems().setAll(
                "EventManager",
                "Customer Support Agent",
                "Vendor",
                "Customer",
                "Admin",
                "Quality Controller",
                "Advertiser",
                "Investor"
        );
        userTypeComboBox.setValue("EventManager");
    }

    @FXML
    public void nextBTOnAction(ActionEvent actionEvent) throws IOException {
        String selectedUserType = userTypeComboBox.getValue();
        if (selectedUserType == null || selectedUserType.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selection Error");
            alert.setHeaderText("User Type Not Selected");
            alert.setContentText("Please select a user type before proceeding.");
            alert.showAndWait();
            return;
        }

        DataManager.getInstance().setSelectedUserType(selectedUserType);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login - " + selectedUserType + " Portal");
        stage.setScene(scene);
        stage.show();
    }
}
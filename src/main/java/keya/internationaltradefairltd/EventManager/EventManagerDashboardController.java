package keya.internationaltradefairltd.EventManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelloApplication;
import keya.internationaltradefairltd.HelperClass.DataManager;
import keya.internationaltradefairltd.User.User;

import java.io.IOException;

public class EventManagerDashboardController {
    @FXML
    private Label userNameLabel;
    @FXML
    private Button signOutButton;

    @FXML
    public void initialize() {
        User u = DataManager.getInstance().getCurrentUser();
        if (u != null) {
            userNameLabel.setText("Welcome, " + u.getFullName() + " (" + u.getUserType() + ")");
        } else {
            userNameLabel.setText("Welcome, Event Manager");
        }
    }

    @FXML
    public void eventVendorRegBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/VendorRegistration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Vendor Registration & Management");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void EventArrMTBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/ArrangeMeetingView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Arrange & View Meetings");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void eventRqstsBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/ViewRequestsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Vendor Special Requests");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void eventAssignStallBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/AssignStall.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Assign & Manage Stalls");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void reportsBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/ViewReports.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Trade Fair Reports");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void eventManagerFeedbackBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/FeedBack.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("User Feedback");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signOutBTOnAction(ActionEvent actionEvent) throws IOException {
        DataManager.getInstance().setCurrentUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void eventMBKBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Dhaka International Trade Fair - Home");
        stage.setScene(scene);
        stage.show();
    }
}
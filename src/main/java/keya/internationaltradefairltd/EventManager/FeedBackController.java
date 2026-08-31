package keya.internationaltradefairltd.EventManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelloApplication;
import keya.internationaltradefairltd.HelperClass.DataManager;
import keya.internationaltradefairltd.HelperClass.Feedback;

import java.io.IOException;

public class FeedBackController {
    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private Label userNameLable;
    @FXML
    private Label feedBackLabel;

    private int currentFeedbackIndex = 0;
    private ObservableList<Feedback> activeFeedbacks = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userTypeComboBox.getItems().setAll(
                "All",
                "Vendor",
                "Customer",
                "Quality Controller",
                "Advertiser",
                "Investor"
        );
        userTypeComboBox.setValue("All");

        filterFeedbacks("All");

        userTypeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                filterFeedbacks(newVal);
            }
        });
    }

    private void filterFeedbacks(String role) {
        if ("All".equalsIgnoreCase(role)) {
            activeFeedbacks = DataManager.getInstance().getFeedbacks();
        } else {
            activeFeedbacks = FXCollections.observableArrayList();
            for (Feedback fb : DataManager.getInstance().getFeedbacks()) {
                if (fb.getUserType() != null && fb.getUserType().equalsIgnoreCase(role)) {
                    activeFeedbacks.add(fb);
                }
            }
        }

        currentFeedbackIndex = 0;
        displayCurrentFeedback();
    }

    private void displayCurrentFeedback() {
        if (activeFeedbacks != null && !activeFeedbacks.isEmpty()) {
            Feedback fb = activeFeedbacks.get(currentFeedbackIndex % activeFeedbacks.size());
            userNameLable.setText(fb.getUserName() + " (" + fb.getUserType() + " | Date: " + fb.getDate() + ")");
            feedBackLabel.setText("\"" + fb.getFeedbackContent() + "\"");
        } else {
            userNameLable.setText("No feedback available");
            feedBackLabel.setText("No feedback entries submitted for the selected user role.");
        }
    }

    @FXML
    public void backBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/EventManagerDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Event Manager Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
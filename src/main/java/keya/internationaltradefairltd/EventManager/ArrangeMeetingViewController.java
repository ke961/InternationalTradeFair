package keya.internationaltradefairltd.EventManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelloApplication;

import java.io.IOException;

public class ArrangeMeetingViewController
{
    @javafx.fxml.FXML
    private TableColumn participantTableColumn;
    @javafx.fxml.FXML
    private TableColumn dateTableColumn;
    @javafx.fxml.FXML
    private TableColumn timeTableColumn;
    @javafx.fxml.FXML
    private TableView meetingTableView;
    @javafx.fxml.FXML
    private DatePicker meetingDatePicker1;
    @javafx.fxml.FXML
    private TableView viewMeetingTableView;
    @javafx.fxml.FXML
    private TableColumn VparticipantTableColumn;
    @javafx.fxml.FXML
    private TableColumn vTimeTableColumn;
    @javafx.fxml.FXML
    private TableColumn vDateTableColumn;
    @javafx.fxml.FXML
    private TextField meetingTimeTextField1;
    @javafx.fxml.FXML
    private TabPane mainTab;
    @javafx.fxml.FXML
    private Tab viewMeetingTab;
    @javafx.fxml.FXML
    private Tab scheduleMeetingTab;
    @javafx.fxml.FXML
    private ComboBox participantComboBox;
    @javafx.fxml.FXML
    private ComboBox filteredParticipantComboBox;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void createMeetingBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void deleteMeetingBTOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void backBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/EventManagerDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Log In!");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void updateBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewMeetingBTOnAction(ActionEvent actionEvent) {
    }

    @Deprecated
    public void vBackBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void vMeetingBackBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void sMeetingbackBTOnAction(ActionEvent actionEvent) {
    }
}
package keya.internationaltradefairltd.EventManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelloApplication;
import keya.internationaltradefairltd.HelperClass.Meeting;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ArrangeMeetingViewController
{
    @javafx.fxml.FXML
    private TableColumn<Meeting,String> participantTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Meeting, LocalDate> dateTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Meeting,String> timeTableColumn;
    @javafx.fxml.FXML
    private TableView<Meeting> meetingTableView;
    @javafx.fxml.FXML
    private DatePicker meetingDatePicker1;
    @javafx.fxml.FXML
    private TableView<Meeting> viewMeetingTableView;
    @javafx.fxml.FXML
    private TableColumn<Meeting,String> VparticipantTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Meeting,String> vTimeTableColumn;
    @javafx.fxml.FXML
    private TableColumn<Meeting,LocalDate> vDateTableColumn;
    @javafx.fxml.FXML
    private TextField meetingTimeTextField1;
    @javafx.fxml.FXML
    private TabPane mainTab;
    @javafx.fxml.FXML
    private Tab viewMeetingTab;
    @javafx.fxml.FXML
    private Tab scheduleMeetingTab;
    @javafx.fxml.FXML
    private ComboBox<String> participantComboBox;
    @javafx.fxml.FXML
    private ComboBox<String> filteredParticipantComboBox;
    ArrayList<Meeting> meetings;

    @javafx.fxml.FXML
    public void initialize() {
        meetings = new ArrayList<>();
        participantComboBox.getItems().addAll("EventManager","Admin","Quality Controller","Customer Support Agent");
        
    }

    @javafx.fxml.FXML
    public void createMeetingBTOnAction(ActionEvent actionEvent) {
        String participantName = participantComboBox.getValue();
        String time = meetingTimeTextField1.getText();
        LocalDate date = meetingDatePicker1.getValue();



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
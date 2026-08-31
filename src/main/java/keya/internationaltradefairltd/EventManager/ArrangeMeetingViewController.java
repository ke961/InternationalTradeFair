package keya.internationaltradefairltd.EventManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelloApplication;
import keya.internationaltradefairltd.HelperClass.DataManager;
import keya.internationaltradefairltd.HelperClass.Meeting;

import java.io.IOException;
import java.time.LocalDate;

public class ArrangeMeetingViewController {
    @FXML
    private TableColumn<Meeting, String> participantTableColumn;
    @FXML
    private TableColumn<Meeting, LocalDate> dateTableColumn;
    @FXML
    private TableColumn<Meeting, String> timeTableColumn;
    @FXML
    private TableView<Meeting> meetingTableView;
    @FXML
    private DatePicker meetingDatePicker1;
    @FXML
    private TableView<Meeting> viewMeetingTableView;
    @FXML
    private TableColumn<Meeting, String> VparticipantTableColumn;
    @FXML
    private TableColumn<Meeting, String> vTimeTableColumn;
    @FXML
    private TableColumn<Meeting, LocalDate> vDateTableColumn;
    @FXML
    private TextField meetingTimeTextField1;
    @FXML
    private TabPane mainTab;
    @FXML
    private Tab viewMeetingTab;
    @FXML
    private Tab scheduleMeetingTab;
    @FXML
    private ComboBox<String> participantComboBox;
    @FXML
    private ComboBox<String> filteredParticipantComboBox;

    @FXML
    public void initialize() {
        participantComboBox.getItems().setAll("EventManager", "Admin", "Quality Controller", "Customer Support Agent", "All Participants");
        participantComboBox.setValue("Admin");

        filteredParticipantComboBox.getItems().setAll("All", "EventManager", "Admin", "Quality Controller", "Customer Support Agent");
        filteredParticipantComboBox.setValue("All");

        participantTableColumn.setCellValueFactory(new PropertyValueFactory<>("participant"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        VparticipantTableColumn.setCellValueFactory(new PropertyValueFactory<>("participant"));
        vTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        vDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        meetingDatePicker1.setValue(LocalDate.now().plusDays(1));
        meetingTimeTextField1.setPromptText("e.g. 10:30 AM");

        refreshMeetingTables();
    }

    private void refreshMeetingTables() {
        ObservableList<Meeting> allMeetings = DataManager.getInstance().getMeetings();
        meetingTableView.setItems(allMeetings);
        viewMeetingTableView.setItems(allMeetings);
    }

    @FXML
    public void createMeetingBTOnAction(ActionEvent actionEvent) {
        String participant = participantComboBox.getValue();
        String time = meetingTimeTextField1.getText();
        LocalDate date = meetingDatePicker1.getValue();

        if (participant == null || participant.trim().isEmpty() || time == null || time.trim().isEmpty() || date == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Incomplete Meeting Details");
            alert.setContentText("Please select participant, enter time, and pick a date.");
            alert.showAndWait();
            return;
        }

        if (date.isBefore(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Date");
            alert.setHeaderText(null);
            alert.setContentText("Meeting date cannot be in the past.");
            alert.showAndWait();
            return;
        }

        Meeting meeting = new Meeting(participant, time.trim(), date);
        DataManager.getInstance().getMeetings().add(meeting);
        refreshMeetingTables();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Meeting Created");
        alert.setHeaderText(null);
        alert.setContentText("Meeting with " + participant + " scheduled on " + date + " at " + time + ".");
        alert.showAndWait();

        meetingTimeTextField1.clear();
        meetingDatePicker1.setValue(LocalDate.now().plusDays(1));
    }

    @FXML
    public void deleteMeetingBTOnAction(ActionEvent actionEvent) {
        Meeting selectedMeeting = meetingTableView.getSelectionModel().getSelectedItem();
        if (selectedMeeting == null) {
            selectedMeeting = viewMeetingTableView.getSelectionModel().getSelectedItem();
        }

        if (selectedMeeting != null) {
            DataManager.getInstance().getMeetings().remove(selectedMeeting);
            refreshMeetingTables();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Meeting Removed");
            alert.setHeaderText(null);
            alert.setContentText("Selected meeting has been successfully deleted.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select a meeting from the table to delete.");
            alert.showAndWait();
        }
    }

    @FXML
    public void updateBTOnAction(ActionEvent actionEvent) {
        String filter = filteredParticipantComboBox.getValue();
        if (filter == null || "All".equalsIgnoreCase(filter)) {
            meetingTableView.setItems(DataManager.getInstance().getMeetings());
        } else {
            ObservableList<Meeting> filtered = FXCollections.observableArrayList();
            for (Meeting m : DataManager.getInstance().getMeetings()) {
                if (m.getParticipant() != null && m.getParticipant().equalsIgnoreCase(filter)) {
                    filtered.add(m);
                }
            }
            meetingTableView.setItems(filtered);
        }
    }

    @FXML
    public void viewMeetingBTOnAction(ActionEvent actionEvent) {
        mainTab.getSelectionModel().select(viewMeetingTab);
        viewMeetingTableView.setItems(DataManager.getInstance().getMeetings());
    }

    @FXML
    public void vMeetingBackBTOnAction(ActionEvent actionEvent) {
        mainTab.getSelectionModel().select(scheduleMeetingTab);
    }

    @FXML
    public void sMeetingbackBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/EventManagerDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Event Manager Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
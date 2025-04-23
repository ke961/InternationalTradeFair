package keya.internationaltradefairltd.EventManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
        participantTableColumn.setCellValueFactory(new PropertyValueFactory<>("participant"));
        dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        VparticipantTableColumn.setCellValueFactory(new PropertyValueFactory<>("participant"));
        vTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        vDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    @javafx.fxml.FXML
    public void createMeetingBTOnAction(ActionEvent actionEvent) {
        String participant = participantComboBox.getValue();
        String time = meetingTimeTextField1.getText();
        LocalDate date = meetingDatePicker1.getValue();

        if(participant==null||participant.isEmpty()||time==null||time.isEmpty()||date==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
            return;
        }
        if(date.isBefore(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Date cannot be past");
            alert.showAndWait();
            return;
        }

        Meeting meeting = new Meeting(participant,time,date);
        meetings.add(meeting);
        filteredParticipantComboBox.getItems().addAll(participant);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Meeting created successfully");
        alert.showAndWait();

        meetingTimeTextField1.clear();
        meetingDatePicker1.setValue(null);






    }

    @javafx.fxml.FXML
    public void deleteMeetingBTOnAction(ActionEvent actionEvent) {
        Meeting selected_meeting = meetingTableView.getSelectionModel().getSelectedItem();
        if(selected_meeting!=null) {
            meetingTableView.getItems().remove(selected_meeting);

        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No meeting selected");
            alert.showAndWait();
            return;
        }
    }


    @javafx.fxml.FXML
    public void updateBTOnAction(ActionEvent actionEvent) {
        String participant = filteredParticipantComboBox.getValue();
        for(Meeting meeting : meetings) {
            if(meeting.getParticipant().equals(participant)) {
                meetingTableView.getItems().setAll(meetings);
                return;

            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Participant does not match");
                alert.showAndWait();
                return;

            }
        }
    }

    @javafx.fxml.FXML
    public void viewMeetingBTOnAction(ActionEvent actionEvent) {
        SingleSelectionModel<Tab> singleSelectionModel = mainTab.getSelectionModel();///to switch one tab to another
        singleSelectionModel.select(viewMeetingTab);
        viewMeetingTableView.getItems().setAll(meetingTableView.getItems());
    }



    @javafx.fxml.FXML
    public void vMeetingBackBTOnAction(ActionEvent actionEvent) {
        SingleSelectionModel<Tab> singleSelectionModel = mainTab.getSelectionModel();///to switch one tab to another
        singleSelectionModel.select(scheduleMeetingTab);

    }

    @javafx.fxml.FXML
    public void sMeetingbackBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/EventManagerDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Log In!");
        stage.setScene(scene);
        stage.show();
    }
}
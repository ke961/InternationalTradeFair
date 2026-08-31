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
import keya.internationaltradefairltd.HelperClass.Report;

import java.io.IOException;

public class ViewReportsController {
    @FXML
    private TabPane mainTab;
    @FXML
    private Tab reportsTab;

    // Reports Tab controls
    @FXML
    private TableView<Report> reportTableView;
    @FXML
    private TableColumn<Report, String> userTypeTableColumn;
    @FXML
    private TableColumn<Report, String> userNameTableColumn;
    @FXML
    private TableColumn<Report, String> reportContentTableColumn;

    // Details Tab controls
    @FXML
    private ComboBox<String> userTypeComboBox;
    @FXML
    private Label userNameLable;
    @FXML
    private Label reportAboutLable;

    private Report selectedReport;

    @FXML
    public void initialize() {
        userTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        userNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        reportContentTableColumn.setCellValueFactory(new PropertyValueFactory<>("reportContent"));

        userTypeComboBox.getItems().setAll("All", "Vendor", "Customer", "Quality Controller", "Admin", "Customer Support Agent");
        userTypeComboBox.setValue("All");

        refreshReportsTable();

        reportTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedReport = newVal;
                displayReportDetails(newVal);
            }
        });

        userTypeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                filterReportsByUserType(newVal);
            }
        });
    }

    private void refreshReportsTable() {
        reportTableView.setItems(DataManager.getInstance().getReports());
    }

    private void filterReportsByUserType(String role) {
        if ("All".equalsIgnoreCase(role)) {
            reportTableView.setItems(DataManager.getInstance().getReports());
        } else {
            ObservableList<Report> filtered = FXCollections.observableArrayList();
            for (Report r : DataManager.getInstance().getReports()) {
                if (r.getUserType() != null && r.getUserType().equalsIgnoreCase(role)) {
                    filtered.add(r);
                }
            }
            reportTableView.setItems(filtered);
        }
    }

    private void displayReportDetails(Report r) {
        if (r != null) {
            userNameLable.setText(r.getUserName() + " (" + r.getUserType() + " | " + r.getDate() + ")");
            reportAboutLable.setText("Title: " + r.getReportTitle() + "\nStatus: " + r.getStatus() + "\n\n" + r.getReportContent());
        } else {
            userNameLable.setText("None Selected");
            reportAboutLable.setText("Please select a report from the Reports table.");
        }
    }

    @FXML
    public void viewReportsBTOnAction(ActionEvent actionEvent) {
        refreshReportsTable();
    }

    @FXML
    public void viewReportDetailesBTOnAction(ActionEvent actionEvent) {
        Report r = reportTableView.getSelectionModel().getSelectedItem();
        if (r != null) {
            selectedReport = r;
            displayReportDetails(r);
            mainTab.getSelectionModel().select(1); // Select Details tab
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a report from the table to view its full details.");
            alert.showAndWait();
        }
    }

    @FXML
    public void detBackBTOnAction(ActionEvent actionEvent) {
        mainTab.getSelectionModel().select(reportsTab);
    }

    @FXML
    public void reportBackBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/EventManagerDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Event Manager Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
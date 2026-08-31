package keya.internationaltradefairltd.EventManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelloApplication;
import keya.internationaltradefairltd.HelperClass.DataManager;
import keya.internationaltradefairltd.HelperClass.Vendor;

import java.io.IOException;

public class ViewRequestsViewController {
    @FXML
    private TextArea requestsDetailesTextField;
    @FXML
    private Button approveBT;
    @FXML
    private Button rejectBT;
    @FXML
    private ComboBox<String> vendorComboBox;

    private Vendor currentSelectedVendor;

    @FXML
    public void initialize() {
        refreshVendorList();

        if (vendorComboBox != null) {
            vendorComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    loadVendorRequest(newVal);
                }
            });
        }
    }

    private void refreshVendorList() {
        ObservableList<String> vendorNames = FXCollections.observableArrayList();
        for (Vendor v : DataManager.getInstance().getVendors()) {
            vendorNames.add(v.getOrganizationName());
        }
        if (vendorComboBox != null) {
            vendorComboBox.setItems(vendorNames);
            if (!vendorNames.isEmpty()) {
                vendorComboBox.setValue(vendorNames.get(0));
                loadVendorRequest(vendorNames.get(0));
            }
        }
    }

    private void loadVendorRequest(String orgName) {
        for (Vendor v : DataManager.getInstance().getVendors()) {
            if (v.getOrganizationName().equalsIgnoreCase(orgName)) {
                currentSelectedVendor = v;
                StringBuilder sb = new StringBuilder();
                sb.append("ORGANIZATION: ").append(v.getOrganizationName()).append("\n");
                sb.append("CONTACT PERSON: ").append(v.getApplicantName()).append("\n");
                sb.append("PHONE: ").append(v.getPhoneNumber()).append("\n");
                sb.append("STATUS: ").append(v.getStatus()).append("\n");
                sb.append("ASSIGNED STALL: ").append(v.getAssignedStallId()).append("\n\n");
                sb.append("SPECIAL REQUEST DETAILS:\n");
                sb.append(v.getSpecialRequest() != null && !v.getSpecialRequest().isEmpty()
                        ? v.getSpecialRequest()
                        : "No special requests filed.");

                requestsDetailesTextField.setText(sb.toString());
                break;
            }
        }
    }

    @FXML
    public void rqstApproveBTOnAction(ActionEvent actionEvent) {
        if (currentSelectedVendor != null) {
            currentSelectedVendor.setStatus("Approved");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Request Approved");
            alert.setHeaderText(null);
            alert.setContentText("Special request for " + currentSelectedVendor.getOrganizationName() + " has been APPROVED and forwarded to Fair Logistics & Power Maintenance.");
            alert.showAndWait();
            loadVendorRequest(currentSelectedVendor.getOrganizationName());
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Vendor Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a vendor from the dropdown.");
            alert.showAndWait();
        }
    }

    @FXML
    public void rqstRejectBTOnAction(ActionEvent actionEvent) {
        if (currentSelectedVendor != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Request Rejected");
            alert.setHeaderText(null);
            alert.setContentText("Special request for " + currentSelectedVendor.getOrganizationName() + " could not be accommodated due to technical/space constraints.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Vendor Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a vendor from the dropdown.");
            alert.showAndWait();
        }
    }

    @FXML
    public void rDetlbackBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/EventManagerDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Event Manager Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
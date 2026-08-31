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
import keya.internationaltradefairltd.HelperClass.Vendor;

import java.io.IOException;

public class VendorRegistrationController {
    @FXML
    private TabPane mainTab;
    @FXML
    private Tab vendorRegistrationTab;
    @FXML
    private Tab viewApplicantsTab;
    @FXML
    private Tab detailesTab;

    // View Applicants Tab controls
    @FXML
    private TableView<Vendor> applicantsTableView;
    @FXML
    private TableColumn<Vendor, String> companyTableColumn;
    @FXML
    private TableColumn<Vendor, String> applicantsNameTableColumn;
    @FXML
    private TableColumn<Vendor, String> emailTableColumn;
    @FXML
    private TableColumn<Vendor, String> phoneNumberTableColumn;

    // Details Tab controls
    @FXML
    private TextArea productDetailesTextArea;
    @FXML
    private TableView<Vendor> approvedApplicantsTableView1;
    @FXML
    private TableColumn<Vendor, String> approvedCompanyTableColumn1;
    @FXML
    private TableColumn<Vendor, String> approvedApplicantsNameTableColumn1;
    @FXML
    private TableColumn<Vendor, String> approvedEmailTableColumn1;
    @FXML
    private TableColumn<Vendor, String> approvedPhoneNumberTableColumn1;

    private Vendor selectedVendor;

    @FXML
    public void initialize() {
        // Configure columns for Applicants Table
        companyTableColumn.setCellValueFactory(new PropertyValueFactory<>("company"));
        applicantsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("applicantName"));
        emailTableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        // Configure columns for Approved Applicants Table
        approvedCompanyTableColumn1.setCellValueFactory(new PropertyValueFactory<>("company"));
        approvedApplicantsNameTableColumn1.setCellValueFactory(new PropertyValueFactory<>("applicantName"));
        approvedEmailTableColumn1.setCellValueFactory(new PropertyValueFactory<>("email"));
        approvedPhoneNumberTableColumn1.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        refreshApplicantsTable();
        refreshApprovedTable();

        applicantsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
            if (newV != null) {
                selectedVendor = newV;
                displayVendorDetails(newV);
            }
        });
    }

    private void refreshApplicantsTable() {
        applicantsTableView.setItems(DataManager.getInstance().getVendors());
    }

    private void refreshApprovedTable() {
        approvedApplicantsTableView1.setItems(DataManager.getInstance().getApprovedVendors());
    }

    private void displayVendorDetails(Vendor v) {
        if (v == null) return;
        StringBuilder sb = new StringBuilder();
        sb.append("Company: ").append(v.getOrganizationName()).append("\n");
        sb.append("Contact Person: ").append(v.getApplicantName()).append("\n");
        sb.append("Phone: ").append(v.getPhoneNumber()).append(" | Email: ").append(v.getEmail()).append("\n");
        sb.append("Current Status: ").append(v.getStatus()).append("\n");
        sb.append("Assigned Stall: ").append(v.getAssignedStallId()).append("\n\n");
        sb.append("--- PRODUCT DETAILS ---\n");
        sb.append(v.getProductDetails()).append("\n\n");
        sb.append("--- SPECIAL REQUESTS ---\n");
        sb.append(v.getSpecialRequest() != null && !v.getSpecialRequest().isEmpty() ? v.getSpecialRequest() : "No special requests specified.");

        productDetailesTextArea.setText(sb.toString());
    }

    @FXML
    public void creatFormBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/CreatVendorRegistrationForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Create Vendor Registration");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void viewApplicantsBTOnAction(ActionEvent actionEvent) {
        mainTab.getSelectionModel().select(viewApplicantsTab);
        refreshApplicantsTable();
    }

    @FXML
    public void showApplicantsBTOnAction(ActionEvent actionEvent) {
        refreshApplicantsTable();
    }

    @FXML
    public void showDetailesBTOnAction(ActionEvent actionEvent) {
        Vendor v = applicantsTableView.getSelectionModel().getSelectedItem();
        if (v != null) {
            selectedVendor = v;
            displayVendorDetails(v);
            mainTab.getSelectionModel().select(detailesTab);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText(null);
            alert.setContentText("Please select a vendor applicant from the table first.");
            alert.showAndWait();
        }
    }

    @FXML
    public void approveBTOnAction(ActionEvent actionEvent) {
        if (selectedVendor == null) {
            selectedVendor = applicantsTableView.getSelectionModel().getSelectedItem();
        }

        if (selectedVendor != null) {
            DataManager.getInstance().approveVendor(selectedVendor);
            displayVendorDetails(selectedVendor);
            refreshApplicantsTable();
            refreshApprovedTable();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vendor Approved");
            alert.setHeaderText(null);
            alert.setContentText("Vendor " + selectedVendor.getOrganizationName() + " has been approved! They are now eligible for stall assignment.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Required");
            alert.setHeaderText(null);
            alert.setContentText("Please select a vendor to approve.");
            alert.showAndWait();
        }
    }

    @FXML
    public void rejectBTOnAction(ActionEvent actionEvent) {
        if (selectedVendor == null) {
            selectedVendor = applicantsTableView.getSelectionModel().getSelectedItem();
        }

        if (selectedVendor != null) {
            DataManager.getInstance().rejectVendor(selectedVendor);
            displayVendorDetails(selectedVendor);
            refreshApplicantsTable();
            refreshApprovedTable();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Vendor Rejected");
            alert.setHeaderText(null);
            alert.setContentText("Vendor " + selectedVendor.getOrganizationName() + " has been marked as Rejected.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Selection Required");
            alert.setHeaderText(null);
            alert.setContentText("Please select a vendor to reject.");
            alert.showAndWait();
        }
    }

    @FXML
    public void viewRequestsBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/ViewRequestsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Vendor Special Requests");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void viewApprovedApplicantsBTOnAction(ActionEvent actionEvent) {
        refreshApprovedTable();
    }

    @FXML
    public void updateApprovedApBTOnAction(ActionEvent actionEvent) {
        refreshApprovedTable();
        refreshApplicantsTable();
    }

    @FXML
    public void viewApplicantsBackBTOnAction(ActionEvent actionEvent) {
        mainTab.getSelectionModel().select(vendorRegistrationTab);
    }

    @FXML
    public void detailesBackBTOnAction(ActionEvent actionEvent) {
        mainTab.getSelectionModel().select(viewApplicantsTab);
    }

    @FXML
    public void vendorRegBackBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/EventManagerDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Event Manager Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
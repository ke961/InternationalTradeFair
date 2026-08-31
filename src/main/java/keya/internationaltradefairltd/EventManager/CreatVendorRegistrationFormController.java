package keya.internationaltradefairltd.EventManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelloApplication;
import keya.internationaltradefairltd.HelperClass.DataManager;
import keya.internationaltradefairltd.HelperClass.Vendor;
import keya.internationaltradefairltd.User.User;

import java.io.IOException;

public class CreatVendorRegistrationFormController {
    @FXML
    private TextField organizationNameTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField userNmeTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private TextField phoneNumberTextField1;
    @FXML
    private TextArea productDetailesTextArea;
    @FXML
    private TextArea requestsTextArea;
    @FXML
    private ComboBox<String> countryComboBox;

    @FXML
    public void initialize() {
        if (countryComboBox != null) {
            countryComboBox.getItems().setAll("Bangladesh", "India", "China", "Japan", "Germany", "United States", "Turkey", "Thailand", "Malaysia", "Other");
            countryComboBox.setValue("Bangladesh");
        }
        organizationNameTextField.setPromptText("Organization / Business Name");
        firstNameTextField.setPromptText("Contact First Name");
        lastNameTextField.setPromptText("Contact Last Name");
        userNmeTextField.setPromptText("Vendor Username");
        passwordTextField.setPromptText("Password (at least 6 chars with number)");
        phoneNumberTextField1.setPromptText("Phone Number (11 digits)");
        productDetailesTextArea.setPromptText("Specify showcase items, product categories, and display specifications...");
        requestsTextArea.setPromptText("Special electrical, water, or corner booth requests (optional)...");
    }

    @FXML
    public void RegisterBTOnAction(ActionEvent actionEvent) {
        String orgName = organizationNameTextField.getText() != null ? organizationNameTextField.getText().trim() : "";
        String fName = firstNameTextField.getText() != null ? firstNameTextField.getText().trim() : "";
        String lName = lastNameTextField.getText() != null ? lastNameTextField.getText().trim() : "";
        String uName = userNmeTextField.getText() != null ? userNmeTextField.getText().trim() : "";
        String pass = passwordTextField.getText() != null ? passwordTextField.getText().trim() : "";
        String phone = phoneNumberTextField1.getText() != null ? phoneNumberTextField1.getText().trim() : "";
        String products = productDetailesTextArea.getText() != null ? productDetailesTextArea.getText().trim() : "";
        String requests = requestsTextArea.getText() != null ? requestsTextArea.getText().trim() : "";

        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (orgName.isEmpty() || fName.isEmpty() || lName.isEmpty() || uName.isEmpty() || pass.isEmpty() || phone.isEmpty() || products.isEmpty()) {
            alert.setTitle("Registration Error");
            alert.setHeaderText("Incomplete Form");
            alert.setContentText("Please fill out all mandatory fields marked with an asterisk (*).");
            alert.showAndWait();
            return;
        }

        if (uName.length() < 4) {
            alert.setTitle("Registration Error");
            alert.setHeaderText("Invalid Username");
            alert.setContentText("Username must be at least 4 characters.");
            alert.showAndWait();
            return;
        }

        if (pass.length() < 6 || !pass.matches(".*\\d.*")) {
            alert.setTitle("Registration Error");
            alert.setHeaderText("Invalid Password");
            alert.setContentText("Password must be at least 6 characters and contain at least one number.");
            alert.showAndWait();
            return;
        }

        if (phone.length() < 11) {
            alert.setTitle("Registration Error");
            alert.setHeaderText("Invalid Phone Number");
            alert.setContentText("Phone number must be at least 11 digits.");
            alert.showAndWait();
            return;
        }

        // Check if company or username already registered
        for (Vendor v : DataManager.getInstance().getVendors()) {
            if (v.getOrganizationName().equalsIgnoreCase(orgName)) {
                alert.setTitle("Registration Error");
                alert.setHeaderText("Company Already Registered");
                alert.setContentText("A vendor application for " + orgName + " already exists in the system.");
                alert.showAndWait();
                return;
            }
        }

        String email = uName + "@" + orgName.toLowerCase().replaceAll("[^a-z0-9]", "") + ".com";
        Vendor newVendor = new Vendor(orgName, fName, lName, uName, pass, phone, email, products, requests);
        DataManager.getInstance().getVendors().add(newVendor);

        // Also add to users list for login
        User u = new User(fName, lName, uName, pass, pass, phone, "Vendor");
        DataManager.getInstance().getUsers().add(u);

        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Submitted");
        alert.setHeaderText("Application Under Review");
        alert.setContentText("Vendor registration for " + orgName + " submitted successfully! Status: Pending Approval.");
        alert.showAndWait();

        organizationNameTextField.clear();
        firstNameTextField.clear();
        lastNameTextField.clear();
        userNmeTextField.clear();
        passwordTextField.clear();
        phoneNumberTextField1.clear();
        productDetailesTextArea.clear();
        requestsTextArea.clear();
    }

    @FXML
    public void loginOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void regDoneBTOnAction(ActionEvent actionEvent) throws IOException {
        regBackBTOnAction(actionEvent);
    }

    @FXML
    public void regBackBTOnAction(ActionEvent actionEvent) throws IOException {
        User u = DataManager.getInstance().getCurrentUser();
        String targetFxml = (u != null && "EventManager".equalsIgnoreCase(u.getUserType()))
                ? "EventManager/EventManagerDashboard.fxml"
                : "HomePageView.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(targetFxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Trade Fair Portal");
        stage.setScene(scene);
        stage.show();
    }
}
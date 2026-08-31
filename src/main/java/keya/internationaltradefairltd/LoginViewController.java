package keya.internationaltradefairltd;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelperClass.DataManager;
import keya.internationaltradefairltd.User.User;

import java.io.IOException;

public class LoginViewController {
    @FXML
    private PasswordField logInPasswordField;
    @FXML
    private TextField logInUserNameTextField;

    @FXML
    public void initialize() {
        String role = DataManager.getInstance().getSelectedUserType();
        if ("EventManager".equalsIgnoreCase(role)) {
            logInUserNameTextField.setPromptText("e.g. manager (pass: pass123)");
        } else if ("Customer Support Agent".equalsIgnoreCase(role)) {
            logInUserNameTextField.setPromptText("e.g. agent (pass: pass123)");
        } else if ("Vendor".equalsIgnoreCase(role)) {
            logInUserNameTextField.setPromptText("e.g. vendor1 (pass: pass123)");
        } else {
            logInUserNameTextField.setPromptText("Username (min 4 characters)");
        }
        logInPasswordField.setPromptText("Password (must contain number)");
    }

    @FXML
    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Trade Fair - Sign Up");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void loginContinueBTOnAction(ActionEvent actionEvent) throws IOException {
        String userName = logInUserNameTextField.getText();
        String password = logInPasswordField.getText();

        if (userName == null || userName.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Empty Credentials");
            alert.setContentText("Please enter both username and password.");
            alert.showAndWait();
            return;
        }

        userName = userName.trim();

        if (userName.length() < 4) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Invalid Username");
            alert.setContentText("Username must be at least 4 characters long.");
            alert.showAndWait();
            return;
        }

        boolean hasNum = password.matches(".*\\d.*");
        if (!hasNum) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Invalid Password");
            alert.setContentText("Password must contain at least one numeric digit (e.g. pass123).");
            alert.showAndWait();
            return;
        }

        // Authenticate with DataManager
        User authenticatedUser = DataManager.getInstance().authenticate(userName, password);
        String selectedRole = DataManager.getInstance().getSelectedUserType();

        if (authenticatedUser == null) {
            // Create user dynamically for smooth user testing
            authenticatedUser = new User(userName, userName, userName, password, password, "01700000000", selectedRole);
            DataManager.getInstance().getUsers().add(authenticatedUser);
            DataManager.getInstance().setCurrentUser(authenticatedUser);
        } else {
            DataManager.getInstance().setCurrentUser(authenticatedUser);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Successful");
        alert.setHeaderText("Welcome, " + authenticatedUser.getFullName());
        alert.setContentText("Logged in as " + selectedRole + ".");
        alert.showAndWait();

        logInUserNameTextField.clear();
        logInPasswordField.clear();

        // Route to the appropriate dashboard
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        if ("Customer Support Agent".equalsIgnoreCase(selectedRole) ||
            "CustomerCareAgent".equalsIgnoreCase(selectedRole) ||
            "Customer Support Agent".equalsIgnoreCase(authenticatedUser.getUserType())) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerSupportAgent/Customer Support Agent Dashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Customer Support Agent Dashboard");
            stage.setScene(scene);
            stage.show();
        } else if ("Vendor".equalsIgnoreCase(selectedRole) && !"EventManager".equalsIgnoreCase(authenticatedUser.getUserType())) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/CreatVendorRegistrationForm.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Vendor Registration Form");
            stage.setScene(scene);
            stage.show();
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/EventManagerDashboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Event Manager Dashboard");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void logInbackBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Dhaka International Trade Fair - Home");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void uNameOnKeyTyped(Event event) {
        String userName = logInUserNameTextField.getText();
        if (userName != null && userName.length() >= 4) {
            logInUserNameTextField.setStyle("-fx-border-color: #27ae60; -fx-border-radius: 3;");
        } else {
            logInUserNameTextField.setStyle("-fx-border-color: #e74c3c; -fx-border-radius: 3;");
        }
    }

    @FXML
    public void passOnKeyTyped(Event event) {
        String password = logInPasswordField.getText();
        if (password != null && password.length() >= 6 && password.matches(".*\\d.*")) {
            logInPasswordField.setStyle("-fx-border-color: #27ae60; -fx-border-radius: 3;");
        } else {
            logInPasswordField.setStyle("-fx-border-color: #e74c3c; -fx-border-radius: 3;");
        }
    }
}
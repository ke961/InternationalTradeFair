package keya.internationaltradefairltd;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelperClass.DataManager;
import keya.internationaltradefairltd.User.User;

import java.io.IOException;

public class SignUpController {
    @FXML
    private TextField signUpPhoneNumberTextField;
    @FXML
    private TextField signUpFirstNameTextField;
    @FXML
    private TextField signUpUserNameTextField;
    @FXML
    private TextField signUpPasswordTextField;
    @FXML
    private TextField signUpLastNameTextField;
    @FXML
    private TextField signUpConfirmPasswordTextField;

    private boolean hasUpdateusername = false;

    @FXML
    public void initialize() {
        signUpFirstNameTextField.setPromptText("First Name");
        signUpLastNameTextField.setPromptText("Last Name");
        signUpUserNameTextField.setPromptText("Username (min 4 chars)");
        signUpPhoneNumberTextField.setPromptText("Phone Number (11 digits)");
        signUpPasswordTextField.setPromptText("Password (min 6 chars, with number)");
        signUpConfirmPasswordTextField.setPromptText("Confirm Password");
    }

    @FXML
    public void logInBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void creatAccountBTOnAction(ActionEvent actionEvent) throws IOException {
        String firstName = signUpFirstNameTextField.getText() != null ? signUpFirstNameTextField.getText().trim() : "";
        String lastName = signUpLastNameTextField.getText() != null ? signUpLastNameTextField.getText().trim() : "";
        String userName = signUpUserNameTextField.getText() != null ? signUpUserNameTextField.getText().trim() : "";
        String password = signUpPasswordTextField.getText() != null ? signUpPasswordTextField.getText() : "";
        String confirmPassword = signUpConfirmPasswordTextField.getText() != null ? signUpConfirmPasswordTextField.getText() : "";
        String phoneNumber = signUpPhoneNumberTextField.getText() != null ? signUpPhoneNumberTextField.getText().trim() : "";

        Alert alert = new Alert(Alert.AlertType.ERROR);

        if (firstName.isEmpty() || lastName.isEmpty() || userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || phoneNumber.isEmpty()) {
            alert.setTitle("Registration Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please fill out all required fields.");
            alert.showAndWait();
            return;
        }

        if (userName.length() < 4) {
            alert.setTitle("Registration Error");
            alert.setHeaderText("Invalid Username");
            alert.setContentText("Username must be at least 4 characters long.");
            alert.showAndWait();
            return;
        }

        if (password.length() < 6 || !password.matches(".*\\d.*")) {
            alert.setTitle("Registration Error");
            alert.setHeaderText("Weak Password");
            alert.setContentText("Password must be at least 6 characters and contain at least one numeric digit.");
            alert.showAndWait();
            return;
        }

        if (!password.equals(confirmPassword)) {
            alert.setTitle("Registration Error");
            alert.setHeaderText("Password Mismatch");
            alert.setContentText("Password and Confirm Password do not match.");
            alert.showAndWait();
            return;
        }

        if (phoneNumber.length() < 11 || !phoneNumber.matches("\\d+")) {
            alert.setTitle("Registration Error");
            alert.setHeaderText("Invalid Phone Number");
            alert.setContentText("Phone number must be at least 11 digits.");
            alert.showAndWait();
            return;
        }

        // Check if username already exists
        for (User u : DataManager.getInstance().getUsers()) {
            if (u.getUserName() != null && u.getUserName().equalsIgnoreCase(userName)) {
                alert.setTitle("Registration Error");
                alert.setHeaderText("User Already Exists");
                alert.setContentText("A user with this username already exists. Please choose a different one.");
                alert.showAndWait();
                return;
            }
        }

        String userType = DataManager.getInstance().getSelectedUserType();
        User newUser = new User(firstName, lastName, userName, password, confirmPassword, phoneNumber, userType);
        DataManager.getInstance().getUsers().add(newUser);
        DataManager.getInstance().setCurrentUser(newUser);

        signUpFirstNameTextField.clear();
        signUpLastNameTextField.clear();
        signUpUserNameTextField.clear();
        signUpPasswordTextField.clear();
        signUpConfirmPasswordTextField.clear();
        signUpPhoneNumberTextField.clear();

        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Registration Successful");
        successAlert.setHeaderText("Welcome, " + firstName + " " + lastName);
        successAlert.setContentText("Your account has been created successfully! Redirecting to login...");
        successAlert.showAndWait();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signUpbackBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Dhaka International Trade Fair - Home");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void passOnKeyTyped(Event event) {
        String password = signUpPasswordTextField.getText();
        if (password != null && password.length() >= 6 && password.matches(".*\\d.*")) {
            signUpPasswordTextField.setStyle("-fx-border-color: #27ae60; -fx-border-radius: 3;");
        } else {
            signUpPasswordTextField.setStyle("-fx-border-color: #e74c3c; -fx-border-radius: 3;");
        }
    }

    @FXML
    public void userNameOnKeyTyped(Event event) {
        hasUpdateusername = true;
        String userName = signUpUserNameTextField.getText();
        if (userName != null && userName.length() >= 4) {
            signUpUserNameTextField.setStyle("-fx-border-color: #27ae60; -fx-border-radius: 3;");
        } else {
            signUpUserNameTextField.setStyle("-fx-border-color: #e74c3c; -fx-border-radius: 3;");
        }
    }

    @FXML
    public void conPassOnKeyTyped(Event event) {
        String pass = signUpPasswordTextField.getText();
        String conPass = signUpConfirmPasswordTextField.getText();
        if (conPass != null && !conPass.isEmpty() && conPass.equals(pass)) {
            signUpConfirmPasswordTextField.setStyle("-fx-border-color: #27ae60; -fx-border-radius: 3;");
        } else {
            signUpConfirmPasswordTextField.setStyle("-fx-border-color: #e74c3c; -fx-border-radius: 3;");
        }
    }

    @FXML
    public void phoneNumberOnKeyTyped(Event event) {
        String phoneNumber = signUpPhoneNumberTextField.getText();
        if (phoneNumber != null && phoneNumber.length() >= 11 && phoneNumber.matches("\\d+")) {
            signUpPhoneNumberTextField.setStyle("-fx-border-color: #27ae60; -fx-border-radius: 3;");
        } else {
            signUpPhoneNumberTextField.setStyle("-fx-border-color: #e74c3c; -fx-border-radius: 3;");
        }
    }

    @FXML
    public void firstNameOnKeyTyped(Event event) {
        if (hasUpdateusername) {
            return;
        }
        String first = signUpFirstNameTextField.getText();
        if (first != null) {
            String autoUser = first.toLowerCase().replaceAll("[^a-z0-9]", "");
            signUpUserNameTextField.setText(autoUser);
        }
    }
}

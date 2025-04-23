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
import keya.internationaltradefairltd.User.User;

import java.io.IOException;
import java.util.ArrayList;

public class SignUpController
{

    @javafx.fxml.FXML
    private TextField signUpPhoneNumberTextField;
    @javafx.fxml.FXML
    private TextField signUpFirstNameTextField;
    @javafx.fxml.FXML
    private TextField signUpUserNameTextField;
    @javafx.fxml.FXML
    private TextField signUpPasswordTextField;
    @javafx.fxml.FXML
    private TextField signUpLastNameTextField;
    @javafx.fxml.FXML
    private TextField signUpConfirmPasswordTextField;
    ArrayList<User> user ;

    boolean hasUpdateusername = false;  /// to suggest first name on user text field




    @javafx.fxml.FXML
    public void initialize() {
        user = new ArrayList<User>();

    }

    @javafx.fxml.FXML
    public void logInBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Log In!");
        stage.setScene(scene);
        stage.show();
    }

    @Deprecated
    public void backBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void creatAccountBTOnAction(ActionEvent actionEvent) throws IOException {
        String userName = signUpUserNameTextField.getText();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        String[] Alphabets={"A","B","C","D","E","F","a","b","c","d","e","f"};
        boolean hasAlphabet=false;
        for(String alphabet: Alphabets){
            if(userName . contains(alphabet)){
                hasAlphabet=true;

                break;
            }
        }
        if(!hasAlphabet){

            alert.setTitle("Error");
            alert.setHeaderText("Invalid Username ");
            alert.setContentText("Please enter a valid username and your username must contain a Alphabet ");
            alert.showAndWait();
            return;
        }
        System.out.println(hasAlphabet);


        String password = signUpPasswordTextField.getText();
        String[] num={"1","2","3","4","5","6","7","8","9","0"};


        boolean hasNum=false;
        for(String num1 : num ) {
            if (password.contains(num1)) {
                hasNum = true;

                break;
            }
        }
        System.out.println(hasNum);

        if (!hasNum){

            alert.setTitle("Error");
            alert.setHeaderText("Incorrect Password");
            alert.setContentText("Please enter a valid password and your password must contain a numeric ");
            alert.showAndWait();
            return;

        }

        String firstName = signUpFirstNameTextField.getText();
        String lastName = signUpLastNameTextField.getText();
        String confirmPassword = signUpConfirmPasswordTextField.getText();
        boolean is_validCred=this.validatePasswordAndConfirmPassword(password,confirmPassword);
        if(!is_validCred ){
            alert.setTitle("Error");
            alert.setHeaderText("Your Password and Confirm Password do not match");
            alert.showAndWait();
            return;
        }
        System.out.println("Passwords match! Proceeding...");
        String phoneNumber = signUpPhoneNumberTextField.getText();
        int phoneNumber_length=phoneNumber.length();
        String[] N= {"0", "1", "2","3","4","5","6","7","8","9"};

        boolean hasN=false;
        for(String Num : N ) {
            if (phoneNumber.contains(Num)||phoneNumber_length<11) {
                hasN=true;
                break;
            }

        }
        if(!hasN){
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Phone Number");
            alert.setHeaderText( "Please enter a valid phone number ");
            alert.setContentText("your phone number must contain a numeric and it must be 11 digits");
            alert.showAndWait();
        }

        for (User user : user) {    /// this line is to check if there is any same user is there or not
            if (user.getUserName().equals(userName)||user.getPassword().equals(password)) {
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Username and Password");
                alert.setContentText(" This Username and password is Already Exist");
                alert.showAndWait();
                break;
            }
        }
        User new_user= new User(userName,password,firstName,lastName,confirmPassword,phoneNumber);/// if there is not then this line will execute
        user.add(new_user);




        signUpFirstNameTextField.clear();
        signUpLastNameTextField.clear();
        signUpUserNameTextField.clear();
        signUpConfirmPasswordTextField.clear();
        signUpConfirmPasswordTextField.clear();
        signUpPhoneNumberTextField.clear();

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successfully");
        alert.setHeaderText("Welcome ,"  +firstName + " " + lastName);
        alert.showAndWait();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Log In!");
        stage.setScene(scene);
        stage.show();













    }

    private boolean validatePasswordAndConfirmPassword(String password, String confirmPassword) {
        if(password==null || confirmPassword==null){
            return false;
        }
        if(!password.equals(confirmPassword)){
            return false;

        }

        return true;
    }



    @javafx.fxml.FXML
    public void signUpbackBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Home Page!");
        stage.setScene(scene);
        stage.show();
    }

    @javafx.fxml.FXML
    public void passOnKeyTyped(Event event) {
        System.out.println("passwordOnKeyTyped");
        String password = signUpPasswordTextField.getText();
        int password_Length = password.length();
        if(password_Length < 6) {
            signUpPasswordTextField.setStyle("-fx-border-color: red");
        }
        else {
            signUpPasswordTextField.setStyle("-fx-border-color: green");
        }
    }


    @javafx.fxml.FXML
    public void userNameOnKeyTyped(Event event) {
        hasUpdateusername=true;   /// to suggest first name on user text field

        System.out.println("userNameOnKeyTyped");
        String userName = signUpUserNameTextField.getText();
        int userName_Length = userName.length();
        if(userName_Length < 4) {
            signUpUserNameTextField.setStyle("-fx-border-color: red");
        }
        else {
            signUpUserNameTextField.setStyle("-fx-border-color: green");
        }
    }

    @javafx.fxml.FXML
    public void conPassOnKeyTyped(Event event) {
        System.out.println("confirmPasswordOnKeyTyped");
        String password = signUpConfirmPasswordTextField.getText();
        int confirmPasswordLength = password.length();
        if(confirmPasswordLength < 6) {
            signUpConfirmPasswordTextField.setStyle("-fx-border-color: red");

        }
        else {
            signUpConfirmPasswordTextField.setStyle("-fx-border-color: green");
        }

    }

    @javafx.fxml.FXML
    public void phoneNumberOnKeyTyped(Event event) {
        System.out.println("phoneNumberOnKeyTyped");
        String phoneNumber = signUpPhoneNumberTextField.getText();
        int phoneNumber_Length = phoneNumber.length();
        if(phoneNumber_Length < 11) {
            signUpPhoneNumberTextField.setStyle("-fx-border-color: red");

        }
        else {
            signUpPhoneNumberTextField.setStyle("-fx-border-color: green");
        }

    }

    @javafx.fxml.FXML
    public void firstNameOnKeyTyped(Event event) {
        if(hasUpdateusername){
            return; //early return
        }

        String Creat_username = signUpFirstNameTextField.getText()
                .toLowerCase()
                .replace(" ", "");

        signUpUserNameTextField.setText(Creat_username);     ///to suggest first name in username


    }
}


package keya.internationaltradefairltd;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import keya.internationaltradefairltd.User.EventManager;
import keya.internationaltradefairltd.User.User;

import java.io.IOException;
import java.util.ArrayList;

public class LoginViewController
{
    @javafx.fxml.FXML
    private PasswordField logInPasswordField;
    @javafx.fxml.FXML
    private TextField logInUserNameTextField;
    ArrayList<User> users; /// To save in database



    @javafx.fxml.FXML
    public void initialize() {
        users = new ArrayList<User>();


    }

    @javafx.fxml.FXML
    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Sign Up!");
        stage.setScene(scene);
        stage.show();

    }






    @javafx.fxml.FXML
    public void loginContinueBTOnAction(ActionEvent actionEvent) throws IOException {
        String userName = logInUserNameTextField.getText();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        int username_length=userName.length();  //for show an error
        String[] Alphabets={"A","B","C","D","E","F","a","b","c","d","e","f"};
        boolean hasAlphabet=false;
        for(String alphabet: Alphabets){
            if(userName . contains(alphabet)){
                hasAlphabet=true;

                break;
            }
        }
        if(username_length <4||!hasAlphabet) {

            alert.setContentText("Username must be at least 4 characters long and it must contain Alphabet.");
            alert.setHeaderText("Invalid Username.");
            alert.showAndWait();
            return;
        }
        String password = logInPasswordField.getText();
        String[] num={"1","2","3","4","5","6","7","8","9","0"};
        boolean hasNum=false;
        for(String num1 : num ){
            if(password.contains(num1)){
                hasNum=true;
                break;
            }
        }

        System.out.println(hasNum);

        if(!hasNum){

            alert.setTitle("Error");
            alert.setHeaderText("Incorrect Password");
            alert.setContentText("Please enter a valid password and your password must contain a numeric ");
            alert.showAndWait();
            return;
        }

//        User Creat_user= null; ///Find user( any registered user is there or not)
//        for(User user: users){
//            if(user.getUserName().equals(userName)||user.getPassword().equals(password)){
//                Creat_user=user;
//                break;
//            }
//        }
//        if (Creat_user == null) { ///if  not then this line will execute
//            alert.setTitle("Error");
//            alert.setHeaderText("Username or Password  is Incorrect");
//            alert.showAndWait();
//            return;
//        }


        logInUserNameTextField.clear();
        logInPasswordField.clear();
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Login Successfully");
        alert.setHeaderText("Welcome,"+userName);
        alert.showAndWait();






        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/EventManagerDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Event Manager Dashboard!");
        stage.setScene(scene);
        stage.show();


    }



    @javafx.fxml.FXML
    public void logInbackBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Event Manager Dashboard!");
        stage.setScene(scene);
        stage.show();
    }





    @javafx.fxml.FXML
    public void uNameOnKeyTyped(Event event) {
        System.out.println("userNameOnKeyTyped");
        String userName = logInUserNameTextField.getText();
        int userName_Length = userName.length();
        if(userName_Length < 4) {
            logInUserNameTextField.setStyle("-fx-border-color: red");
        }
        else {
            logInUserNameTextField.setStyle("-fx-border-color: green");
        }
    }

    @javafx.fxml.FXML
    public void passOnKeyTyped(Event event) {
        System.out.println("passwordOnKeyTyped");
        String password = logInPasswordField.getText();
        int password_Length = password.length();
        if(password_Length < 6) {
            logInPasswordField.setStyle("-fx-border-color: red");
        }
        else {
            logInPasswordField.setStyle("-fx-border-color: green");
        }
    }
}
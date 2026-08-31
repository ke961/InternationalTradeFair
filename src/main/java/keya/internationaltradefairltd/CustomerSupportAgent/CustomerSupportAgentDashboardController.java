package keya.internationaltradefairltd.CustomerSupportAgent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelloApplication;
import keya.internationaltradefairltd.HelperClass.DataManager;
import keya.internationaltradefairltd.User.User;

import java.io.IOException;

public class CustomerSupportAgentDashboardController {
    @FXML
    private Label userNameLabel;

    @FXML
    public void initialize() {
        User u = DataManager.getInstance().getCurrentUser();
        if (u != null) {
            userNameLabel.setText("Welcome, " + u.getFullName() + " (Support Agent)");
        } else {
            userNameLabel.setText("Welcome, Customer Care Representative");
        }
    }

    @FXML
    public void customerQueriesButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerSupportAgent/Quaries.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Customer Queries Management");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void resolveIssuesButtonOnAction(ActionEvent actionEvent) throws IOException {
        customerQueriesButtonOnAction(actionEvent);
    }

    @FXML
    public void knowledgeBaseButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerSupportAgent/KnowledgeBase.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Knowledge Base & FAQ");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void faqButtonOnAction(ActionEvent actionEvent) throws IOException {
        knowledgeBaseButtonOnAction(actionEvent);
    }

    @FXML
    public void summaryButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerSupportAgent/Summary.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Trade Fair Summary & Statistics");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void signOutButtonOnAction(ActionEvent actionEvent) throws IOException {
        DataManager.getInstance().setCurrentUser(null);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Loginview.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void agentBackButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("HomePageView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Dhaka International Trade Fair - Home");
        stage.setScene(scene);
        stage.show();
    }
}
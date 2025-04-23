package keya.internationaltradefairltd.EventManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelloApplication;

import java.io.IOException;

public class VendorRegistrationController
{
    @javafx.fxml.FXML
    private TableColumn approvedPhoneNumberTableColumn1;
    @javafx.fxml.FXML
    private TableView applicantsTableView;
    @javafx.fxml.FXML
    private TableColumn companyTableColumn;
    @javafx.fxml.FXML
    private TableColumn phoneNumberTableColumn;
    @javafx.fxml.FXML
    private TableView approvedApplicantsTableView1;
    @javafx.fxml.FXML
    private TableColumn approvedEmailTableColumn1;
    @javafx.fxml.FXML
    private Tab vendorRegistrationTab;
    @javafx.fxml.FXML
    private TableColumn approvedApplicantsNameTableColumn1;
    @javafx.fxml.FXML
    private TableColumn emailTableColumn;
    @javafx.fxml.FXML
    private TabPane mainTab;
    @javafx.fxml.FXML
    private Tab detailesTab;
    @javafx.fxml.FXML
    private TextArea productDetailesTextArea;
    @javafx.fxml.FXML
    private TableColumn approvedCompanyTableColumn1;
    @javafx.fxml.FXML
    private TableColumn applicantsNameTableColumn;
    @javafx.fxml.FXML
    private Tab viewApplicantsTab;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void viewApplicantsBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewRequestsBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void updateApprovedApBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void rejectBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void showApplicantsBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void creatFormBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewApplicantsBackBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void showDetailesBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void detailesBackBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void approveBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void viewApprovedApplicantsBTOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void vendorRegBackBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/EventManagerDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Event Manager Dashboard!");
        stage.setScene(scene);
        stage.show();
    }
}
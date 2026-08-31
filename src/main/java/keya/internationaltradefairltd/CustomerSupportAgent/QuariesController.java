package keya.internationaltradefairltd.CustomerSupportAgent;

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
import keya.internationaltradefairltd.HelperClass.CustomerQuery;
import keya.internationaltradefairltd.HelperClass.DataManager;

import java.io.IOException;
import java.time.LocalDate;

public class QuariesController {
    // Filter controls
    @FXML
    private ComboBox<String> categoryFilterComboBox;
    @FXML
    private ComboBox<String> statusFilterComboBox;

    // Table controls
    @FXML
    private TableView<CustomerQuery> queriesTableView;
    @FXML
    private TableColumn<CustomerQuery, String> queryIdColumn;
    @FXML
    private TableColumn<CustomerQuery, String> customerNameColumn;
    @FXML
    private TableColumn<CustomerQuery, String> categoryColumn;
    @FXML
    private TableColumn<CustomerQuery, String> subjectColumn;
    @FXML
    private TableColumn<CustomerQuery, String> statusColumn;
    @FXML
    private TableColumn<CustomerQuery, LocalDate> dateColumn;

    // Selected Query Details
    @FXML
    private Label selectedQueryHeaderLabel;
    @FXML
    private TextArea queryDetailsTextArea;
    @FXML
    private TextArea resolutionNoteTextArea;

    // New Query Logging
    @FXML
    private TextField newCustomerNameTextField;
    @FXML
    private TextField newCustomerEmailTextField;
    @FXML
    private ComboBox<String> newCategoryComboBox;
    @FXML
    private TextField newSubjectTextField;

    private CustomerQuery selectedQuery;

    @FXML
    public void initialize() {
        // Setup filter comboboxes
        categoryFilterComboBox.getItems().setAll("All", "Ticketing", "Stall Inquiry", "Lost & Found", "Facilities", "Security", "General");
        categoryFilterComboBox.setValue("All");

        statusFilterComboBox.getItems().setAll("All", "Open", "In Progress", "Resolved");
        statusFilterComboBox.setValue("All");

        newCategoryComboBox.getItems().setAll("Ticketing", "Stall Inquiry", "Lost & Found", "Facilities", "Security", "General");
        newCategoryComboBox.setValue("Ticketing");

        // Setup columns
        queryIdColumn.setCellValueFactory(new PropertyValueFactory<>("queryId"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<>("subject"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        refreshQueriesTable();

        // Listen for table selection
        queriesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedQuery = newVal;
                displaySelectedQuery(newVal);
            }
        });

        // Filter listeners
        categoryFilterComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> applyFilters());
        statusFilterComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> applyFilters());
    }

    private void refreshQueriesTable() {
        queriesTableView.setItems(DataManager.getInstance().getCustomerQueries());
    }

    private void applyFilters() {
        String cat = categoryFilterComboBox.getValue();
        String stat = statusFilterComboBox.getValue();

        ObservableList<CustomerQuery> filtered = FXCollections.observableArrayList();
        for (CustomerQuery q : DataManager.getInstance().getCustomerQueries()) {
            boolean matchesCat = (cat == null || "All".equalsIgnoreCase(cat) || q.getCategory().equalsIgnoreCase(cat));
            boolean matchesStat = (stat == null || "All".equalsIgnoreCase(stat) || q.getStatus().equalsIgnoreCase(stat));
            if (matchesCat && matchesStat) {
                filtered.add(q);
            }
        }
        queriesTableView.setItems(filtered);
    }

    private void displaySelectedQuery(CustomerQuery q) {
        if (q != null) {
            selectedQueryHeaderLabel.setText("[" + q.getQueryId() + "] " + q.getCustomerName() + " (" + q.getCustomerEmail() + ") | Category: " + q.getCategory() + " | Status: " + q.getStatus());
            queryDetailsTextArea.setText("SUBJECT: " + q.getSubject() + "\n\nDETAILS:\n" + q.getDetails());
            resolutionNoteTextArea.setText(q.getResolutionNote() != null ? q.getResolutionNote() : "");
        } else {
            selectedQueryHeaderLabel.setText("Select a query from the table above");
            queryDetailsTextArea.clear();
            resolutionNoteTextArea.clear();
        }
    }

    @FXML
    public void resetFiltersBTOnAction(ActionEvent actionEvent) {
        categoryFilterComboBox.setValue("All");
        statusFilterComboBox.setValue("All");
        refreshQueriesTable();
    }

    @FXML
    public void markInProgressBTOnAction(ActionEvent actionEvent) {
        if (selectedQuery != null) {
            selectedQuery.setStatus("In Progress");
            selectedQuery.setResolutionNote(resolutionNoteTextArea.getText());
            displaySelectedQuery(selectedQuery);
            queriesTableView.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ticket Updated");
            alert.setHeaderText(null);
            alert.setContentText("Query " + selectedQuery.getQueryId() + " marked as IN PROGRESS.");
            alert.showAndWait();
        } else {
            showNoSelectionWarning();
        }
    }

    @FXML
    public void markResolvedBTOnAction(ActionEvent actionEvent) {
        if (selectedQuery != null) {
            String note = resolutionNoteTextArea.getText();
            if (note == null || note.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Resolution Note Required");
                alert.setHeaderText("Empty Resolution");
                alert.setContentText("Please provide a response / resolution note before closing this ticket.");
                alert.showAndWait();
                return;
            }

            selectedQuery.setStatus("Resolved");
            selectedQuery.setResolutionNote(note.trim());
            displaySelectedQuery(selectedQuery);
            queriesTableView.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ticket Resolved");
            alert.setHeaderText("Issue Closed");
            alert.setContentText("Query " + selectedQuery.getQueryId() + " resolved and resolution note logged.");
            alert.showAndWait();
        } else {
            showNoSelectionWarning();
        }
    }

    @FXML
    public void reopenQueryBTOnAction(ActionEvent actionEvent) {
        if (selectedQuery != null) {
            selectedQuery.setStatus("Open");
            displaySelectedQuery(selectedQuery);
            queriesTableView.refresh();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ticket Reopened");
            alert.setHeaderText(null);
            alert.setContentText("Query " + selectedQuery.getQueryId() + " has been reopened.");
            alert.showAndWait();
        } else {
            showNoSelectionWarning();
        }
    }

    @FXML
    public void logNewQueryBTOnAction(ActionEvent actionEvent) {
        String name = newCustomerNameTextField.getText() != null ? newCustomerNameTextField.getText().trim() : "";
        String email = newCustomerEmailTextField.getText() != null ? newCustomerEmailTextField.getText().trim() : "";
        String cat = newCategoryComboBox.getValue();
        String subj = newSubjectTextField.getText() != null ? newSubjectTextField.getText().trim() : "";

        if (name.isEmpty() || email.isEmpty() || cat == null || subj.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Fields");
            alert.setContentText("Please enter customer name, email, category, and subject.");
            alert.showAndWait();
            return;
        }

        int nextNum = 1000 + DataManager.getInstance().getCustomerQueries().size() + 1;
        String queryId = "QRY-" + nextNum;

        CustomerQuery newQ = new CustomerQuery(queryId, name, email, cat, subj, "Customer submitted ticket via Help Desk: " + subj);
        DataManager.getInstance().getCustomerQueries().add(0, newQ);

        newCustomerNameTextField.clear();
        newCustomerEmailTextField.clear();
        newSubjectTextField.clear();

        refreshQueriesTable();
        queriesTableView.getSelectionModel().select(newQ);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ticket Logged");
        alert.setHeaderText("Query Created Successfully");
        alert.setContentText("New Ticket ID: " + queryId + " assigned.");
        alert.showAndWait();
    }

    private void showNoSelectionWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Query Selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a query from the table first.");
        alert.showAndWait();
    }

    @FXML
    public void backBTOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CustomerSupportAgent/Customer Support Agent Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Customer Support Agent Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
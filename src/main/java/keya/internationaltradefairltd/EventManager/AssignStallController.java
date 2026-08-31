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
import keya.internationaltradefairltd.HelperClass.Stall;
import keya.internationaltradefairltd.HelperClass.Vendor;

import java.io.IOException;

public class AssignStallController {
    @FXML
    private TabPane MainTab;
    @FXML
    private Tab assignStallTab;
    @FXML
    private Tab createDetailsTab;
    @FXML
    private Tab availableStallsTab;

    // Create & Details tab controls
    @FXML
    private TextField stallIDTextField;
    @FXML
    private TextField stallLocationTextField;
    @FXML
    private TextField stallSizeTextField;
    @FXML
    private TextField rentTextField;
    @FXML
    private ComboBox<String> stallIdComboBox;
    @FXML
    private TableView<Stall> stallsTableView;
    @FXML
    private TableColumn<Stall, String> stallIDTableColumn;
    @FXML
    private TableColumn<Stall, String> stallSizeTableColumn;
    @FXML
    private TableColumn<Stall, String> stallLocationTableColumn;
    @FXML
    private TableColumn<Stall, Double> rentTableColumn;

    // Available Stalls tab controls
    @FXML
    private TableView<Stall> availableStallTableView;
    @FXML
    private TableColumn<Stall, String> availableStallIdTavleColumn;
    @FXML
    private TableColumn<Stall, String> availStallSizeTableColumn;
    @FXML
    private TableColumn<Stall, String> availableStallSizeTableColumn;
    @FXML
    private TableColumn<Stall, Double> availavleRentTableColumn;

    // Approved Company Table
    @FXML
    private TableView<Vendor> approvedCompanyTableView;
    @FXML
    private TableColumn<Vendor, String> showApprovedCompantTableColumn;

    // Assignment controls & table
    @FXML
    private TextField enterCompanyNameTextField;
    @FXML
    private TextField enterStallIDTextField;
    @FXML
    private TableView<Stall> assignStallTableView;
    @FXML
    private TableColumn<Stall, String> assignCompTableColumn;
    @FXML
    private TableColumn<Stall, String> assignstallIDTableColumn;
    @FXML
    private TableColumn<Stall, String> assignStallSizeTableColumn;
    @FXML
    private TableColumn<Stall, String> assignStallLocationTableColumn;
    @FXML
    private TableColumn<Stall, Double> assignRentTableColumn;

    @FXML
    public void initialize() {
        // Setup Table Columns for All Stalls
        stallIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("stallId"));
        stallSizeTableColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        stallLocationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        rentTableColumn.setCellValueFactory(new PropertyValueFactory<>("rent"));

        // Setup Table Columns for Available Stalls
        availableStallIdTavleColumn.setCellValueFactory(new PropertyValueFactory<>("stallId"));
        availStallSizeTableColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        availableStallSizeTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        availavleRentTableColumn.setCellValueFactory(new PropertyValueFactory<>("rent"));

        // Setup Table Columns for Approved Companies
        showApprovedCompantTableColumn.setCellValueFactory(new PropertyValueFactory<>("organizationName"));

        // Setup Table Columns for Assigned Stalls
        assignCompTableColumn.setCellValueFactory(new PropertyValueFactory<>("assignedCompany"));
        assignstallIDTableColumn.setCellValueFactory(new PropertyValueFactory<>("stallId"));
        assignStallSizeTableColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
        assignStallLocationTableColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        assignRentTableColumn.setCellValueFactory(new PropertyValueFactory<>("rent"));

        // Load data
        refreshAllStallsTable();
        refreshAvailableStallsTable();
        refreshApprovedVendorsTable();
        refreshAssignedStallsTable();
        refreshStallIdComboBox();

        // Listen for table selections to auto-fill assignment inputs
        if (availableStallTableView != null) {
            availableStallTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
                if (newV != null) {
                    enterStallIDTextField.setText(newV.getStallId());
                }
            });
        }
        if (approvedCompanyTableView != null) {
            approvedCompanyTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldV, newV) -> {
                if (newV != null) {
                    enterCompanyNameTextField.setText(newV.getOrganizationName());
                }
            });
        }
    }

    private void refreshAllStallsTable() {
        stallsTableView.setItems(DataManager.getInstance().getStalls());
    }

    private void refreshAvailableStallsTable() {
        availableStallTableView.setItems(DataManager.getInstance().getAvailableStalls());
    }

    private void refreshApprovedVendorsTable() {
        approvedCompanyTableView.setItems(DataManager.getInstance().getApprovedVendors());
    }

    private void refreshAssignedStallsTable() {
        ObservableList<Stall> assigned = FXCollections.observableArrayList();
        for (Stall s : DataManager.getInstance().getStalls()) {
            if (!s.isAvailable()) {
                assigned.add(s);
            }
        }
        assignStallTableView.setItems(assigned);
    }

    private void refreshStallIdComboBox() {
        ObservableList<String> ids = FXCollections.observableArrayList();
        ids.add("All Stalls");
        for (Stall s : DataManager.getInstance().getStalls()) {
            ids.add(s.getStallId());
        }
        stallIdComboBox.setItems(ids);
        if (!ids.isEmpty()) {
            stallIdComboBox.setValue(ids.get(0));
        }
    }

    @FXML
    public void createStallBTOnAction(ActionEvent actionEvent) {
        String id = stallIDTextField.getText() != null ? stallIDTextField.getText().trim() : "";
        String location = stallLocationTextField.getText() != null ? stallLocationTextField.getText().trim() : "";
        String sizeStr = stallSizeTextField.getText() != null ? stallSizeTextField.getText().trim() : "";
        String rentStr = rentTextField.getText() != null ? rentTextField.getText().trim() : "";

        if (id.isEmpty() || location.isEmpty() || sizeStr.isEmpty() || rentStr.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Fields");
            alert.setContentText("Please fill out Stall ID, Location, Size, and Rent.");
            alert.showAndWait();
            return;
        }

        // Check if stall ID already exists
        for (Stall s : DataManager.getInstance().getStalls()) {
            if (s.getStallId().equalsIgnoreCase(id)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Duplicate ID");
                alert.setHeaderText("Stall ID Already Exists");
                alert.setContentText("A stall with ID " + id + " already exists in the system.");
                alert.showAndWait();
                return;
            }
        }

        double rent;
        try {
            rent = Double.parseDouble(rentStr);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText("Invalid Rent Format");
            alert.setContentText("Rent must be a valid numeric amount (e.g. 60000).");
            alert.showAndWait();
            return;
        }

        Stall newStall = new Stall(id, sizeStr, location, rent);
        DataManager.getInstance().getStalls().add(newStall);

        refreshAllStallsTable();
        refreshAvailableStallsTable();
        refreshStallIdComboBox();

        stallIDTextField.clear();
        stallLocationTextField.clear();
        stallSizeTextField.clear();
        rentTextField.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Stall Created");
        alert.setContentText("Stall " + id + " created and marked as available.");
        alert.showAndWait();
    }

    @FXML
    public void showDTBTOnAction(ActionEvent actionEvent) {
        String selected = stallIdComboBox.getValue();
        if (selected == null || "All Stalls".equalsIgnoreCase(selected)) {
            stallsTableView.setItems(DataManager.getInstance().getStalls());
        } else {
            ObservableList<Stall> match = FXCollections.observableArrayList();
            for (Stall s : DataManager.getInstance().getStalls()) {
                if (s.getStallId().equalsIgnoreCase(selected)) {
                    match.add(s);
                }
            }
            stallsTableView.setItems(match);
        }
    }

    @FXML
    public void availableStallBTOnAction(ActionEvent actionEvent) {
        refreshAvailableStallsTable();
    }

    @FXML
    public void showCompanyBTOnAction(ActionEvent actionEvent) {
        refreshApprovedVendorsTable();
    }

    @FXML
    public void assignBTOnAction(ActionEvent actionEvent) {
        String compName = enterCompanyNameTextField.getText() != null ? enterCompanyNameTextField.getText().trim() : "";
        String stallId = enterStallIDTextField.getText() != null ? enterStallIDTextField.getText().trim() : "";

        if (compName.isEmpty() || stallId.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Information");
            alert.setContentText("Please select or enter both Company Name and Stall ID.");
            alert.showAndWait();
            return;
        }

        boolean success = DataManager.getInstance().assignStallToCompany(stallId, compName);
        if (success) {
            refreshAllStallsTable();
            refreshAvailableStallsTable();
            refreshAssignedStallsTable();

            enterCompanyNameTextField.clear();
            enterStallIDTextField.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stall Assigned");
            alert.setHeaderText("Assignment Successful");
            alert.setContentText("Stall " + stallId + " has been assigned to " + compName + ".");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Assignment Error");
            alert.setHeaderText("Stall Not Found");
            alert.setContentText("Could not find stall with ID: " + stallId);
            alert.showAndWait();
        }
    }

    @FXML
    public void ViewAvailableBTOnAction(ActionEvent actionEvent) {
        MainTab.getSelectionModel().select(availableStallsTab);
        refreshAvailableStallsTable();
        refreshApprovedVendorsTable();
        refreshAssignedStallsTable();
    }

    @FXML
    public void creatAndShowBTOnAction(ActionEvent actionEvent) {
        MainTab.getSelectionModel().select(createDetailsTab);
        refreshAllStallsTable();
        refreshStallIdComboBox();
    }

    @FXML
    public void availableAndAssignBackBTOnAction(ActionEvent actionEvent) {
        MainTab.getSelectionModel().select(assignStallTab);
    }

    @FXML
    public void creatAndDetBackBTOnAction(ActionEvent actionEvent) {
        MainTab.getSelectionModel().select(assignStallTab);
    }

    @FXML
    public void assignStallBackButtonOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EventManager/EventManagerDashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setTitle("Event Manager Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
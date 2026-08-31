package keya.internationaltradefairltd.CustomerSupportAgent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import keya.internationaltradefairltd.HelloApplication;
import keya.internationaltradefairltd.HelperClass.CustomerQuery;
import keya.internationaltradefairltd.HelperClass.DataManager;
import keya.internationaltradefairltd.HelperClass.Stall;
import keya.internationaltradefairltd.HelperClass.Vendor;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SummaryController {
    @FXML
    private Label stallOccupancyLabel;
    @FXML
    private Label stallsCountSubLabel;
    @FXML
    private Label revenueLabel;
    @FXML
    private Label vendorsCountLabel;
    @FXML
    private Label vendorsPendingSubLabel;
    @FXML
    private Label queryResolutionLabel;
    @FXML
    private Label queryCountSubLabel;

    @FXML
    private PieChart stallPieChart;
    @FXML
    private BarChart<String, Number> queryCategoryBarChart;

    @FXML
    public void initialize() {
        calculateAndRenderAnalytics();
    }

    private void calculateAndRenderAnalytics() {
        // Stalls statistics
        ObservableList<Stall> allStalls = DataManager.getInstance().getStalls();
        int totalStalls = allStalls.size();
        int occupiedStalls = 0;
        double totalRevenue = 0;

        for (Stall s : allStalls) {
            if (!s.isAvailable()) {
                occupiedStalls++;
                totalRevenue += s.getRent();
            }
        }
        int availableStalls = totalStalls - occupiedStalls;
        double occupancyPercent = (totalStalls > 0) ? (occupiedStalls * 100.0 / totalStalls) : 0;

        stallOccupancyLabel.setText(occupiedStalls + " / " + totalStalls + " (" + String.format("%.0f", occupancyPercent) + "%)");
        stallsCountSubLabel.setText(availableStalls + " Stalls Currently Available");

        NumberFormat currencyFormat = NumberFormat.getNumberInstance(Locale.US);
        revenueLabel.setText(currencyFormat.format(totalRevenue) + " BDT");

        // Vendor statistics
        ObservableList<Vendor> allVendors = DataManager.getInstance().getVendors();
        int approvedVendors = 0;
        int pendingVendors = 0;
        for (Vendor v : allVendors) {
            if (v.isApproved()) {
                approvedVendors++;
            } else if ("Pending".equalsIgnoreCase(v.getStatus())) {
                pendingVendors++;
            }
        }
        vendorsCountLabel.setText(approvedVendors + " Approved");
        vendorsPendingSubLabel.setText(pendingVendors + " Pending Applications");

        // Query statistics
        ObservableList<CustomerQuery> allQueries = DataManager.getInstance().getCustomerQueries();
        int totalQueries = allQueries.size();
        int resolvedQueries = 0;
        int openQueries = 0;
        Map<String, Integer> categoryCounts = new HashMap<>();

        for (CustomerQuery q : allQueries) {
            if ("Resolved".equalsIgnoreCase(q.getStatus())) {
                resolvedQueries++;
            } else {
                openQueries++;
            }

            String cat = q.getCategory() != null ? q.getCategory() : "General";
            categoryCounts.put(cat, categoryCounts.getOrDefault(cat, 0) + 1);
        }

        double resolutionRate = (totalQueries > 0) ? (resolvedQueries * 100.0 / totalQueries) : 0;
        queryResolutionLabel.setText(String.format("%.0f", resolutionRate) + "% (" + resolvedQueries + "/" + totalQueries + ")");
        queryCountSubLabel.setText(openQueries + " Active Open / In-Progress Tickets");

        // Render Pie Chart
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Occupied / Assigned (" + occupiedStalls + ")", occupiedStalls),
                new PieChart.Data("Available for Allotment (" + availableStalls + ")", availableStalls)
        );
        stallPieChart.setData(pieData);

        // Render Bar Chart
        queryCategoryBarChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Number of Queries");

        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        queryCategoryBarChart.getData().add(series);
    }

    @FXML
    public void refreshBTOnAction(ActionEvent actionEvent) {
        calculateAndRenderAnalytics();
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
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
import keya.internationaltradefairltd.HelperClass.DataManager;
import keya.internationaltradefairltd.HelperClass.KnowledgeArticle;

import java.io.IOException;

public class KnowledgeBaseController {
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<String> categoryFilterComboBox;

    @FXML
    private TableView<KnowledgeArticle> articlesTableView;
    @FXML
    private TableColumn<KnowledgeArticle, String> articleIdColumn;
    @FXML
    private TableColumn<KnowledgeArticle, String> categoryColumn;
    @FXML
    private TableColumn<KnowledgeArticle, String> questionColumn;

    @FXML
    private Label selectedQuestionLabel;
    @FXML
    private TextArea answerTextArea;

    // Add article controls
    @FXML
    private ComboBox<String> newCategoryComboBox;
    @FXML
    private TextField newQuestionTextField;
    @FXML
    private TextArea newAnswerTextArea;

    @FXML
    public void initialize() {
        categoryFilterComboBox.getItems().setAll("All", "Ticketing", "Stall & Vendor", "Facilities", "Parking", "Lost & Found", "Security", "General");
        categoryFilterComboBox.setValue("All");

        newCategoryComboBox.getItems().setAll("Ticketing", "Stall & Vendor", "Facilities", "Parking", "Lost & Found", "Security", "General");
        newCategoryComboBox.setValue("Ticketing");

        articleIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));

        refreshArticlesTable();

        articlesTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                selectedQuestionLabel.setText("[" + newVal.getCategory() + "] " + newVal.getQuestion());
                answerTextArea.setText(newVal.getAnswer());
            } else {
                selectedQuestionLabel.setText("Select an article to view standard operating response:");
                answerTextArea.clear();
            }
        });

        searchTextField.textProperty().addListener((obs, oldVal, newVal) -> applyFilterAndSearch());
        categoryFilterComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> applyFilterAndSearch());
    }

    private void refreshArticlesTable() {
        articlesTableView.setItems(DataManager.getInstance().getKnowledgeArticles());
    }

    private void applyFilterAndSearch() {
        String keyword = searchTextField.getText() != null ? searchTextField.getText().trim().toLowerCase() : "";
        String cat = categoryFilterComboBox.getValue();

        ObservableList<KnowledgeArticle> filtered = FXCollections.observableArrayList();
        for (KnowledgeArticle ka : DataManager.getInstance().getKnowledgeArticles()) {
            boolean matchesCat = (cat == null || "All".equalsIgnoreCase(cat) || ka.getCategory().equalsIgnoreCase(cat));
            boolean matchesSearch = keyword.isEmpty() ||
                    ka.getQuestion().toLowerCase().contains(keyword) ||
                    ka.getAnswer().toLowerCase().contains(keyword);

            if (matchesCat && matchesSearch) {
                filtered.add(ka);
            }
        }
        articlesTableView.setItems(filtered);
    }

    @FXML
    public void resetSearchBTOnAction(ActionEvent actionEvent) {
        searchTextField.clear();
        categoryFilterComboBox.setValue("All");
        refreshArticlesTable();
    }

    @FXML
    public void addNewArticleBTOnAction(ActionEvent actionEvent) {
        String cat = newCategoryComboBox.getValue();
        String q = newQuestionTextField.getText() != null ? newQuestionTextField.getText().trim() : "";
        String ans = newAnswerTextArea.getText() != null ? newAnswerTextArea.getText().trim() : "";

        if (cat == null || q.isEmpty() || ans.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Missing Fields");
            alert.setContentText("Please select category and provide both Question and Answer.");
            alert.showAndWait();
            return;
        }

        String id = "KB-" + (DataManager.getInstance().getKnowledgeArticles().size() + 1);
        KnowledgeArticle newArticle = new KnowledgeArticle(id, cat, q, ans);
        DataManager.getInstance().getKnowledgeArticles().add(newArticle);

        newQuestionTextField.clear();
        newAnswerTextArea.clear();

        refreshArticlesTable();
        articlesTableView.getSelectionModel().select(newArticle);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Article Added");
        alert.setHeaderText("Knowledge Base Updated");
        alert.setContentText("Article " + id + " has been added successfully.");
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

package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

public class SearchByQuantityViewController {
  @FXML
  private TextField quantitySearchTextField;
  @FXML
  private Button quantitySearchButton;
  @FXML
  private Button quantityClearButton;
  @FXML
  private TableView<Product> quantityTable;
  @FXML
  private TableColumn<Product, Long> idColumn;
  @FXML
  private TableColumn<Product, String> nameColumn;
  @FXML
  private TableColumn<Product, Double> purchasePriceColumn;
  @FXML
  private TableColumn<Product, Double> salesPriceColumn;
  @FXML
  private TableColumn<Product, Long> quantityColumn;
  @FXML
  private Button dashboardButton;

  // Event Listener on Button[#nameSearchButton].onAction
  @FXML
  public void handleQuantitySearchButtonClick(ActionEvent event) throws IOException {
    this.displayResult();
  }

  // Event Listener on Button[#nameClearButton].onAction
  @FXML
  public void handleQuantityClearButtonClick(ActionEvent event) {
    this.quantitySearchTextField.setText("");
  }

  @FXML
  void handleDashboardButtonClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardView.fxml"));
    Pane root = (Pane) loader.load();
    Stage primaryStage = (Stage) this.dashboardButton.getScene().getWindow();
    Scene searchViewScene = new Scene(root);
    primaryStage.setScene(searchViewScene);
    primaryStage.setTitle(Constants.eCommerce);
    primaryStage.show();
  }

  private void displayResult() throws IOException {

    try {
      DashboardViewController.setProductArrayList(Serializer.deserialize(Serializer.databasePath));
      if (DashboardViewController.getProductArrayList() == null) {
        DashboardViewController.setProductArrayList(new ArrayList<>());
      }

      String query = this.quantitySearchTextField.getText();

      if (DashboardViewController.SearchQuantityList(query).isEmpty() == true) {
        throw new InvalidSearchException("NO SUCH QUANTITY RESULT FOUND!");
      }

      DashboardViewController.setProductObservableList(
          FXCollections.observableArrayList(DashboardViewController.SearchQuantityList(query)));

      this.idColumn.setCellValueFactory(new PropertyValueFactory<Product, Long>("productId"));
      this.nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
      this.purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPurchasePrice"));
      this.salesPriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Double>("productSalesPrice"));
      this.quantityColumn.setCellValueFactory(new PropertyValueFactory<Product, Long>("productQuantity"));

      this.quantityTable.setItems(DashboardViewController.getProductObservableList());
    } catch (Exception ex) {
      Stage primaryStage = (Stage) this.quantitySearchButton.getScene().getWindow();
      try {
        ViewUtilities.displayErrorDialogueBox(ex.getMessage(), primaryStage);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}

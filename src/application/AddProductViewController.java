package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

public class AddProductViewController {

  @FXML
  private TextField addIdTextField;
  @FXML
  private TextField addNameTextField;
  @FXML
  private TextField addPurchasePriceTextField;
  @FXML
  private TextField addSalesPriceTextField;
  @FXML
  private TextField addQuantityTextField;
  @FXML
  private Button addSaveButton;
  @FXML
  private Button addClearButton;
  @FXML
  private Button listSwitchButton;
  @FXML
  private Button choosePictureButton;
  @FXML
  private ImageView productImage;
  @FXML
  private Button backToDashboardButton;

  private String productPhotoPath = null;

  @FXML
  void handleChoosePictureButtonClick(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = (Stage) this.choosePictureButton.getScene().getWindow();
    File selectfile = fileChooser.showOpenDialog(primaryStage);

    if (selectfile != null) {
      String selectedFilePath = selectfile.toURI().getPath();
      this.productPhotoPath = selectedFilePath;

      Image myImage = new Image("file://" + productPhotoPath);
      this.productImage.setImage(myImage);
    }
  }

  // Event Listener on Button[#addSaveButton].onAction
  @FXML
  public void handleAddSaveButtonClick(ActionEvent event) {

    String addId = this.addIdTextField.getText();
    String addName = this.addNameTextField.getText();
    String addPurchasePrice = this.addPurchasePriceTextField.getText();
    String addSalesPrice = this.addSalesPriceTextField.getText();
    String addQuantity = this.addQuantityTextField.getText();
    String pathToProductPhoto = this.productPhotoPath;

    try {
      Validation.checkData(addId, addName, addPurchasePrice, addSalesPrice, addQuantity, pathToProductPhoto);

      long id = Long.parseLong(addId);
      double purchasePrice = Double.parseDouble(addPurchasePrice);
      double salesPrice = Double.parseDouble(addSalesPrice);
      long quantity = Long.parseLong(addQuantity);

      Product myProduct1 = new Product(id, addName, purchasePrice, salesPrice, quantity, pathToProductPhoto);

      DashboardViewController.getProductArrayList().add(myProduct1);
      DashboardViewController.getProductObservableList().add(myProduct1);

      Iterator<Product> itr = DashboardViewController.getProductArrayList().iterator();
      while (itr.hasNext()) {
        System.out.println(itr.next());
      }

      Serializer.serialize(Serializer.databasePath, DashboardViewController.getProductArrayList());

      this.resetGUI();
    } catch (Exception ex) {

      Stage primaryStage = (Stage) this.addSaveButton.getScene().getWindow();

      try {
        ViewUtilities.displayErrorDialogueBox(ex.getMessage(), primaryStage);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @FXML
  void handleAddClearButtonClick(ActionEvent event) {
    this.resetGUI();
  }

  @FXML
  void handleListSwitchButtonClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductListView.fxml"));
    Parent root = loader.load();
    ProductListViewController controller = loader.getController();
    controller.getProductList().setItems(DashboardViewController.getProductObservableList());
    Stage primaryStage = (Stage) this.listSwitchButton.getScene().getWindow();
    Scene productListViewScene = new Scene(root);
    primaryStage.setScene(productListViewScene);
    primaryStage.setTitle("PRODUCT LIST");
    primaryStage.show();
  }

  @FXML
  void handlebackToDashbordButtonClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardView.fxml"));
    Pane root = (Pane) loader.load();
    Scene listView = new Scene(root);
    Stage stage = (Stage) this.backToDashboardButton.getScene().getWindow();
    stage.setScene(listView);
    stage.setTitle(Constants.eCommerce);
    stage.show();
  }

  @FXML
  public void resetGUI() {
    this.addIdTextField.setText("");
    this.addNameTextField.setText("");
    this.addPurchasePriceTextField.setText("");
    this.addSalesPriceTextField.setText("");
    this.addQuantityTextField.setText("");
    this.productPhotoPath = null;
    this.productImage.setImage(null);
  }

  @FXML
  public void initialize() {
    DashboardViewController.setProductArrayList(Serializer.deserialize(Serializer.databasePath));
    if (DashboardViewController.getProductArrayList() == null) {
      DashboardViewController.setProductArrayList(new ArrayList<>());
    }
    DashboardViewController
        .setProductObservableList(FXCollections.observableArrayList(DashboardViewController.getProductArrayList()));
  }
}

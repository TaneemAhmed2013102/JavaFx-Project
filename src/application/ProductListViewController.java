package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;

import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Iterator;

public class ProductListViewController {

  @FXML
  private ListView<Product> productList;
  @FXML
  private Button productDetailViewButton;
  @FXML
  private Button editProductButton;
  @FXML
  private Button deleteProductButton;
  @FXML
  private Button dashboardButton;

  private static int choosenIndex = -1;

  public static String pathOfPhoto;

  // Event Listener on Button[#productDetailViewButton].onAction
  @FXML
  public void handleProductDetailViewButton(ActionEvent event) throws IOException {
    Product myProduct = this.productList.getSelectionModel().getSelectedItem();

    if (myProduct != null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductDetailView.fxml"));
      Pane root = (Pane) loader.load();
      ProductDetailViewController productDetail = loader.getController();

      productDetail.transferData(myProduct);

      Stage stage = (Stage) this.productDetailViewButton.getScene().getWindow();
      Scene detailView = new Scene(root);
      stage.setScene(detailView);
      stage.setTitle("PRODUCT DETAIL: " + myProduct.getProductName());
      stage.show();
    }
  }

  // Event Listener on Button[#editProductButton].onAction
  @FXML
  public void handleEditProductButton(ActionEvent event) throws IOException {

    int chosenProductIndex = this.productList.getSelectionModel().getSelectedIndex();
    ProductListViewController.choosenIndex = chosenProductIndex;

    FXMLLoader loader = new FXMLLoader(getClass().getResource("EditProductView.fxml"));
    Pane root = (Pane) loader.load();
    EditProductViewController controller = loader.getController();

    if (chosenProductIndex != -1) {
      Product chooseProduct = DashboardViewController.getProductArrayList().get(chosenProductIndex);

      controller.getEditIdTextField().setText(String.valueOf(chooseProduct.getProductId()));
      controller.getEditNameTextField().setText(chooseProduct.getProductName());
      controller.getEditPurchasePriceTextField().setText(String.valueOf(chooseProduct.getProductPurchasePrice()));
      controller.getEditSalesPriceTextField().setText(String.valueOf(chooseProduct.getProductSalesPrice()));
      controller.getEditQuantityTextField().setText(String.valueOf(chooseProduct.getProductQuantity()));
      ProductListViewController.pathOfPhoto = chooseProduct.getPathToProductPhoto();
      controller.getProductImage().setImage(new Image("file://" + pathOfPhoto));

      Stage primaryStage = (Stage) this.editProductButton.getScene().getWindow();
      Scene editViewScene = new Scene(root);
      primaryStage.setScene(editViewScene);
      primaryStage.setTitle("EDIT DETAIL : " + chooseProduct.getProductName());
      primaryStage.setUserData(chooseProduct);
      primaryStage.show();
    }
  }

  // Event Listener on Button[#deleteProductButton].onAction
  @FXML
  public void handleDeleteProductButton(ActionEvent event) {
    int listIndex = this.productList.getSelectionModel().getSelectedIndex();

    ArrayList<Product> idList = new ArrayList<>();
    idList = DashboardViewController.getProductArrayList();
    ArrayList<Product> sortedArray = new ArrayList<>();
    Iterator<Product> itr = idList.iterator();

    Product product = null;
    int index = 0;

    while (itr.hasNext()) {
      product = itr.next();
      if (index != listIndex) {
        sortedArray.add(product);
      }
      index++;
    }
    Serializer.serialize(Serializer.databasePath, sortedArray);
    this.initialize();
  }

  @FXML
  void handleDashboardButtonClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardView.fxml"));
    Pane root = (Pane) loader.load();
    Scene listView = new Scene(root);
    Stage stage = (Stage) this.dashboardButton.getScene().getWindow();
    stage.setScene(listView);
    stage.setTitle(Constants.productListTitle);
    stage.show();
  }

  @FXML
  public void initialize() {
    DashboardViewController.setProductArrayList(Serializer.deserialize(Serializer.databasePath));
    if (DashboardViewController.getProductArrayList() == null) {
      DashboardViewController.setProductArrayList(new ArrayList<>());
    }
    DashboardViewController
        .setProductObservableList(FXCollections.observableArrayList(DashboardViewController.getProductArrayList()));
    this.getProductList().setItems(DashboardViewController.getProductObservableList());
  }

  public static int getChoosenIndex() {
    return choosenIndex;
  }

  public static void setChoosenIndex(int choosenIndex) {
    ProductListViewController.choosenIndex = choosenIndex;
  }

  public ListView<Product> getProductList() {
    return productList;
  }

  public void setProductList(ListView<Product> productList) {
    this.productList = productList;
  }

  public static String getPathOfPhoto() {
    return pathOfPhoto;
  }

  public static void setPathOfPhoto(String pathOfPhoto) {
    ProductListViewController.pathOfPhoto = pathOfPhoto;
  }
}

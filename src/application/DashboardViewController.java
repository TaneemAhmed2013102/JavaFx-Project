package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DashboardViewController {
  @FXML
  private MenuButton inventoryButton;
  @FXML
  private MenuItem addProductMenu;
  @FXML
  private MenuItem productListMenu;
  @FXML
  private MenuButton searchButton;
  @FXML
  private MenuItem searchByIdMenu;
  @FXML
  private MenuItem searchByNameMenu;
  @FXML
  private MenuItem searchByPurchasePriceMenu;
  @FXML
  private MenuItem searchBySalesPriceMenu;
  @FXML
  private MenuItem searchByQuantityMenu;

  private static ArrayList<Product> productArrayList;

  private static ObservableList<Product> productObservableList;

  // Event Listener on MenuItem[#addProductMenu].onAction
  @FXML
  public void handleAddProductClick(ActionEvent event) throws IOException {
    FXMLLoader addLoader = new FXMLLoader(getClass().getResource("AddProductView.fxml"));
    Pane root = (Pane) addLoader.load();
    Stage primaryStage = (Stage) this.inventoryButton.getScene().getWindow();
    Scene addProductViewScene = new Scene(root);
    primaryStage.setScene(addProductViewScene);
    primaryStage.setTitle(Constants.addProductTitle);
    primaryStage.show();
  }

  // Event Listener on MenuItem[#productListMenu].onAction
  @FXML
  public void handleProductListClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductListView.fxml"));
    Parent root = loader.load();
    ProductListViewController controller = loader.getController();
    controller.getProductList().refresh();
    Stage primaryStage = (Stage) this.inventoryButton.getScene().getWindow();
    Scene productListViewScene = new Scene(root);
    primaryStage.setScene(productListViewScene);
    primaryStage.setTitle(Constants.productListTitle);
    primaryStage.show();
  }

  // Event Listener on MenuItem[#searchByIdMenu].onAction
  @FXML
  public void handleSearchByIdClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchByIdView.fxml"));
    Pane root = (Pane) loader.load();
    Stage primaryStage = (Stage) this.searchButton.getScene().getWindow();
    Scene searchViewScene = new Scene(root);
    primaryStage.setScene(searchViewScene);
    primaryStage.setTitle(Constants.SearchIdTitle);
    primaryStage.show();
  }

  // Event Listener on MenuItem[#searchByNameMenu].onAction
  @FXML
  public void handleSearchByNameClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchByNameView.fxml"));
    Pane root = (Pane) loader.load();
    Stage primaryStage = (Stage) this.searchButton.getScene().getWindow();
    Scene searchViewScene = new Scene(root);
    primaryStage.setScene(searchViewScene);
    primaryStage.setTitle(Constants.SearchNameTitle);
    primaryStage.show();
  }

  // Event Listener on MenuItem[#searchByPurchasePriceMenu].onAction
  @FXML
  public void handleSearchByPurchasePriceClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchByPurchasePriceView.fxml"));
    Pane root = (Pane) loader.load();
    Stage primaryStage = (Stage) this.searchButton.getScene().getWindow();
    Scene searchViewScene = new Scene(root);
    primaryStage.setScene(searchViewScene);
    primaryStage.setTitle(Constants.SearchPurchasePriceTitle);
    primaryStage.show();
  }

  // Event Listener on MenuItem[#searchBySalesPriceMenu].onAction
  @FXML
  public void handleSearchBySalesPriceClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchBySalesPriceView.fxml"));
    Pane root = (Pane) loader.load();
    Stage primaryStage = (Stage) this.searchButton.getScene().getWindow();
    Scene searchViewScene = new Scene(root);
    primaryStage.setScene(searchViewScene);
    primaryStage.setTitle(Constants.SearchSalesPriceTitle);
    primaryStage.show();
  }

  // Event Listener on MenuItem[#searchByQuantityMenu].onAction
  @FXML
  public void handleSearchByQuantityClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchByQuantityView.fxml"));
    Pane root = (Pane) loader.load();
    Stage primaryStage = (Stage) this.searchButton.getScene().getWindow();
    Scene searchViewScene = new Scene(root);
    primaryStage.setScene(searchViewScene);
    primaryStage.setTitle(Constants.SearchQuantityTitle);
    primaryStage.show();
  }

  public static ArrayList<Product> getProductArrayList() {
    return productArrayList;
  }

  public static void setProductArrayList(ArrayList<Product> productArrayList) {
    DashboardViewController.productArrayList = productArrayList;
  }

  public static ObservableList<Product> getProductObservableList() {
    return productObservableList;
  }

  public static void setProductObservableList(ObservableList<Product> productObservableList) {
    DashboardViewController.productObservableList = productObservableList;
  }

  public static ArrayList<Product> SearchIdList(String query) {

    ArrayList<Product> searchArray = new ArrayList<>();
    Iterator<Product> itr = productArrayList.iterator();

    Product product = null;
    long myData = Long.parseLong(query);
    while (itr.hasNext()) {
      // System.out.println(itr.next().getProductId());
      product = itr.next();
      if (product.getProductId() == myData) {
        searchArray.add(product);
      }
    }
    return searchArray;
  }

  public static ArrayList<Product> SearchNameList(String query) {

    ArrayList<Product> searchNameArray = new ArrayList<>();
    Iterator<Product> itr = productArrayList.iterator();

    Product product = null;
    while (itr.hasNext()) {
      product = itr.next();
      if (product.getProductName().equals(query) == true) {
        searchNameArray.add(product);
      }
    }
    return searchNameArray;
  }

  public static ArrayList<Product> SearchPurchasePriceList(String query) {

    ArrayList<Product> searchIdArray = new ArrayList<>();
    Iterator<Product> itr = productArrayList.iterator();

    Product product = null;
    double myData = Double.parseDouble(query);
    while (itr.hasNext()) {
      product = itr.next();
      if (product.getProductPurchasePrice() == myData) {
        searchIdArray.add(product);
      }
    }
    return searchIdArray;
  }

  public static ArrayList<Product> SearchSalesPriceList(String query) {

    ArrayList<Product> searchIdArray = new ArrayList<>();
    Iterator<Product> itr = productArrayList.iterator();

    Product product = null;
    double myData = Double.parseDouble(query);
    while (itr.hasNext()) {
      product = itr.next();
      if (product.getProductSalesPrice() == myData) {
        searchIdArray.add(product);
      }
    }
    return searchIdArray;
  }

  public static ArrayList<Product> SearchQuantityList(String query) {

    ArrayList<Product> searchArray = new ArrayList<>();
    Iterator<Product> itr = productArrayList.iterator();

    Product product = null;
    long myData = Long.parseLong(query);
    while (itr.hasNext()) {
      product = itr.next();
      if (product.getProductQuantity() == myData) {
        searchArray.add(product);
      }
    }
    return searchArray;
  }
}

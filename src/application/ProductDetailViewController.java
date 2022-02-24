package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProductDetailViewController {
  @FXML
  private ImageView detailImage;
  @FXML
  private Label idDetail;
  @FXML
  private Label nameDetail;
  @FXML
  private Label purchasePriceDetail;
  @FXML
  private Label salesPriceDetail;
  @FXML
  private Label quantityDetail;
  @FXML
  private Button detailCloseButton;

  public void transferData(Product myProducts) {
    long id = myProducts.getProductId();
    String name = myProducts.getProductName();
    double purchasePrice = myProducts.getProductPurchasePrice();
    double salesPrice = myProducts.getProductSalesPrice();
    long quantity = myProducts.getProductQuantity();
    String pathToProductPicture = myProducts.getPathToProductPhoto();

    // System.out.println(pathToProductPicture);

    this.idDetail.setText(String.valueOf(id));
    this.nameDetail.setText(name);
    this.purchasePriceDetail.setText(String.valueOf(purchasePrice));
    this.salesPriceDetail.setText(String.valueOf(salesPrice));
    this.quantityDetail.setText(String.valueOf(quantity));
    this.detailImage.setImage(new Image("file://" + pathToProductPicture));
  }

  // Event Listener on Button[#detailCloseButton].onAction
  @FXML
  public void handleDetailCloseButtonClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductListView.fxml"));
    Pane root = (Pane) loader.load();
    Scene listView = new Scene(root);
    Stage stage = (Stage) this.detailCloseButton.getScene().getWindow();
    stage.setScene(listView);
    stage.setTitle(Constants.productListTitle);
    stage.show();
  }
}

package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class EditProductViewController {
  @FXML
  private TextField editIdTextField;
  @FXML
  private TextField editNameTextField;
  @FXML
  private TextField editPurchasePriceTextField;
  @FXML
  private TextField editSalesPriceTextField;
  @FXML
  private TextField editQuantityTextField;
  @FXML
  private ImageView productImage;
  @FXML
  private Button choosePictureButton;
  @FXML
  private Button backToListButton;
  @FXML
  private Button editUpdateButton;

  private String editProductPhotoPath;

  private int PathIndex = -1;

  public TextField getEditIdTextField() {
    return editIdTextField;
  }

  public void setEditIdTextField(TextField editIdTextField) {
    this.editIdTextField = editIdTextField;
  }

  public TextField getEditNameTextField() {
    return editNameTextField;
  }

  public void setEditNameTextField(TextField editNameTextField) {
    this.editNameTextField = editNameTextField;
  }

  public TextField getEditPurchasePriceTextField() {
    return editPurchasePriceTextField;
  }

  public void setEditPurchasePriceTextField(TextField editPurchasePriceTextField) {
    this.editPurchasePriceTextField = editPurchasePriceTextField;
  }

  public TextField getEditSalesPriceTextField() {
    return editSalesPriceTextField;
  }

  public void setEditSalesPriceTextField(TextField editSalesPriceTextField) {
    this.editSalesPriceTextField = editSalesPriceTextField;
  }

  public TextField getEditQuantityTextField() {
    return editQuantityTextField;
  }

  public void setEditQuantityTextField(TextField editQuantityTextField) {
    this.editQuantityTextField = editQuantityTextField;
  }

  public ImageView getProductImage() {
    return productImage;
  }

  public void setProductImage(ImageView productImage) {
    this.productImage = productImage;
  }

  // Event Listener on Button[#choosePictureButton].onAction
  @FXML
  public void handleChoosePictureButtonClick(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    Stage primaryStage = (Stage) this.choosePictureButton.getScene().getWindow();
    File selectfile = fileChooser.showOpenDialog(primaryStage);

    if (selectfile != null) {
      String selectedFilePath = selectfile.toURI().getPath();
      this.editProductPhotoPath = selectedFilePath;
      this.PathIndex = 1;
      Image myImage = new Image("file://" + editProductPhotoPath);
      this.productImage.setImage(myImage);
    }
  }

  // Event Listener on Button[#backToListButton].onAction
  @FXML
  public void handlebackToListButtonClick(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductListView.fxml"));
    Parent root = loader.load();
    ProductListViewController controller = loader.getController();
    controller.getProductList().refresh();
    Stage primaryStage = (Stage) this.backToListButton.getScene().getWindow();
    Scene productListViewScene = new Scene(root);
    primaryStage.setScene(productListViewScene);
    primaryStage.setTitle(Constants.productListTitle);
    primaryStage.show();
  }

  // Event Listener on Button[#editUpdateButton].onAction
  @FXML
  public void handleEditUpdateButtonClick(ActionEvent event) {
    System.out.println(ProductListViewController.getPathOfPhoto());
    String editId = this.editIdTextField.getText();
    String editName = this.editNameTextField.getText();
    String editPurchasePrice = this.editPurchasePriceTextField.getText();
    String editSalesPrice = this.editSalesPriceTextField.getText();
    String editQuantity = this.editQuantityTextField.getText();
    if (this.PathIndex == -1) {
      editProductPhotoPath = ProductListViewController.getPathOfPhoto();
    }
    String pathToProductPhoto = editProductPhotoPath;

    try {
      Validation.checkData(editId, editName, editPurchasePrice, editSalesPrice, editQuantity, pathToProductPhoto);

      long id = Long.parseLong(editId);
      double purchasePrice = Double.parseDouble(editPurchasePrice);
      double salesPrice = Double.parseDouble(editSalesPrice);
      long quantity = Long.parseLong(editQuantity);

      Product myProduct1 = new Product(id, editName, purchasePrice, salesPrice, quantity, pathToProductPhoto);

      int myChoosenIndex = ProductListViewController.getChoosenIndex();

      ArrayList<Product> mainList = new ArrayList<>();
      ArrayList<Product> editedArray = new ArrayList<>();

      mainList = DashboardViewController.getProductArrayList();
      Iterator<Product> itr = mainList.iterator();

      Product product = null;
      int index = 0;
      while (itr.hasNext()) {
        product = itr.next();
        if (myChoosenIndex == index) {
          editedArray.add(myProduct1);
        } else {
          editedArray.add(product);
        }
        index++;
      }
      Serializer.serialize(Serializer.databasePath, editedArray);

      this.resetGUI();

    } catch (Exception ex) {
      Stage primaryStage = (Stage) this.editUpdateButton.getScene().getWindow();

      try {
        ViewUtilities.displayErrorDialogueBox(ex.getMessage(), primaryStage);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @FXML
  public void resetGUI() {
    this.editIdTextField.setText("");
    this.editNameTextField.setText("");
    this.editPurchasePriceTextField.setText("");
    this.editSalesPriceTextField.setText("");
    this.editQuantityTextField.setText("");
    this.editProductPhotoPath = null;
    this.productImage.setImage(null);
  }
}

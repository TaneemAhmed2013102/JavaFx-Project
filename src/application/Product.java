package application;

import java.io.Serializable;

public class Product implements Serializable {

  private static final long serialVersionUID = 490450828935324052L;
  private long productId;
  private String productName;
  private double productPurchasePrice;
  private double productSalesPrice;
  private long productQuantity;
  private String pathToProductPhoto;

  public Product(long productId, String productName, double productPurchasePrice, double productSalesPrice,
      long productQuantity, String pathToProductPhoto) {
    super();
    this.productId = productId;
    this.productName = productName;
    this.productPurchasePrice = productPurchasePrice;
    this.productSalesPrice = productSalesPrice;
    this.productQuantity = productQuantity;
    this.pathToProductPhoto = pathToProductPhoto;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public double getProductPurchasePrice() {
    return productPurchasePrice;
  }

  public void setProductPurchasePrice(double productPurchasePrice) {
    this.productPurchasePrice = productPurchasePrice;
  }

  public double getProductSalesPrice() {
    return productSalesPrice;
  }

  public void setProductSalesPrice(double productSalesPrice) {
    this.productSalesPrice = productSalesPrice;
  }

  public long getProductQuantity() {
    return productQuantity;
  }

  public void setProductQuantity(long productQuantity) {
    this.productQuantity = productQuantity;
  }

  public String getPathToProductPhoto() {
    return pathToProductPhoto;
  }

  public void setPathToProductPhoto(String pathToProductPhoto) {
    this.pathToProductPhoto = pathToProductPhoto;
  }

  public String toString() {
    return this.getProductName();
  }
}

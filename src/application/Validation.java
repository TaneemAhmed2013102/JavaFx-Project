package application;

public class Validation {

  private static final int MIN_NAME_LENGTH = 3, MAX_NAME_LENGTH = 100;

  public static boolean checkData(String productId, String productName, String productPurchasePrice,
      String productSalesPrice, String productQuantity, String pathToProductPicture) throws Exception {

    if (!isIdNotNull(productId)) {
      throw new InvalidNumericalValueException(productId + " is invalid! ID must have a valid postive number!");
    }

    if (!isNameNotNull(productName)) {
      throw new InvalidNameException(productName + " is invalid! Names must have at least " + MIN_NAME_LENGTH
          + " characters and at most " + MIN_NAME_LENGTH + " charaters!");
    }

    if (!isPriceCountNotNull(productPurchasePrice)) {
      throw new InvalidNumericalValueException(
          productPurchasePrice + " is invalid! Prices must have a valid postive number!");
    }

    if (!isPriceCountNotNull(productSalesPrice)) {
      throw new InvalidNumericalValueException(
          productSalesPrice + " is invalid! Prices must have a valid postive number!");
    }

    if (!isQuantityCountNotNull(productQuantity)) {
      throw new InvalidNumericalValueException(
          productQuantity + " is invalid! Quantity must have a valid postive long number!");
    }

    if (isNull(pathToProductPicture)) {
      throw new NullException("PRODUCT'S PICTURE MUST HAVE A PHOTO!");
    }

    return true;
  }

  public static <T> boolean isNull(T myString) {
    return null == myString;
  }

  public static boolean isIdNotSame(String string) {
    return !isNull(string) && string.length() > 0;
  }

  public static boolean isIdNotNull(String string) {
    return !isNull(string) && isLong(string);
  }

  public static boolean isNameNotNull(String value) {
    return !isNull(value) && (value.length() >= MIN_NAME_LENGTH && value.length() <= MAX_NAME_LENGTH);
  }

  public static boolean isPriceCountNotNull(String value) {
    return !isNull(value) && isDouble(value);
  }

  public static boolean isQuantityCountNotNull(String value) {
    return !isNull(value) && isLong(value);
  }

  public static boolean isLong(String supposedlyNumericData) {
    try {
      long myData = Long.parseLong(supposedlyNumericData);
      if (myData <= 0) {
        return false;
      }
    } catch (Exception parseException) {
      return false;
    }
    return true;
  }

  public static boolean isDouble(String supposedlyNumericData) {
    try {
      double myData = Double.parseDouble(supposedlyNumericData);

      if (myData < 0.0) {
        return false;
      }
    } catch (Exception parseException) {
      return false;
    }
    return true;
  }
}

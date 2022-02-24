package application;

public class InvalidNumericalValueException extends Exception {

  private static final long serialVersionUID = -84854873948088447L;

  public InvalidNumericalValueException() {
    super();
  }

  public InvalidNumericalValueException(String message) {
    super(message);
  }

}

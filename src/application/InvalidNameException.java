package application;

public class InvalidNameException extends Exception {

  private static final long serialVersionUID = -5079633826757985043L;

  public InvalidNameException() {
    super();
  }

  public InvalidNameException(String message) {
    super(message);
  }
}

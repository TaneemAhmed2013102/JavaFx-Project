package application;

public class InvalidSearchException extends Exception {

  private static final long serialVersionUID = 8155256003758022354L;

  public InvalidSearchException() {
    super();
  }

  public InvalidSearchException(String message) {
    super(message);
  }
}

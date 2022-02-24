package application;

public class NullException extends Exception {

  private static final long serialVersionUID = -4379494827480652333L;

  public NullException() {
    super();
  }

  public NullException(String message) {
    super(message);
  }
}

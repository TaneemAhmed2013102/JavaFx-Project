package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorMessageViewController {
  @FXML
  private Label errorMessageLabel;
  @FXML
  private Button closeButton;

  // Event Listener on Button[#closeButton].onAction
  @FXML
  public void handleErrorCloseButtonClick(ActionEvent event) throws IOException {
    Stage stage = (Stage) this.closeButton.getScene().getWindow();
    stage.close();
  }

  public void setErrorMessageLabel(String errorMessage) {
    this.errorMessageLabel.setText(errorMessage);
  }
}

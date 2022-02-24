package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewUtilities {

  public static void displayErrorDialogueBox(String errorMessage, Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader(ViewUtilities.class.getResource("ErrorMessageView.fxml"));
    Pane root = (Pane) loader.load();
    ErrorMessageViewController error = loader.getController();
    error.setErrorMessageLabel(errorMessage);

    Stage stage = new Stage();
    Scene erroView = new Scene(root);
    stage.setScene(erroView);
    stage.initOwner(primaryStage);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle(Constants.errorTitle);
    stage.showAndWait();
  }
}

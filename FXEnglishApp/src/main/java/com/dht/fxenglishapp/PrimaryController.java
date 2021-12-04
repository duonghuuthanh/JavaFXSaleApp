package com.dht.fxenglishapp;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;


public class PrimaryController {
   public void practiceHandler(ActionEvent event) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLPractice.fxml"));
       Parent root = fxmlLoader.load();
       
       FXMLPracticeController controller = fxmlLoader.getController();
       
       TextInputDialog dialog = new TextInputDialog();
       dialog.setHeaderText("Enter your expected number of questions");
       Optional<String> num  = dialog.showAndWait();
       
       if (num.isPresent()) {
            controller.setNum(Integer.parseInt(num.get()));
            controller.load();

            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
       }
   }
}

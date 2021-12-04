/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.fxenglishappv2;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author duonghuuthanh
 */
public class FXMLMainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void practiceHandler(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FXMLPratice.fxml"));
        Parent root = fxmlLoader.load();
        
        TextInputDialog diag = new TextInputDialog();
        diag.setHeaderText("Enter your num: ");
        Optional<String> ans = diag.showAndWait();
        if (ans.isPresent()) {
            FXMLPraticeController controller = fxmlLoader.getController();
            controller.setNum(Integer.parseInt(ans.get()));
            controller.loadPractice();
            controller.loadQuestion();


            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Practice");
            stage.show();
        }
    }
}

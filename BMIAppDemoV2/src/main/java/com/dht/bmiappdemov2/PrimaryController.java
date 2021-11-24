package com.dht.bmiappdemov2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


public class PrimaryController {
    @FXML private TextField txtWeight;
    @FXML private TextField txtHeight;
    @FXML private Label lblResult;
    
    public void bmiHandler(ActionEvent event) {
        try {
            double height = Double.parseDouble(this.txtHeight.getText());
            double weight = Double.parseDouble(this.txtWeight.getText());

            double bmi = weight / Math.pow(height, 2);

            String msg = "";
            if (bmi < 18.5)
                msg = "Underweight";
            else if (bmi < 24.9)
                msg = "Normal weight";
            else if (bmi < 30)
                msg = "Overweight";
            else
                msg = "Obesity";

            this.lblResult.setTextFill(Color.DARKBLUE);
            this.lblResult.setText(String.format("BMI = %.1f - %s", bmi, msg));
        } catch (NumberFormatException ex) {
            this.lblResult.setTextFill(Color.RED);
            this.lblResult.setText("Invalid data!!!");
        }
        
    }
}

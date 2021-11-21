package com.dht.bmiappdemo;

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
            double w = Double.parseDouble(this.txtWeight.getText());
            double h = Double.parseDouble(this.txtHeight.getText());

            double bmi = w/Math.pow(h, 2);
            String msg = "";
            if (bmi <= 18.5)
                msg = "Underweight";
            else if (bmi < 24.9)
                msg = "Normal weight";
            else if (bmi < 29.9)
                msg = "Overweight";
            else
                msg = "Obesity";

            lblResult.setTextFill(Color.DARKBLUE);
            lblResult.setText(String.format("BMI = %.1f - %s", bmi, msg));
        } catch (NumberFormatException ex) {
            lblResult.setTextFill(Color.RED);
            lblResult.setText("Invalid data!!!");
        }
        
    }
}

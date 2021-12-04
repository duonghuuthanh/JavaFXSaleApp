/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.fxenglishappv2;

import com.dht.configs.Utils;
import com.dht.pojo.Question;
import com.dht.services.QuestionServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

/**
 * FXML Controller class
 *
 * @author duonghuuthanh
 */
public class FXMLPraticeController implements Initializable {
    @FXML private Label lblContent;
    @FXML private RadioButton rdoA;
    @FXML private RadioButton rdoB;
    @FXML private RadioButton rdoC;
    @FXML private RadioButton rdoD;
//    
    private static final QuestionServices S = new QuestionServices();
    private List<Question> practiceQuestions;
    private int current = 0;
    private int num = 5;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            this.loadQuestion();
//        } catch (SQLException ex) {
//            Logger.getLogger(FXMLPraticeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    } 
    
    public void checkAnswerHandler(ActionEvent event) {
        Question q = this.practiceQuestions.get(this.current);
        RadioButton[] rdos = { rdoA, rdoB, rdoC, rdoD };
        
        for (int i = 0; i < q.getChoices().size(); i++)
            if (q.getChoices().get(i).isCorrect() == true) {
                if (rdos[i].isSelected() == true)
                    Utils.getBox("EXACTILY!!!", Alert.AlertType.INFORMATION).show();
                else
                    Utils.getBox("Wrongly!!!", Alert.AlertType.WARNING).show();
                
                break;
            }
    }
    
    public void loadPractice() {
        try {
            this.practiceQuestions = S.getPracticeQuestion(this.num);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLPraticeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void nextHandler(ActionEvent event) {
        if (this.current < this.practiceQuestions.size() - 1)
            this.current++;
        else
            this.current = 0;
        
      
         loadQuestion();
       
    }
    
    public void loadQuestion() {
        try {
            Question q = this.practiceQuestions.get(this.current);
            this.lblContent.setText(q.getContent());
            if (q.getChoices() == null)
                q.setChoices(S.getChoices(q.getId()));
            
            RadioButton[] rdos = { rdoA, rdoB, rdoC, rdoD };
            for (int i = 0; i < rdos.length; i++)
                rdos[i].setText(q.getChoices().get(i).getContent());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLPraticeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }
}

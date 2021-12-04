/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.fxenglishapp;

import com.dht.conf.Utils;
import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import com.dht.services.QuestionService;
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
public class FXMLPracticeController implements Initializable {
    @FXML private Label lblContent;
    @FXML private RadioButton rdoA;
    @FXML private RadioButton rdoB;
    @FXML private RadioButton rdoC;
    @FXML private RadioButton rdoD;
    private int currentQuestion = 0;
    private List<Question> questions;
    private static final QuestionService SERVICE = new QuestionService();
    private int num = 2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    public void load() {
        try {
            this.questions = SERVICE.loadQuestions(this.getNum());
            this.loadAQuestion();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLPracticeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void checkHandler(ActionEvent event) {
        Question q = this.questions.get(currentQuestion);
        
        RadioButton[] rdos = {rdoA, rdoB, rdoC, rdoD};
        for (int i = 0; i < rdos.length; i++) {
            if (q.getChoices().get(i).isCorrect() == true)
                if (rdos[i].isSelected() == true)
                    Utils.getBox("EXACTLY!!!", Alert.AlertType.INFORMATION).show();
                else
                    Utils.getBox("WRONGLY!!!", Alert.AlertType.INFORMATION).show();
            
            break;
        }
    }   
    
    public void nextHandler(ActionEvent event) throws SQLException {
        if (this.currentQuestion < this.questions.size() - 1)
            this.currentQuestion++;
        else
            this.currentQuestion = 0;
        
        this.loadAQuestion();
    }
    
    private void loadAQuestion() throws SQLException {
        Question q = this.questions.get(this.currentQuestion);
        this.lblContent.setText(q.getContent());
        
        
        List<Choice> choices = SERVICE.getChoicesByQuestionId(q.getId());
        q.setChoices(choices);
        
        
//        this.rdoA.setText(choices.get(0).getContent());
//        this.rdoB.setText(choices.get(1).getContent());
//        this.rdoC.setText(choices.get(2).getContent());
//        this.rdoD.setText(choices.get(3).getContent());
        
        RadioButton[] rdos = {rdoA, rdoB, rdoC, rdoD};
        for (int i = 0; i < rdos.length; i++)
            rdos[i].setText(choices.get(i).getContent());
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }
}

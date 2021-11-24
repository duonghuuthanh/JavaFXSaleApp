/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.fxenglishapp;

import com.dht.conf.Utils;
import com.dht.pojo.Category;
import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import com.dht.services.CategoryService;
import com.dht.services.QuestionService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author duonghuuthanh
 */
public class FXMLQuestionController implements Initializable {
    @FXML private TextField txtKeyword;
    @FXML private TableView<Question> tbQuestions;
    @FXML private ComboBox<Category> cbCategories;
    @FXML private TextField txtContent;
    @FXML private TextField txtA;
    @FXML private TextField txtB;
    @FXML private TextField txtC;
    @FXML private TextField txtD;
    @FXML private RadioButton rdoA;
    @FXML private RadioButton rdoB;
    @FXML private RadioButton rdoC;
    @FXML private RadioButton rdoD;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadTableView();
        
        CategoryService s = new CategoryService();
        try {
            this.cbCategories.setItems(FXCollections.observableList(s.getCategories()));
            this.loadTableDate(null);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLQuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtKeyword.textProperty().addListener((evt) -> {
            try {
                this.loadTableDate(this.txtKeyword.getText());
            } catch (SQLException ex) {
                Logger.getLogger(FXMLQuestionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }    
    
    public void addQuestionHandler(ActionEvent event) {
        Question q = new Question(UUID.randomUUID().toString(), 
                this.txtContent.getText(), this.cbCategories.getSelectionModel().getSelectedItem().getId());
        
        
        Choice a = new Choice(UUID.randomUUID().toString(), this.txtA.getText(), 
                this.rdoA.isSelected(), q.getId());
        Choice b = new Choice(UUID.randomUUID().toString(), this.txtB.getText(), 
                this.rdoB.isSelected(), q.getId());
        Choice c = new Choice(UUID.randomUUID().toString(), this.txtC.getText(), 
                this.rdoC.isSelected(), q.getId());
        Choice d = new Choice(UUID.randomUUID().toString(), this.txtD.getText(), 
                this.rdoD.isSelected(), q.getId());
        
        List<Choice> choices = new ArrayList<>();
        choices.add(a);
        choices.add(b);
        choices.add(c);
        choices.add(d);
        
        QuestionService s = new QuestionService();
        try {
            s.addQuestion(q, choices);
            
            Utils.getBox("Add question successful!", Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Utils.getBox("Add question failed!", Alert.AlertType.WARNING).show();
        }
    }
    
    private void loadTableView() {
        TableColumn colContent = new TableColumn("Question content");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(400);
        
        TableColumn colCate = new TableColumn("Category Id");
        colCate.setCellValueFactory(new PropertyValueFactory("categoryId"));
        colCate.setPrefWidth(150);
        
        this.tbQuestions.getColumns().addAll(colContent, colCate);
    }
    
    private void loadTableDate(String kw) throws SQLException {
        QuestionService s = new QuestionService();
        
        this.tbQuestions.setItems(FXCollections.observableList(s.getQuestions(kw)));
    }
}

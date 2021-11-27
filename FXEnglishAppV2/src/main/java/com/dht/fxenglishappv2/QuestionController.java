/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.fxenglishappv2;

import com.dht.configs.Utils;
import com.dht.pojo.Category;
import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import com.dht.services.CategoryServices;
import com.dht.services.QuestionServices;
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
public class QuestionController implements Initializable {
    @FXML private TableView<Question> tbQuestions;
    @FXML private ComboBox<Category> cbCategories;
    @FXML private TextField txtKeyword;
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
        CategoryServices s = new CategoryServices();
        List<Category> cates = s.getCategories();
        
        this.cbCategories.setItems(FXCollections.observableList(cates));
        
        this.loadTableColumns();
        try {
            this.loadTableData(null);
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.txtKeyword.textProperty().addListener(evt -> {
            try {
                this.loadTableData(this.txtKeyword.getText());
            } catch (SQLException ex) {
                Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    } 
    
    public void addQuestionHandler(ActionEvent event) {
        Question q = new Question(UUID.randomUUID().toString(), 
                this.txtContent.getText(), 
                this.cbCategories.getSelectionModel().getSelectedItem().getId());
        
        Choice c1 = new Choice(UUID.randomUUID().toString(), this.txtA.getText(), 
                this.rdoA.isSelected(), q.getId());
        Choice c2 = new Choice(UUID.randomUUID().toString(), this.txtB.getText(), 
                this.rdoB.isSelected(), q.getId());
        Choice c3 = new Choice(UUID.randomUUID().toString(), this.txtC.getText(), 
                this.rdoC.isSelected(), q.getId());
        Choice c4 = new Choice(UUID.randomUUID().toString(), this.txtD.getText(), 
                this.rdoD.isSelected(), q.getId());
        
        List<Choice> choices = new ArrayList<>();
        choices.add(c1);
        choices.add(c2);
        choices.add(c3);
        choices.add(c4);
        
        QuestionServices s = new QuestionServices();
        try {
            s.addQuestion(q, choices);
            
            Utils.getBox("Add successful!!!", Alert.AlertType.INFORMATION).show();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            Utils.getBox("Add failed!!!", Alert.AlertType.WARNING).show();
        }
    }
    
    private void loadTableColumns() {
        TableColumn colContent = new TableColumn("Question content");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(400);
        
        TableColumn colCategory = new TableColumn("Category");
        colCategory.setCellValueFactory(new PropertyValueFactory("categoryId"));
        colCategory.setPrefWidth(150);
        
        this.tbQuestions.getColumns().addAll(colContent,colCategory);
    }
    
    private void loadTableData(String kw) throws SQLException {
        QuestionServices s = new QuestionServices();
        List<Question> questions = s.getQuestions(kw);
        
        this.tbQuestions.setItems(FXCollections.observableList(questions));
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import com.dht.conf.JdbcUtils;
import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duonghuuthanh
 */
public class QuestionService {
    private static final int CHOICE_NUM = 4;
            
    public void addQuestion(Question q, List<Choice> choices) throws SQLException {
        if (choices.size() == CHOICE_NUM) {
            try (Connection conn = JdbcUtils.getConn()) {
                conn.setAutoCommit(false);
                
                PreparedStatement stm1 = conn.prepareStatement("INSERT INTO question(id, content, category_id) VALUES(?, ?, ?)");
                stm1.setString(1, q.getId());
                stm1.setString(2, q.getContent());
                stm1.setInt(3, q.getCategoryId());
                
                stm1.executeUpdate();
                
                PreparedStatement stm2 = conn.prepareStatement("INSERT INTO choice(id, content, is_correct, question_id) VALUES(?, ?, ?, ?)");
                for (Choice c: choices) {
                    stm2.setString(1, c.getId());
                    stm2.setString(2, c.getContent());
                    stm2.setBoolean(3, c.isCorrect());
                    stm2.setString(4, c.getQuestionId());
                    
                    stm2.executeUpdate();
                }
                
                conn.commit();
            }
        }
    }
    
    public List<Question> getQuestions(String kw) throws SQLException {
        List<Question> questions = new ArrayList<>();
        
        try (Connection conn = JdbcUtils.getConn()) {
            String sql = "SELECT * FROM question";
            if (kw != null && !kw.isEmpty())
                sql += " WHERE content like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareStatement(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Question q = new Question(rs.getString("id"), rs.getString("content"), 
                        rs.getInt("category_id"));
                questions.add(q);
            }
            
        }
        
        
        return questions;
    }
    
    public Question getQuestionById(String questionId) throws SQLException {
        Question q = null;
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE id=?");
            stm.setString(1, questionId);
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                q = new Question(rs.getString("id"), 
                        rs.getString("content"), rs.getInt("category_id"));
                break;
            }
        }
        
        return q;
    }
    
    public List<Choice> getChoicesByQuestionId(String questionId) throws SQLException {
        List<Choice> choices = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            PreparedStatement stm = conn.prepareCall("SELECT * FROM choice WHERE question_id=?");
            stm.setString(1, questionId);
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Choice c = new Choice(rs.getString("id"), rs.getString("content"), 
                        rs.getBoolean("is_correct"), rs.getString("question_id"));
                choices.add(c);
            }
        }
        
        return choices;
    }
}

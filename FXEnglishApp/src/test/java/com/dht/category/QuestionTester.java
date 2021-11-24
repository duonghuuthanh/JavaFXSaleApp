/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.category;

import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import com.dht.services.QuestionService;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author duonghuuthanh
 */
public class QuestionTester {
    private static QuestionService s;
    private static Question q;
    private static Choice c1, c2, c3, c4;
    
    @BeforeAll
    public static void init() {
        s = new QuestionService();
        q = new Question(UUID.randomUUID().toString(), "Test Question", 1);
        c1 = new Choice(UUID.randomUUID().toString(), "Choice A", true, q.getId());
        c2 = new Choice(UUID.randomUUID().toString(), "Choice B", false, q.getId());
        c3 = new Choice(UUID.randomUUID().toString(), "Choice C", false, q.getId());
        c4 = new Choice(UUID.randomUUID().toString(), "Choice D", false, q.getId());
    }
    
    @Test
    public void testGetQuestionByInvlidId() {
        Question q;
        try {
            q = s.getQuestionById("1");
            Assertions.assertNull(q);
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test 
    public void testGetQuestionByValidId() {
        Question q;
        try {
            q = s.getQuestionById("28c93aff-323b-4e5b-b3b1-0283fae8a943");
            
            Assertions.assertEquals(q.getContent(), "This ... a book");
            Assertions.assertEquals(q.getCategoryId(), 2);
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testChoices() {
        String questionId = "28c93aff-323b-4e5b-b3b1-0283fae8a943";
        try {
            List<Choice> choices = s.getChoicesByQuestionId(questionId);
            
            Assertions.assertEquals(choices.size(), 4);
            Assertions.assertEquals(choices.stream().filter(c -> c.isCorrect() == true).count(), 1);
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void addQuestionInvalidChoices() {
        List<Choice> choices = new ArrayList<>();
        choices.add(c1);
        choices.add(c2);
        
        try {
            Question myQ = new Question(UUID.randomUUID().toString(), "a", 1);
            s.addQuestion(myQ, choices);
            
            Question question = s.getQuestionById(myQ.getId());
            Assertions.assertNull(question);
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void addQuestion() {
        List<Choice> choices = new ArrayList<>();
        choices.addAll(Arrays.asList(new Choice[] {c1, c2, c3, c4}));
        
        try {
            s.addQuestion(q, choices);
            
            Question question = s.getQuestionById(q.getId());
            Assertions.assertEquals(question.getContent(), q.getContent());
            Assertions.assertEquals(question.getCategoryId(), q.getCategoryId());
            
            List<Choice> myChoices = s.getChoicesByQuestionId(q.getId());
            Assertions.assertEquals(myChoices.size(), 4);
            Assertions.assertEquals(myChoices.stream().filter(c -> c.isCorrect() == true).count(), 1);
        } catch (SQLException ex) {
            Logger.getLogger(QuestionTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

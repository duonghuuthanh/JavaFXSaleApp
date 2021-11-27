
import com.dht.pojo.Choice;
import com.dht.pojo.Question;
import com.dht.services.QuestionServices;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author duonghuuthanh
 */
public class QuestionTester {
    private static List<Question> questions;
    private static QuestionServices service;
//    private static List<Choice> choices;
    
    @BeforeAll
    public static void init() {
        questions = new ArrayList<>();
        questions.add(new Question(UUID.randomUUID().toString(), "QUESTION 1", 0));
        questions.add(new Question(UUID.randomUUID().toString(), "QUESTION 2", 1));
        
        service = new QuestionServices();
    }
    
    @Test
    public void testLackChoices() throws SQLException {
        Question q = questions.get(1);
        
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice A", true, q.getId()));
        
        service.addQuestion(q, choices);
        
        Question actual = service.getQuestionById(q.getId());
        Assertions.assertNull(actual);
    }
    
    @Test
    public void testInvalidChoices() throws SQLException {
        Question q = questions.get(1);
        
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice A", false, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice B", false, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice C", false, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice D", false, q.getId()));
        
        service.addQuestion(q, choices);
        
        Question actual = service.getQuestionById(q.getId());
        Assertions.assertNull(actual);
    }
    
    @Test
    public void testAddInvalidQuestion() {
        Question q = questions.get(0);
        
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice A", true, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice B", false, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice C", false, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice D", false, q.getId()));
        
        
        Assertions.assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
            service.addQuestion(q, choices);
        });
    }
    
    @Test
    public void testAddSuccessful() throws SQLException {
        Question q = questions.get(1);
        
        List<Choice> choices = new ArrayList<>();
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice A", true, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice B", false, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice C", false, q.getId()));
        choices.add(new Choice(UUID.randomUUID().toString(), "Choice D", false, q.getId()));
        
        service.addQuestion(q, choices);
        
        Question actual = service.getQuestionById(q.getId());
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(actual.getContent(), q.getContent());
        
        List<Choice> actualChoices = service.getChoices(q.getId());
        Assertions.assertEquals(actualChoices.size(), 4);
        Assertions.assertEquals(actualChoices.stream().filter(c -> c.isCorrect()).count(), 1);
    }
}

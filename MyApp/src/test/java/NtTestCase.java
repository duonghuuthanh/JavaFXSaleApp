
import com.dht.myapp.Utils;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author duonghuuthanh
 */
public class NtTestCase {
    @BeforeAll
    public static void beforeAll() {
        System.out.println("BEFORE ALL");
    }
    
    @AfterAll
    public static void afterAll() {
        System.out.println("AFTER ALL");
    }
    
    @BeforeEach
    public void beforeEach() {
        System.out.println("BEFORE EACH");
    }
    
    @AfterEach
    public void afterEach() {
        System.out.println("AFTER EACH");
    }
    
    @Test
    public void testNtChan() {
        int n = 2;
        boolean expected = true;
        boolean actual = Utils.ktNt(n);
        
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    @Tag("Critical")
    @DisplayName("Kiem tra so nguyen to le")
    public void testNtLe() {
        int n = 5;
        boolean actual;
       
        actual = Utils.ktNt(n);
        Assertions.assertTrue(actual);
    }
    
    @Test
    public void testNtException() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Utils.ktNt(-5);
        });
    }
    
    @Test
    @Tag("Critical")
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Utils.ktNt(6);
        });
    }
    
    @ParameterizedTest
    @ValueSource(ints = {2, 5, 7, 97, 103})
    public void testTrue(int n) {
        Assertions.assertTrue(Utils.ktNt(n), "ABC");
    }
    
    @ParameterizedTest
    @CsvSource({"1,false,abc", "98,false,def", "97,true,ght"})
    public void testAll(int n, boolean expected, String message) {
        Assertions.assertEquals(expected, Utils.ktNt(n), message);
    }
    
    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/data.csv", numLinesToSkip = 0)
    public void testAllInFile(int n, boolean expected, String message) {
        Assertions.assertEquals(expected, Utils.ktNt(n), message);
    }
}

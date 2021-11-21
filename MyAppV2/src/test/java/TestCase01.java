
import com.dht.myappv2.Utils;
import java.time.Duration;
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
public class TestCase01 {
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
    @Tag("critical")
    @DisplayName("Kiem thu so le la so nguyen to")
    public void testNtLe() {
        int n = 5;
        boolean expected = true;
        boolean actual = Utils.ktNt(n);
        
        Assertions.assertEquals(expected, actual, "Kiem tra so le nguyen to SAI");
    }
    
    @Test
    @Tag("critical")
    public void testNtChan() {
        int n = 4;
        boolean expected = false;
        boolean actual = Utils.ktNt(n);
        
        Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void testException() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Utils.ktNt(-5);
        });
    }
    
    @Test
    public void testTimeout() {
        Assertions.assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            Utils.ktNt(113);
        });
    }
    
    @ParameterizedTest
    @ValueSource(ints = {3, 5, 7, 11, 97, 113})
    public void testNguyenToLe(int n) {
        boolean actual = Utils.ktNt(n);
        
        Assertions.assertTrue(actual);
        
    }
    
    @ParameterizedTest
    @CsvSource({"2,true,loi 1", "4,false,loi 2", "113,true,loi 3"})
    public void testNt(int n, boolean expected, String msg) {
        boolean actual = Utils.ktNt(n);
        
        Assertions.assertEquals(expected, actual, msg);
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "testdata.csv", numLinesToSkip = 1)
    public void testNt2(int n, boolean expected, String msg) {
        boolean actual = Utils.ktNt(n);
        
        Assertions.assertEquals(expected, actual, msg);
    }
}

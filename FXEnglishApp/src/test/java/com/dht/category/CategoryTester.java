/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.category;

import com.dht.conf.JdbcUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author duonghuuthanh
 */
public class CategoryTester {
    private static Connection conn;
    
    @BeforeAll
    public static void beforeAll() throws SQLException {
        conn = JdbcUtils.getConn();
    }
    
    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null)
            conn.close();
    }
    
    @Test
    public void testUnique() throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM category");
        
        List<String> kq = new ArrayList<>();
        while (rs.next()) {
            String name = rs.getString("name");
            kq.add(name);
        }
        
        Set<String> kq2 = new HashSet<>(kq);
        
        Assertions.assertEquals(kq.size(), kq2.size());
    }
}

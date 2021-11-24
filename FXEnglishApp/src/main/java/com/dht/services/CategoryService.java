/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import com.dht.conf.JdbcUtils;
import com.dht.pojo.Category;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author duonghuuthanh
 */
public class CategoryService {
    public List<Category> getCategories() throws SQLException {
        List<Category> results = new ArrayList<>();
        try (Connection conn = JdbcUtils.getConn()) {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM category");
            
            while (rs.next()) {
                Category c = new Category(rs.getInt("id"), rs.getString("name"));
                results.add(c);
            }
        }
        
        return results;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.services;

import com.dht.configs.JdbcUtils;
import com.dht.pojo.Category;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duonghuuthanh
 */
public class CategoryServices {
    public List<Category> getCategories() {
        List<Category> kq = new ArrayList<>();
        
        Connection conn;
        try {
            conn = JdbcUtils.getConn();
            
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM category");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                
                kq.add(new Category(id, name));
            }
            
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kq;
    }
}

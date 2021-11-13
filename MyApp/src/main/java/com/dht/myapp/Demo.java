/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.myapp;

/**
 *
 * @author duonghuuthanh
 */
public class Demo {
    public static void main(String[] args) {
        int[] a = {2, 3, 5, 6, 4, 8, 9, 11};
        
        int sum = 0;
        for (int x: a)
            if (Utils.ktNt(x) == true)
                sum += x;
        
        System.out.println(sum);
    }
}

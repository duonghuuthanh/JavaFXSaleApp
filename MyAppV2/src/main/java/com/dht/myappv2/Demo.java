/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.myappv2;

/**
 *
 * @author duonghuuthanh
 */
public class Demo {
    public static void main(String[] args) {
        int[] a = {5, 6, 7, 3, 4, 2};
        
        int sum = 0;
        for (int x: a)
            if (Utils.ktNt(x))
                sum += x;
        
        System.out.println(sum);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dht.myappv2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duonghuuthanh
 */
public class Utils {
    public static boolean ktNt(int n) {
        if (n >= 2) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
//            }
            for (int i = 2; i <= Math.sqrt(n); i++)
                if (n % i == 0)
                    return false;
            
            return true;
        } else
            throw new ArithmeticException();
    }
}

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
        System.out.println(Test.TUAN.soNgay());
    }
}

enum Test {
    TUAN {
        @Override
        public int soNgay() {
            return 7;
        }
    }, NGAY {
        @Override
        public int soNgay() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    
    public abstract int soNgay();
}

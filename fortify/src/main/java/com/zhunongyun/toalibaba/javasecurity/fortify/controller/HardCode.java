package com.zhunongyun.toalibaba.javasecurity.fortify.controller;

/**
 * Fortify 硬编码问题
 *
 * @author oscar
 * @date 2022/1/5 17:19
 */
public class HardCode {

    private static final String password = "abcd@1234";

    private static final String pwd = "12345678";

    private static final String key = "12345678";

    public static void main(String[] args) {
        System.out.println(password);
        System.out.println(pwd);
        System.out.println(key);
    }

}
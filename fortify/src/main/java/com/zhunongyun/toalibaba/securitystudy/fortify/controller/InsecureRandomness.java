package com.zhunongyun.toalibaba.securitystudy.fortify.controller;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class InsecureRandomness {

    public static void main(String[] args) {
        Random random = new Random();
        SecureRandom secureRandom = new SecureRandom();
        random.nextInt(100);
        secureRandom.nextInt(100);
    }

    // ceshi1daima1
    private static void test() {
        try {
            SecretKeySpec key = new SecretKeySpec("keyBytes".getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.zhunongyun.toalibaba.javasecurity.fortify.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.SecureRandom;

/**
 * 反序列化漏洞
 *
 * @author oscar
 * @date 2021/12/16 16:50
 */
public class SerializationLeak {

    public static void main(String[] args) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("d:/soft/log4j.bin")))) {
            Object obj = objectInputStream.readObject();
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
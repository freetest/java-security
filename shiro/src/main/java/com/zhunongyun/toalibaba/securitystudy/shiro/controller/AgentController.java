package com.zhunongyun.toalibaba.securitystudy.shiro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author oscar
 * @date 2022/1/14 11:20
 */
@RestController
@RequestMapping("agent")
public class AgentController {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @GetMapping("test")
    public void agentTest() {
        // 设置一个已经过期的License时间
        final String expireDate = "2020-10-01 00:00:00";

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    String time = "[" + DATE_FORMAT.format(new Date()) + "] ";
                    // 检测license是否已经过期
                    String msg = checkExpiry(expireDate) ? "您的授权已过期，请重新购买授权！" : ("您的授权正常，截止时间为：" + expireDate);
                    System.out.println(msg);

                    try {
                        // sleep 5秒
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private static boolean checkExpiry(String expireDate) {
        try {
            Date date = DATE_FORMAT.parse(expireDate);
            // 检测当前系统时间早于License授权截至时间
            if (new Date().before(date)) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;
    }
}

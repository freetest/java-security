package com.zhunongyun.toalibaba.securitystudy.fortify.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("api")
public class TestController {

    @GetMapping("bugApi")
    public void bugApi(HttpServletRequest request){
        log.info("请求已经收到,请求信息:{}", request.getRemoteAddr());
    }
}

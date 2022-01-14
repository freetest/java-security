package com.zhunongyun.toalibaba.securitystudy.fortify.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhunongyun.toalibaba.securitystudy.fortify.utils.FortifyUtils;
import com.zhunongyun.toalibaba.securitystudy.fortify.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志伪造漏洞
 * @author oscar
 */
@Slf4j
@RestController
@RequestMapping("log")
public class LogForgingController {

    /**
     * 通过输入特殊字符,对输出日志进行伪造
     * @param requestData
     * @return
     */
    @PostMapping("logForging")
    public ResponseVO logForging(@RequestBody String requestData) {
        JSONObject jsonObject = JSON.parseObject(requestData);

        log.info("处理之前, 输出日志:{}", jsonObject.getString("info"));

        String logStr = FortifyUtils.validLog(jsonObject.getString("info"));
        log.info("处理之后, 输出日志:{}", logStr);

        return ResponseVO.success();
    }
}
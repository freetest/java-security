package com.zhunongyun.toalibaba.javasecurity.fortify.controller;

import com.zhunongyun.toalibaba.javasecurity.fortify.vo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @author oscar
 * @date 2021/12/24 20:20
 */
@Slf4j
@RestController
@RequestMapping("xss")
public class XSSController {

    @GetMapping("reflection")
    public ResponseVO reflectionXss(@RequestParam String data) {


        return ResponseVO.success(data);
    }

    @PostMapping("storage")
    public ResponseVO storageXssPost(@RequestBody String data) {


        return ResponseVO.success();
    }

    @GetMapping("storage")
    public ResponseVO storageXssGet() {
        // 从数据读取内容

        return ResponseVO.success();
    }
}

package com.zhunongyun.toalibaba.securitystudy.fortify.controller;

import com.zhunongyun.toalibaba.securitystudy.fortify.vo.ResponseVO;
import com.zhunongyun.toalibaba.securitystudy.fortify.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 越权
 * @author oscar
 */
@Slf4j
@RestController
@RequestMapping("order")
public class AuthorityController {

    private final OrderService orderService;

    public AuthorityController(OrderService orderService) {
        this.orderService = orderService;
    }


    /**
     * 查询他人订单信息
     * 测试用例
     * GET  localhost:8080/order/queryUserOrder?orderId=2
     *
     * @param orderId 订单号
     * @return 订单信息
     */
    @GetMapping("queryUserOrder")
    public ResponseVO queryUserOrder(@RequestParam("orderId") Long orderId) {
        return ResponseVO.success(orderService.getById(orderId));
    }
}
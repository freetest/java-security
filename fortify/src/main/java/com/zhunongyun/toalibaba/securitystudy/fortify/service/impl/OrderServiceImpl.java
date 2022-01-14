package com.zhunongyun.toalibaba.securitystudy.fortify.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhunongyun.toalibaba.securitystudy.fortify.entity.Order;
import com.zhunongyun.toalibaba.securitystudy.fortify.mapper.OrderMapper;
import com.zhunongyun.toalibaba.securitystudy.fortify.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}

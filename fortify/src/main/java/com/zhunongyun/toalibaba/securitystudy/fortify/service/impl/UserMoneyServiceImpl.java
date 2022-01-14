package com.zhunongyun.toalibaba.javasecurity.fortify.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhunongyun.toalibaba.javasecurity.fortify.entity.UserMoney;
import com.zhunongyun.toalibaba.javasecurity.fortify.mapper.UserMoneyMapper;
import com.zhunongyun.toalibaba.javasecurity.fortify.service.UserMoneyService;
import org.springframework.stereotype.Service;

/**
 * @author oscar
 */
@Service
public class UserMoneyServiceImpl extends ServiceImpl<UserMoneyMapper, UserMoney> implements UserMoneyService {
}

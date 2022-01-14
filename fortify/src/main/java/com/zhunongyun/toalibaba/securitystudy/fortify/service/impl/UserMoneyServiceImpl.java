package com.zhunongyun.toalibaba.securitystudy.fortify.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhunongyun.toalibaba.securitystudy.fortify.entity.UserMoney;
import com.zhunongyun.toalibaba.securitystudy.fortify.mapper.UserMoneyMapper;
import com.zhunongyun.toalibaba.securitystudy.fortify.service.UserMoneyService;
import org.springframework.stereotype.Service;

/**
 * @author oscar
 */
@Service
public class UserMoneyServiceImpl extends ServiceImpl<UserMoneyMapper, UserMoney> implements UserMoneyService {
}

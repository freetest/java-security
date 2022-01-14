package com.zhunongyun.toalibaba.javasecurity.fortify.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhunongyun.toalibaba.javasecurity.fortify.entity.User;
import com.zhunongyun.toalibaba.javasecurity.fortify.mapper.UserMapper;
import com.zhunongyun.toalibaba.javasecurity.fortify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:  操作用户信息
 * @author oscar
 * @date: 2021/12/16 11:07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @description: 更加
     * @param: null
     * @return:
     * @author oscar
     * @date: 2021/12/16 10:04
     */
    @Override
    public List<User> queryUserByName(String name, String orderByName) {
        Map<String, String> paramMap = new HashMap<>(2);
        paramMap.put("name", name);
        paramMap.put("orderByName", orderByName);
        return userMapper.queryUserByName(paramMap);
    }

    @Override
    public boolean updatePassword(Long id, String password) {
        // 查询用户信息
        User user = super.getById(id);

        user.setPassword(password);

        return super.updateById(user);
    }
}

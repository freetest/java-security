package com.zhunongyun.toalibaba.javasecurity.fortify.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhunongyun.toalibaba.javasecurity.fortify.entity.User;

import java.util.List;

/**
 * @author oscar
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名查询用户信息
     * <p> xxxxxxxx
     * @param name 用户名称
     * @param orderByName 排序字段名
     * @return 用户信息
     * @author oscar
     * @date: 2021/12/16 11:08
     */
    List<User> queryUserByName(String name, String orderByName);

    boolean updatePassword(Long id, String password);
}